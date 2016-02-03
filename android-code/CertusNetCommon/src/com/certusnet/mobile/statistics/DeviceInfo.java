package com.certusnet.mobile.statistics;

import java.util.Date;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class DeviceInfo {
	private static DeviceInfo instance = new DeviceInfo();

	private DeviceInfo() {
	}

	public static DeviceInfo getInstance() {
		return instance;
	}

	boolean checkPermission(Context context, String permission) {
		return android.content.pm.PackageManager.PERMISSION_GRANTED == context.checkPermission(permission, Process.myPid(),
				Binder.getCallingUid());
	}

	public String getDeviceId(Context context) {
		String str = null;
		if (checkPermission(context, android.Manifest.permission.READ_PHONE_STATE)) {
			str = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
			if (str != null)
				return str;
		}
		String mac = getMac(context);
		if (mac != null) {
			str = "certusnet" + mac.hashCode() + "mac";
			return str;
		}
		str = "certusnet" + ((new Date().getTime() + "").hashCode()) + "time";
		return str;
	}

	public String getResolution(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels + "x" + dm.heightPixels;
	}

	public float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	public String getMac(Context context) {
		if (checkPermission(context, android.Manifest.permission.ACCESS_WIFI_STATE)) {
			return ((WifiManager) context.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
		}
		return null;
	}
}
