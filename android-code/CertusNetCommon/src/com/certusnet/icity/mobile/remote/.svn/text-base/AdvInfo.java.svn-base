package com.certusnet.icity.mobile.remote;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 广告信息
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-20
 */
public class AdvInfo implements Parcelable {
	private String title;
	private String imgUrl;
	private String text;

	public AdvInfo() {
	}

	public AdvInfo(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void readFromParcel(Parcel in) {
		title = in.readString();
		imgUrl = in.readString();
		text = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(imgUrl);
		dest.writeString(text);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static final Parcelable.Creator<AdvInfo> CREATOR = new Parcelable.Creator<AdvInfo>() {
		public AdvInfo createFromParcel(Parcel in) {
			return new AdvInfo(in);
		}

		public AdvInfo[] newArray(int size) {
			return new AdvInfo[size];
		}
	};
}
