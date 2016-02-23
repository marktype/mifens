package com.ymhd.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.ParseException;

import com.example.mifen.R;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymhd.ad.Adv;
import com.ymhd.ad.CubeTransformer;
import com.ymhd.ad.DefaultTransformer;
import com.ymhd.mifei.tool.DataUri;
import com.ymhd.mifei.tool.MyScrollView;
import com.ymhd.mifen.adapter.MyGridViewAdapter;
import com.ymhd.mifen.http.APP_url;
import com.ymhd.mifen.http.HttpManager;
import com.ymhd.mifen.myself.collection;
import com.ymhd.mifen.myself.register;
import com.ymhd.mifen.order.myorderall;
import com.ymhd.mifen.shopping.commodity_info;
import com.zxing.android.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnScrollChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.tsz.afinal.FinalBitmap;

@SuppressLint({ "HandlerLeak", "ClickableViewAccessibility", "InflateParams" })
public class homepage2 extends baseActivity implements com.ymhd.mifei.tool.MyScrollView.OnScrollListener {
	PullToRefreshScrollView mPullRefreshScrollView;
	MyScrollView mScrollView;
	private LinearLayout lin1, lin2, lin3, homepage_suspend1, homepage_suspend2, homepage_tejia, homepage_mycollection,
			homepage_myorder, homepage_qiandao;
	RadioGroup homepage_suspend3;
	private View view, view_dapeituijian, view_brandstreet;
	private LayoutInflater mInflater;
	public FinalBitmap finalImageLoader;
	private GridView gv;
	private List<View> imageViews; // 滑动的图片集合
	private View v_dot0, v_dot1, v_dot2, v_dot3, v_dot4, v_dot5, v_dot6;
	private ViewPager vp;
	private MyViewpagerAdapter myAdapter;
	MyGridViewAdapter mygridviewadapter;
	private List<View> dots; // 图片标题正文的那些点
	private int currentItem = 0; // 当前图片的索引号
	private List<Adv> imageResUrl; // 图片ID
	private Button btn;
	private RadioButton tv_liuxing, tv_hotcommodity, tv_onlyforyou;
	private int searchLayoutTop;
	private Timer adTimer = null;
	private Integer[] imgs = { R.drawable.img14, R.drawable.img15, R.drawable.img16, R.drawable.img17, R.drawable.img18,
			R.drawable.img19 };
	private String[] str = { "BF风夹棉外套棉衣", "时尚混搭针织毛衣", "加厚拉链纯色小脚裤", "羊羔毛内里长款宽松棉衣", "BF风夹棉外套棉衣", "时尚混搭针织衫毛衣" };
	private Integer[] imgs2 = { R.drawable.img14, R.drawable.img15, R.drawable.img16, R.drawable.img17,
			R.drawable.img18, R.drawable.img19, R.drawable.img14, R.drawable.img15, R.drawable.img16, R.drawable.img17,
			R.drawable.img18, R.drawable.img19 };
	private String[] str2 = { "BF风夹棉外套棉衣", "时尚混搭针织毛衣", "加厚拉链纯色小脚裤", "羊羔毛内里长款宽松棉衣", "BF风夹棉外套棉衣", "时尚混搭针织衫毛衣",
			"BF风夹棉外套棉衣", "时尚混搭针织毛衣", "加厚拉链纯色小脚裤", "羊羔毛内里长款宽松棉衣", "BF风夹棉外套棉衣", "时尚混搭针织衫毛衣" };

