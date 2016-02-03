package com.certusnet.icity.mobile.remote;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户信息
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-20
 */
public class UserInfo implements Parcelable {
	private String userName;
	/** md5 */
	private String password;

	public UserInfo() {
	}

	public UserInfo(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		userName = in.readString();
		password = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.userName);
		dest.writeString(this.password);
	}

	/**
	 * 密码MD5
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码MD5
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取用户名
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
		public UserInfo createFromParcel(Parcel in) {
			return new UserInfo(in);
		}

		public UserInfo[] newArray(int size) {
			return new UserInfo[size];
		}
	};
}
