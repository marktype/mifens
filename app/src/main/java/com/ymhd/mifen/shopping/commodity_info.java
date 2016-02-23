package com.ymhd.mifen.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.mifen.R;
import com.ymhd.ad.Adv;
import com.ymhd.ad.CubeTransformer;
import com.ymhd.ad.DefaultTransformer;
import com.ymhd.mifen.order.order_info_sure;
import com.ymhd.mifen.order.order_pay;
import com.ymhd.mifen.pop.commoditypopochoose;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import net.tsz.afinal.FinalBitmap;

public class commodity_info extends Activity{
	private LayoutInflater mInflater;
	public FinalBitmap finalImageLoader ;   
	
	private List<View> imageViews; // 滑动的图片集合
	private TextView tv_num;
	private ViewPager vp;
	private MycommodityAdapter myAdapter; 
	private int currentItem = 0; // 当前图片的索引号
	private List<Adv> imageResUrl; // 图片ID
	private LinearLayout commodity_lin1,scrollview2_lin2;
	private View commodity_view1,commodity_view2;
	private RelativeLayout commodity_main;
	private ScrollView commodity_sc,commodity_sc2;
	private Button btn_buy;
	private commoditypopochoose commoditypopchoose1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commodity_info);
		mInflater = LayoutInflater.from(this);
		finalImageLoader=FinalBitmap.create(this);
		finalImageLoader.configLoadingImage(R.drawable.img10);//设置图片加载完成之前显示的图片
		init();
		initAD();
	}
	
	public void init()
	{
		vp=(ViewPager) findViewById(R.id.commodity_vp);
		tv_num = (TextView) findViewById(R.id.commodity_num);
		vp.setPageTransformer(true, new DefaultTransformer());
		commodity_main = (RelativeLayout) findViewById(R.id.main);
		
		
		btn_buy = (Button) findViewById(R.id.commodity_buy);
		btn_buy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				commoditypopchoose1 = new commoditypopochoose(commodity_info.this, itemsOnClick);
				commoditypopchoose1.
				 showAtLocation(commodity_info.this.findViewById(R.id.main), 
						 Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置  
				//产生背景变暗效果
			    WindowManager.LayoutParams lp=getWindow().getAttributes();
				lp.alpha = 0.4f;
				getWindow().setAttributes(lp);
				commoditypopchoose1.setOnDismissListener(new OnDismissListener() {
					
					@Override
					public void onDismiss() {
						// TODO Auto-generated method stub
						WindowManager.LayoutParams lp=getWindow().getAttributes();
		    			lp.alpha = 1f;
		    			getWindow().setAttributes(lp);	
					}
				});
			}
		});
		
		
	}
	//为弹出窗口实现监听类  
    private OnClickListener  itemsOnClick = new OnClickListener(){  
  
        public void onClick(View v) {  
        	commoditypopchoose1.dismiss();  
            switch (v.getId()) {  
            case R.id.commodity_choose_ok: 
            	Intent in = new Intent(commodity_info.this,order_info_sure.class);
            	startActivity(in);
            	commodity_main.setAlpha(1.0f);
                break;  
//            case R.id.btn_pick_photo:                 
//                break;  
            default:  
                break;  
            }  
              
                  
        }  
          
    };
	private void initAD() {
		imageResUrl=new ArrayList<Adv>();
		Adv adv=new Adv();
		adv.setImg_url("http://img0.imgtn.bdimg.com/it/u=3810191139,3441323557&fm=21&gp=0.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("2");
		imageResUrl.add(adv);
		
		adv=new Adv();
		adv.setImg_url("http://img0.imgtn.bdimg.com/it/u=3810191139,3441323557&fm=21&gp=0.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("2");
		imageResUrl.add(adv);
		
		adv=new Adv();
		adv.setImg_url("http://img0.imgtn.bdimg.com/it/u=3810191139,3441323557&fm=21&gp=0.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("2");
		imageResUrl.add(adv);
		
		adv=new Adv();
		adv.setImg_url("http://img0.imgtn.bdimg.com/it/u=3810191139,3441323557&fm=21&gp=0.jpg");
		adv.setTarget_url("www.baidu.com");
		adv.setType("2");
		imageResUrl.add(adv);
	}
	@Override
	protected void onResume() {
		super.onResume();
		currentItem=0;
		initViewPaper();
		adTimer=new Timer();
		adTimer.schedule(new ScrollTask(), 5*1000, 3* 1000);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(adTimer!=null){
			adTimer.cancel();
			adTimer=null;
		}
	}
	private void initViewPaper() {
		//首先将白点全部隐藏，再根据获取的图片数量显示
		imageViews = new ArrayList<View>();
		// 初始化图片资源
		for (int i = 0; i < imageResUrl.size(); i++) {
			final Adv adv=imageResUrl.get(i);
			View view = mInflater.inflate(R.layout.ad_img, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.img);
			finalImageLoader.display(imageView,adv.getImg_url());
			//设置广告点击事件
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					switch (Integer.parseInt(adv.getType())) {
					case 1://连接类型
						 Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(adv.getTarget_url()));
						 it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
						 startActivity(it);  
						break;
					case 2:
						Toast.makeText(getApplicationContext(), "点击了", Toast.LENGTH_LONG).show();
						break;
				   }
			    }
			});
			imageViews.add(view);			
		}
		
		if( imageResUrl.size()<=1){
			tv_num.setVisibility(View.GONE);
		}
		myAdapter= new MycommodityAdapter();
		vp.setPageTransformer(true, new CubeTransformer());
		vp.setAdapter(myAdapter);// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		vp.setOnPageChangeListener(new MyPageChangeListener());
	}
	private Timer adTimer = null;

	// 切换当前显示的图片
	@SuppressLint("HandlerLeak")
		private Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					vp.setCurrentItem(currentItem);// 切换当前显示的图片
					break;
				}
			};
		};
	/**
	 * 换行切换任务
	 * 
	 * @author byl
	 * 
	 */
	private class ScrollTask extends TimerTask {
		public void run() {
			if(imageViews!=null&&imageViews.size()>0){
				currentItem = (currentItem + 1) % imageViews.size();
				handler.sendEmptyMessage(1); // 通过Handler切换图片
			}
		}

	}
	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author cjj
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;
		public void onPageSelected(int position) {
			currentItem = position;
			int i = currentItem+1;
			tv_num.setText(""+i);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}
	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author cjj
	 * 
	 */
	private class MycommodityAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}
	
	

}
