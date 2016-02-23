package com.utils;

import android.util.Log;


/**
 * Created by Administrator on 2016/1/23.
 */
public class ExceptionUtils {
    private static String TAG = "ExceptionUtils";

    public static String getExceptionMessage(Throwable e) {
        if (e == null) return null;
        StringBuilder builder = new StringBuilder();
        builder.append("cause by:")
                .append(e.getCause() == null ? "null" : e.getCause())
                .append("\n");
        builder.append("error message:")
                .append(e.getMessage() == null ? "null" : e.getMessage())
                .append("\n");
        StackTraceElement[] stacks = e.getStackTrace();
        for (StackTraceElement stack : stacks) {
            builder.append(stack.toString())
                    .append("\n");
        }
        Log.e(TAG, "hello exception == " + builder.toString());
        return builder.toString();
    }
}
