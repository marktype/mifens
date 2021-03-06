package com.ymhd.fragment.ui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.example.mifen.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;

@SuppressWarnings("static-access")
public abstract class IndicatorFragmentActivity2 extends FragmentActivity implements OnPageChangeListener {
	private static final String TAG = "DxFragmentActivity";

	public static final String EXTRA_TAB = "tab";
	public static final String EXTRA_QUIT = "extra.quit";

	protected int mCurrentTab = 0;
	protected int mLastTab = -1;

	protected ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	// viewpager adapter
	protected MyAdapter myAdapter = null;

	// viewpager
	protected ViewPager mPager;

	protected TitleIndicator2 mIndicator;

	public TitleIndicator2 getIndicator() {
		return mIndicator;
	}
	public void start()
	{
		initViews();
		mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.padding2));
		mPager.setPageMarginDrawable(R.color.common_grey_disabled);
	}

	public class MyAdapter extends FragmentPagerAdapter {
		ArrayList<TabInfo> tabs = null;
		Context context = null;

		public MyAdapter(Context context, FragmentManager fm, ArrayList<TabInfo> tabs) {
			super(fm);
			this.tabs = tabs;
			this.context = context;
		}

		@Override
		public Fragment getItem(int pos) {
			Fragment fragment = null;
			if (tabs != null && pos < tabs.size()) {
				TabInfo tab = tabs.get(pos);
				if (tab == null)
					return null;
				fragment = tab.createFragment();
			}
			return fragment;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public int getCount() {
			if (tabs != null && tabs.size() > 0)
				return tabs.size();
			return 0;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabInfo tab = tabs.get(position);
			Fragment fragment = (Fragment) super.instantiateItem(container, position);
			tab.fragment = fragment;
			return fragment;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

//		setContentView(getMainViewResId());
//		initViews();
//
//		mPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.padding2));
//		mPager.setPageMarginDrawable(R.color.common_grey_disabled);
	}

	@Override
	protected void onDestroy() {
		mTabs.clear();
		mTabs = null;
		myAdapter.notifyDataSetChanged();
		myAdapter = null;
		mPager.setAdapter(null);
		mPager = null;
		mIndicator = null;

		super.onDestroy();
	}

	private final void initViews() {
		mCurrentTab = supplyTabs(mTabs);
		Intent intent = getIntent();
		if (intent != null) {
			mCurrentTab = intent.getIntExtra(EXTRA_TAB, mCurrentTab);
		}
		Log.d(TAG, "mTabs.size() == " + mTabs.size() + ", cur: " + mCurrentTab);
		myAdapter = new MyAdapter(this, getSupportFragmentManager(), mTabs);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(myAdapter);
		mPager.setOnPageChangeListener(this);
		mPager.setOffscreenPageLimit(mTabs.size());

		mIndicator = (TitleIndicator2) findViewById(R.id.pagerindicator);
		mIndicator.init(mCurrentTab, mTabs, mPager);

		mPager.setCurrentItem(mCurrentTab);
		mLastTab = mCurrentTab;
	}

	public void addTabInfo(TabInfo tab) {
		mTabs.add(tab);
		myAdapter.notifyDataSetChanged();
	}

	/**
	 * 浠庡垪琛ㄦ坊鍔犻?夐」鍗?
	 * 
	 * @param tabs
	 */
	public void addTabInfos(ArrayList<TabInfo> tabs) {
		mTabs.addAll(tabs);
		myAdapter.notifyDataSetChanged();
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		mIndicator.onScrolled((mPager.getWidth() + mPager.getPageMargin()) * position + positionOffsetPixels);
	}

	@Override
	public void onPageSelected(int position) {
		mIndicator.onSwitched(position);
		mCurrentTab = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		if (state == ViewPager.SCROLL_STATE_IDLE) {
			mLastTab = mCurrentTab;
		}
	}

	protected TabInfo getFragmentById(int tabId) {
		if (mTabs == null)
			return null;
		for (int index = 0, count = mTabs.size(); index < count; index++) {
			TabInfo tab = mTabs.get(index);
			if (tab.getId() == tabId) {
				return tab;
			}
		}
		return null;
	}

	public void navigate(int tabId) {
		for (int index = 0, count = mTabs.size(); index < count; index++) {
			if (mTabs.get(index).getId() == tabId) {
				mPager.setCurrentItem(index);
			}
		}
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	public int getMainViewResId(int id) {
		return id;
	}

	protected abstract int supplyTabs(List<TabInfo> tabs);

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// for fix a known issue in support library
		// https://code.google.com/p/android/issues/detail?id=19917
		outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
		super.onSaveInstanceState(outState);
	}

	public static class TabInfo implements Parcelable {
		private int num ;
		private int id;
		private int icon;
		private String name = null;
		public boolean hasTips = true;// 显示图片
		public Fragment fragment = null;
		public boolean notifyChange = false;
		@SuppressWarnings("rawtypes")
		public Class fragmentClass = null;

		@SuppressWarnings("rawtypes")
		public TabInfo(int id, String name, Class clazz,int num) {
			this(id, name, 0, clazz, num);
		}

		@SuppressWarnings("rawtypes")
		public TabInfo(int id, String name, boolean hasTips, Class clazz, int num) {
			this(id, name, 0, clazz,num);
			this.hasTips = hasTips;
			this.num = num;
		}

		@SuppressWarnings("rawtypes")
		public TabInfo(int id, String name, int iconid, Class clazz ,int num) {
			super();

			this.name = name;
			this.id = id;
			icon = iconid;
			fragmentClass = clazz;
		}

		public TabInfo(Parcel p) {
			this.id = p.readInt();
			this.name = p.readString();
			this.icon = p.readInt();
			this.notifyChange = p.readInt() == 1;
		}

		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		public int getNum() {
			return id;
		}

		public void setNum(int num) {
			this.id = num;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setIcon(int iconid) {
			icon = iconid;
		}

		public int getIcon() {
			return icon;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Fragment createFragment() {
			if (fragment == null) {
				Constructor constructor;
				try {
					constructor = fragmentClass.getConstructor(new Class[0]);
					fragment = (Fragment) constructor.newInstance(new Object[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return fragment;
		}

		public static final Parcelable.Creator<TabInfo> CREATOR = new Parcelable.Creator<TabInfo>() {
			@Override
			public TabInfo createFromParcel(Parcel p) {
				return new TabInfo(p);
			}

			@Override
			public TabInfo[] newArray(int size) {
				return new TabInfo[size];
			}
		};

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel p, int flags) {
			p.writeInt(id);
			p.writeString(name);
			p.writeInt(icon);
			p.writeInt(notifyChange ? 1 : 0);
		}

	}

}
