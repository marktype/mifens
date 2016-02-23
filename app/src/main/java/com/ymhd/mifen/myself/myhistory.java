package com.ymhd.mifen.myself;

import com.example.mifen.R;
import com.ymhd.mifen.adapter.MyGridViewAdapterforcollection_hsitory;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myhistory extends Activity{
	private GridView gv;
	private MyGridViewAdapterforcollection_hsitory mygvadapter;
	private Integer[] imgs = { R.drawable.img14, R.drawable.img15, R.drawable.img16, R.drawable.img17, R.drawable.img18,
			R.drawable.img19 };
	private String[] str = { "BF风夹棉外套棉衣", "时尚混搭针织毛衣", "加厚拉链纯色小脚裤", "羊羔毛内里长款宽松棉衣", "BF风夹棉外套棉衣", "时尚混搭针织衫毛衣" };
	private TextView tv_changge, tv_delete;
	private ImageView im_delete;
	private LinearLayout myself_collection_history_delete;
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	private boolean type = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myself_collection_history);
		init();
	}
	public void init()
	{
		initid();
		tv_changge.setText("浏览历史");
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
	}
	public void initid() {
		tv_changge = (TextView) findViewById(R.id.textChangge);
		myself_collection_history_delete = (LinearLayout) findViewById(R.id.myself_collection_history_delete_lin);
		gv = (GridView) findViewById(R.id.myself_collection_grivideview);
		tv_delete = (TextView) findViewById(R.id.myself_collection_history_delete_titletv);
		im_delete = (ImageView) findViewById(R.id.myself_collection_history_delete_titleim);

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
				type= false;
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
