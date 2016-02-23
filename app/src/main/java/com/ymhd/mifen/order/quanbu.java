package com.ymhd.mifen.order;

import java.util.ArrayList;
import java.util.List;

import com.example.mifen.R;
import com.ymhd.mifei.listadapter.order_list_adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class quanbu extends Fragment{
	private View order_list;
	private List<String> list;
	private ListView li1;
	private order_list_adapter order_list_adapter;
	private int[] type = {0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		order_list = inflater.inflate(R.layout.order_list, container, false);
		init();
		return order_list;
	}
	public void init()
	{
		list = new ArrayList<String>();
		for(int i=0;i<9;i++)
		{
			list.add("1");
		}
		order_list_adapter = new order_list_adapter(list, getActivity(), null, type);
		li1 = (ListView) order_list.findViewById(R.id.order_info_list);
		li1.setAdapter(order_list_adapter);
	}

}
