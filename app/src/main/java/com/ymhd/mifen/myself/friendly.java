package com.ymhd.mifen.myself;
import com.example.mifen.R;
import com.ymhd.mifen.adapter.MyRecyclerViewAdapterforfriendlygroup;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class friendly extends Fragment{
	private View friendlygroup;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		friendlygroup = inflater.inflate(R.layout.myself_friendly, container, false);
		initVertical();
		return friendlygroup;
	}

    public void initVertical(){
        RecyclerView recyclerView = (RecyclerView) friendlygroup.findViewById(R.id.recyclerview_vertical);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        // 创建数据集
        String[] dataset = new String[100];
        for (int i = 0; i < dataset.length; i++){
            dataset[i] = "item" + i;
        }
        // 创建Adapter，并指定数据集
        MyRecyclerViewAdapterforfriendlygroup adapter = new MyRecyclerViewAdapterforfriendlygroup(dataset,getActivity());
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }

  
}
