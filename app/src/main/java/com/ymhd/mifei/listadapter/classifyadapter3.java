package com.ymhd.mifei.listadapter;

import java.util.List;

import com.example.mifen.R;
import com.ymhd.mifei.listadapter.nearbylistadapter.ViewHolder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class classifyadapter3 extends BaseAdapter{
	private Context context;
	private List<String> list;
	private OnClickListener itemsOnClick;
	private Integer[] imgs = { R.drawable.classify1,R.drawable.classify2,R.drawable.classify3,
			R.drawable.classify4,R.drawable.classify5,R.drawable.classify6};
	private String[] str = {"为您推荐","外套","上衣","裙装","裤装","配饰"};
	public  classifyadapter3(List<String> list, 
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



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null; 
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.classify_classifyac, null);// 这个过程相当耗时间
				viewHolder = new ViewHolder();
				viewHolder.img = (ImageView) convertView.findViewById(R.id.classify_classifyac_img);
				viewHolder.tv = (TextView) convertView.findViewById(R.id.classify_classifyac_tv);
				convertView.setTag(viewHolder);
				
			} else {
				 viewHolder = (ViewHolder)convertView.getTag();

			}
			viewHolder.img.setImageResource(imgs[position]);
			viewHolder.tv.setText(str[position]);
			return convertView;
		}

	class ViewHolder {
		public ImageView img;
		public TextView tv;
	}

}
