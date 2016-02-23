package com.ymhd.mifen.setting;

import com.example.mifen.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

public class editaddress extends Activity{
	private LinearLayout lin1;
	private Mydialog_quyu quyudailog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_editaddress);
		init();
	}
	public void init()
	{
		lin1 = (LinearLayout) findViewById(R.id.setting_editaddres_quyudialog);
		quyudailog = new Mydialog_quyu(editaddress.this, R.style.dialog2);
		Window win = quyudailog.getWindow();
		LayoutParams params = new LayoutParams();
		win.setAttributes(params);
		quyudailog.setCanceledOnTouchOutside(true);
		lin1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				quyudailog.show();
			}
		});
	}

}
