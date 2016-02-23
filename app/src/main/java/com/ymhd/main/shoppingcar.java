package com.ymhd.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.example.mifen.R;
import com.ymhd.mifei.listadapter.shoppingcarlistadapter;
import com.ymhd.mifen.http.APP_url;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import android.widget.ListView;

//购物车
@SuppressLint("HandlerLeak")
public class shoppingcar extends baseActivity {
	private ListView shoppingcar_li1;
	private shoppingcarlistadapter spc_listadapter;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcar);
		init();
		shopcargettask spct = new shopcargettask();
		spct.execute();
	}

	public void init() {
		shoppingcar_li1 = (ListView) findViewById(R.id.shoppingcar_listorder);
		list = new ArrayList<String>();
		shoppingcar_li1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		spc_listadapter = new shoppingcarlistadapter(list, getApplicationContext());
		shoppingcar_li1.setAdapter(spc_listadapter);

	}

	public void adapterdata(JSONObject res_j)
	{
		JSONArray ja = res_j.getJSONArray("data");
		if(ja.size()>0)
		{
			for(int i=0;i<ja.size();i++)
			{
				list.add("1");
			}
		}
		
	}
	// 购物车请求task
	class shopcargettask extends AsyncTask<Void, Void, String> {

		public shopcargettask() {
			
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
				} else {
					adapterdata(res_j);
					spc_listadapter.notifyDataSetChanged();
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
					String st_bearertoken = sp.getString("logintoken", "");
					js = ap.getShopcar(st_bearertoken);
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
