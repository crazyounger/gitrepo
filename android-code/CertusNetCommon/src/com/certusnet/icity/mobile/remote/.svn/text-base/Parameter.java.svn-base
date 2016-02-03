package com.certusnet.icity.mobile.remote;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 接口交互参数类
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-28
 */
public class Parameter extends Base {
	protected Serializable model;
	public static final Parcelable.Creator<Parameter> CREATOR = new Parcelable.Creator<Parameter>() {
		public Parameter createFromParcel(Parcel in) {
			return new Parameter(in);
		}

		public Parameter[] newArray(int size) {
			return new Parameter[size];
		}
	};

	public Parameter() {
	}

	public Parameter(Serializable model) {
		this.model = model;
	}

	public Parameter(Parcel in) {
		super(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void readFromParcel(Parcel in) {
		model = in.readSerializable();
		super.readFromParcel(in);
	}

	public Serializable getModel() {
		return model;
	}

	public void setModel(Serializable model) {
		this.model = model;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(model);
		super.writeToParcel(dest, flags);
	}
}
