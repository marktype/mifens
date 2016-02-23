package com.ymhd.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.mifen.R;
import com.ymhd.mifei.listadapter.classifyadapter;
import com.ymhd.mifei.listadapter.classifyadapter2;
import com.ymhd.mifei.listadapter.classifyadapter3;
import com.ymhd.mifei.tool.MyRelativeLayout;
import com.ymhd.mifen.setting.Mydialog2;

import java.util.ArrayList;
import java.util.List;

public class classifypage extends baseActivity {
    private ListView li1, li2, li3;
    private LinearLayout view_lin, classify_lin;
    private List<String> list;
    private classifyadapter classifyadapter;
    private classifyadapter2 classifyadapter2;
    private classifyadapter3 classifyadapter3;
    private MyRelativeLayout seach_re;
    private EditText edt_search;
    int i = 0;
    private Mydialog2 mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifyxml);
        init();
        initdialog();
    }

    public void initdialog() {
        mydialog = new Mydialog2(this, R.style.dialog2);
        Window win = mydialog.getWindow();
        LayoutParams params = new LayoutParams();
        win.setAttributes(params);
        mydialog.setCanceledOnTouchOutside(false);
    }

    public void init() {
        seach_re = (MyRelativeLayout) findViewById(R.id.seach_rela_parent);
        seach_re = new MyRelativeLayout(getApplicationContext());
        li1 = (ListView) findViewById(R.id.listview_classify);
        li2 = (ListView) findViewById(R.id.listview_commoditylist);
        li3 = (ListView) findViewById(R.id.listview_activity1);
        view_lin = (LinearLayout) findViewById(R.id.lin_storagerack2);
        edt_search = (EditText) findViewById(R.id.home_page_seachcontent);
        edt_search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in = new Intent(classifypage.this, seachpage.class);
                startActivity(in);
            }
        });
        final WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        list = new ArrayList<String>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        classifyadapter = new classifyadapter(list, getApplicationContext(), null);
        classifyadapter2 = new classifyadapter2(list, getApplicationContext(), null);
        classifyadapter3 = new classifyadapter3(list, getApplicationContext(), null);
        li1.setAdapter(classifyadapter);
        li2.setAdapter(classifyadapter2);
        li3.setAdapter(classifyadapter3);
        final RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) li3.getLayoutParams();
        final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view_lin.getLayoutParams();
        //点击滑动动画
        li3.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                mydialog.show();
                li1.setSelection(position);
                classifyadapter.init();
                classifyadapter.setSelet_c(position, true);
                view_lin.setVisibility(View.VISIBLE);
                lp.leftMargin = (int) (0.6 * wm.getDefaultDisplay().getWidth());//设置第二层页面，位于第一层页面之下，距离左边0.6的屏幕宽度；
                lp.rightMargin = (int) (-0.6 * wm.getDefaultDisplay().getWidth());
                view_lin.setLayoutParams(lp);
                final TranslateAnimation tAnim;
                tAnim = new TranslateAnimation(0, (float) (-0.6 * wm.getDefaultDisplay().getWidth()), 0, 0);
                tAnim.setInterpolator(new DecelerateInterpolator());
                tAnim.setDuration(600);//0.6秒完成动画；
                //开始动画，进行点触拦截
                view_lin.startAnimation(tAnim);
                li3.startAnimation(tAnim);
                li3.setEnabled(false);

                tAnim.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // TODO Auto-generated method stub
                        li3.setVisibility(View.GONE);
                        li3.setEnabled(true);
                        lp.leftMargin = (int) (0);
                        lp.rightMargin = (int) (0);
                        view_lin.setLayoutParams(lp);
                        mydialog.dismiss();
                    }
                });

            }
        });
        //右滑回到主界面
        li1.setOnTouchListener(new OnTouchListener() {
            int lastx;
            int dx;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        lastx = (int) event.getX();// 获取触摸事件触摸位置的原始X坐标
                        Log.e("dy", "" + lastx);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        dx = (int) (event.getX() - lastx);
                        Log.e("dy", "" + dx);
                        if (dx > 200) {
                            i = 0;
                            final TranslateAnimation tAnim2;
                            tAnim2 = new TranslateAnimation(0, (float) (0.6 * wm.getDefaultDisplay().getWidth()), 0, 0);
                            tAnim2.setInterpolator(new DecelerateInterpolator());
                            tAnim2.setDuration(600);

                            lp2.leftMargin = (int) (-0.6 * wm.getDefaultDisplay().getWidth());
                            lp2.rightMargin = (int) (0.6 * wm.getDefaultDisplay().getWidth());
                            li3.setVisibility(View.VISIBLE);
                            li3.setLayoutParams(lp2);
                            li3.startAnimation(tAnim2);
                            view_lin.startAnimation(tAnim2);
                            tAnim2.setAnimationListener(new AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    // TODO Auto-generated method stub
                                    view_lin.setVisibility(View.GONE);
                                    lp2.leftMargin = (int) (0);
                                    lp2.rightMargin = (int) (0);
                                    li3.setLayoutParams(lp2);
                                }
                            });

                        }
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

}
