package com.certusnet.common.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class HttpManager {
	private static final ExecutorService pool = Executors.newFixedThreadPool(6);
	public static final int timeout = 10000;

	public static synchronized void get(final String url,
			final RequestListener requestListener) {
		pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					HttpParams params = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(params, timeout);
					HttpConnectionParams.setSoTimeout(params, timeout);
					HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
					HttpProtocolParams.setContentCharset(params, "UTF-8");
					HttpGet get = new HttpGet(url);
					get.addHeader("Accept", "text/plain");
					get.getParams().setParameter(ClientPNames.COOKIE_POLICY,
							CookiePolicy.BROWSER_COMPATIBILITY);
					HttpCookieManager.addCookies(get);
					HttpResponse response = new DefaultHttpClient(params)
							.execute(get);
					HttpCookieManager.handleResponseHeaders(
							response.getAllHeaders(), get.getURI());
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String json = EntityUtils.toString(response.getEntity());
						if (requestListener != null)
							requestListener.onComplete(json);
					} else {
						if (requestListener != null)
							requestListener.onError(response.getStatusLine()
									.getStatusCode(), response.getStatusLine()
									.getReasonPhrase());
					}
				} catch (ClientProtocolException e) {
					if (requestListener != null)
						requestListener.onError(e);
				} catch (IOException e) {
					if (requestListener != null)
						requestListener.onError(e);
				}
			}
		});

	}

	public static synchronized void post(final String url,
			final String requestData, final RequestListener requestListener) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					HttpParams params = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(params, timeout);
					HttpConnectionParams.setSoTimeout(params, timeout);
					HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
					HttpProtocolParams.setContentCharset(params, "UTF-8");
					HttpPost post = new HttpPost(url);
					post.addHeader("Accept", "text/plain");
					post.getParams().setParameter(ClientPNames.COOKIE_POLICY,
							CookiePolicy.BROWSER_COMPATIBILITY);
					post.setEntity(new StringEntity(requestData));
					HttpCookieManager.addCookies(post);
					HttpResponse response = new DefaultHttpClient(params)
							.execute(post);
					HttpCookieManager.handleResponseHeaders(
							response.getAllHeaders(), post.getURI());
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String json = EntityUtils.toString(response.getEntity());
						if (requestListener != null)
							requestListener.onComplete(json);
					} else {
						if (requestListener != null)
							requestListener.onError(response.getStatusLine()
									.getStatusCode(), response.getStatusLine()
									.getReasonPhrase());
					}
				} catch (ClientProtocolException e) {
					if (requestListener != null)
						requestListener.onError(e);
				} catch (IOException e) {
					if (requestListener != null)
						requestListener.onError(e);
				}
			}
		});

	}

	public static synchronized void post(final String url,
			final Map<String, String> map, final RequestListener requestListener) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					HttpParams params = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(params, timeout);
					HttpConnectionParams.setSoTimeout(params, timeout);
					HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
					HttpProtocolParams.setContentCharset(params, "UTF-8");
					HttpPost post = new HttpPost(url);
					post.addHeader("Accept", "text/plain");
					post.getParams().setParameter(ClientPNames.COOKIE_POLICY,
							CookiePolicy.BROWSER_COMPATIBILITY);
					Set<Entry<String, String>> set = map.entrySet();
					Iterator<Entry<String, String>> it = set.iterator();
					List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
					while (it.hasNext()) {
						Entry<String, String> entry = it.next();
						parameters.add(new BasicNameValuePair(entry.getKey(),
								entry.getValue()));
					}
					post.setEntity(new UrlEncodedFormEntity(parameters));
					HttpCookieManager.addCookies(post);
					HttpResponse response = new DefaultHttpClient(params)
							.execute(post);
					HttpCookieManager.handleResponseHeaders(
							response.getAllHeaders(), post.getURI());
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String json = EntityUtils.toString(response.getEntity());
						if (requestListener != null)
							requestListener.onComplete(json);
					} else {
						if (requestListener != null)
							requestListener.onError(response.getStatusLine()
									.getStatusCode(), response.getStatusLine()
									.getReasonPhrase());
					}
				} catch (ClientProtocolException e) {
					if (requestListener != null)
						requestListener.onError(e);
				} catch (IOException e) {
					if (requestListener != null)
						requestListener.onError(e);
				}
			}
		});

	}
}
