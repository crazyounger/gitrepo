package com.certusnet.icity.mobile.remote;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * App信息
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-21
 */
public class AppInfo implements Parcelable {
	private String versionCode;
	private String md5;
	private String url;

	public AppInfo() {
	}

	public AppInfo(String versionCode, String md5, String url) {
		this.versionCode = versionCode;
		this.md5 = md5;
		this.url = url;
	}

	public AppInfo(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		versionCode = in.readString();
		md5 = in.readString();
		url = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(versionCode);
		dest.writeString(md5);
		dest.writeString(url);
	}

	public static final Parcelable.Creator<AppInfo> CREATOR = new Parcelable.Creator<AppInfo>() {
		public AppInfo createFromParcel(Parcel in) {
			return new AppInfo(in);
		}

		public AppInfo[] newArray(int size) {
			return new AppInfo[size];
		}
	};

	/**
	 * @return apk versionCode
	 */
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * @return APK MD5值
	 */
	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return apk升级URL地址
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
