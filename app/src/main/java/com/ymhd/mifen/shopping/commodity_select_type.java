package com.ymhd.mifen.shopping;

import com.example.mifen.R;
import com.ymhd.mifen.order.takebyselfaddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class commodity_select_type extends Activity{

	private LinearLayout commodity_choose_takeaddress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commodity_select_type);
		init();
	}
	public void init()
	{
		commodity_choose_takeaddress = (LinearLayout) findViewById(R.id.commodity_choose_takeaddress);
		commodity_choose_takeaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(commodity_select_type.this,takebyselfaddress.class);
				startActivity(in);
			}
		});
	}

}
