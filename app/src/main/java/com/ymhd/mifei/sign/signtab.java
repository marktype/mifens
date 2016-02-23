package com.ymhd.mifei.sign;

import java.util.List;

import com.example.mifen.R;
import com.ymhd.fragment.ui.IndicatorFragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class signtab extends IndicatorFragmentActivity {
	private ImageView sign_setback;
	Intent in = new Intent();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sign_setback = (ImageView) findViewById(R.id.sign_setback);
        sign_setback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				in.setAction("com.ymhd.select");
				sendBroadcast(in);
				finish();
			}
		});
    }
	@Override
	protected int supplyTabs(List<TabInfo> tabs) {
		tabs.add(new TabInfo(0, getString(R.string.signin), signin.class));
		tabs.add(new TabInfo(1, getString(R.string.signup), signup.class));

		return 0;
	}
	
	
	
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		in.setAction("com.ymhd.select");
		sendBroadcast(in);
		finish();
	}
	// -------------------------------------隐藏输入法-----------------------------------------------------
    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
