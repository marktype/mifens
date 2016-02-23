package com.ymhd.main;

import java.util.ArrayList;
import java.util.List;

import com.example.mifen.R;
import com.ymhd.mifei.listadapter.nearbylistadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class nearbypage_list extends Activity{
	private ListView nearbyforlistinfo;
	private nearbylistadapter nearbylistadapter;
	private List<String> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearbyforlist);
		init();
	}
	public void init()
	{
		nearbyforlistinfo = (ListView) findViewById(R.id.nearby_listinfo);
		list = new ArrayList<String>();
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		nearbylistadapter = new nearbylistadapter(list, this, null);
		nearbyforlistinfo.setAdapter(nearbylistadapter);
		
		
	}

}
