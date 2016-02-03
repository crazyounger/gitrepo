package com.certusnet.auth;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Properties;

import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.certusnet.common.net.HttpCookieManager;
import com.certusnet.common.net.HttpManager;
import com.certusnet.common.net.RequestListener;
import com.certusnet.icity.mobile.secret.jni.CryptoUtils;

public class Login implements RequestListener {
	public static String baseUrl = "http://sso.smartsh.net:80";

	public static synchronized void authorize(String userName, String password,
			String accountType, ICityAuthListener response, Context context) {
		new Login(userName, password, accountType, response, context)
				.ssoVerify();
	}

	String userName;
	String password;
	String accountType;
	ICityAuthListener loginResponse;
	Context mContext;
	int step;
	private String key;

	private Login(String userName, String password, String accountType,
			ICityAuthListener response, Context context) {
		super();
		this.userName = userName;
		this.password = password;
		this.accountType = accountType;
		this.loginResponse = response;
		this.mContext = context;
		try {
			Properties p = new Properties();
			p.load(mContext.getAssets().open("config.properties"));
			String auth_url = p.getProperty("auth_url");
			if (!TextUtils.isEmpty(auth_url))
				baseUrl = auth_url;
		} catch (IOException e) {
			Log.w("icity auth config", "使用默认认证地址!");
		}
	}

	public void ssoVerify() {
		HttpCookieManager.clearCookies();
		step = 1;
		StringBuffer buffer = new StringBuffer();
		buffer.append(baseUrl)
				.append("/api/auth/ssoVerify")
				.append("/" + 1)
				.append("/" + userName)
				.append("/" + accountType)
				.append("/third-"
						+ CryptoUtils.encrypt(2, mContext.getPackageName(),
								null));
		HttpManager.get(buffer.toString(), this);
	}

	public void sso(String userToken) {
		try {
			step = 2;
			key = password + userToken;
			StringBuffer buffer = new StringBuffer();
			buffer.append(baseUrl).append("/api/auth/sso");
			JSONObject requestData = new JSONObject();
			requestData.put("userAccount", userName);
			requestData.put("userPassword",
					CryptoUtils.encrypt(2, password, null));
			requestData.put("userToken", userToken);
			requestData.put("isCustmised", 0);
			requestData.put("mac", "");
			requestData.put("deviceCode", "");
			requestData.put("resolution", "");
			requestData.put("termType", "phone");
			requestData.put("osType", "android");
			requestData.put("osVersion", Build.VERSION.RELEASE);
			requestData.put("manufacturer", Build.MANUFACTURER);
			HttpManager.post(buffer.toString(),
					CryptoUtils.encrypt(0, requestData.toString(), key), this);
		} catch (JSONException e) {
			loginResponse.onFailure(-1, e.getMessage());
		}
	}

	@Override
	public void onComplete(String reponse) {
		try {
			if (step == 1) {
				JSONObject json = new JSONObject(reponse);
				String code = json.optString("code");
				String userToken = json.optString("userToken");
				if ("0".equals(code)) {
					sso(userToken);
				} else {
					int errorCode = parseInt(code, -1);
					switch (errorCode) {
					case 3303:
						loginResponse.onFailure(errorCode, "用户名不存在");
						break;
					default:
						loginResponse.onFailure(errorCode,
								json.optString("msg"));
					}
				}
			} else if (step == 2) {
				reponse = CryptoUtils.decrypt(0, reponse, key);
				if (TextUtils.isEmpty(reponse)) {
					loginResponse.onFailure(3305, "用户名或密码错误");
					return;
				}
				JSONObject json = new JSONObject(reponse);
				String code = json.optString("code");
				String deviceId = json.optString("deviceId");
				if ("0".equals(code)) {
					Bundle bundle = new Bundle();
					bundle.putString("access_token",
							HttpCookieManager.getCookiesString());
					bundle.putString("deviceId", deviceId);
					bundle.putLong("expires_in", 30 * 60 * 1000);
					bundle.putString("user_name", userName);
					loginResponse.onSuccess(bundle);
				} else {
					loginResponse.onFailure(parseInt(code, -1),
							json.optString("msg"));
				}
			}
		} catch (JSONException e) {
			if (reponse != null && reponse.startsWith("<html>"))
				loginResponse.onFailure(-1, "当前网络无效");
			else
				loginResponse.onFailure(3008, "不能识别的消息格式，请升级SDK版本");
		}
	}

	public int parseInt(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	@Override
	public void onError(int code, String message) {
		loginResponse.onFailure(code, message);
	}

	@Override
	public void onError(Exception e) {
		if (e instanceof ConnectException) {
			loginResponse.onFailure(3004, "服务不可用");
		} else if (e instanceof SocketTimeoutException)
			loginResponse.onFailure(3005, "服务响应超时");
		else if (e instanceof HttpHostConnectException) {
			loginResponse.onFailure(-1, "服务不可用");
		} else {
			loginResponse.onFailure(-1, "服务不可用");
		}
	}
}