	private int lasty;
	private ImageView home_seach,home_zxing;
	private int type_timer = 1;
	private String token;
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage2);
		final WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		mInflater = LayoutInflater.from(this);
		finalImageLoader = FinalBitmap.create(this);
		finalImageLoader.configLoadingImage(R.drawable.ic_launcher);// 设置图片加载完成之前显示的图片
		mScrollView = (MyScrollView) findViewById(R.id.myscv);
		// 小米系统在此处不能进行滚动监听，否则会报java.lang.noclassdeffounderror
		// mScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
		//
		// @Override
		// public void onScrollChange(View v, int scrollX, int scrollY, int
		// oldScrollX, int oldScrollY) {
		// // TODO Auto-generated method stub
		// if(scrollY >= searchLayoutTop){
		// if (homepage_suspend3.getParent()!=homepage_suspend2) {
		// homepage_suspend1.removeView(homepage_suspend3);
		// homepage_suspend2.addView(homepage_suspend3);
		// }
		// }else{
		// if (homepage_suspend3.getParent()!=homepage_suspend1) {
		// homepage_suspend2.removeView(homepage_suspend3);
		// homepage_suspend1.addView(homepage_suspend3);
		// }
		// }
		//
		// }
		// });

		mScrollView.setOnScrollListener(this);
		mScrollView.setOnTouchListener(new OnTouchListener() {
			int lastY;
			int dy;
			Boolean didup = false;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:
					lastY = (int) event.getRawY();// 获取触摸事件触摸位置的原始Y坐标
					break;
				case MotionEvent.ACTION_MOVE:
					dy = (int) ((event.getRawY() - lastY) * 0.5);//到达顶部后进行的动作
					if (v.getScrollY() <= 0) {
						Log.e("scroll view", "top");

					} else if (mScrollView.getChildAt(0).getMeasuredHeight() <= v.getHeight() + v.getScrollY()) {//到达底部后进行的动作
						Log.e("scroll view", "bottom");
						Log.e("scroll view",
								"view.getMeasuredHeight() = " + mScrollView.getMeasuredHeight() + ", v.getHeight() = "
										+ v.getHeight() + ", v.getScrollY() = " + v.getScrollY()
										+ ", view.getChildAt(0).getMeasuredHeight() = "
										+ mScrollView.getChildAt(0).getMeasuredHeight());
						Intent in = new Intent();
						in.setAction("com.android.crtcommodity");
						sendBroadcast(in);
					}

					break;
				case MotionEvent.ACTION_UP:
					break;
				default:
					break;
				}
				return false;
			}
		});

		IntentFilter filter = new IntentFilter();
		// 向过滤器中添加action
		filter.addAction("com.android.crtcommodity");
		// 注册广播
		registerReceiver(crtcommodity, filter);
		initid();
		init();
		sp = getSharedPreferences("token&refreshtoken", Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public void initid() {
		homepage_tejia = (LinearLayout) findViewById(R.id.homepage_todaytejia);
		homepage_mycollection = (LinearLayout) findViewById(R.id.homepage_mycollection);
		homepage_myorder = (LinearLayout) findViewById(R.id.homepage_myorder);
		homepage_qiandao = (LinearLayout) findViewById(R.id.homepage_qiandao);
		home_zxing = (ImageView) findViewById(R.id.homepage_zxing);
	}

	public void init() {

		vp = (ViewPager) findViewById(R.id.vp);
		// 白点
		v_dot0 =  findViewById(R.id.v_dot0);
		v_dot1 =  findViewById(R.id.v_dot1);
		v_dot2 =  findViewById(R.id.v_dot2);
		v_dot3 =  findViewById(R.id.v_dot3);
		v_dot4 =  findViewById(R.id.v_dot4);
		v_dot5 =  findViewById(R.id.v_dot5);
		v_dot6 =  findViewById(R.id.v_dot6);
		vp.setPageTransformer(true, new DefaultTransformer());

		lin1 = (LinearLayout) findViewById(R.id.home_lin1);
		// lin1.addView(view);
		gv = (GridView) findViewById(R.id.gridView1);
		mygridviewadapter = new MyGridViewAdapter(getApplicationContext(), imgs, str, gv);
		// 为GridView设置适配器
		gv.setAdapter(mygridviewadapter);
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// 注册监听事件
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent in = new Intent(homepage2.this, commodity_info.class);
				startActivity(in);
			}
		});
		lin2 = (LinearLayout) findViewById(R.id.dapeituijian);
		view_dapeituijian = mInflater.inflate(R.layout.dapei_homepage, null);
		lin2.addView(view_dapeituijian);

		lin3 = (LinearLayout) findViewById(R.id.brandstreet);
		view_brandstreet = mInflater.inflate(R.layout.homepage_brandstreet, null);
		lin3.addView(view_brandstreet);

		//悬浮banner相关
		homepage_suspend1 = (LinearLayout) findViewById(R.id.homepage_suspend1);
		homepage_suspend2 = (LinearLayout) findViewById(R.id.homepage_suspend2);
		homepage_suspend3 = (RadioGroup) findViewById(R.id.homepage_suspend3);

		home_seach = (ImageView) findViewById(R.id.home_seach);
		home_seach.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(homepage2.this, seachpage.class);
				startActivity(in);
			}
		});
		homepage_mycollection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(homepage2.this, collection.class);
				startActivity(in);
			}
		});
		homepage_myorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(homepage2.this, myorderall.class);
				startActivity(in);
			}
		});
		homepage_qiandao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(homepage2.this, register.class);
				startActivity(in);
			}
		});
		home_zxing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(homepage2.this, CaptureActivity.class);
				startActivity(in);
			}
		});
		initAD();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			searchLayoutTop = lin3.getBottom();// 获取Layout的顶部位置
		}
	}

	// broadcast receiver
	private BroadcastReceiver crtcommodity = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			mygridviewadapter = new MyGridViewAdapter(getApplicationContext(), imgs2, str2, gv);
			gv.setAdapter(mygridviewadapter);
			mygridviewadapter.notifyDataSetChanged();

		}

	};
	@Override
	protected void onDestroy() {
		unregisterReceiver(crtcommodity);
		super.onDestroy();
	}

	private void initAD() {
		imageResUrl = new ArrayList<Adv>();
		Adv adv = new Adv();
		adv.setImg_url("http://a.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600b203f735ef50352ac75cb767.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("1");
		imageResUrl.add(adv);

		adv = new Adv();
		adv.setImg_url("http://a.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600b203f735ef50352ac75cb767.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("1");
		imageResUrl.add(adv);

		adv = new Adv();
		adv.setImg_url("http://a.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600b203f735ef50352ac75cb767.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("1");
		imageResUrl.add(adv);

		adv = new Adv();
		adv.setImg_url("http://a.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600b203f735ef50352ac75cb767.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("2");
		imageResUrl.add(adv);
	}

	private void initViewPaper() {
		// 首先将白点全部隐藏，再根据获取的图片数量显示
		v_dot0.setVisibility(View.GONE);
		v_dot1.setVisibility(View.GONE);
		v_dot2.setVisibility(View.GONE);
		v_dot3.setVisibility(View.GONE);
		v_dot4.setVisibility(View.GONE);
		v_dot5.setVisibility(View.GONE);
		v_dot6.setVisibility(View.GONE);
		vp.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {

				case MotionEvent.ACTION_MOVE://点中广告图片切换时，中断adtimer
					if (type_timer == 1) {
						adTimer.cancel();
						adTimer = null;
						type_timer = 2;
					}
					break;
				case MotionEvent.ACTION_UP://重启adtimer
					adTimer = new Timer();
					adTimer.schedule(new ScrollTask(), 5 * 1000, 3 * 1000);
					type_timer = 1;
				default:
					break;
				}
				return false;
			}
		});
		imageViews = new ArrayList<View>();
		// 初始化图片上层切换白点
		dots = new ArrayList<View>();
		// 初始化图片资源
		for (int i = 0; i < imageResUrl.size(); i++) {
			final Adv adv = imageResUrl.get(i);
			View view = mInflater.inflate(R.layout.ad_img, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.img);
			finalImageLoader.display(imageView, adv.getImg_url());
			// 设置广告点击事件
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					switch (Integer.parseInt(adv.getType())) {
					case 1:// 连接类型
						Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(adv.getTarget_url()));
						it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
						startActivity(it);
						break;
					case 2:
						Toast.makeText(getApplicationContext(), "点击了", Toast.LENGTH_LONG).show();
						break;
					}
				}
			});
			imageViews.add(view);
			switch (i) {
			case 0:
				v_dot0.setVisibility(View.VISIBLE);
				dots.add(v_dot0);
				break;
			case 1:
				v_dot1.setVisibility(View.VISIBLE);
				dots.add(v_dot1);
				break;
			case 2:
				v_dot2.setVisibility(View.VISIBLE);
				dots.add(v_dot2);
				break;
			case 3:
				v_dot3.setVisibility(View.VISIBLE);
				dots.add(v_dot3);
				break;
			case 4:
				v_dot4.setVisibility(View.VISIBLE);
				dots.add(v_dot4);
				break;
			case 5:
				v_dot5.setVisibility(View.VISIBLE);
				dots.add(v_dot5);
				break;
			case 6:
				v_dot6.setVisibility(View.VISIBLE);
				dots.add(v_dot6);
				break;
			}

		}

		if (imageResUrl.size() <= 1) {
			v_dot0.setVisibility(View.GONE);
		} else {
			for (int i = 0; i < dots.size(); i++) {
				if (i == 0) {
					dots.get(i).setBackgroundResource(R.drawable.ic_dot_selected);
				} else {
					dots.get(i).setBackgroundResource(R.drawable.ic_dot_nor);
				}
			}
		}
		myAdapter = new MyViewpagerAdapter();
		vp.setPageTransformer(true, new CubeTransformer());
		vp.setAdapter(myAdapter);// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		vp.setOnPageChangeListener(new MyPageChangeListener());
	}

	// 切换当前显示的图片
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				vp.setCurrentItem(currentItem);// 切换当前显示的图片
				break;
			}
		};
	};

	/**
	 * 换行切换任务
	 * 
	 * @author byl
	 * 
	 */
	private class ScrollTask extends TimerTask {
		public void run() {
			if (imageViews != null && imageViews.size() > 0) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.sendEmptyMessage(1); // 通过Handler切换图片
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		currentItem = 0;
		initViewPaper();
		adTimer = new Timer();
		adTimer.schedule(new ScrollTask(), 5 * 1000, 3 * 1000);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (adTimer != null) {
			adTimer.cancel();
			adTimer = null;
		}
	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author cjj
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		public void onPageSelected(int position) {
			currentItem = position;
			dots.get(oldPosition).setBackgroundResource(R.drawable.ic_dot_nor);
			dots.get(position).setBackgroundResource(R.drawable.ic_dot_selected);
			oldPosition = position;
		}
		public void onPageScrollStateChanged(int arg0) {
		}
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author cjj
	 * 
	 */
	private class MyViewpagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	@Override
	public void onScroll(int scrollY) {
		// TODO Auto-generated method stub
		if (scrollY >= searchLayoutTop) {
			if (homepage_suspend3.getParent() != homepage_suspend2) {
				homepage_suspend1.removeView(homepage_suspend3);
				homepage_suspend2.addView(homepage_suspend3);
			}
		} else {
			if (homepage_suspend3.getParent() != homepage_suspend1) {
				homepage_suspend2.removeView(homepage_suspend3);
				homepage_suspend1.addView(homepage_suspend3);
			}
		}
	}

	/**
	 * ${TODO} <处理验证结果的Handler>
	 */
	Handler handler_token = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
            {
				super.handleMessage(msg);
				JSONObject res_h = (JSONObject) msg.obj;
				token =res_h.getString("access_token");
				editor.putString("token", token);
				editor.commit();
            }
	};

	/**
	 * ${TODO} <登录验证的线程>
	 */
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			APP_url au = new APP_url();
			JSONObject js;
			Message msg = handler_token.obtainMessage();
			try{
				js = au.token("app.android", "app.android.key", DataUri.data_now());
				msg.obj = js;
				handler_token.sendMessage(msg);
				handler_token.removeCallbacks(runnable);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	class tokentask extends AsyncTask<Void, Void, String> {

		public tokentask() {

		}

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			new Thread(runnable).start();
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}

}
