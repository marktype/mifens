package com.ymhd.mifen.adapter;

import com.example.mifen.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyRecyclerViewAdapterforfriendlygroup extends RecyclerView.Adapter<MyRecyclerViewAdapterforfriendlygroup.ViewHolder> {

	// 数据集
	private String[] mDataset;
	private  Context context;

	public MyRecyclerViewAdapterforfriendlygroup(String[] dataset, Context context) {
		super();
		mDataset = dataset;
		this.context = context;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

		View view = View.inflate(viewGroup.getContext(), R.layout.myself_friendly_adapter, null);
		// 创建一个ViewHolder
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int i) {
		// 绑定数据到ViewHolder上
		// viewHolder.mTextView.setText(mDataset[i]);
		viewHolder.friendly_dianzhan_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "你点了赞"+i, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return mDataset.length;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextView,friendly_dianzhan_tv;

		public ViewHolder(View itemView) {
			super(itemView);
			// mTextView = (TextView) itemView;
			friendly_dianzhan_tv = (TextView) itemView.findViewById(R.id.friendly_dianzhan);
			
		}
	}
}
