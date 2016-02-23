package com.ymhd.main;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ymhd.mifei.user.ShareManager;
import com.ymhd.mifei.user.User;

import org.xutils.x;

import java.util.Map;

/**
 * Created by Administrator on 2016/2/17.
 */
public class MyApplication extends Application {
    private  static String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initXhttp();
        registerShareListener();
    }
    protected  void  initXhttp(){
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
    private static MyApplication instance;

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            Map map = sharedPreferences.getAll();
            if (map == null) return;
            Log.i(TAG, "onSharedPreferenceChanged key == " + s + " ;; value == " + map.get(s).toString());
            if(s.equals("access_token")){
                User user = new User();
                user.setExpires_in_change_time(System.currentTimeMillis()+"");
                user.saveFieldInShare();
            }
        }
    };
    private void registerShareListener() {
        SharedPreferences userSharedPreferences = getSharedPreferences(ShareManager.FILE_USER, Context.MODE_PRIVATE);
        userSharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
    }
}
