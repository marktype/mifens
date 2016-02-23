package com.ymhd.main;

import java.io.IOException;
import java.text.ParseException;
import com.example.mifen.R;
import com.ymhd.mifei.sign.signtab;
import com.ymhd.mifei.sign.signup;
import com.ymhd.mifen.http.APP_url;
import com.ymhd.mifen.myself.collection;
import com.ymhd.mifen.myself.friendly;
import com.ymhd.mifen.myself.friendlytab;
import com.ymhd.mifen.myself.midouxiangxi;
import com.ymhd.mifen.myself.myhistory;
import com.ymhd.mifen.myself.register;
import com.ymhd.mifen.myself.sms;
import com.ymhd.mifen.order.myorderall;
import com.ymhd.mifen.setting.mifensetting;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressLint("HandlerLeak")
public class myself extends Activity{

	private TextView tv_collection;
	private LinearLayout myself_qiandao,myorder_all,daifukuan_lin,daifahuo_lin,
	daishouhuo_lin,daipingjia_lin,tuihuanhuo_lin,myself_history,myself_friendly;
	private ImageView setback;
	private RelativeLayout myself_sms_re;
	private LinearLayout myself_midou_mingxi,agency_lin;
	private boolean stype=true;
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	private String login_token;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myself);
		sp = getSharedPreferences("token&refreshtoken", Context.MODE_PRIVATE);
		editor = sp.edit();
		login_token = sp.getString("logintoken", "");
		init();
	}
	public void init()
	{
		myself_qiandao = (LinearLayout) findViewById(R.id.myself_qiandao);
		myself_qiandao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,register.class);
				startActivity(in);
			}
		});
		tv_collection = (TextView) findViewById(R.id.mycollection);
		tv_collection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in =  new  Intent(myself.this,collection.class);
				startActivity(in);
					
			}
		});
		setback = (ImageView) findViewById(R.id.setback);
		setback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,mifensetting.class);
				startActivity(in);
			}
		});
		myself_sms_re = (RelativeLayout) findViewById(R.id.myself_sms_re);
		myself_sms_re.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,sms.class);
				startActivity(in);
			}
		});
		myself_midou_mingxi = (LinearLayout) findViewById(R.id.myself_midou_mingxi);
		myself_midou_mingxi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,midouxiangxi.class);
				startActivity(in);
			}
		});
		myorder_all = (LinearLayout) findViewById(R.id.myorder_all);
		myorder_all.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myorderall.class);
				in.putExtra("type", 0);
				startActivity(in);
			}
		});
		daifukuan_lin = (LinearLayout) findViewById(R.id.daifukuan_lin);
		daifukuan_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myorderall.class);
				in.putExtra("type", 1);
				startActivity(in);
			}
		});
		daifahuo_lin = (LinearLayout) findViewById(R.id.daifahuo_lin);
		daifahuo_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myorderall.class);
				in.putExtra("type", 2);
				startActivity(in);
			}
		});
		daishouhuo_lin = (LinearLayout) findViewById(R.id.daishouhuo_lin);
		daishouhuo_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myorderall.class);
				in.putExtra("type", 3);
				startActivity(in);
			}
		});
		daipingjia_lin = (LinearLayout) findViewById(R.id.daipingjia_lin);
		daipingjia_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myorderall.class);
				in.putExtra("type", 4);
				startActivity(in);
			}
		});
		tuihuanhuo_lin = (LinearLayout) findViewById(R.id.tuihuanhuo_lin);
		tuihuanhuo_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myorderall.class);
				in.putExtra("type", 5);
				startActivity(in);
			}
		});
		myself_history = (LinearLayout) findViewById(R.id.myself_history);
		myself_history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,myhistory.class);
				startActivity(in);
			}
		});
		myself_friendly = (LinearLayout) findViewById(R.id.myself_friendly);
		myself_friendly.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,friendlytab.class);
				startActivity(in);
			}
		});
		agency_lin = (LinearLayout) findViewById(R.id.agency);
		agency_lin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(myself.this,signtab.class);
				startActivity(in);
			}
		});
		memberinfo_task task = new memberinfo_task();
		task.execute();
	}
	
	public void initin()
	{
		Intent in = new Intent(myself.this,signtab.class);
		startActivity(in);
		
	}
	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 800) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(sp.getBoolean("stype", stype))
		{
			initin();
		}
//		initin();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	class memberinfo_task extends AsyncTask<Void, Void, String> {


		public memberinfo_task() {

		}

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			new Thread(run).start();
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

		}

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				JSONObject res_j = (JSONObject) msg.obj;
				if (!res_j.getBoolean("status")) {
					Log.e("111", res_j.getString("msg"));
				}
			}
		};
		Runnable run = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				APP_url ap = new APP_url();
				JSONObject js;
				Message msg = handler.obtainMessage();
				try {
					js = ap.getMemberinfo(login_token);
					msg.obj = js;
					handler.sendMessage(msg);
					handler.removeCallbacks(run);
				} catch (JSONException | ParseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};

	}
	
}
