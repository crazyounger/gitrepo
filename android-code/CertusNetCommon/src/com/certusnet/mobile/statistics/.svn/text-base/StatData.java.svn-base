package com.certusnet.mobile.statistics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.airshiplay.mobile.util.TelephoneUtil;
import com.certusnet.common.util.FileUtils;

import android.content.Context;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonWriter;

public class StatData {
	private JSONObject pageJson;
	private JSONObject globalJson;
	private JSONObject baseInfoJson;
	private JSONObject recentPage;
	private long onResumeTimeMillis;
	private long onEndTimeMillis;
	private long sessionTimeout;
	private long startTimeMillis;
	Context mContext;

	protected StatData(Context context) {
		mContext = context.getApplicationContext();
		globalJson = new JSONObject();
		try {
			globalJson.put("packageName", context.getPackageName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		baseInfo();
	}

	public void baseInfo() {
		try {
			baseInfoJson = new JSONObject();
			baseInfoJson.put("termType", "phone");
			baseInfoJson.put("osType", "android");
			baseInfoJson.put("osVersion", Build.VERSION.RELEASE);
			baseInfoJson.put("deviceModel", Build.MODEL);
			baseInfoJson.put("manufacturer", Build.MANUFACTURER);
			baseInfoJson.put("deviceBrand", Build.BRAND);
			baseInfoJson.put("deviceId", DeviceInfo.getInstance().getDeviceId(mContext));
			baseInfoJson.put("density", DeviceInfo.getInstance().getDensity(mContext));
			baseInfoJson.put("resolution", DeviceInfo.getInstance().getResolution(mContext));
			globalJson.put("baseInfo", baseInfoJson);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	void init(Context context, long startMillis) {
		this.mContext = context.getApplicationContext();
		this.startTimeMillis = startMillis;
		try {
			pageJson = new JSONObject();
			pageJson.put("startMillis", startMillis);
			pageJson.put("pageStatList", new JSONArray());
			globalJson.put("stat", pageJson);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onSaveReStart(Context context, long startMillis) {
		FileUtils.saveFile(context, "page" + startTimeMillis + ".json", globalJson.toString(), false);
		init(context, startMillis);
	}

	public void pageData(String id, long onResumeMillis, long onPauseMillis) {
		this.onResumeTimeMillis = onResumeMillis;
		this.onEndTimeMillis = onPauseMillis;
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("id", id);
			jObject.put("duration", onEndTimeMillis - onResumeTimeMillis);
			jObject.put("deep", onResumeTimeMillis - startTimeMillis);
			recentPage = jObject;
			putJSONArray("pageStatList", recentPage);
			pageJson.put("endMillis", onEndTimeMillis);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public long getRecentMills(String id) {
		try {
			if (recentPage != null && id.equals(recentPage.get("id"))) {
				return onEndTimeMillis;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void putJSONArray(String name, Object value) throws JSONException {
		JSONArray array = null;
		array = pageJson.optJSONArray(name);
		if (null == array) {
			array = new JSONArray();
			pageJson.put(name, array);
		}
		array.put(value);
	}

}
