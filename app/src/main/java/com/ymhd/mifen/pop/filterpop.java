package com.ymhd.mifen.pop;

import java.util.HashMap;
import java.util.Map;

import com.example.mifen.R;
import com.ymhd.mifei.tool.SingleSelectCheckBoxs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class filterpop extends PopupWindow {
	private TextView btn_cancel;
	private View mMenuView;
	private Context context;
	private SingleSelectCheckBoxs sscb;
	private Map<Integer, String> mColorData;
	private LinearLayout lin_select, lin_selec_box;
	private View view1, view2;
	private ExpandableListView exlist;

	public filterpop(Activity context, OnClickListener itemsOnClick) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.seach_filterpop, null);
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth((int) ( wm.getDefaultDisplay().getWidth()*0.8));
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.mypopwindow_anim_style2);
		// 实例化一个ColorDrawable颜色为半透明
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		// this.setBackgroundDrawable(dw);
		this.getBackground().setAlpha(0);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.seach_filterpop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
		lin_select = (LinearLayout) mMenuView.findViewById(R.id.seach_filter_ex);
		view1 = inflater.inflate(R.layout.seach_filterpop_expandlist, null);
		view2 = inflater.inflate(R.layout.seach_filterpop_singleselect, null);
		lin_select.addView(view1);
		lin_selec_box = (LinearLayout) view1.findViewById(R.id.seach_filterpop_select_box);
		lin_selec_box.addView(view2);
		sscb = (SingleSelectCheckBoxs) view2.findViewById(R.id.sscb);
		// 控件需要的数据类型
		mColorData = new HashMap<Integer, String>();
		mColorData.put(0, "红色");
		mColorData.put(1, "蓝色");
		mColorData.put(2, "绿色");
		mColorData.put(3, "紫色");
		mColorData.put(4, "白色");
		sscb.setData(mColorData);
		// btn_cancel = (TextView)
		// mMenuView.findViewById(R.id.myself_edit_cancel);
		// btn_cancel.setOnClickListener(itemsOnClick);
	}
}
