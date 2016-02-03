package com.certusnet.client.api;

import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;

import com.certusnet.client.HttpClientFactory;
import com.certusnet.client.MobileCookieManager;

public class InternalRequestSender implements Runnable ,HttpStatusCode{
	FWRequest request;

	public InternalRequestSender(FWRequest request) {
		this.request = request;
	}

	@Override
	public void run() {
		try {
			HttpClient httpClient = HttpClientFactory.getInstance(this.request
					.getConfig());
			setConnectionTimeout(httpClient);
			MobileCookieManager.addCookies(this.request);
			HttpResponse httpResponse = httpClient
					.execute(this.request.getGetRequest(),
							this.request.getHttpContext());
			MobileCookieManager.handleResponseHeaders(httpResponse.getAllHeaders(),
					request.getGetRequest().getURI());
			this.request.requestFinished(httpResponse);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			this.request.getRequestListener().onFailure(REQUEST_TIMEOUT, "请求超时");
			return;
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			this.request.getRequestListener().onFailure(UNRESPONSIVE_HOST, "当前服务无效");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			this.request.getRequestListener().onFailure(UNEXPECTED_ERROR, "发生异常，请重试");
			return;
		}

	}

	private void setConnectionTimeout(HttpClient httpClient) {
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), 30000);
		HttpConnectionParams
				.setConnectionTimeout(httpClient.getParams(), 30000);
	}
}
