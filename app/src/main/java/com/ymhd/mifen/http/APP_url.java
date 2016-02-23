package com.ymhd.mifen.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

import com.ymhd.mifei.tool.DataUri;

import android.util.Log;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * #{TODO} <一个用于连接服务器的工具类>
 * 
 * @author ljw
 *
 */
public class APP_url {

	public static String APP_URL = "http://api.mefans.hk/";
	public static String token_path = APP_URL + "token";
	public static String Login_path = APP_URL + "/V1/member/login";
	public static String area_get = APP_URL + "/V1/area";
	public static String signup_username = APP_URL + "V1/member/register/username";
	public static String signup_tellphone = APP_URL + "V1/member/register/cellphone";
	public static String ad_get = APP_URL + "/V1/ad/index";
	public static String SMS_get = APP_URL + "/V1/sms";
	public static String SMS_verify = APP_URL + "/V1/sms/verify";
	public static String member_info = APP_URL + "/V1/member/info";
	public static String shopcar_get = APP_URL + "/V1/cart";

	/**
	 * ${TODO} <获取token>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject token(String appid, String appSelect, String now)
			throws ParseException, IOException, JSONException {

		Log.e("URL", token_path);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credentials");
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPost request = new HttpPost(token_path);
		request.addHeader("Authorization", "basic " + headrtoken(appid, appSelect, now).replace("\n", ""));
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = EntityUtils.toString(httpResponse.getEntity());
		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <用户名注册>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject signup_username(String bearertoken, String username, String password, String confirm,
			String remId, String reComId) throws ParseException, IOException, JSONException {

		Log.e("URL", signup_username);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", username);
		params.put("password", password);
		params.put("confirm", confirm);
		params.put("remId", remId);
		params.put("reComId", reComId);
		params.put("from", "" + 2);
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPost request = new HttpPost(signup_username);
		request.addHeader("Authorization", "bearer " + bearertoken);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = EntityUtils.toString(httpResponse.getEntity());
		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <手机注册>
	 * 如果remid和reComId不存在,则保存空字节,不能乱填参数
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject signup_tellphone(String bearertoken, String tellphone, String imei, String password,
			String confirm, String remId, String reComId) throws ParseException, IOException, JSONException {

		Log.e("URL", signup_tellphone);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("cellphone", tellphone);
		params.put("imei", imei);
		params.put("password", password);
		params.put("confirm", confirm);
		params.put("remId", remId);
		params.put("reComId", reComId);
		params.put("from", "" + 2);
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPost request = new HttpPost(signup_tellphone);
		request.addHeader("Authorization", "bearer " + bearertoken);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = EntityUtils.toString(httpResponse.getEntity());
		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <登陆验证>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject Login(String bearertoken, String username, String password)
			throws ParseException, IOException, JSONException {

		Log.e("URL", Login_path);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		params.put("from", "" + 2);
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPost request = new HttpPost(Login_path);
		request.addHeader("Authorization", "bearer " + bearertoken);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		result2 = EntityUtils.toString(httpResponse.getEntity());
		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <token过时，重新申请认证，反参与login一致>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject refreh_token(String appid, String appSelect, String now, String refresh_token)
			throws ParseException, IOException, JSONException {

		Log.e("URL", token_path);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "refresh_token");
		params.put("refresh_token", refresh_token);
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPost request = new HttpPost(token_path);
		request.addHeader("Authorization", "basic " + headrtoken(appid, appSelect, now).replace("\n", ""));
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		result2 = EntityUtils.toString(httpResponse.getEntity());

		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <获取地域>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject area(String token) throws ParseException, IOException, JSONException {

		Log.e("URL", area_get);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(area_get);
		request.addHeader("Authorization", "bearer " + token);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		if (code == 200) {
			result2 = EntityUtils.toString(httpResponse.getEntity());
		} else {
			return null;
		}

		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <获取广告>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject getAD(String token) throws ParseException, IOException, JSONException {

		Log.e("URL", ad_get);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(ad_get);
		request.addHeader("Authorization", "bearer " + token);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		if (code == 200) {
			result2 = EntityUtils.toString(httpResponse.getEntity());
		} else {
			return null;
		}

		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <获取购物车列表>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject getShopcar(String token) throws ParseException, IOException, JSONException {

		Log.e("URL", shopcar_get);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(shopcar_get);
		request.addHeader("Authorization", "bearer " + token);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		result2 = EntityUtils.toString(httpResponse.getEntity());
		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <获取短信>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject getSMS(String token, String cellphone) throws ParseException, IOException, JSONException {

		Log.e("URL", SMS_get);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("cellPhone", cellphone);
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPost request = new HttpPost(SMS_get);
		request.addHeader("Authorization", "bearer " + token);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		result2 = EntityUtils.toString(httpResponse.getEntity());

		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <获取用户扩展信息>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject getMemberinfo(String login_token) throws ParseException, IOException, JSONException {

		Log.e("URL", member_info);

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(member_info);
		request.addHeader("Authorization", "bearer " + login_token);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		result2 = EntityUtils.toString(httpResponse.getEntity());

		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	/**
	 * ${TODO} <验证短信>
	 * 
	 * @return JSONObject
	 * @throws ParseException
	 * @throws IOException
	 * @throws JSONException
	 */
	public JSONObject verifySMS(String token, String cellphone, String code_y)
			throws ParseException, IOException, JSONException {

		Log.e("URL", SMS_verify);

		HttpClient httpClient = new DefaultHttpClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("verifyCode", code_y);
		params.put("cellphone", cellphone);
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		HttpPut request = new HttpPut(SMS_verify);
		request.addHeader("Authorization", "bearer " + token);
		request.addHeader("Content-Type", "application/x-www-form-urlencoded");
		request.setEntity(new StringEntity(sb.toString()));
		HttpResponse httpResponse = httpClient.execute(request);
		int code = httpResponse.getStatusLine().getStatusCode();
		String result2 = null;
		result2 = EntityUtils.toString(httpResponse.getEntity());

		Log.e("=============", result2);
		new JSONObject();
		JSONObject result1 = JSONObject.fromObject(result2);
		return result1;
	}

	public String headrtoken(String appid, String appSelect, String now) {
		String str = DataUri.base64(appid + ":" + now) + ":" + DataUri.sha256(appid, appSelect, now);
		String str2 = DataUri.base64(str.replace("\n", ""));
		return str2;
	}

}
