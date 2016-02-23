package com.ymhd.mifei.sign;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.mifen.R;
import com.ymhd.mifei.tool.SendSmsVerifyUtils;
import com.ymhd.mifei.user.User;
import com.ymhd.mifen.http.APP_url;
import com.ymhd.mifen.http.AsyncCallback;
import com.ymhd.mifen.http.HttpManager;
import com.ymhd.mifen.http.UICallback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class signup extends Fragment {
    private View view, view2;
    private LinearLayout lin;
    private TextView getSMS;
    private Button onekey_button;
    private EditText signup_onekey_ed, yanzhengma_ed, password_ed, confirm_ed;
    private String code, password, confirm, imei;
    private SmsObserver mObserver;
    private Uri SMS_INBOX = Uri.parse("content://sms/inbox");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        view = inflater.inflate(R.layout.signup, container, false);
        view2 = inflater.inflate(R.layout.signupforonekey, container, false);
        lin = (LinearLayout) view.findViewById(R.id.signuplin);
        lin.addView(view2);
        initid();
        init();
        return view;

    }

    public void initid() {
        onekey_button = (Button) view2.findViewById(R.id.onekey_button);
        signup_onekey_ed = (EditText) view2.findViewById(R.id.onkeysign_tellphone);
        getSMS = (TextView) view2.findViewById(R.id.getSMS);
        yanzhengma_ed = (EditText) view2.findViewById(R.id.yanzhengma_ed);
        password_ed = (EditText) view2.findViewById(R.id.password_ed);
        confirm_ed = (EditText) view2.findViewById(R.id.confirm_ed);


    }

    public void init() {

        onekey_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = yanzhengma_ed.getText().toString();
                String tellphone = signup_onekey_ed.getText().toString();
                password = password_ed.getText().toString();
                confirm = confirm_ed.getText().toString();
                if (str.equals("") || str.toString() == null) {
                    Log.e("111", "yanzhengma not be null");
                } else {
                    Log.e("111", "yanzhengma : " + str);
                    User user = User.getDefaultUser();
                    user.setCellPhone(tellphone);
                    user.setVerifyCode(str);
                    HttpManager.verifySms(user,smsVerifyUiCallback);
                }
            }
        });

        getSMS.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String tellphone = signup_onekey_ed.getText().toString();
                if (tellphone.equals("") || tellphone == null) {

                } else {
                    User user = new User();
                    user.setCellPhone(tellphone);
                    HttpManager.requestSms(user, UICallback.getDefaultCallback());
                    new SendSmsVerifyUtils(getSMS).execute();
                }
            }
        });
        mObserver = new SmsObserver(Handler);
        getActivity().getContentResolver().registerContentObserver(SMS_INBOX, true, mObserver);
    }
    private UICallback smsVerifyUiCallback = new UICallback(){
        @Override
        public void onSuccess(String result) {
            super.onSuccess(result);
            if(requestIsSuccess(result)){
                startRegisterNewUser();
            }
        }
    };

    private void startRegisterNewUser(){

        String tellphone = signup_onekey_ed.getText().toString();
        password = password_ed.getText().toString();
        confirm = confirm_ed.getText().toString();
        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        imei = tm.getDeviceId();

        User user = new User();
        user.setCellPhone(tellphone);
        user.setPassword(password);
        user.setConfirm(confirm);
        user.setImei(imei);
        user.setFrom("2");

        HttpManager.registerNewUser(user,new UICallback());

    }
    class SmsObserver extends ContentObserver {
        private Handler mHandler;

        public SmsObserver(Handler handler) {
            super(handler);
            mHandler = handler;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Uri inboxUri = Uri.parse("content://sms/inbox");
            Cursor cursor = getActivity().getContentResolver().query(inboxUri, null, null, null, "date desc");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String address = cursor.getString(cursor.getColumnIndex("address"));
                    String body = cursor.getString(cursor.getColumnIndex("body"));
                    Log.e("DEBUG", "发件人为：" + address + " " + "短信内容为：" + body);
                    Pattern pattern = Pattern.compile("(\\d{6})");// 正则表达式
                    Matcher matcher = pattern.matcher(body);
                    if (matcher.find()) {
                        String code = matcher.group(0);
                        Log.d("DEBUG", "code is" + code);
                        mHandler.obtainMessage(1, code).sendToTarget();

                    }

                }
            }
        }

    }

    private Handler Handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                code = (String) msg.obj;
                yanzhengma_ed.setText(code);
            }
        }

        ;
    };
}
