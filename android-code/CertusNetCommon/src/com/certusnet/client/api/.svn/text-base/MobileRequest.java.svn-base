package com.certusnet.client.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.text.TextUtils;

import com.airshiplay.mobile.util.MobileResource;
import com.certusnet.icity.mobile.secret.Md5Cryptor;

public class MobileRequest {
	static Md5Cryptor md5 = new Md5Cryptor();
	String url;
	String cacheKey;
	ArrayList<NameValuePair> params;
	int cacheTime;
	String method;
	HttpRequestBase requestBase;
	private Context context;

	public MobileRequest(Context context, String request) {
		this(context, request, "get", null, -1);
	}

	public Context getContext() {
		return context;
	}

	public MobileRequest(Context context, String url, String method,
			ArrayList<NameValuePair> params, int time) {
		this.context = context;
		this.url = url;
		this.params = params;
		this.method = method;
		this.cacheTime = time;
	}

	public HttpRequestBase getHttpRequest() {
		if (requestBase != null)
			return requestBase;
		if (method.equalsIgnoreCase("get")) {
			paramConvert(params);
			url = url + "?" + getPartPath4Get(params);
			// cacheKey = url.hashCode() + "";
			return (requestBase = new HttpGet(url));
		} else if (method.equalsIgnoreCase("post")) {
			HttpPost post = new HttpPost(url);
			post.getParams().setParameter(ClientPNames.COOKIE_POLICY,
					CookiePolicy.BROWSER_COMPATIBILITY);
			post.addHeader("Accept", "text/plain");
			if (params != null) {
				UrlEncodedFormEntity urlEntity;
				try {
					params.add(new BasicNameValuePair("call_id", new Date()
							.getTime() + ""));
					urlEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
					urlEntity
							.setContentType("application/x-www-form-urlencoded");
					((HttpPost) post).setEntity(urlEntity);
				} catch (Exception e) {
				}
			}
			return (requestBase = post);
		} else
			return null;
	}

	public String getCacheKey() {
		if (TextUtils.isEmpty(cacheKey)) {
			cacheKey = (url + getPartPath4Get(params)).hashCode() + "";
		}
		return cacheKey;
	}

	public String getURL() {
		return url;
	}

	private void paramConvert(ArrayList<NameValuePair> params) {
		params.add(new BasicNameValuePair("call_id", new Date().getTime() + ""));
		Collections.sort(params, new Comparator<NameValuePair>() {
			@Override
			public int compare(NameValuePair lhs, NameValuePair rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
		});
		StringBuffer sign = new StringBuffer();
		for (NameValuePair nameValuePair : params) {
			sign.append(nameValuePair.getName() + nameValuePair.getValue());
		}
		sign.append(MobileResource.getInstance(context).getString("sign_str"));
		params.add(new BasicNameValuePair("scity_sign", md5.encrypt(
				sign.toString(), null)));
	}

	private static String getPartPath4Get(List<NameValuePair> params) {
		StringBuffer result = new StringBuffer();
		try {
			for (int i = 0; i < params.size(); i++) {
				if (i == params.size() - 1) {
					result.append(params.get(i).getName()
							+ "="
							+ URLEncoder.encode(URLEncoder.encode(params.get(i).getValue()+"",
									"UTF-8"),"UTF-8"));
				} else {
					result.append(params.get(i).getName()
							+ "="
							+ URLEncoder.encode( URLEncoder.encode(params.get(i).getValue()+"",
									"UTF-8"),"UTF-8") + "&");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
