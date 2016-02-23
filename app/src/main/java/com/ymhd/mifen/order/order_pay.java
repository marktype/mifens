package com.ymhd.mifen.order;

import com.example.mifen.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;

public class order_pay extends Activity{
	private RadioButton rd_alibab,rd_wechat,rd_banks,rd_bankcar,rd_qqpay;
	private ImageView back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_pay);
		init();
		
	}
	public void init()
	{
		rd_alibab = (RadioButton) findViewById(R.id.alibabapay);
		rd_wechat = (RadioButton) findViewById(R.id.wechatpay);
		rd_bankcar = (RadioButton) findViewById(R.id.bankcar);
		rd_banks = (RadioButton) findViewById(R.id.banks);
		rd_qqpay = (RadioButton) findViewById(R.id.qqpay);
		back = (ImageView) findViewById(R.id.orderpayback);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		rd_alibab.setText(Html
				.fromHtml("<font  size=9sp><big>支付宝</big></font>"+"<br>"
		+"<font color=#6f6f6f size=2sp><small>推荐有支付宝账号的用户使用<small></font>"));
		
		rd_wechat.setText(Html
				.fromHtml("<font  size=9sp><big>微信支付</big></font>"+"<br>"
		+"<font color=#6f6f6f size=2sp><small>推荐使用<small></font>"));
		
		rd_bankcar.setText(Html
				.fromHtml("<font  size=9sp><big>银行卡快捷支付</big></font>"+"<br>"
		+"<font color=#6f6f6f size=2sp><small>储蓄卡，信用卡快捷支付，无需网银<small></font>"));
		
		rd_banks.setText(Html
				.fromHtml("<font  size=9sp><big>银联在线支付</big></font>"+"<br>"
		+"<font color=#6f6f6f size=2sp><small>支持所有储蓄卡，信用卡支付<small></font>"));
		
		rd_qqpay.setText(Html
				.fromHtml("<font  size=9sp><big>微信支付</big></font>"+"<br>"
		+"<font color=#6f6f6f size=2sp><small>建议已安装手机QQ用户使用<small></font>"));
		
	}
	

}
