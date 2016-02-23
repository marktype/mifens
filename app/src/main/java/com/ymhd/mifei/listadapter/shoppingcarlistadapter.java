package com.ymhd.mifei.listadapter;

import java.util.HashMap;
import java.util.List;

import com.example.mifen.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class shoppingcarlistadapter extends BaseAdapter {
	private Context context;
	private List<String> list;
	private OnClickListener itemsOnClick;
	private int index = -1;
	private boolean ischeck = false;
	private SharedPreferences sp;
	SharedPreferences.Editor editor;

	public shoppingcarlistadapter(List<String> list, Context context) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
		this.itemsOnClick = itemsOnClick;
		init();
	}
	public void init()
	{
		sp = context.getSharedPreferences("checktype", Context.MODE_PRIVATE);
		editor = sp.edit();
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
			convertView = LayoutInflater.from(context).inflate(R.layout.shoppingcar_itemsistview, null);// 这个过程相当耗时间
			viewHolder = new ViewHolder();
			viewHolder.img = (CheckBox) convertView.findViewById(R.id.shoppingcar_selectimg);
			viewHolder.lin = (LinearLayout) convertView.findViewById(R.id.shoppingcar_selectlin);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}
		viewHolder.img.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					editor.putBoolean("shoppingcar"+position, true);
				} else {
					editor.putBoolean("shoppingcar"+position, false);
				}
				editor.commit();
			}
		});
		viewHolder.img.setChecked(sp.getBoolean("shoppingcar"+position, false));

		return convertView;
	}


	public static class ViewHolder {
		public View view_promotion;
		public CheckBox img;
		public LinearLayout lin;
		public int index = -1;
	}

}
