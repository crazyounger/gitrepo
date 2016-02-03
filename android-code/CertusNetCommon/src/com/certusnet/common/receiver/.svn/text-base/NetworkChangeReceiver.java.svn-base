package com.certusnet.common.receiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.airshiplay.mobile.util.TelephoneUtil;
import com.certusnet.common.util.Constants;

/**
 * 网络状态变更
 * 
 * @author airshiplay
 * @Create Date 2013-2-14
 * @version 1.0
 * @since 1.0
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
	private static List<StatusChangeListener> listeners = new ArrayList<StatusChangeListener>();

	@Override
	public void onReceive(Context context, Intent intent) {
		NetworkInfo networkInfo = TelephoneUtil
				.getAvailableNetworkInfo(context);
		if (networkInfo != null) {
			if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				Constants.timeout = Constants.wifiTimeout;
			} else {
				Constants.timeout = Constants.mobileTimeout;
			}
		}
		for(StatusChangeListener listener:listeners){
			listener.change(context, intent);
		}
	}

	public static void registerListener(StatusChangeListener listener) {
		listeners.add(listener);
	}

	public static void unregisterListener(StatusChangeListener listener) {
		listeners.remove(listener);
	}

	public static void clear() {
		listeners.clear();
	}
}
