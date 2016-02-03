package com.certusnet.client.api;

import java.net.SocketTimeoutException;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.text.TextUtils;
import android.util.Log;

import com.certusnet.client.FWClient;
import com.certusnet.client.MobileCookieManager;
import com.certusnet.common.util.ACache;

public class InternalMobileRequestSender implements Runnable, HttpStatusCode {
	RequestListener listener;
	int requestTimeoutInMilliseconds;
	MobileRequest request;

	public InternalMobileRequestSender(MobileRequest httpRequest,
			int requestTimeoutInMilliseconds, RequestListener listener) {
		this.request = httpRequest;
		this.requestTimeoutInMilliseconds = requestTimeoutInMilliseconds;
		this.listener = listener;
	}

	@Override
	public void run() {

		FWClient client = FWClient.getInstance();
		HttpClient httpClient = AsyncSend.getHttpClient();

		if (this.requestTimeoutInMilliseconds > 0) {
			HttpConnectionParams.setSoTimeout(httpClient.getParams(),
					this.requestTimeoutInMilliseconds);
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
					this.requestTimeoutInMilliseconds);
		}
		String	response=null;
		try {
//		Log.e("message","url="+ this.request.getHttpRequest().getURI().toString());
			MobileCookieManager.addCookies(this.request.getHttpRequest());
			HttpResponse httpResponse = httpClient.execute(
					this.request.getHttpRequest(), client.getHttpContext());
			MobileCookieManager.handleResponseHeaders(httpResponse
					.getAllHeaders(), request.getHttpRequest().getURI());
//			Log.d("requesturl", this.request.getURL());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatusCode.SC_OK) {
				response = EntityUtils.toString(httpResponse.getEntity(),
						HTTP.UTF_8);
				this.listener.onCache(request.getContext(),request.getCacheKey(), request.cacheTime*ACache.TIME_HOUR);
//				if(response!=null&&(response.contains("\"code\":\"0\"")||response.contains("\"code\":0")))
//				ACache.get(request.getContext()).put(request.getCacheKey(), response,request.cacheTime*ACache.TIME_HOUR);
			} else {
				this.listener.onFailure(httpResponse.getStatusLine()
						.getStatusCode(), this.request.getURL());
				return;
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			this.listener.onFailure(REQUEST_TIMEOUT, this.request.getURL());
			return;
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			this.listener.onFailure(UNRESPONSIVE_HOST, this.request.getURL());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			this.listener.onFailure(UNEXPECTED_ERROR, this.request.getURL());
			return;
		}
		this.listener.onSuccess(response);
	}

}
