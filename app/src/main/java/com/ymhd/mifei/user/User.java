package com.ymhd.mifei.user;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/2/17.
 */
public class User {
    /**
     * 这个user不会存值在本地
     */
    private static User defaultUser;

    public static User getDefaultUser() {
        if (defaultUser == null) {
            defaultUser = new User();
        }
        return defaultUser;
    }

    public String getStatus() {
        return status;
    }

    private String status;
    private String access_token;
    private String token_type;
    private String expires_in;
    private String expires_in_change_time;

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRemId() {
        return remId;
    }

    public void setRemId(String remId) {
        this.remId = remId;
    }

    public String getReComId() {
        return reComId;
    }

    public void setReComId(String reComId) {
        this.reComId = reComId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    // 注册参数
    private String cellPhone;
    private String imei;
    private String password;
    private String confirm;
    private String remId;
    private String reComId;
    private String from;


    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    //验证验证码 verifyCode & cellPhone
    private String verifyCode;

    public String getExpires_in_change_time() {
        if (expires_in_change_time != null) return expires_in_change_time;
        return ShareManager.getUserAnyString("expires_in_change_time");
    }

    public void setExpires_in_change_time(String expires_in_change_time) {
        this.expires_in_change_time = expires_in_change_time;
    }

    public String getAccess_token() {
        if (access_token != null) return access_token;
        return ShareManager.getUserAnyString("access_token");
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        if (token_type != null) return token_type;
        return ShareManager.getUserAnyString("token_type");
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        if (expires_in != null) return expires_in;
        return ShareManager.getUserAnyString("expires_in");
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public void saveFieldInShare() {
        if(defaultUser != null && this == defaultUser){
            Log.e("error","this user not save in share;; save user error");
        }
        Class c = getClass();
        Field fields[] = c.getDeclaredFields();
        if (fields == null || fields.length == 0) return;
        Gson gson = new Gson();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                String key = f.getName();
                Object object = f.get(this);
                if (object == null || object == defaultUser) continue;
                String value;
                if (object instanceof String) value = object.toString();
                else value = gson.toJson(object);
                ShareManager.saveUserAnyString(key, value);
            } catch (Exception e) {
                continue;
            }
        }
    }

}
