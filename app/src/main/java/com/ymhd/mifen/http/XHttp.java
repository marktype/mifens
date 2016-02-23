
package com.ymhd.mifen.http;

import android.text.style.ReplacementSpan;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
public class XHttp {
    private static final String TAG = "XHttp";
    private static final int timeOut = 45000;

    private static void logParams(RequestParams params) {

        Log.i(TAG, params.getUri());
        List<KeyValue> list = params.getBodyParams();
        for (KeyValue keyValue : list) {
            Log.i(TAG, keyValue.toString());
        }
        String content = params.getBodyContent();
        if (content != null && !content.equals("")) {
            Log.i(TAG, "jsonParams == " + content);
        }
    }

    public static void POST(RequestParams params, AsyncCallback callback) {
        try {
            callback.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (params.getUri().equals("error")) {
            Log.i(TAG, "wait for request http ");
            return;
        }
        params.setConnectTimeout(timeOut);
        x.http().post(params, callback);
        logParams(params);
    }

    public static void PUT(RequestParams params, AsyncCallback callback) {
        try {
            callback.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (params.getUri().equals("error")) {
            Log.i(TAG, "wait for request http ");
            return;
        }
        params.setConnectTimeout(timeOut);
        x.http().request(HttpMethod.PUT, params, callback);
        logParams(params);
    }
}
