package com.ymhd.mifei.listadapter;

import java.util.List;

import com.example.mifen.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class addresslistadapter extends BaseAdapter{

	private Context context;
	private List<String> list;
	private OnClickListener itemsOnClick;
	private Integer[] imgs = { R.drawable.classify1,R.drawable.classify2,R.drawable.classify3,
			R.drawable.classify4,R.drawable.classify5,R.drawable.classify6};
	private String[] str = {"为您推荐","外套","上衣","裙装","裤装","配饰"};
	private boolean ischeck = false;
	private int position2;
	public  addresslistadapter(List<String> list, 
			Context context,OnClickListener itemsOnClick) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
		this.itemsOnClick = itemsOnClick;
	}
	
	

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public void setSelet_c(int positon, boolean ischeck)
	{
		this.position2 = positon;
		this.ischeck = true;
		notifyDataSetChanged();
	}
	public void init()
	{
		ischeck = false;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null; 
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.setting_address_listview_adapter, null);// 这个过程相当耗时间
				viewHolder = new ViewHolder();
				viewHolder.img = (ImageView) convertView.
						findViewById(R.id.address_default);
				viewHolder.address_select = (RadioButton) convertView.findViewById(R.id.address_selectimg);
				viewHolder.editaddress = (ImageView) convertView.findViewById(R.id.setting_editaddress);
				convertView.setTag(viewHolder);
				
			} else {
				 viewHolder = (ViewHolder)convertView.getTag();

			}

			viewHolder.address_select.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						Log.e("11", "11");
						position2 = position;
					}else
					{
						
					}
					notifyDataSetChanged();
				}
			});
			if(position==position2)
			{
				viewHolder.address_select.setChecked(true);
				viewHolder.img.setVisibility(View.VISIBLE);
			}else
			{
				viewHolder.address_select.setChecked(false);
				viewHolder.img.setVisibility(View.GONE);
			}
			viewHolder.address_select.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			viewHolder.editaddress.setOnClickListener(itemsOnClick);
			return convertView;
		}

	class ViewHolder {
		public ImageView img,editaddress;
		public TextView tv;
		public RadioButton address_select;
	}
}
