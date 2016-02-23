package com.ymhd.mifen.setting;

import java.util.ArrayList;
import java.util.List;

import com.example.mifen.R;
import com.ymhd.mifei.listadapter.addresslistadapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class address extends Activity{
	private ListView address_list;
	private addresslistadapter listadapter;
	private ImageView setback;
	private TextView tv_newaddress;
	private List<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_address);
		init();
	}
	public void init()
	{
		address_list = (ListView) findViewById(R.id.setting_address_list);
		list = new ArrayList<String>();
		list.add("11");
		list.add("11");
		list.add("11");
		list.add("11");
		listadapter = new addresslistadapter(list, getApplicationContext(), itemsOnClick);
		address_list.setAdapter(listadapter);
		init2();
	}
	public void init2()
	{
		setback = (ImageView) findViewById(R.id.setback);
		setback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tv_newaddress = (TextView) findViewById(R.id.newaddress);
		tv_newaddress.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(address.this,newaddress.class);
				startActivity(in);
			}
		});
	}
	 OnClickListener  itemsOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.setting_editaddress:
				Intent in = new Intent(address.this,editaddress.class);
				startActivity(in);
				break;

			default:
				break;
			}
		}
	};
	
}
