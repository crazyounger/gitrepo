package com.certusnet.client.api;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.certusnet.client.FWConfig;

public class FWRequest {
	private RequestListener requestListener;
	private HttpGet postRequest;
	private HttpContext httpContext;
	private FWConfig config;
	private Context context;
	private String requestURL = null;

	public FWRequest(RequestListener wlRequestListener,
			HttpContext httpContext, FWConfig wlConfig, Context context) {
		this.requestListener = wlRequestListener;
		this.httpContext = httpContext;
		this.config = wlConfig;
		this.context = context;
	}

	public void makeRequest(String requestPath) {
		makeRequest(requestPath, false);
	}

	public void makeRequest(String requestPath, boolean isFullPath) {
		this.requestURL = null;
		if (!isFullPath)
			this.requestURL = new StringBuilder()
					.append(this.config.getAppBaseURL())
					.append(URLEncoder.encode(requestPath)).toString();
		else {
			this.requestURL = new StringBuilder()
					.append(this.config.getAppBaseURL()).append("/")
					.append(requestPath).toString();
		}

		sendRequest(this.requestURL);
	}

	private void sendRequest(String requestURL) {
		this.postRequest = new HttpGet(requestURL);
		try {
			postRequest.addHeader("Accept", "text/plain");
			postRequest.getParams().setParameter(ClientPNames.COOKIE_POLICY,
					CookiePolicy.BROWSER_COMPATIBILITY);
			AsyncSend.getInstance().sendRequestAsync(this);
		} catch (Exception e) {
			e.printStackTrace();
			getRequestListener().onFailure(HttpStatusCode.UNEXPECTED_ERROR, "发生异常，请重试");
		}
	}

	public RequestListener getRequestListener() {
		return requestListener;
	}

	public void requestFinished(HttpResponse response) {
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			try {
				getRequestListener().onSuccess(
						EntityUtils.toString(response.getEntity(), HTTP.UTF_8));
			} catch (ParseException e) {
				e.printStackTrace();
				getRequestListener().onFailure(HttpStatusCode.PARSE_ERROR, "解析失败");
			} catch (IOException e) {
				e.printStackTrace();
				getRequestListener().onFailure(HttpStatusCode.UNEXPECTED_ERROR, "发生异常，请重试");
			}
		} else {
			if (response.getStatusLine().getStatusCode() >= 500)
				getRequestListener().onFailure(response.getStatusLine().getStatusCode(), "当前服务无效");
			else if (response.getStatusLine().getStatusCode() >= 408)
				getRequestListener().onFailure(response.getStatusLine().getStatusCode(), "请求超时");
			else if (response.getStatusLine().getStatusCode() >= 404)
				getRequestListener().onFailure(response.getStatusLine().getStatusCode(), "服务不存在");
			else
				getRequestListener().onFailure(response.getStatusLine().getStatusCode(), "发生异常，请重试");
		}
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}

	public HttpUriRequest getGetRequest() {
		return postRequest;
	}

	public FWConfig getConfig() {
		return config;
	}

}
