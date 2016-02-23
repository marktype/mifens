package com.ymhd.mifei.sign;

import java.io.IOException;
import java.text.ParseException;

import com.example.mifen.R;
import com.ymhd.mifen.http.APP_url;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@SuppressLint("HandlerLeak")
public class signin extends Fragment {
	private View view, view2, view3;
	private LinearLayout linformore,li2;
	private ImageView more, more2;
	private Button sign_login_btn;
	private EditText edt_signuser,edt_signpassword;
	private String user_name,password;
	private SharedPreferences sp;
	SharedPreferences.Editor editor;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		view = inflater.inflate(R.layout.signin, container, false);
		linformore = (LinearLayout) view.findViewById(R.id.linformore);
		view3 = inflater.inflate(R.layout.signin3, container, false);
		view2 = inflater.inflate(R.layout.signin2, container, false);
		linformore.addView(view2);
		more = (ImageView) view2.findViewById(R.id.more);
		li2 = (LinearLayout) view2.findViewById(R.id.li2);
		more2 = (ImageView) view3.findViewById(R.id.more2);

		li2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linformore.removeAllViews();
				Log.e("1", "1");
				linformore.addView(view3);
				Log.e("2", "2");
			}
		});
		more2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("2", "2");
				linformore.removeAllViews();
				linformore.addView(view2);
			}
		});
		init();
		return view;
	}
	public void init()
	{
		sign_login_btn = (Button) view.findViewById(R.id.sign_login_btn);
		edt_signuser = (EditText) view.findViewById(R.id.signin_user);
		edt_signpassword = (EditText) view.findViewById(R.id.signin_password);
		sp = getActivity().getSharedPreferences("token&refreshtoken", Context.MODE_PRIVATE);
		editor = sp.edit();
		sign_login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user_name = edt_signuser.getText().toString();
				password = edt_signpassword.getText().toString();
				logintask lo = new logintask();
				lo.execute();
			}
		});
	}
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
            {
				super.handleMessage(msg);
				JSONObject res_j = (JSONObject) msg.obj;
				if(res_j.getString("token_type").equals("bearer"))
				{
					Log.e("111", "success");
					editor.putBoolean("stype", false);
					editor.putString("logintoken", res_j.getString("access_token"));
					editor.commit();
					getActivity().finish();
				}
            }
	};

	/**
	 * ${TODO} <登录验证的线程>
	 */
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			String st_bearertoken;
			JSONObject js = null;
			APP_url ap = new APP_url();
			Message msg = handler.obtainMessage();
			try {
				st_bearertoken = sp.getString("token","");
				js = ap.Login(st_bearertoken, user_name, password);
				msg.obj = js;
				handler.sendMessage(msg);
				handler.removeCallbacks(runnable);
			} catch (JSONException | ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	class logintask extends AsyncTask<Void, Void, String> {

		public logintask() {

		}

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			new Thread(runnable).start();
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
		}

		
	}
}
