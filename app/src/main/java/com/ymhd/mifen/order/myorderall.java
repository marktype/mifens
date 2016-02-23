package com.ymhd.mifen.order;

import java.util.List;

import com.example.mifen.R;
import com.ymhd.fragment.ui.IndicatorFragmentActivity2;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class myorderall extends IndicatorFragmentActivity2{
	private int type;
	private ImageView img_setback;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.myorder_all);
        start();
        img_setback = (ImageView) findViewById(R.id.myorder_setback);
        img_setback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
    }
	@Override
	protected int supplyTabs(List<TabInfo> tabs) {
		// TODO Auto-generated method stub
		tabs.add(new TabInfo(0, getString(R.string.all), false,quanbu.class,0));
		tabs.add(new TabInfo(1, getString(R.string.obligation),false, daifukuan.class,0));
		tabs.add(new TabInfo(2, getString(R.string.daifahuo), false,daifahuo.class,0));
		tabs.add(new TabInfo(3, getString(R.string.daishouhuo), true,daishouhuo.class,3));
		tabs.add(new TabInfo(4, getString(R.string.daipingjia), true,daipingjia.class,2));
		tabs.add(new TabInfo(5, getString(R.string.tuihuanhuo), false,tuihuanhuo.class,0));
		type = getIntent().getIntExtra("type", 0);
		return type;
	}

}
