package com.certusnet.mobile.statistics;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;

public class CrashExceptionHandler implements UncaughtExceptionHandler {
	private static CrashExceptionHandler INSTANCE = null;
	private Context mContext;
	private UncaughtExceptionHandler mDefaultHandler;
	CrashExceptionInterface crashHandler;

	public static CrashExceptionHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashExceptionHandler();
		return INSTANCE;
	}

	private CrashExceptionHandler() {
	}

	public void init(Context context) {
		this.mContext = context;
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public void setCrashExceptionHandler(CrashExceptionInterface handler) {
		this.crashHandler = handler;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (crashHandler != null)
			crashHandler.uncaughtException(mContext, ex);
		if ((this.mDefaultHandler != null)) {
			this.mDefaultHandler.uncaughtException(thread, ex);
		}
	}

	public static String handleException(Throwable tr) {
		StringWriter writer = new StringWriter();
		PrintWriter err = new PrintWriter(writer);
		tr.printStackTrace(err);
		return writer.toString();
	}
}
