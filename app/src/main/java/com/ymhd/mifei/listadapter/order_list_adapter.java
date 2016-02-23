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
import android.widget.LinearLayout;
import android.widget.TextView;

public class order_list_adapter extends BaseAdapter {
	private Context context;
	private List<String> list;
	private OnClickListener itemsOnClick;
	private int[] type;

	public order_list_adapter(List<String> list, Context context, OnClickListener itemsOnClick, int[] type) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
		this.itemsOnClick = itemsOnClick;
		this.type = type;
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
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.order_list_success_adapter, null);// 这个过程相当耗时间
			viewHolder.order_success = (LinearLayout) convertView.findViewById(R.id.ordersuccess);
			viewHolder.order_daifukuan = (LinearLayout) convertView.findViewById(R.id.orderdafukuan);
			viewHolder.order_daishouhuo = (LinearLayout) convertView.findViewById(R.id.orderdaishouhuo);
			viewHolder.order_daipingjia = (LinearLayout) convertView.findViewById(R.id.orderdaipingjia);
			viewHolder.order_tuihuanhuo = (LinearLayout) convertView.findViewById(R.id.ordertuihuanhuo);
			viewHolder.tv = (TextView) convertView.findViewById(R.id.order_type);
			
			convertView.setTag(viewHolder);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}
		Log.e("kkk", ""+position+"::"+type[position]);
		switch (type[position]) {
		case 0:
			viewHolder.tv.setText(context.getResources().getString(R.string.success));
			viewHolder.order_success.setVisibility(View.VISIBLE);//success;
			viewHolder.order_daifukuan.setVisibility(View.GONE);
			viewHolder.order_daipingjia.setVisibility(View.GONE);
			viewHolder.order_daishouhuo.setVisibility(View.GONE);
			viewHolder.order_tuihuanhuo.setVisibility(View.GONE);
			notifyDataSetChanged();
			break;
		case 1:
			viewHolder.tv.setText(context.getResources().getString(R.string.obligation));
			viewHolder.order_daifukuan.setVisibility(View.VISIBLE);//待付款;
			viewHolder.order_success.setVisibility(View.GONE);
			viewHolder.order_daipingjia.setVisibility(View.GONE);
			viewHolder.order_daishouhuo.setVisibility(View.GONE);
			viewHolder.order_tuihuanhuo.setVisibility(View.GONE);
			notifyDataSetChanged();
			break;
		case 2:
			viewHolder.tv.setText(context.getResources().getString(R.string.daifahuo));
			viewHolder.order_success.setVisibility(View.GONE);
			viewHolder.order_daifukuan.setVisibility(View.GONE);
			viewHolder.order_daipingjia.setVisibility(View.GONE);
			viewHolder.order_tuihuanhuo.setVisibility(View.GONE);
			viewHolder.order_daishouhuo.setVisibility(View.GONE);
			notifyDataSetChanged();
			//待发货;
			break;
		case 3:
			viewHolder.tv.setText(context.getResources().getString(R.string.daishouhuo));
			viewHolder.order_daishouhuo.setVisibility(View.VISIBLE);//待收货
			viewHolder.order_success.setVisibility(View.GONE);
			viewHolder.order_daifukuan.setVisibility(View.GONE);
			viewHolder.order_daipingjia.setVisibility(View.GONE);
			viewHolder.order_tuihuanhuo.setVisibility(View.GONE);
			notifyDataSetChanged();
			break;
		case 4:
			viewHolder.tv.setText(context.getResources().getString(R.string.daipingjia));
			viewHolder.order_daipingjia.setVisibility(View.VISIBLE);//待评价
			viewHolder.order_success.setVisibility(View.GONE);
			viewHolder.order_daifukuan.setVisibility(View.GONE);
			viewHolder.order_tuihuanhuo.setVisibility(View.GONE);
			viewHolder.order_daishouhuo.setVisibility(View.GONE);
			notifyDataSetChanged();
			break;
		case 5:
			viewHolder.tv.setText(context.getResources().getString(R.string.tuihuanhuo));
			viewHolder.order_tuihuanhuo.setVisibility(View.VISIBLE);//退换货
			viewHolder.order_success.setVisibility(View.GONE);
			viewHolder.order_daifukuan.setVisibility(View.GONE);
			viewHolder.order_daipingjia.setVisibility(View.GONE);
			viewHolder.order_daishouhuo.setVisibility(View.GONE);
			notifyDataSetChanged();
			break;

		default:
			break;
		}
		return convertView;
	}

	class ViewHolder {
		public LinearLayout order_success, order_daifukuan, order_daifahuo, order_daishouhuo, order_daipingjia,
				order_tuihuanhuo;
		public ImageView img;
		public TextView tv;
	}

}
