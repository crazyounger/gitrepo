package com.airshiplay.mobile.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.view.WindowManager;

import com.airshiplay.mobile.log.LoggerFactory;
import com.airshiplay.mobile.util.ScreenUtil;
import com.certusnet.common.util.ACache;
import com.certusnet.common.util.CrashHandler;

/**
 * @author airshiplay
 * @Create Date 2013-3-8
 * @version 1.0
 * @since 1.0
 */
public class MobileApplication extends Application {
	private WindowManager.LayoutParams winLayoutParams = new WindowManager.LayoutParams();
	private static MobileApplication instance;
	private List<Activity> listActivities;
	private ACache mCache;

	public static MobileApplication getInstance() {
		return instance;
	}

	public final WindowManager.LayoutParams getWinLayoutParams() {
		return this.winLayoutParams;
	}
	protected static boolean sIsScreenLarge;

	public static boolean isScreenLarge() {
		return sIsScreenLarge;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		CrashHandler.getInstance().init(getApplicationContext());
		mCache = ACache.get(this);
		final int screenSize = getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;
		sIsScreenLarge = screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE
				|| screenSize == 4;
		this.listActivities = new ArrayList<Activity>();
		ScreenUtil.init(getApplicationContext());
		LoggerFactory.init(getApplicationContext());
	}

	public void addAcitvity(Activity activity) {
		this.listActivities.add(activity);
	}

	public void finishAllActivity() {
		Iterator<Activity> itr = this.listActivities.iterator();
		while (itr.hasNext())
			(itr.next()).finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	public List<Activity> getList() {
		return this.listActivities;
	}

	public void remove(Activity activity) {
		this.listActivities.remove(activity);
	}

	public ACache getMCache() {
		return mCache;
	}

	public void setMCache(ACache mCache) {
		this.mCache = mCache;
	}
}
