package com.certusnet.client.api;

import android.content.Context;

/**
 * 名称: RequestListener.java<br>
 * 描述: 网络请求，异步返回<br>
 * 类型: JAVA<br>
 * 最近修改时间:2013-6-18 上午8:58:02<br>
 * 
 * @since 2013-6-18
 * @author lig
 */
public interface RequestListener {

	/**
	 * 请求失败返回信息
	 * 
	 * @param errorCode
	 *            错误码
	 * @param msg
	 *            错误信息,请求地址
	 * @变更记录 2013-6-18 上午8:58:29 lig
	 * 
	 */
	void onFailure(int errorCode, String msg);

	/**
	 * 请求成功，返回的string 字符串
	 * 
	 * @param response
	 * @变更记录 2013-6-18 上午8:58:47 lig
	 * 
	 */
	public abstract void onSuccess(String response);
	
	public void onCache(Context context,String cacheKey,long cacheTime);
}
