package com.certusnet.mobile.statistics;

import java.lang.ref.WeakReference;

import org.json.JSONException;
import org.json.JSONObject;

import com.certusnet.common.util.FileUtils;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;

public class Stat implements CrashExceptionInterface {
	private HandlerThread thread = new HandlerThread("certusnet-stat-thread");
	private WeakReference<Context> activity;
	private WeakReference<Fragment> v4Fragment;
	private WeakReference<Object> fragment;
	private long onResumeTimeMillis;
	private long sessionTimeout = 30000;
	private long startTimeMillis;
	private Handler handler;
	private static Stat instance = new Stat();
	private boolean isFirst = true;
	StatData data;

	private Stat() {
		thread.start();
		thread.setPriority(10);
		handler = new Handler(thread.getLooper());

	}

	public static Stat getInstance() {
		return instance;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public long getSessionTimeout() {
		return this.sessionTimeout;
	}

	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout * 1000;
	}

	private void firstData(final Context context, final long currentMillis) {
		if (isFirst()) {
			data = new StatData(context);
			setFirst(false);
			startTimeMillis = currentMillis;
			handler.post(new Runnable() {
				@Override
				public void run() {
					data.init(context, startTimeMillis);
				}
			});
		}
	}

	public void onResume(Fragment fragment, long currentTimeMillis) {

	}

	public void onResume(final Context context, final long currentMillis) {
		firstData(context, currentMillis);
		handler.post(new Runnable() {
			@Override
			public void run() {
				String id = context.getClass().getSimpleName();
				long recentMills = data.getRecentMills(id);
				if (recentMills != 0 && currentMillis - recentMills > getSessionTimeout()) {// 超过30秒保存数据，算第二次启动
					data.onSaveReStart(context, currentMillis);
				}
				onResumeTimeMillis = currentMillis;
				activity = new WeakReference<Context>(context);
			}
		});

	}

	public void onResume(android.app.Fragment fragment, long currentMillis) {

	}

	public void onPause(final Context context, final long currentMillis) {
		handler.post(new Runnable() {

			@Override
			public void run() {
				Context oldContext = activity.get();
				if (oldContext != null && oldContext == context) {
					String id = context.getClass().getSimpleName();
					data.pageData(id, onResumeTimeMillis, currentMillis);
				}
			}
		});
	}

	public void onPause(Fragment fragment, long currentMillis) {

	}

	public void onPause(Object fragment, long currentMillis) {

	}

	public void onError(Context context) {
		CrashExceptionHandler.getInstance().init(context);
		CrashExceptionHandler.getInstance().setCrashExceptionHandler(this);
	}

	@Override
	public void uncaughtException(final Context context, final Throwable ex) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				String error = CrashExceptionHandler.handleException(ex);
				JSONObject jObject = new JSONObject();
				try {
					long millis = System.currentTimeMillis();
					jObject.put("time", System.currentTimeMillis());
					jObject.put("message", error);
					FileUtils.saveFile(context, millis + ".err", jObject.toString(), false);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
