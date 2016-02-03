package com.certusnet.client;

import org.apache.http.protocol.BasicHttpContext;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;

import com.certusnet.client.api.AsyncSend;
import com.certusnet.client.api.FWRequest;
import com.certusnet.client.api.RequestListener;

/**
 * @author lig
 * 
 */
public class FWClient {
	private static FWClient wlClient;
	private FWConfig config;
	private Context context;
	private BasicHttpContext httpContext;
	private Accesstoken accessToken;

	public FWConfig getConfig() {
		return this.config;
	}

	public Context getContext() {
		return this.context;
	}

	private FWClient(Context context) {
		this.config = new FWConfig(context);
		this.httpContext = new BasicHttpContext();
		this.context = context;
	}

	public BasicHttpContext getHttpContext() {
		return httpContext;
	}

	/**
	 * 恢复
	 * 
	 * @param savedInstanceState
	 */
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		String cookies = savedInstanceState.getString("CertusnetHttpCookies");
		if (!TextUtils.isEmpty(cookies)) {
			MobileCookieManager.setCookies(cookies, FWClient.getInstance().getConfig().getSSORootURL());
		}
		Accesstoken token = (Accesstoken) savedInstanceState.getSerializable("scity-accesstoken");
		if (token != null) {
			this.accessToken = token;
		}
	}

	/**
	 * 保存
	 * 
	 * @param outState
	 */
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("CertusnetHttpCookies", MobileCookieManager.getHeaderSetCookies());
		outState.putSerializable("scity-accesstoken", accessToken);
	}

	public static FWClient createInstance(Context context) {
		if (wlClient != null) {
			releaseInstance();
		}
		wlClient = new FWClient(context);
		CookieSyncManager.createInstance(context);
		return wlClient;
	}

	public void setAccessToken(Accesstoken accessToken) {
		this.accessToken = accessToken;
	}

	public Accesstoken getAccessToken() {
		return this.accessToken;
	}

	public static FWClient getInstance() {
		if (wlClient == null) {
			throw new RuntimeException(
					"FWClient object has not been created. You must call WLClient.createInstance first.");
		}
		return wlClient;
	}

	private static void releaseInstance() {
		AsyncSend.releaseHttpClient();
		MobileCookieManager.clearCookies();
		wlClient = null;
	}

	public void connect(RequestListener responseListener) {
		MobileCookieManager.clearCookies();
		FWRequest initRequest = new FWRequest(responseListener, this.httpContext, this.config, this.context);
		initRequest.makeRequest("init");
	}

	public void sendInvoke(String partPath, RequestListener responseListener) {
		if (responseListener == null) {
			throw new IllegalArgumentException(
					"Error during invocation of remote procedure, because responseListener parameter can't be null.");
		}
		FWRequest invokeProcedureWLRequest = new FWRequest(responseListener, this.httpContext, this.config,
				this.context);
		invokeProcedureWLRequest.makeRequest(partPath);
	}
}
