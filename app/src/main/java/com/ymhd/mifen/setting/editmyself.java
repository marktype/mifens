package com.ymhd.mifen.setting;

import com.example.mifen.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class editmyself extends Activity {

	private ImageView settingedit_back;
	private LinearLayout settingaddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_edit_myself);
		init();
	}

	public void init() {
		settingedit_back = (ImageView) findViewById(R.id.settingedit_back);
		settingaddress = (LinearLayout) findViewById(R.id.setting_address);
		iniImageback();
		settingedit_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		settingaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(editmyself.this,address.class);
				startActivity(in);
			}
		});
	}

	public void iniImageback() {
		{
			// Matrix matrix=new Matrix();
			// settingedit_back.setScaleType(ScaleType.MATRIX); //required
			// matrix.postRotate((float) 180);
			// settingedit_back.setImageMatrix(matrix);
		}
	}

}
