package com.ymhd.mifei.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout{
	private boolean stype = false;
	public MyRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		IntentFilter filter = new IntentFilter();
		// 向过滤器中添加action
		filter.addAction("com.ymhd.stype");
		// 注册广播
		context.registerReceiver(stype_select, filter);
	}
	public MyRelativeLayout(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    public MyRelativeLayout(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
       
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent ev) {  
        Log.e("111","--onInterceptTouchEvent--A");  
        return stype;  
    }  
      
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        Log.e("111","--onTouchEvent---A" );  
        return true;  
    }  
    private BroadcastReceiver stype_select = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			stype = intent.getBooleanExtra("stype", false);
			Log.e("111","----A"+stype);  
		}

	};
}
