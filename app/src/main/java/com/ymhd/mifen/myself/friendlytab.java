package com.ymhd.mifen.myself;

import java.util.List;

import com.example.mifen.R;
import com.ymhd.fragment.ui.IndicatorFragmentActivity2;
import com.ymhd.fragment.ui.IndicatorFragmentActivity2.TabInfo;
import com.ymhd.mifen.order.quanbu;

import android.os.Bundle;

public class friendlytab extends IndicatorFragmentActivity2{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself_friendly_tab);
        start();
    }
	@Override
	protected int supplyTabs(List<TabInfo> tabs) {
		// TODO Auto-generated method stub
		tabs.add(new TabInfo(0, getString(R.string.friendlist), false,friendlylist.class,0));
		tabs.add(new TabInfo(1, getString(R.string.friendgroup), false,friendly.class,0));

		return 0;
	}

}
