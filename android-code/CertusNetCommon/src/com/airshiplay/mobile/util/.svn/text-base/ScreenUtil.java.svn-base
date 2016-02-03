package com.airshiplay.mobile.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * android屏幕相关处理工具类
 * 
 * @author airshiplay
 * @Create 2013-7-14
 * @version 1.0
 * @since 1.0
 */
public class ScreenUtil {

	public static int[] size;
	public static float density;
	public static int[] resolutionXY;

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	public static void init(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		ScreenUtil.density = dm.density;
		ScreenUtil.size = new int[2];
		ScreenUtil.size[0] = dm.widthPixels;
		ScreenUtil.size[1] = dm.heightPixels;
		ScreenUtil.resolutionXY = getScreenResolutionXY(context);
	}

	public static int dp2px(float dp) {
		return (int) (0.5f + ScreenUtil.density * dp);
	}

	/**
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2px(Context context, int dp) {
		return dp2px(dp);
	}

	/**
	 * 获取屏幕的宽高（像素点）
	 * 
	 * @param context
	 * @return int[0]为屏幕最小边像素点，int[1]为屏幕最大边像素点
	 */
	public static int[] getScreenResolutionXY(Context context) {
		int[] resolutionXY = new int[2];
		DisplayMetrics dm = new DisplayMetrics();
		((WindowManager) context.getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
		if (dm.widthPixels < dm.heightPixels) {
			resolutionXY[0] = dm.widthPixels;
			resolutionXY[1] = dm.heightPixels;
		} else {
			resolutionXY[0] = dm.heightPixels;
			resolutionXY[1] = dm.widthPixels;
		}
		return resolutionXY;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((WindowManager) context.getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((WindowManager) context.getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);

		return dm.heightPixels;
	}

	/**
	 * 状态栏高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getStatusBarHeight(Activity activity) {
		Rect rect = new Rect();
		Window win = activity.getWindow();
		win.getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight = rect.top;
		return statusBarHeight;
	}

	/**
	 * 标题栏高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getTitleBarHeight(Activity activity) {
		Rect rect = new Rect();
		Window win = activity.getWindow();
		win.getDecorView().getWindowVisibleDisplayFrame(rect);
		int statusBarHeight = rect.top;
		int contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT)
				.getTop();
		int titleBarHeight = contentViewTop - statusBarHeight;
		return titleBarHeight;
	}
}
