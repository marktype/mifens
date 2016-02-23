package com.ymhd.mifen.adapter;

import com.example.mifen.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyGridViewAdapter extends BaseAdapter {
	// 上下文对象
	private Context context;
	private Integer[] imgs;
	private String[] str;
	private GridView gv;
	// 图片数组
	public MyGridViewAdapter(Context context,Integer[] imgs,String[] str,GridView v) {
		this.context = context;
		this.imgs = imgs;
		this.str = str;
		this.gv = v;
	}

	public int getCount() {
		return imgs.length;
	}

	public Object getItem(int item) {
		return item;
	}

	public long getItemId(int id) {
		return id;
	}

	// 创建View方法
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null; 
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.home_gridview_adapter, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_name = (TextView) convertView.findViewById(R.id.commodityname_homepage);
			viewHolder.tv_price = (TextView) convertView.findViewById(R.id.pirce_homepage);
			viewHolder.tv_num = (TextView) convertView.findViewById(R.id.lovernum);
			viewHolder.commodity_img_homepage = (ImageView) convertView.findViewById(R.id.commodityimg_homepage);
			convertView.setTag(viewHolder);
			
		} 
		else {
			 viewHolder = (ViewHolder)convertView.getTag();

		}
		
		viewHolder.commodity_img_homepage.setImageResource(imgs[position]);
		viewHolder.tv_name.setText(str[position]);
        viewHolder.tv_price.setText("288");
        viewHolder.tv_num.setText("50");
		return convertView;
	}

	class ViewHolder {
		public TextView tv_name, tv_price, tv_num;
		public ImageView commodity_img_homepage;
	}
}
