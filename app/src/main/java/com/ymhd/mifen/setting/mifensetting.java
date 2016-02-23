package com.ymhd.mifen.setting;

import com.example.mifen.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class mifensetting extends Activity{

	private TextView tv_share,tv_versioninfo,tv_lockpush,tv_push,tv_editmyself;
	private Mydialog mydailog_share;
	private Mydialog_push mydailog_push;
	private Mydialog_lockpush mydailog_lockpush;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		initDailog();
		initTv();
	}
	public void initDailog()
	{
		mydailog_share = new Mydialog(this,R.style.dialog2);
		Window win = mydailog_share.getWindow();
		LayoutParams params = new LayoutParams();
		win.setAttributes(params);
		mydailog_share.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
		mydailog_push = new Mydialog_push(this, R.style.dialog2);
		Window win2 = mydailog_push.getWindow();
		win2.setAttributes(params);
		mydailog_push.setCanceledOnTouchOutside(true);
		mydailog_lockpush = new Mydialog_lockpush(this, R.style.dialog2);
		Window win3 = mydailog_push.getWindow();
		win3.setAttributes(params);
		mydailog_lockpush.setCanceledOnTouchOutside(true);
		
	}
	
	public void initTv()
	{
		tv_share = (TextView) findViewById(R.id.share_setting);
		tv_versioninfo = (TextView) findViewById(R.id.setting_versioninfo);
		tv_push = (TextView) findViewById(R.id.setting_push);
		tv_lockpush = (TextView) findViewById(R.id.settiong_lock);
		tv_editmyself = (TextView) findViewById(R.id.setting_editmyself);
		tv_share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mydailog_share.show();
			}
		});
		tv_versioninfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(mifensetting.this,versioninfo.class);
				startActivity(in);
				
			}
		});
		tv_push.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mydailog_push.show();
			}
		});
		tv_lockpush.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mydailog_lockpush.show();
			}
		});
		tv_editmyself.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(mifensetting.this,editmyself.class);
				startActivity(in);
			}
		});
		
	}

}
