package com.ymhd.mifei.tool;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/12/21.
 */
public class SendSmsVerifyUtils extends AsyncTask<Void, Integer, Void> {
    public int times = 61;
    private int currentTime;
    private TextView targetView;

    public SendSmsVerifyUtils(TextView tv) {
        this.targetView = tv;
        currentTime = times;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (currentTime > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
            publishProgress(--currentTime);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        targetView.setText(values[0] + "s后重发");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        targetView.setText("重新获取验证码");
        targetView.setEnabled(true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        targetView.setEnabled(false);
    }
}
