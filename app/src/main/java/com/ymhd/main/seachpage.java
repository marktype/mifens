package com.ymhd.main;

import java.util.ArrayList;

import com.example.mifen.R;
import com.ymhd.mifei.tool.FlowLayout;
import com.ymhd.mifen.shopping.seach_shopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class seachpage extends Activity {
    private FlowLayout hotlable, historylabel;
    private ArrayList<TextView> tvs;
    private String[] str = {"毛衣", "大衣", "皮夹克", "毛衣", "棉服", "连衣裙", "靴子", "羽绒服"};
    private TextView clearhistory, seachpage_cancel;
    private EditText et_search;
    private boolean stype = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        init();
    }

    //初始化，云标签
    public void init() {
        hotlable = (FlowLayout) findViewById(R.id.hotlabel);
        historylabel = (FlowLayout) findViewById(R.id.historylabel);
        clearhistory = (TextView) findViewById(R.id.clearhistory_text);
        et_search = (EditText) findViewById(R.id.home_page_seachcontent2);
        et_search.setTextColor(getApplication().getResources().getColor(R.color.background));
        int i = 0;
        while (i < 8) {
            final TextView tv = new TextView(getApplicationContext());
            if (i % 2 == 1) {
                tv.setTextColor(getResources().getColor(R.color.red));
            } else {
                tv.setTextColor(getResources().getColor(R.color.common_black_g));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            tv.setText(str[i] + i + "\b\b");
            tv.setTextSize(13);
            tv.setLayoutParams(params);
            tv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent in = new Intent(seachpage.this, seach_shopping.class);
                    in.putExtra("tv", tv.getText().toString());
                    startActivity(in);
                }
            });
            i++;
            hotlable.addView(tv);
        }
        i = 0;
        while (i < 8) {
            final TextView tv2 = new TextView(getApplicationContext());
            if (i % 2 == 1) {
                tv2.setTextColor(getResources().getColor(R.color.red));
            } else {
                tv2.setTextColor(getResources().getColor(R.color.common_black_g));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            tv2.setText(str[i] + i + "  ");
            tv2.setTextSize(13);
            tv2.setLayoutParams(params);
            tv2.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent in = new Intent(seachpage.this, seach_shopping.class);
                    in.putExtra("tv", tv2.getText().toString());
                    startActivity(in);
                }
            });
            i++;
            historylabel.addView(tv2);
        }
        clearhistory.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                historylabel.removeAllViews();
                ;
            }
        });
        seachpage_cancel = (TextView) findViewById(R.id.seachpage_cancel);
        seachpage_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        et_search.setOnKeyListener(onKeyListener);
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

    private OnKeyListener onKeyListener = new OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && stype) {
                // 隐藏软键盘
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }

                Intent in = new Intent(seachpage.this, seach_shopping.class);
                in.putExtra("tv", et_search.getText().toString());
                startActivity(in);
                stype = false;
                return true;
            } else {
                stype = true;
            }
            return false;
        }
    };

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
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
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
