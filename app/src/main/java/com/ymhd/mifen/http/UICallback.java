package com.ymhd.mifen.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.ymhd.mifei.user.User;

import org.xutils.common.Callback;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2015/12/4.
 */
public class UICallback extends AsyncCallback {


    private Context context;
    private boolean isShowDialog;
    private List<View> enableViews;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    private Object tag;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isShowDialog() {
        return isShowDialog;
    }

    public void setIsShowDialog(Context context, boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
        this.context = context;
    }


    public List<View> getEnableViews() {
        return enableViews;
    }

    public void addEnableView(View v) {
        if (enableViews == null) {
            enableViews = new ArrayList<>();
        }
        enableViews.add(v);
    }


    @Override
    public void onFinished() {
        super.onFinished();
        setViewEnable(true);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        super.onError(ex, isOnCallback);
        setViewEnable(true);
    }

    @Override
    public void onCancelled(CancelledException cex) {
        super.onCancelled(cex);
        setViewEnable(true);
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        setViewEnable(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        setViewEnable(false);
    }


    protected  boolean requestIsSuccess(String result){
        User user = getGson().fromJson(result,User.class);
        return Boolean.parseBoolean(user.getStatus());
    }

    private void setViewEnable(boolean enable) {
        if (enableViews == null || enableViews.size() == 0) return;
        for (View v : enableViews) {
            v.setEnabled(enable);
        }
    }



    /**
     * this defaultCallback is use in that method dot need any operate.
     * because it is extend {@link AsyncCallback},so I can watch Log.
     */
    private static UICallback defaultCallback;

    public static UICallback getDefaultCallback() {
        if (defaultCallback == null) defaultCallback = new UICallback();
        return defaultCallback;
    }
}
