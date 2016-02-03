package com.certusnet.common.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class Utils {
	
	
	public static File getStorePath(Context context) {
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		if (mExternalStorageAvailable && mExternalStorageWriteable) {
			File f = Environment.getExternalStoragePublicDirectory("certusnet.icity");
			if (!f.exists())
				f.mkdirs();
			return f;
		}
		return context.getDir("certusnet.icity", Context.MODE_PRIVATE);
	}

}
