package com.certusnet.client;

import java.io.Serializable;
import java.net.HttpURLConnection;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import com.certusnet.client.api.MobileRequest;
import com.certusnet.common.util.Constants;

import android.util.Base64;

public class Accesstoken implements Serializable {
	private String access_token;
	private String token_type;
	private String expires_in;
	private String sign_algorithm;
	private String sign_key;
	private String refresh_token;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public String getSign_algorithm() {
		return sign_algorithm;
	}

	public void setSign_algorithm(String sign_algorithm) {
		this.sign_algorithm = sign_algorithm;
	}

	public String getSign_key() {
		return sign_key;
	}

	public void setSign_key(String sign_key) {
		this.sign_key = sign_key;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	/**
	 * 签名
	 * 
	 * @param url
	 * @return
	 */
	private static String sign(Accesstoken accesstoken, String url, long timestamp, int numcode) {
		// HmacSHA1签名
		// For Android
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(accesstoken.getAccess_token());
		stringBuilder.append(timestamp);
		stringBuilder.append(numcode);
		/* stringBuilder.append(url); */
		String sign = hmacSHA1(accesstoken.getSign_key(), stringBuilder.toString());
		return sign;
	}

	private static String hmacSHA1(String key, String content) {
		String result = null;
		try {
			SecretKeySpec kg = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(kg);
			byte[] bytes = mac.doFinal(content.getBytes());
			result = Base64.encodeToString(bytes, Base64.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getAuthorization(Accesstoken accesstoken, String url) {
		/* 时间戳 */
		long timestamp = System.currentTimeMillis() / 1000;
		/* 随机数 */
		int numcode = (int) ((Math.random() * 9 + 1) * 100000);
		/* 消息认证码 */
		String sign = sign(accesstoken, url, timestamp, numcode);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SIGN id=");
		stringBuilder.append("\"\"");
		stringBuilder.append(accesstoken.getAccess_token());
		stringBuilder.append("\"\"");
		stringBuilder.append(",ts=");
		stringBuilder.append("\"\"");
		stringBuilder.append(timestamp);
		stringBuilder.append("\"\"");
		stringBuilder.append(",nonce=");
		stringBuilder.append("\"\"");
		stringBuilder.append(numcode);
		stringBuilder.append("\"\"");
		stringBuilder.append(",ext=");
		stringBuilder.append("\"\"");
		stringBuilder.append("");
		stringBuilder.append("\"\"");
		stringBuilder.append(",sign=");
		stringBuilder.append("\"\"");
		stringBuilder.append(sign);
		stringBuilder.append("\"\"");
		return stringBuilder.toString();
	}

	public static HttpGet addHeader(HttpGet httpRequest, Accesstoken accesstoken, String url) {
		if (null != accesstoken && Constants.AUTH_SWITCH) {
			String authorization = getAuthorization(accesstoken, url);
			httpRequest.addHeader(Constants.AUTHORIZATION, authorization);
		}
		return httpRequest;
	}

	public static HttpPost addHeader(HttpPost httpRequest, Accesstoken accesstoken, String url) {
		if (null != accesstoken && Constants.AUTH_SWITCH) {
			String authorization = getAuthorization(accesstoken, url);
			httpRequest.addHeader(Constants.AUTHORIZATION, authorization);
		}
		return httpRequest;
	}
	public static HttpURLConnection addHeader(HttpURLConnection httpRequest, Accesstoken accesstoken, String url) {
		if (null != accesstoken && Constants.AUTH_SWITCH) {
			String authorization = getAuthorization(accesstoken, url);
			httpRequest.setRequestProperty(Constants.AUTHORIZATION, authorization);
		}
		return httpRequest;
	}

	public static HttpRequestBase addHeader(HttpRequestBase httpRequest, Accesstoken accesstoken, String url) {
		if (null != accesstoken && Constants.AUTH_SWITCH) {
			String authorization = getAuthorization(accesstoken, url);
			httpRequest.addHeader(Constants.AUTHORIZATION, authorization);
		}
		return httpRequest;
	}

	public static MobileRequest addHeader(MobileRequest httpRequest, Accesstoken accesstoken, String url) {
		if (null != accesstoken && Constants.AUTH_SWITCH) {
			String authorization = getAuthorization(accesstoken, url);
			httpRequest.getHttpRequest().addHeader(Constants.AUTHORIZATION, authorization);
		}
		return httpRequest;
	}
}
