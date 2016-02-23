package com.ymhd.mifen.http;

import android.util.Log;

import com.google.gson.Gson;
import com.utils.ExceptionUtils;
import com.ymhd.mifei.tool.DataUri;
import com.ymhd.mifei.user.User;

import org.xutils.http.RequestParams;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/2/17.
 */
public class HttpManager {
    private static String TAG = "HttpManager";

    private static String headrtoken(String appid, String appSelect, String now) {
        String str = DataUri.base64(appid + ":" + now) + ":" + DataUri.sha256(appid, appSelect, now);
        String str2 = DataUri.base64(str.replace("\n", ""));
        return str2;
    }

    private static Gson gson;

    private static String getJsonString(User user) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.toJson(user);
    }

    public static void requestToken(final Object... objects) {
        Log.i(TAG, "now start request system token!");
        RequestParams params = new RequestParams(APP_url.token_path);
        params.addHeader("Authorization", "basic " + headrtoken("app.android", "app.android.key", DataUri.data_now()).replace("\n", ""));
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
        params.addBodyParameter("grant_type", "client_credentials");
        Throwable throwable = new Throwable();
        StackTraceElement stackTraceElement[] = throwable.getStackTrace();
        String methodName = null;
        for (StackTraceElement stack : stackTraceElement) {
            if (stack.getClassName().equals(HttpManager.class.getName())) {
                if (stack.getMethodName().equals("getJsonString")
                        || stack.getMethodName().equals("headrtoken")
                        || stack.getMethodName().equals("requestToken")
                        || stack.getMethodName().equals("invokeHttpRequestAfterRefreshToken")
                        || stack.getMethodName().equals("getRequestParams")
                        || stack.getMethodName().equals("isNeedRefreshToken")) {
                    continue;
                } else {
                    methodName = stack.getMethodName();
                    break;
                }
            }
        }
        final String finalMethodName = methodName;
        new HttpRequestToken(params, new UICallback() {
            @Override
            public void onFinished() {
                super.onFinished();
                invokeHttpRequestAfterRefreshToken(finalMethodName, objects);
            }
        });
    }

    private static void invokeHttpRequestAfterRefreshToken(String methodName, Object... objects) {

        if (methodName == null || methodName.isEmpty()) {
            return;
        }

        try {
            Method method = null;
            Class cla = HttpManager.class;
            Method methods[] = cla.getDeclaredMethods();
            if (methods == null || methods.length == 0) return;
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    method = m;
                    break;
                }
            }
            if(method == null)return;
            method.setAccessible(true);
            method.invoke(null, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static RequestParams getRequestParams(String url, Object... objects) {

        if (isNeedRefreshToken()) {
            Log.i(TAG, "token has out of date,now to refresh ,when == " + System.currentTimeMillis());
            requestToken(objects);
            return new RequestParams("error");
        }
        RequestParams params = new RequestParams(url);
        params.addHeader("Authorization", "bearer " + User.getDefaultUser().getAccess_token());
        params.addHeader("Content-Type", "application/x-www-form-urlencoded");
        return params;
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

    public static void registerNewUser(User user, AsyncCallback callback) {
        RequestParams params = getRequestParams(APP_url.signup_tellphone, user, callback);
        params.setBodyContent(getJsonString(user));
        XHttp.POST(params, callback);
    }

    public static void requestSms(User user, AsyncCallback callback) {
        RequestParams params = getRequestParams(APP_url.SMS_get, user, callback);
        params.setBodyContent(getJsonString(user));
        XHttp.POST(params, callback);
    }

    public static void verifySms(User user, AsyncCallback callback) {
        RequestParams params = getRequestParams(APP_url.SMS_verify, user, callback);
        params.setBodyContent(getJsonString(user));
        XHttp.PUT(params, callback);
    }
}
