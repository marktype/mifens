package com.ymhd.mifen.adapter;

import com.example.mifen.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyGridViewAdapterforcollection_hsitory extends BaseAdapter {
	// 上下文对象
	private Context context;
	private Integer[] imgs;
	private String[] str;
	private GridView gv;
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	private boolean ischeck = false;
	private int position2;

	// 图片数组
	public MyGridViewAdapterforcollection_hsitory(Context context, Integer[] imgs, String[] str, GridView v) {
		this.context = context;
		this.imgs = imgs;
		this.str = str;
		this.gv = v;
		init();
	}

	public void init() {
		sp = context.getSharedPreferences("checktype", Context.MODE_PRIVATE);//是否被选中的存储
		editor = sp.edit();
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

	public void setSelet_c(int positon, boolean ischeck) {//添加被选中的按钮
		this.position2 = positon;
		this.ischeck = ischeck;
		editor.putBoolean("collection" + position2, ischeck);
		editor.commit();
		notifyDataSetChanged();
	}


	public void checkselectall(LinearLayout lin,CheckBox ck) {

		if (sp.getBoolean("allcheck_collection_history", false)) {
			lin.setVisibility(View.VISIBLE);
			ck.setChecked(true);
		} else {
			lin.setVisibility(View.GONE);
			ck.setChecked(false);
		}
	}

	public void checkselect(int position, LinearLayout lin, CheckBox ck) {//设置被选中位置的状态

		if (sp.getBoolean("collection" + position, false)) {
			lin.setVisibility(View.VISIBLE);
			ck.setChecked(true);
		} else {
			lin.setVisibility(View.GONE);
			ck.setChecked(false);
		}
	}

	// 创建View方法
	@SuppressLint("ResourceAsColor")
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.myself_collection_history_gridviewadapter,
					null);
			viewHolder = new ViewHolder();
			viewHolder.mengcheng = (LinearLayout) convertView.findViewById(R.id.myself_collection_gridview_mengcheng);
			viewHolder.yesornomengcheng = (RelativeLayout) convertView
					.findViewById(R.id.myself_collection_gridview_yesornomengcheng);
			viewHolder.ck = (CheckBox) convertView.findViewById(R.id.myself_collection_gridview_check);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (sp.getBoolean("yes", false)) {
			viewHolder.yesornomengcheng.setVisibility(View.VISIBLE);
		} else {
			viewHolder.yesornomengcheng.setVisibility(View.GONE);
		}
		viewHolder.ck.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					setSelet_c(position, true);
				} else {
					setSelet_c(position, false);
				}
			}
		});
		if (sp.getBoolean("type_select", false)) {
			checkselectall(viewHolder.mengcheng,viewHolder.ck);
		} else {
			checkselect(position, viewHolder.mengcheng, viewHolder.ck);
		}
		return convertView;
	}

	class ViewHolder {
		public CheckBox ck;
		public RelativeLayout yesornomengcheng;
		public TextView tv_name, tv_price, tv_num;
		public ImageView commodity_img_homepage;
		public LinearLayout mengcheng;
	}
}
