package com.ymhd.mifen.order;

import com.example.mifen.R;
import com.ymhd.mifen.setting.address;
import com.ymhd.mifen.shopping.commodity_select_type;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class order_info_sure extends Activity{
	private Button commodity_submit;
	private LinearLayout oderselec,order_sure_selectaddress;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_sure);
		init();
	}
	public void init()
	{
		commodity_submit = (Button) findViewById(R.id.commodity_submitorder);
		oderselec = (LinearLayout) findViewById(R.id.order_select_payandsendtype);
		order_sure_selectaddress = (LinearLayout) findViewById(R.id.order_sure_selectaddress);
		commodity_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(order_info_sure.this,order_pay.class);
				startActivity(in);
			}
		});
		oderselec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(order_info_sure.this,commodity_select_type.class);
				startActivity(in);
			}
		});
		order_sure_selectaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(order_info_sure.this,address.class);
				startActivity(in);
			}
		});
	}
}
