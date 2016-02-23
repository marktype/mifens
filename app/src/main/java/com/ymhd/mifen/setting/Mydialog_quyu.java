package com.ymhd.mifen.setting;

import java.util.ArrayList;
import java.util.List;

import com.example.mifen.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Mydialog_quyu extends AlertDialog{

	private Button  butn;
	private TextView num;
	private Spinner sp_provice,sp_city,sp_district;
	int i;
	private Context context;
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter_province;
	public Mydialog_quyu(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		this.context = context;
		
	}

	public Mydialog_quyu(Context context) {
	    super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_address_quyulist);
	    init();
	   
	}
	public void init()
	{
		sp_provice = (Spinner) findViewById(R.id.spinner_province);
		sp_city = (Spinner) findViewById(R.id.spinner_city);
		sp_district = (Spinner) findViewById(R.id.spinner_district);
		list.add("北京");
		list.add("上海");
		list.add("广东");
		list.add("天津");
		list.add("四川");
		list.add("海南");
		list.add("重庆");
		list.add("厦门");
		initAdaper();
	}
	public void initAdaper()
	{
		adapter_province = 
				new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);
		//为适配器设置下拉列表下拉时的菜单样式。    
		adapter_province.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_provice.setAdapter(adapter_province);
		
	}
	
}




























