package com.ymhd.mifen.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.ymhd.mifei.user.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xutils.common.util.KeyValue;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;

/**
 * Created by Administrator on 2016/2/17.
 */
public class HttpRequestToken {
    private static final String TAG ="HttpRequestToken" ;
    private static Handler handler;

    private static Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    public HttpRequestToken(RequestParams params, AsyncCallback callback) {
        new Thread(new TokenRunnable(params, callback)).start();
    }

    private class TokenRunnable implements Runnable {
        final RequestParams params;
        final AsyncCallback callback;

        public TokenRunnable(RequestParams params, AsyncCallback callback) {
            this.params = params;
            this.callback = callback;
        }

        @Override
        public void run() {
            requestToken(params, callback);
        }
    }
    private static boolean isNeedRefreshToken() {
        String token = User.getDefaultUser().getAccess_token();
        String expires_in = User.getDefaultUser().getExpires_in();
        if (token == null || expires_in == null) {
            return true;
        }
        String expires_in_change = User.getDefaultUser().getExpires_in_change_time();
        try {
            return System.currentTimeMillis() - Long.parseLong(expires_in_change) >=
                    Long.parseLong(expires_in);
        } catch (Exception e) {
            return true;
        }

    }
    private synchronized void requestToken(final RequestParams params, final AsyncCallback callback) {
        if(!isNeedRefreshToken()){
            Log.i(TAG,"token maybe has refresh");
            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess("token maybe has refresh");
                }
            });
        }

        HttpClient httpClient = new DefaultHttpClient();
        final HttpPost request = new HttpPost(params.getUri());
        for (RequestParams.Header header : params.getHeaders()) {
            request.addHeader(header.key, header.getValueStr());
        }
        StringBuilder sb = new StringBuilder();
        for (KeyValue keyValue : params.getBodyParams()) {
            sb.append(keyValue.key).append("=");
            sb.append(keyValue.value);
            sb.append("&");
        }
        for (KeyValue keyValue : params.getQueryStringParams()) {
            sb.append(keyValue.key).append("=");
            sb.append(keyValue.value);
            sb.append("&");
        }
        String content = params.getBodyContent();
        if (content != null) {
            sb.append(content);
        }
        sb.deleteCharAt(sb.length() - 1);

        try {
            request.setEntity(new StringEntity(sb.toString()));
            HttpResponse httpResponse = httpClient.execute(request);
            final int code = httpResponse.getStatusLine().getStatusCode();
            final String result = EntityUtils.toString(httpResponse.getEntity());
            if (code == 200) {
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });
                Gson gson = new Gson();
                User user = gson.fromJson(result, User.class);
                user.saveFieldInShare();
            }else{
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(new HttpException(code,result),false);
                    }
                });
            }

        } catch (final Exception e) {
            e.printStackTrace();
            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    callback.onError(e, true);
                }
            });
        } finally {
            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    callback.onFinished();
                }
            });
        }

    }
}
