package com.certusnet.client;

import android.content.Context;

import com.airshiplay.mobile.util.MobileResource;

/**
 * port host ServerContext
 * 
 * @author lig
 * 
 */
public class FWConfig {
	protected Context mContext;

	public FWConfig(Context context) {
		this.mContext = context.getApplicationContext();
	}

	public Context getContext() {
		return mContext;
	}

	public String getAppBaseURL() {
		String port = ":" + getPort();
		String url = String.format("%s://%s%s%s", new Object[] { getProtocol(),
				getHost(), port, getServerContext() });
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}

	public String getRootURL() {
		String port = ":" + getPort();
		String url = String.format("%s://%s%s", new Object[] { getProtocol(),
				getHost(), port });
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}

	public String getSSORootURL() {
		String port = ":" + getSSOPort();
		String url = String.format("%s://%s%s", new Object[] { getProtocol(), getSSOHost(), port });
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}

	public String getApiRootURL() {
		String port = ":" + getPort();
		String url = String.format("%s://%s%s", new Object[] { getProtocol(), getHost(), port });
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}
	public String getApiContextURL() {
		String port = ":" + getPort();
		String url = String.format("%s://%s%s%s", new Object[] { getProtocol(), getHost(), port, getApiServerContext() });
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}
	public String getTcpURL(){
		String port = ":" + getTCPPort();
		String url = String.format("%s://%s%s", new Object[] { getProtocol(), getTCPHost(), port  });
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		return url;
	}
	public String getApiServerContext() {
		return  MobileResource.getInstance(mContext).getString("ApiServerContext"); 
	}
	public String getProtocol() {
		return "http";
	}

	public String getPort() {
		return MobileResource.getInstance(mContext).getString("port");
	}

	public String getHost() {
		return MobileResource.getInstance(mContext).getString("host");
	}

	public String getSSOPort() {
		return  MobileResource.getInstance(mContext).getString("sso_port");
	}

	public String getSSOHost() {
		return  MobileResource.getInstance(mContext).getString("sso_host");
	}
	public String getServerContext() {
		return MobileResource.getInstance(mContext).getString("ServerContext");
	}
	public String getTCPPort(){
		return MobileResource.getInstance(mContext).getString("tcp_port");

	}
	public String getTCPHost(){
		return MobileResource.getInstance(mContext).getString("tcp_host");
	}
	
}
