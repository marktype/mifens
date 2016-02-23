
package com.ymhd.mifen.http;

import android.util.Log;

import com.google.gson.Gson;
import com.ymhd.mifei.user.User;
import org.xutils.common.Callback;


/**
 * Created by Administrator on 2015/12/4.
 */
public abstract class AsyncCallback implements Callback.CommonCallback<String> {
    private String TAG = "AsyncCallback";

    public boolean isSave() {
        return isSave;
    }

    public static Gson gson;

    public Gson getGson() {
        if (gson == null) gson = new Gson();
        return gson;
    }

    public void setIsSave(boolean isSave) {
        this.isSave = isSave;
    }

    private boolean isSave = true;

    @Override
    public void onSuccess(String result) {
        Log.i(TAG, "onSuccess result ==" + result);
        if (isSave()) {
            try {
                Gson gson = getGson();
                User user = gson.fromJson(result, User.class);
                user.saveFieldInShare();
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.i(TAG, "onError  ex  == " + ex.getClass().getName() + ";; isOnCallback == " + isOnCallback);

        if (ex.getMessage() != null) {
            Log.i(TAG, "onError  ex message  == " + ex.getMessage());
        }
        if(ex.getCause() != null){
            Log.i(TAG, "onError  ex cause  == " + ex.getCause());
        }

    }

    @Override
    public void onCancelled(CancelledException cex) {
        Log.i(TAG, "onCancelled  ex == " + cex.getCause());
    }

    @Override
    public void onFinished() {
        Log.i(TAG, "onFinished");
    }


    public void onStart() {
    }
}
