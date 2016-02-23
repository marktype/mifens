package com.ymhd.mifei.listadapter;



import java.util.List;

import com.example.mifen.R;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class nearbylistadapter extends BaseAdapter{
	private Context context;
	private List<String> list;
	private OnClickListener itemsOnClick;
	Button btn_gohere2;
	public  nearbylistadapter(List<String> list, 
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
						R.layout.nearbyforlistview, null);// 这个过程相当耗时间
				viewHolder = new ViewHolder();
				viewHolder.btn_gohere = (Button) convertView.findViewById(R.id.btngohere);

				convertView.setTag(viewHolder);
				
			} else {
				 viewHolder = (ViewHolder)convertView.getTag();

			}
//			btn_gohere2 = (Button) convertView.findViewById(R.id.btngohere);
			viewHolder.btn_gohere.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.e("111", "111");
				}
			});
			viewHolder.btn_gohere.setOnClickListener(itemsOnClick);
			return convertView;
		}

	class ViewHolder {
		public Button btn_gohere;
	}

}
