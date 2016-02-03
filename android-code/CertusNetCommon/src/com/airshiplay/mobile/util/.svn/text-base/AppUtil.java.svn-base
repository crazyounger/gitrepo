/**
 * 
 */
package com.airshiplay.mobile.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * android应用相关工具类
 * 
 * @author airshiplay
 * @Create Date 2013-3-8
 * @version 1.0
 * @since 1.0
 */
public class AppUtil {
	/**
	 * 应用图标
	 * 
	 * @param context
	 * @return
	 */
	public static Drawable getAppIcon(Context context) {
		return context.getApplicationInfo().loadIcon(context.getPackageManager());
	}

	/**
	 * 应用名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
	}

	/**
	 * @param context
	 * @param pkgName
	 * @return
	 */
	public static String getAppName(Context context, String pkgName) {
		try {
			PackageManager pm = context.getPackageManager();
			return pm.getApplicationLabel(pm.getApplicationInfo(pkgName, PackageManager.GET_META_DATA)).toString();
		} catch (NameNotFoundException e) {
		}
		return "";
	}

	/**
	 * 应用包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getPackageName(Context context) {
		return context.getPackageName();
	}

	/**
	 * 运行中的应用进程ID PID
	 * 
	 * @param context
	 * @param processName
	 *            进程名
	 * @return
	 */
	public static int getPid(Context context, String processName) {
		Iterator<ActivityManager.RunningAppProcessInfo> itr = ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses().iterator();
		ActivityManager.RunningAppProcessInfo runningAppProcessInfo;
		do {
			if (!itr.hasNext())
				return 0;
			runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) itr.next();
		} while (!processName.equals(runningAppProcessInfo.processName));
		return runningAppProcessInfo.pid;
	}

	/**
	 * 应用版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			int versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionCode;
			return versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getVersionCode(Context context, String pkgName) {
		try {
			int versionCode = context.getPackageManager().getPackageInfo(pkgName, 16384).versionCode;
			return versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 应用版本名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		try {
			String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
			return versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getVersionName(Context context, String pkgName) {
		try {
			String versionName = context.getPackageManager().getPackageInfo(pkgName, 16384).versionName;
			return versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 未安装APK信息
	 * 
	 * @param context
	 * @param archiveFilePath
	 *            apk路径
	 * @return
	 */
	public static PackageInfo getPackageInfo(Context context, String archiveFilePath) {
		PackageInfo pkgInfo = context.getPackageManager().getPackageArchiveInfo(archiveFilePath,
				PackageManager.GET_ACTIVITIES);
		if (pkgInfo.applicationInfo != null) {
			pkgInfo.applicationInfo.sourceDir = archiveFilePath;
			pkgInfo.applicationInfo.publicSourceDir = archiveFilePath;
		}
		return pkgInfo;
	}

	/**
	 * 是否为系统应用
	 * 
	 * @param context
	 * @param packageName
	 *            应用包名
	 * @return
	 */
	public static boolean isSystemApplication(Context context, String packageName) {
		PackageManager manager = context.getPackageManager();
		try {
			PackageInfo packageInfo = manager.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);
			if (new File("/data/app/" + packageInfo.packageName + ".apk").exists()) {
				return true;
			}
			if (packageInfo.versionName != null && packageInfo.applicationInfo.uid < 10000) {
				return true;
			}
			if ((packageInfo.applicationInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) != 0) {
				return true;
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 启动新应用
	 * 
	 * @param context
	 * @param packageName
	 * @param launchableActivity
	 */
	public static void startApplication(Context context, String packageName, String launchableActivity) {
		context.startActivity(getActivityIntent(packageName, launchableActivity));

	}

	public static Intent getActivityIntent(String packageName, String launchableActivity) {
		ComponentName cn = new ComponentName(packageName, launchableActivity);
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setComponent(cn);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		return intent;
	}

	/**
	 * @param context
	 * @param packageName
	 * @param launchableActivity
	 * @return
	 */
	public static boolean existApplication(Context context, String packageName, String launchableActivity) {
		PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(
				getActivityIntent(packageName, launchableActivity), 0);
		return (activities.size() > 0);

	}

	/**
	 * @param context
	 * @param path
	 */
	public static void installAPK(Context context, String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
