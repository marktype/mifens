package com.ymhd.mifen.pop;

import com.example.mifen.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class commoditypopochoose extends PopupWindow {  
	  
	  
    private Button btn_choose_ok;  
    private View mMenuView; 
    private Context context;
  
    public commoditypopochoose(Activity context,OnClickListener itemsOnClick) {  
        super(context);  
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        mMenuView = inflater.inflate(R.layout.commodity_pop_choice, null);  
        //设置SelectPicPopupWindow的View  
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(LayoutParams.FILL_PARENT);  
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
        this.setAnimationStyle(R.style.mypopwindow_anim_style);  
        //实例化一个ColorDrawable颜色为半透明  
//        ColorDrawable dw = new ColorDrawable(0xb0000000);  
////        //设置SelectPicPopupWindow弹出窗体的背景  
//        this.setBackgroundDrawable(dw); 
        this.getBackground().setAlpha(0);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框  
        mMenuView.setOnTouchListener(new OnTouchListener() {  
              
            public boolean onTouch(View v, MotionEvent event) {  
                  
                int height = mMenuView.findViewById(R.id.poplaout).getTop();  
                int y=(int) event.getY();  
                if(event.getAction()==MotionEvent.ACTION_UP){  
                    if(y<height){  
                        dismiss();  
                    }  
                }                 
                return true;  
            }  
        });  
        
        
        btn_choose_ok = (Button) mMenuView.findViewById(R.id.commodity_choose_ok);  
//        btn_pick_photo = (Button) mMenuView.findViewById(R.id.btn_pick_photo);  
//        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);  
//        //取消按钮  
//        btn_cancel.setOnClickListener(new OnClickListener() {  
//  
//            public void onClick(View v) {  
//                //销毁弹出框  
//                dismiss();  
//            }  
//        });  
//        //设置按钮监听  
        btn_choose_ok.setOnClickListener(itemsOnClick);  
//        btn_take_photo.setOnClickListener(itemsOnClick); 
//  
    }
    
  
}  
