/*
 * @author cjj
 * @time 2016-1-28
 */
package com.ymhd.mifei.tool;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Base64;
import android.util.Log;

public class DataUri {
	public static String token = "";

	public static String data_now()
	{
		long currentTime=System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(currentTime);
		sb.delete(10, 13);
		return sb.toString();
	}
	// base64加密
	public static String base64(String str_) {
		// 加密传入的数据是byte类型的，并非使用decode方法将原始数据转二进制，String类型的数据 使用 str.getBytes()即可
		String str = str_;
		// 在这里使用的是encode方式，返回的是byte类型加密数据，可使用new String转为String类型
		String strBase64 = new String(Base64.encode(str.getBytes(), Base64.DEFAULT));

		// 这里 encodeToString 则直接将返回String类型的加密数据
		String enToStr = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
		Log.e("Test", "encode >>>" + str_);
		Log.e("Test", "encode >>>" + strBase64);
		return strBase64;
	}
//sha256加密
	public static String sha256(String appId, String appSelect, String now) {
		String encrypting = String.format("clientid=%s&clientsecret=%s&timestamp=%s", appId, appSelect, now);
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			md.update(encrypting.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = md.digest();
		String encrypted = String.format("%064x", new BigInteger(1, bytes));
		Log.e("Test", "encode >>>" + encrypted);
		return encrypted;
	}
}
