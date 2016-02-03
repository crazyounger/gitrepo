package com.certusnet.client.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.text.TextUtils;

import com.airshiplay.mobile.log.Logger;
import com.airshiplay.mobile.log.LoggerFactory;
import com.certusnet.client.Accesstoken;
import com.certusnet.client.FWClient;
import com.certusnet.client.HttpClientFactory;
import com.certusnet.common.util.ACache;
import com.certusnet.common.util.Constants;

public class AsyncSend {
	private static final ExecutorService pool = Executors.newFixedThreadPool(16);
	private static AsyncSend sender;
	private transient final static Logger log = LoggerFactory.getLogger(AsyncSend.class);

	public static synchronized AsyncSend getInstance() {
		if (sender != null) {
			return sender;
		}
		sender = new AsyncSend();
		return sender;
	}

	public static void releaseHttpClient() {
		HttpClientFactory.releaseInstance();
	}

	public static HttpClient getHttpClient() {
		return HttpClientFactory.getInstance(FWClient.getInstance().getConfig());
	}

	public void sendRequestAsync(FWRequest request) {
		pool.execute(new InternalRequestSender(request));
	}

	public void sendMobileRequestAsync(MobileRequest request, RequestListener listener) {
		String response = ACache.get(request.getContext()).getAsString(request.getCacheKey());
		if (request.cacheTime != -1 && !TextUtils.isEmpty(response)) {
			listener.onSuccess(response);
			return;
		}
		if (FWClient.getInstance().getAccessToken() != null) {
			Accesstoken.addHeader(request, FWClient.getInstance().getAccessToken(), "");
		}
		pool.execute(new InternalMobileRequestSender(request, Constants.timeout, listener));
	}

	public void sendCustomRequestAsync(HttpRequestBase httpRequest, RequestListener listener) {
		if (FWClient.getInstance().getAccessToken() != null) {
			Accesstoken.addHeader(httpRequest, FWClient.getInstance().getAccessToken(), "");
		}
		pool.execute(new InternalCustomRequestSender(httpRequest, Constants.timeout, listener));
	}

	/**
	 * @param httpGet
	 * @param listener
	 */
	public void sendCustomRequestAsync(HttpGet httpGet, RequestListener listener) {
		httpGet.addHeader("Accept", "*/*");
		httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		if (FWClient.getInstance().getAccessToken() != null) {
			Accesstoken.addHeader(httpGet, FWClient.getInstance().getAccessToken(), "");
		}
		pool.execute(new InternalCustomRequestSender(httpGet, Constants.timeout, listener));
	}

	/**
	 * @param httpGet
	 * @param requestParams
	 * @param listener
	 */
	public void sendCustomRequestAsync(HttpGet httpGet, Map<String, String> requestParams, RequestListener listener) {
		httpGet.addHeader("Accept", "*/*");
		httpGet.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		HttpParams httpParams = new BasicHttpParams();
		for (String paramName : requestParams.keySet()) {
			httpParams.setParameter(paramName, requestParams.get(paramName));
		}
		httpGet.setParams(httpParams);
		if (FWClient.getInstance().getAccessToken() != null) {
			Accesstoken.addHeader(httpGet, FWClient.getInstance().getAccessToken(), "");
		}
		pool.execute(new InternalCustomRequestSender(httpGet, Constants.timeout, listener));
	}

	/**
	 * @param httpPost
	 * @param params
	 * @param listener
	 */
	public void sendCustomRequestAsync(HttpPost httpPost, List<NameValuePair> params, RequestListener listener) {
		httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpPost.addHeader("Accept", "*/*");
		if (params != null) {
			UrlEncodedFormEntity urlEntity;
			try {
				urlEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				urlEntity.setContentType("text/plain");
				httpPost.setEntity(urlEntity);
				sendCustomRequestAsync(httpPost, listener);
			} catch (UnsupportedEncodingException e) {
				listener.onFailure(HttpStatusCode.UnsupportedEncodingException, "");
			}
		}

	}

	public void sendCustomRequestAsync(HttpPost httpPost, List<NameValuePair> params, String contentType,
			RequestListener listener) {
		httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpPost.addHeader("Accept", "*/*");
		if (params != null) {
			UrlEncodedFormEntity urlEntity;
			try {
				urlEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				urlEntity.setContentType(contentType);
				httpPost.setEntity(urlEntity);
				sendCustomRequestAsync(httpPost, listener);
			} catch (UnsupportedEncodingException e) {
				listener.onFailure(HttpStatusCode.UnsupportedEncodingException, "");
			}
		}

	}

	public void sendCustomRequestAsync(HttpPost httpPost, String params, RequestListener listener) {
		httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpPost.addHeader("Accept", "*/*");
		// httpPost.addHeader("Accept", "text/xml");
		if (params != null) {
			StringEntity urlEntity;
			try {
				urlEntity = new StringEntity(params, HTTP.UTF_8);
				urlEntity.setContentType("text/plain");
				httpPost.setEntity(urlEntity);
				sendCustomRequestAsync(httpPost, listener);
			} catch (UnsupportedEncodingException e) {
				listener.onFailure(HttpStatusCode.UnsupportedEncodingException, "");
			}
		}
	}

	public void sendCustomRequestAsync(HttpPost httpPost, String params, String contentType, RequestListener listener) {
		httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpPost.addHeader("Accept", "*/*");
		if (params != null) {
			StringEntity urlEntity;
			try {
				urlEntity = new StringEntity(params, HTTP.UTF_8);
				urlEntity.setContentType(contentType);
				httpPost.setEntity(urlEntity);
				sendCustomRequestAsync(httpPost, listener);
			} catch (UnsupportedEncodingException e) {
				listener.onFailure(HttpStatusCode.UnsupportedEncodingException, "");
			}
		}
	}

	/**支持GZIP压缩请求
	 * @param httpPost
	 * @param str
	 * @param listener
	 */
	public void sendCustomGzipRequestAsync(HttpPost httpPost, String str, RequestListener listener) {
		httpPost.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpPost.addHeader("Accept", "*/*");
		// httpPost.addHeader("Accept", "text/xml");
		//httpPost.addHeader("Accept-Encoding", "gzip");
		httpPost.setHeader("Connection", "Keep-Alive");
		httpPost.removeHeaders("Cache-Control");
		if (str != null) {
			try {
				ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
				byte[] sourceBytes = str.getBytes("UTF-8");
				byte[] desbytes;
				if (str.length() < 256) {
					desbytes = sourceBytes;
				} else {
					httpPost.addHeader("Content-Encoding", "gzip");
					GZIPOutputStream gzip = new GZIPOutputStream(byteOutput);
					gzip.write(sourceBytes);
					gzip.close();
					desbytes = byteOutput.toByteArray();
					log.debug("before Gzip:" + sourceBytes.length + " bytes, after Gzip:" + desbytes.length + " bytes");
				}
				ByteArrayEntity byteEntity = new ByteArrayEntity(desbytes);
				httpPost.setEntity(byteEntity);
				sendCustomRequestAsync(httpPost, listener);
			} catch (UnsupportedEncodingException e) {
				log.error("编码不支持", e);
				listener.onFailure(HttpStatusCode.UnsupportedEncodingException, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
