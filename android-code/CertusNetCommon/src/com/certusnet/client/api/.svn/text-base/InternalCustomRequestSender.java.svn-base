package com.certusnet.client.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.certusnet.client.FWClient;
import com.certusnet.client.MobileCookieManager;

public class InternalCustomRequestSender implements Runnable, HttpStatusCode {
	RequestListener listener;
	int requestTimeoutInMilliseconds;
	HttpRequestBase httpRequest;

	public InternalCustomRequestSender(HttpRequestBase httpRequest, int requestTimeoutInMilliseconds,
			RequestListener listener) {
		this.httpRequest = httpRequest;
		this.requestTimeoutInMilliseconds = requestTimeoutInMilliseconds;
		this.listener = listener;
	}

	@Override
	public void run() {

		FWClient client = FWClient.getInstance();
		HttpClient httpClient = AsyncSend.getHttpClient();

		if (this.requestTimeoutInMilliseconds > 0) {
			HttpConnectionParams.setSoTimeout(httpClient.getParams(), this.requestTimeoutInMilliseconds);
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), this.requestTimeoutInMilliseconds);
		}

		String response = null;
		try {
			// Log.e("message","url="+ this.httpRequest.getURI().toString());
			MobileCookieManager.addCookies(this.httpRequest);
			HttpResponse httpResponse = httpClient.execute(this.httpRequest, client.getHttpContext());
			MobileCookieManager.handleResponseHeaders(httpResponse.getAllHeaders(), httpRequest.getURI());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatusCode.SC_OK) {

				Header res = httpResponse.getFirstHeader("Content-Encoding");
				if (null!=res&&null != res.getValue() && res.getValue().contains("gzip")) {
					HttpEntity entity = httpResponse.getEntity();
					response =deocdeGZipEntity(entity, HTTP.UTF_8) ;
				}else{
					response = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
				}
			} else {
				this.listener.onFailure(httpResponse.getStatusLine().getStatusCode(), this.httpRequest.getURI()
						.toString());
				return;
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			this.listener.onFailure(REQUEST_TIMEOUT, this.httpRequest.getURI().toString());
			return;
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			this.listener.onFailure(UNRESPONSIVE_HOST, this.httpRequest.getURI().toString());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			this.listener.onFailure(UNEXPECTED_ERROR, this.httpRequest.getURI().toString());
			return;
		}
		this.listener.onSuccess(response);
	}

	public static String deocdeGZipEntity(HttpEntity entity, String defaultCharset) throws IOException {
		InputStream input = entity.getContent();
		DataInputStream dataInputStream = new DataInputStream(input);
		byte[] orgialBytes = new byte[(int) (entity).getContentLength()];
		dataInputStream.readFully(orgialBytes);
		ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(orgialBytes);
		GZIPInputStream gzip = new GZIPInputStream(localByteArrayInputStream);
		byte[] buffer = new byte[4096];
		ByteArrayOutputStream output = new ByteArrayOutputStream(orgialBytes.length * 2);
		int i = 0;
		while ((i = gzip.read(buffer)) != -1)
			output.write(buffer, 0, i);
		return  new String(output.toByteArray(),defaultCharset) ;
	}
}
