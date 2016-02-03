package com.certusnet.mobile.statistics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

/**
 * 名称: StatService.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2013-8-8 上午9:44:13<br>
 * 
 * @since 2013-8-8
 * @author lig
 */
public class StatService {
	private static boolean check(Class<?> cls, String method) {

		StackTraceElement[] trace = new Throwable().getStackTrace();
		if (trace.length >= 2) {
			for (int i = 2; i < trace.length; i++) {
				StackTraceElement element = trace[i];

				if (element.getMethodName().equals(method)) {
					try {
						Class<?> cCls = Class.forName(element.getClassName());
						Class<?> superCls = cCls.getSuperclass();
						if ((superCls != null) && (superCls != cls)) {
							if ((superCls.getSuperclass() != null) && (superCls.getSuperclass() != cls))
								return false;
						}
						return true;
					} catch (Exception e) {
					}
				}
			}
		}
		return false;

	}

	public static synchronized void onError(Context context) {
		if (context == null)
			return;
		Stat.getInstance().onError(context);
	}

	/**
	 * 用于统计单个Activity页面开始时间 嵌入位置：Activity的onResume()函数中
	 * 调用方式：StatService.onResume(this);
	 * 
	 * @param context
	 *            - 调用页面的设备上下文
	 * 
	 * @变更记录 2013-8-8 上午9:53:13 lig
	 * 
	 */
	public static synchronized void onResume(Context context) {
		if (!check(Activity.class, "onResume"))
			throw new SecurityException(
					"onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
		Stat.getInstance().onResume(context, System.currentTimeMillis());
	}

	/**
	 * 用于统计单个android.support.v4.app.Fragment页面开始时间 嵌入位置：Fragment的onResume()函数中
	 * 调用方式：StatService.onResume(this); minSdkVersion=1.6
	 * 
	 * @param fragment
	 *            调用页面
	 * 
	 * @变更记录 2013-8-8 上午9:53:40 lig
	 * 
	 */
	public static synchronized void onResume(android.support.v4.app.Fragment fragment) {
		if (!check(Fragment.class, "onResume"))
			throw new SecurityException(
					"onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
		Stat.getInstance().onResume(fragment, System.currentTimeMillis());
	}

	/**
	 * 用于统计单个android.app.Fragment页面开始时间 嵌入位置：Fragment的onResume()函数中
	 * 调用方式：StatService.onResume(this); minSdkVerion = 3.0
	 * 
	 * @param fragment
	 * 
	 * @变更记录 2013-8-8 上午9:54:09 lig
	 * 
	 */
	@SuppressLint("NewApi")
	public static synchronized void onResume(android.app.Fragment fragment) {
		if (!check(android.app.Fragment.class, "onResume"))
			throw new SecurityException(
					"onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
		Stat.getInstance().onResume(fragment, System.currentTimeMillis());
	}

	/**
	 * 用于统计单个自定义页面的起始和onPageEnd同时使用，不可单独使用 嵌入位置：Fragment的onResume()函数中
	 * 调用方式：StatService.onResume(this); 注意： 自定义页面的访问的起始时间调用，配合onPageEnd函数成对调用
	 * 同一页面不可同时调用或者交叉调用onResume和onPause onPageStart和onPageEnd这两对函数
	 * 如果是要统计Activity页面，请使用onResume和onPause这对函数的调用
	 * 如果要统计Fragment页面，请使用onResume和onPause这对函数的调用
	 * 如果是显示了覆盖屏幕的自定义View，可以使用onPageStart这对函数来统计该页面的访问。
	 * 
	 * @param context
	 * @param pageName
	 *            该页面的名称 下个版本上
	 * 
	 * @变更记录 2013-8-8 上午9:55:21 lig
	 * 
	 */
	public static synchronized void onPageStart(Context context, String pageName) {
		if (TextUtils.isEmpty(pageName))
			return;
	}

	/**
	 * 用于统计单个Activity页面结束时间 嵌入位置：Activity的onPause()函数中
	 * 调用方式：StatService.onPageEnd(mContext, "btn_self_define_page_name"); 注意:
	 * 自定义页面的访问的结束时间，配合onPageStart函数成对调用 同一页面不可同时调用或者交叉调用onResume和onPause
	 * onPageStart和onPageEnd这两对函数， 如果是要统计Activity页面，请使用onResume和onPause这对函数的调用
	 * 如果要统计Fragment页面，请使用onResume和onPause这对函数的调用
	 * 如果是显示了覆盖屏幕的自定义View，可以使用onPageStart这对函数来统计该页面的访问。
	 * 
	 * @param context
	 * @param pageName
	 *            该页面的名称 下个版本上
	 * 
	 * @变更记录 2013-8-8 上午9:55:52 lig
	 * 
	 */
	public static synchronized void onPageEnd(Context context, String pageName) {
		if (TextUtils.isEmpty(pageName))
			return;
	}

	/**
	 * 用于统计单个Activity页面结束时间 嵌入位置：Activity的onPause()函数中
	 * 调用方式：StatService.onPause(this);
	 * 
	 * @param context
	 * 
	 * @变更记录 2013-8-8 上午9:56:23 lig
	 * 
	 */
	public static synchronized void onPause(Context context) {
		if (!check(Activity.class, "onPause"))
			throw new SecurityException(
					"onPause(Context context)不在Activity.onPause()中被调用||onPause(Context context)is not called in Activity.onPause().");
		Stat.getInstance().onPause(context, System.currentTimeMillis());
	}

	/**
	 * 用于统计单个Fragment页面结束时间 嵌入位置：Fragment的onPause()函数中
	 * 调用方式：StatService.onPause(this);
	 * 
	 * @param fragment
	 * 
	 * @变更记录 2013-8-8 上午9:56:37 lig
	 * 
	 */
	public static synchronized void onPause(Fragment fragment) {
		if (!check(Fragment.class, "onPause"))
			throw new SecurityException(
					"Fragment onPause(Context context)不在Fragment.onPause()中被调用||onPause(Context context)is not called in Fragment.onPause().");
		Stat.getInstance().onPause(fragment, System.currentTimeMillis());
	}

	/**
	 * 用于统计单个android.app.Fragment页面结束时间 嵌入位置：android.app.Fragment的onPause()函数中
	 * 调用方式：StatService.onPause(this);
	 * 
	 * @param fragment
	 * 
	 * @变更记录 2013-8-8 上午9:56:53 lig
	 * 
	 */
	public static synchronized void onPause(Object fragment) {
		if (!check(fragment.getClass(), "onPause"))
			throw new SecurityException(
					"android.app.Fragment onPause(Context context)不在android.app.Fragment.onPause()中被调用||onPause(Context context)is not called in android.app.Fragment.onPause().");
		Stat.getInstance().onPause(fragment, System.currentTimeMillis());
	}

	/**
	 * 用于统计自定义事件的发生次数 嵌入位置：任意，一般在开发者自定义事件(如点击事件等)的监听位置
	 * 调用示例：StatService.onEvent(context, "registered id", "pass", 1);
	 * 
	 * @param context
	 * @param event_id
	 *            事件ID,注意要先在智慧社区中注册此事件ID
	 * @param label
	 *            自定义事件标签
	 * @param acc
	 *            自定义事件计数
	 * 
	 * @变更记录 2013-8-8 上午9:57:30 lig
	 * 
	 */
	public static void onEvent(Context context, String event_id, String label, int acc) {

	}

	/**
	 * 用于统计自定义事件的发生次数 嵌入位置：任意，一般在开发者自定义事件(如点击事件等)的监听位置
	 * 调用示例：StatService.onEvent(context, "registered id", "pass");
	 * 
	 * @param context
	 * @param event_id
	 *            事件ID,注意要先在智慧社区中注册此事件ID
	 * @param label
	 *            自定义事件标签
	 * 
	 * @变更记录 2013-8-8 上午9:58:24 lig
	 * 
	 */
	public static void onEvent(Context context, String event_id, String label) {
		onEvent(context, event_id, label, 1);
	}

	/**
	 * 用于统计自定义事件的时长，此为开启计时的函数。注意此函数中的事件ID应该与onEvent函数中的不同
	 * 
	 * @param context
	 * @param event_id
	 *            事件ID,注意要先在智慧社区中注册此事件ID
	 * @param label
	 *            自定义事件标签
	 * @see
	 * @变更记录 2013-8-8 上午9:59:02 lig
	 * 
	 */
	public static void onEventStart(Context context, String event_id, String label) {

	}

	/**
	 * 用于统计自定义事件的时长，此为结束计时的函数。注意此函数中的事件ID应该与onEvent函数中的不同
	 * 
	 * @param context
	 * @param event_id
	 *            事件ID,注意要先在智慧社区中注册此事件ID
	 * @param label
	 *            自定义事件标签
	 * 
	 * @变更记录 2013-8-8 上午10:00:02 lig
	 * 
	 */
	public static void onEventEnd(Context context, String event_id, String label) {

	}

	/**
	 * 用于统计自定义事件的时长，此为开发者传入时长的函数。注意此函数中的事件ID应该与onEvent函数中的不同
	 * 此函数等价于(onEventStart+onEventEnd)，推荐使用该函数。
	 * 
	 * @param context
	 * @param event_id
	 *            事件ID,注意要先在智慧社区中注册此事件ID
	 * @param label
	 *            自定义事件标签
	 * @param milliseconds
	 *            事件时长的毫秒数,注意单位为毫秒。
	 * 
	 * @变更记录 2013-8-8 上午10:26:19 lig
	 * 
	 */
	public static void onEventDuration(Context context, String event_id, String label, long milliseconds) {

	}

	/**
	 * 设置AppKey 嵌入位置：Activity onCreate()
	 * 
	 * @param appKey
	 * 
	 * @变更记录 2013-8-8 上午10:27:50 lig
	 * 
	 */
	public static void setAppKey(String appKey) {

	}

	/**
	 * @param appChannel
	 * 
	 * @变更记录 2013-8-8 上午10:28:12 lig
	 * 
	 */
	public static void setAppChannel(String appChannel) {

	}

	/**
	 * 设置Session超时的秒数 单位为秒，大小为1到600之间，默认为30 解释：“Session超时”指的是 应用停留在后台期间，用户无操作的时长
	 * 
	 * @param seconds
	 * 
	 * @变更记录 2013-8-8 上午10:28:24 lig
	 * 
	 */
	public static void setSessionTimeOut(int seconds) {
		Stat.getInstance().setSessionTimeout(seconds);
	}
}
