package com.certusnet.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.CookieSpecBase;

import com.certusnet.client.api.FWRequest;

/**
 * @author lig
 * 
 */
public class MobileCookieManager {
	private static Set<Cookie> cookies;
	private static String instanceAuthId = null;

	public static String getInstanceAuthId() {
		return instanceAuthId;
	}

	public static void setInstanceAuthId(String instanceAuthId) {
		MobileCookieManager.instanceAuthId = instanceAuthId;
	}

	public static void setCookies(String cookiesString, String domain) {
		if ((cookiesString == null) || (cookiesString.length() == 0)) {
			return;
		}
		if ((domain == null) || (domain.length() == 0)) {
			return;
		}

		HashSet<Cookie> cookieSet = new HashSet<Cookie>();

		String[] cookies = cookiesString.split(";");
		for (int i = 0; i < cookies.length; i++) {
			String[] keyValue = cookies[i].trim().split("=");
			if (keyValue.length == 2) {
				BasicClientCookie cookie = new BasicClientCookie(keyValue[0].trim(), keyValue[1].trim());
				cookie.setDomain(domain);
				cookieSet.add(cookie);
			} else {
			}
		}

		MobileCookieManager.cookies = cookieSet;
	}

	public static Set<Cookie> getCookies() {
		return cookies;
	}

	public static void handleResponseHeaders(Header[] headers, URI uri) {
		if (cookies == null) {
			cookies = new HashSet<Cookie>();
		}

		CookieOrigin origin = new CookieOrigin(uri.getHost(), uri.getPort(), "/api/rs", false);
		CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
		for (int i = 0; i < headers.length; i++)
			if (headers[i].getName().toLowerCase().equals("set-cookie")) {
				List<Cookie> theCookies;
				try {
					theCookies = cookieSpecBase.parse(headers[i], origin);
				} catch (MalformedCookieException e) {

					throw new RuntimeException(e);
				}
				cookies.addAll(theCookies);
			}
	}

	public static void addCookies(FWRequest request) {
		if ((cookies != null) && (!cookies.isEmpty())) {
			CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			List<Cookie> cookies = new ArrayList<Cookie>();
			cookies.addAll(getCookies());
			List<Header> cookieHeader = cookieSpecBase.formatCookies(cookies);
			request.getGetRequest().setHeader((Header) cookieHeader.get(0));
		}
	}

	public static void addCookies(HttpRequestBase httpRequest) {
		if ((cookies != null) && (!cookies.isEmpty())) {
			CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			List<Cookie> cookies = new ArrayList<Cookie>();
			cookies.addAll(getCookies());
			List<Header> cookieHeader = cookieSpecBase.formatCookies(cookies);
			httpRequest.setHeader((Header) cookieHeader.get(0));
		}
	}

	/**
	 * HttpClient请求 session获取
	 * 
	 * @return
	 * @变更记录 2013-6-17 下午7:35:49 lig
	 * 
	 */
	public static String getHeaderSetCookies() {
		if ((cookies != null) && (!cookies.isEmpty())) {
			CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			List<Cookie> cookies = new ArrayList<Cookie>();
			cookies.addAll(getCookies());
			List<Header> cookieHeader = cookieSpecBase.formatCookies(cookies);
			String cc = cookieHeader.get(0).getValue();
			int id = cc.indexOf("CERTUS_SESSIONID");
			if (id == -1) {
				id = cc.indexOf("JSESSIONID");
				if (id == -1) {
					return "";
				}
			}
			return cc.substring(id);
		}
		return null;
	}

	public static void clearCookies() {
		instanceAuthId = null;
		if (cookies != null)
			cookies.clear();
	}

}
