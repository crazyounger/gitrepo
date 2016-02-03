package com.certusnet.common.util;

/**
 * 名称: Constants.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2013-7-16 上午9:22:04<br>
 * 
 * @since 2013-7-16
 * @author lig
 */
public class Constants {

	public static final int wifiTimeout = 30000;
	public static final int mobileTimeout = 60000;
	/**
	 * 超时时间，可以重新赋值
	 */
	public static int timeout = mobileTimeout;
	/**
	 * 是否加密接口
	 */
	public static final boolean AUTH_SWITCH = true;
	/**
	 * 加密头格式
	 * */
	public static final String AUTHORIZATION = "Authorization";

}
