package com.certusnet.client.api;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.certusnet.common.BuildConfig;

/**
 * 网络请求返回信息，handler处理，直接实现其抽象方法 在UI线程处理
 * 
 * @author lig
 * @Created on 2013-7-1下午3:00:23
 * @param <T>
 */
public abstract class AbsRequestJsonHandlerListener<T> extends Handler implements RequestListener {
	private Class<T> cls;
	private T t;
	private int errorCode;
	private JSONObject jObject;

	/**
	 * @param c
	 *            json格式解析的类
	 */
	public AbsRequestJsonHandlerListener(Class<T> c) {
		this.cls = c;
	}

	/**
	 * json解析，直接解析为 具体的类
	 * 
	 * @param t
	 *            t永远不会为null， 当 json解析失败后，则作为解析失败，不可识别的消息格式处理
	 */
	public abstract void onHandlerSuccess(T t);

	/**
	 * 网络请求，数据解析失败，等错误信息 {@link HttpStatusCode}
	 * 
	 * @param errorCode
	 */
	public abstract void onHandlerFailure(int errorCode);

	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			onHandlerSuccess(t);
			break;
		case 1:
			onHandlerFailure(errorCode);
			break;
		default:
			break;
		}
	}

	@Override
	public void onFailure(int errorCode, String msg) {
		Log.e("请求error", errorCode + ";" + msg);
		if (errorCode > 1000)// 大于1000的为智慧社区错误码，网络请求各种方面
			this.errorCode = errorCode;
		else if (errorCode >= 500)// 小于1000的http status code，进行统一编码
			this.errorCode = HttpStatusCode.UNRESPONSIVE_HOST;
		else if (errorCode >= 408)
			this.errorCode = HttpStatusCode.REQUEST_TIMEOUT;
		else if (errorCode >= 404)
			this.errorCode = HttpStatusCode.REQUEST_SERVICE_NOT_FOUND;
		sendEmptyMessage(1);
	}

	@Override
	public void onSuccess(String response) {
		if (BuildConfig.DEBUG)
			Log.d("json response:", response);
		try {
			jObject =new JSONObject(response);
			if (jObject == null) {
				this.errorCode = HttpStatusCode.JSON_PARSE_ERROR;
				sendEmptyMessage(1);
				return;
			}
			handlerSuccessData(jObject);
			sendEmptyMessage(0);
		} catch (JSONException e) {
			this.errorCode = HttpStatusCode.JSON_PARSE_ERROR;
			sendEmptyMessage(1);
		}

	}

	/**对请求成功的数据进行处理，线程中处理，非UI线程，例如排序等特殊操作
	 * @param t
	 * 
	 * @变更记录  2013-7-29 下午6:39:52 lig
	 * 
	 */
	public void handlerSuccessData(JSONObject json) {
	}
}