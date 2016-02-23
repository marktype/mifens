package com.ymhd.mifen.shopping;

import com.example.mifen.R;
import com.ymhd.mifen.order.order_info_sure;
import com.ymhd.mifen.pop.filterpop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class seach_shopping extends Activity {
	private RadioButton salesvolume, seach_price;
	private View view1;
	private LinearLayout lin1;
	private ImageView seach_back;
	private boolean type1, type2;
	private TextView seach_filter,seach_name;
	private filterpop seach_filterpop;
	private String tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seach_shopping);
		tv = getIntent().getStringExtra("tv");
		
		init();
	}

	public void init() {
		view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.seach_button, null);
		lin1 = (LinearLayout) findViewById(R.id.seach_shopping_linearlayout);
		seach_name = (TextView) view1.findViewById(R.id.tv_name);
		seach_name.setText(tv);
		lin1.addView(view1);
		
		seach_price = (RadioButton) findViewById(R.id.seach_price);
		salesvolume = (RadioButton) findViewById(R.id.seach_salesvolume);
		salesvolume.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					inittype1();
				} else {
					Drawable drawable = getResources().getDrawable(R.drawable.seach_05);
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					salesvolume.setCompoundDrawables(null, null, drawable, null);
				}
			}
		});
		seach_price.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					inittype2();
				} else {
					Drawable drawable = getResources().getDrawable(R.drawable.seach_05);
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					seach_price.setCompoundDrawables(null, null, drawable, null);
				}
			}
		});
		seach_price.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (type2) {
					Drawable drawable = getResources().getDrawable(R.drawable.seach_04);
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					seach_price.setCompoundDrawables(null, null, drawable, null);
					type2 = false;
				} else {
					Drawable drawable = getResources().getDrawable(R.drawable.seach_03);
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					seach_price.setCompoundDrawables(null, null, drawable, null);
					type2 = true;
				}
			}
		});
		salesvolume.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (type1) {
					Drawable drawable = getResources().getDrawable(R.drawable.seach_04);
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					salesvolume.setCompoundDrawables(null, null, drawable, null);
					type1 = false;
				} else {
					Drawable drawable = getResources().getDrawable(R.drawable.seach_03);
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					salesvolume.setCompoundDrawables(null, null, drawable, null);
					type1 = true;
				}
			}
		});
		seach_back = (ImageView) findViewById(R.id.seach_back);
		seach_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		seach_filter = (TextView) findViewById(R.id.seach_filter);
		seach_filter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				seach_filterpop = new filterpop(seach_shopping.this, itemsOnClick);
				seach_filterpop.showAtLocation(seach_shopping.this.findViewById(R.id.seach_main),
						Gravity.RIGHT | Gravity.CENTER_VERTICAL, 0, 0); // 设置layout在PopupWindow中显示的位置
				// 产生背景变暗效果
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 0.4f;
				getWindow().setAttributes(lp);
				seach_filterpop.setOnDismissListener(new OnDismissListener() {

					@Override
					public void onDismiss() {
						// TODO Auto-generated method stub
						WindowManager.LayoutParams lp = getWindow().getAttributes();
						lp.alpha = 1f;
						getWindow().setAttributes(lp);
					}
				});
			}
		});
	}

	public void inittype1() {
		type1 = false;
	}

	public void inittype2() {
		type2 = false;
	}

	// 为弹出窗口实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			switch (v.getId()) {
			// case R.id.commodity_choose_ok:
			// break;

			default:
				break;
			}

		}
	};
}
