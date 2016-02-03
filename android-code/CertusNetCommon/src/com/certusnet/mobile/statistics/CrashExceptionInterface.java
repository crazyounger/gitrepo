package com.certusnet.mobile.statistics;

import android.content.Context;

public interface CrashExceptionInterface {
	public void uncaughtException(Context context, Throwable ex);
}
