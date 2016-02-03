package com.certusnet.client.api;

import android.content.Context;

import com.certusnet.common.R;

public class ErrorMessage {
	private static int[] ErrorCode;
	private static String[] ErrorName;

	/**
	 * support multi thread
	 * 
	 * @param context
	 * @param code
	 * @return
	 */
	public static synchronized String findMessageByErrorCode(Context context, int code) {
		if (null == ErrorCode)
			ErrorCode = (context.getResources().getIntArray(R.array.ErrorCode));
		if (null == ErrorName)
			ErrorName = (context.getResources().getStringArray(R.array.ErrorName));
		if (null == ErrorCode || null == ErrorName || ErrorCode.length != ErrorName.length)
			return "应用程序异常";
		int index = indexOf(ErrorCode, code);
		if (index == -1)
			return "当前服务异常，错误码:" + code;
		return ErrorName[index];
	}

	public static int indexOf(int[] array, int object) {
		int[] a = array;
		int s = a.length;
		for (int i = 0; i < s; i++) {
			if (object == (a[i])) {
				return i;
			}
		}
		return -1;
	}

}
