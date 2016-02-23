package com.ymhd.mifei.tool;

import com.example.mifen.R;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollViewforviewpager extends ScrollView {
	private Context context;

private boolean canScroll;
	private GestureDetector mGestureDetector;
	View.OnTouchListener mGestureListener;
	public MyScrollViewforviewpager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@SuppressWarnings("deprecation")
	public MyScrollViewforviewpager(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		canScroll = true;
	}
	

	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(ev.getAction() == MotionEvent.ACTION_UP)
			canScroll = true;
		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if(canScroll)
				if (Math.abs(distanceY) >= Math.abs(distanceX))
					canScroll = true;
				else
					canScroll = false;
			return canScroll;
		}
	}
}
	

