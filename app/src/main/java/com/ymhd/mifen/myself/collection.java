package com.ymhd.mifen.myself;

import com.example.mifen.R;
import com.ymhd.mifen.adapter.MyGridViewAdapterforcollection_hsitory;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class collection extends Activity {
	private GridView gv;
	private MyGridViewAdapterforcollection_hsitory mygvadapter;
	private Integer[] imgs = { R.drawable.img14, R.drawable.img15, R.drawable.img16, R.drawable.img17, R.drawable.img18,
			R.drawable.img19 };
	private String[] str = { "BF风夹棉外套棉衣", "时尚混搭针织毛衣", "加厚拉链纯色小脚裤", "羊羔毛内里长款宽松棉衣", "BF风夹棉外套棉衣", "时尚混搭针织衫毛衣" };
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	private TextView tv_changge, tv_delete;
	private ImageView im_delete,myself_collection_history_back;
	private LinearLayout myself_collection_history_delete;
	private boolean type = false;
	private CheckBox collection_history_select_all;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myself_collection_history);
		init();
	}

	public void init() {
		initid();

		tv_changge.setText("我的收藏");
		sp = getSharedPreferences("checktype", Context.MODE_PRIVATE);
		editor = sp.edit();
		mygvadapter = new MyGridViewAdapterforcollection_hsitory(getApplicationContext(), imgs, str, gv);
		gv.setAdapter(mygvadapter);
		gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				myself_collection_history_delete.setVisibility(View.VISIBLE);
				tv_delete.setVisibility(View.VISIBLE);
				im_delete.setVisibility(View.GONE);
				editor.putBoolean("yes", true);
				editor.commit();
				type = true;
				mygvadapter.notifyDataSetChanged();
				return false;
			}
		});
		myself_collection_history_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		collection_history_select_all.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					editor.putBoolean("allcheck_collection_history", true);
					editor.putBoolean("type_select", true);

				} else {
					editor.putBoolean("allcheck_collection_history", false);
					editor.putBoolean("type_select", false);
					for (int i = 0; i < str.length; i++) {
						Log.e("11", ""+i);
						editor.putBoolean("collection"+i, false);
					}
				}
				editor.commit();
				mygvadapter.notifyDataSetChanged();
			}
		});

	}

	public void initid() {
		tv_changge = (TextView) findViewById(R.id.textChangge);
		myself_collection_history_delete = (LinearLayout) findViewById(R.id.myself_collection_history_delete_lin);
		gv = (GridView) findViewById(R.id.myself_collection_grivideview);
		tv_delete = (TextView) findViewById(R.id.myself_collection_history_delete_titletv);
		im_delete = (ImageView) findViewById(R.id.myself_collection_history_delete_titleim);
		collection_history_select_all = (CheckBox) findViewById(R.id.collection_history_select_all);
		myself_collection_history_back = (ImageView) findViewById(R.id.myself_collection_history_back);
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (type) {
				myself_collection_history_delete.setVisibility(View.GONE);
				tv_delete.setVisibility(View.GONE);
				im_delete.setVisibility(View.VISIBLE);
				editor.putBoolean("yes", false);
				editor.commit();
				mygvadapter.notifyDataSetChanged();
				type = false;
			} else {
				editor.clear();
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
