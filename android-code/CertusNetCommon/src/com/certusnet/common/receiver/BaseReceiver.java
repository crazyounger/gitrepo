package com.certusnet.common.receiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class BaseReceiver extends BroadcastReceiver{

	protected static List<StatusChangeListener> listeners = new ArrayList<StatusChangeListener>();
	@Override
	public void onReceive(Context context, Intent intent) {
		Iterator<StatusChangeListener> iter = listeners.iterator();
		if (iter.hasNext())
			iter.next().change(context, intent);
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
