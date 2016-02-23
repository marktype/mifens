package com.ymhd.main;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.example.mifen.R;

public class maintab extends TabActivity implements OnTabChangeListener {
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	TabHost tab;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		tab = (TabHost) this.findViewById(android.R.id.tabhost);
		tab.setup();
		View view1 = this.getLayoutInflater().inflate(R.layout.custom, null);
		View view2 = this.getLayoutInflater().inflate(R.layout.custom2, null);
		View view3 = this.getLayoutInflater().inflate(R.layout.custom3, null);
		View view4 = this.getLayoutInflater().inflate(R.layout.custom4, null);
		View view5 = this.getLayoutInflater().inflate(R.layout.custom5, null);
		TabSpec tab11 = tab.newTabSpec("home").setIndicator(view1).setContent(new Intent(this, homepage2.class));
		TabSpec tab12 = tab.newTabSpec("classify").setIndicator(view2).setContent(new Intent(this, classifypage.class));
		TabSpec tab13 = tab.newTabSpec("nearby").setIndicator(view3).setContent(new Intent(this, nearbypage.class));

//		TabSpec tab13 = tab.newTabSpec("nearby").setIndicator(view3).setContent(new Intent(this, LocationSourceActivity.class));
		TabSpec tab14 = tab.newTabSpec("shoppingcar").setIndicator(view4)
				.setContent(new Intent(this, shoppingcar.class));
		TabSpec tab15 = tab.newTabSpec("myself").setIndicator(view5).setContent(new Intent(this, myself.class));

		tab.addTab(tab11);
		tab.addTab(tab12);
		tab.addTab(tab13);
		tab.addTab(tab14);
		tab.addTab(tab15);
		tab.setCurrentTab(0);
		tab.setOnTabChangedListener(this);
		sp = getSharedPreferences("checktype", Context.MODE_PRIVATE);
		editor = sp.edit();
		editor.clear();
		editor.commit();
		IntentFilter filter = new IntentFilter();
		// 向过滤器中添加action
		filter.addAction("com.ymhd.sendtype");
		// 注册广播
		registerReceiver(type, filter);
		IntentFilter filter2 = new IntentFilter();
		// 向过滤器中添加action
		filter2.addAction("com.ymhd.select");
		// 注册广播
		registerReceiver(select, filter2);
		
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
	}
	@Override
	protected void onDestroy() {
		unregisterReceiver(type);
		unregisterReceiver(select);
		super.onDestroy();
	}

	// broadcast receiver
	private BroadcastReceiver type = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getIntExtra("type", 0) == 1) {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 0.4f;
				getWindow().setAttributes(lp);
			} else {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}

		}

	};
	private BroadcastReceiver select = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			tab.setCurrentTab(0);//tabhost位于第一个界面；
		}

	};
}
