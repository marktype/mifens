package com.ymhd.mifei.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.ymhd.main.MyApplication;
import com.ymhd.mifei.annotation.NotNull;

/**
 * Created by Administrator on 2016/2/17.
 */
public class ShareManager {
    public final static String FILE_USER = "mifei.com.user";
    protected static SharedPreferences getUserSharePreferences() {
        return MyApplication.getInstance().getSharedPreferences(FILE_USER, Context.MODE_PRIVATE);
    }
    public static void saveUserAnyString(@NotNull String key, @NotNull String value) {
        if (value == null) return;
        getUserSharePreferences().edit().putString(key, value).apply();
    }

    public static String getUserAnyString(@NotNull String key) {
        return getUserSharePreferences().getString(key, null);
    }
}
