package com.ymhd.mifen.myself;

import com.example.mifen.R;
import com.ymhd.mifei.tool.BadgeTextView;
import com.ymhd.mifei.tool.CalendarSign;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class register extends Activity{
	private ImageView myself_register_setback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myself_register);
		initId();
	}
	public void initId()
	{
		myself_register_setback = (ImageView) findViewById(R.id.myself_register_setback);
		CalendarSign calendarSign = (CalendarSign) findViewById(R.id.register_calendar);
		BadgeTextView todayBadgeTextView = calendarSign.getTodayBadgeTextView();
		BadgeTextView notodayBadgeTextView = calendarSign.getTodayBadgeTextView();
		BadgeTextView activityBadgeTextView = calendarSign.getactivityBadgeTextView(24);
		activityBadgeTextView.setChecked(true);
		todayBadgeTextView.setBadgeString("+99");
		todayBadgeTextView.startDayAnim();
	}
	public void init()
	{
		myself_register_setback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
