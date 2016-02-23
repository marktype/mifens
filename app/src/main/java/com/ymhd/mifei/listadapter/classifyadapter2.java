package com.ymhd.mifei.listadapter;

import java.util.List;

import com.example.mifen.R;
import com.ymhd.mifei.listadapter.nearbylistadapter.ViewHolder;
import com.ymhd.mifen.shopping.seach_shopping;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class classifyadapter2 extends BaseAdapter{
	private Context context;
	private List<String> list;
	private OnClickListener itemsOnClick;
	private String[] str = {"所有外套","夹克","单件西装","皮衣","风衣","大衣","羽绒服","棉服","披肩","皮草"};
	public  classifyadapter2(List<String> list, 
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null; 
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.classify_classifycom, null);// 这个过程相当耗时间
				viewHolder = new ViewHolder();
				viewHolder.com_tv = (TextView) convertView.findViewById(R.id.classify_classify_com_tv);
				convertView.setTag(viewHolder);
				
			} else {
				 viewHolder = (ViewHolder)convertView.getTag();

			}
			viewHolder.com_tv.setText(str[position]);
			viewHolder.com_tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent in = new Intent(context,seach_shopping.class);
					in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					in.putExtra("tv", str[position]);
					context.startActivity(in);
				}
			});
			return convertView;
		}

	class ViewHolder {
		public TextView com_tv;
	}

}