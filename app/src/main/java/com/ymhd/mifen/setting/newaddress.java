package com.ymhd.mifen.setting;

import com.example.mifen.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

public class newaddress extends Activity{

	private LinearLayout setting_newaddress_dailog_lin;
	private Mydialog_quyu quyudailog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_newaddress);
		init();
	}
	public void init()
	{
		setting_newaddress_dailog_lin = (LinearLayout) findViewById(R.id.setting_newaddress_dailog_lin);
		quyudailog = new Mydialog_quyu(newaddress.this, R.style.dialog2);
		Window win = quyudailog.getWindow();
		LayoutParams params = new LayoutParams();
		win.setAttributes(params);
		quyudailog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
		setting_newaddress_dailog_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				quyudailog.show();
			}
		});
		
	}
}
