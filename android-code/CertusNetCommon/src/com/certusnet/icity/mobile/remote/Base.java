package com.certusnet.icity.mobile.remote;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Remote Service 基础类
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-28
 */
public abstract class Base implements Parcelable {
	protected byte b;
	protected int i;
	protected long l;
	protected float f;
	protected double d;
	protected String s;

	public Base() {
	}

	public Base(byte b) {
		this.b = b;
	}

	public Base(int i) {
		this.i = i;
	}

	public Base(long l) {
		this.l = l;
	}

	public Base(float f) {
		this.f = f;
	}

	public Base(double d) {
		this.d = d;
	}

	public Base(String s) {
		this.s = s;
	}

	public Base(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		b = in.readByte();
		i = in.readInt();
		l = in.readLong();
		f = in.readFloat();
		d = in.readDouble();
		s = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte(b);
		dest.writeInt(i);
		dest.writeLong(l);
		dest.writeFloat(f);
		dest.writeDouble(d);
		dest.writeString(s);
	}

	public byte getByte() {
		return b;
	}

	public void setByte(byte b) {
		this.b = b;
	}

	public int getInt() {
		return i;
	}

	public void setInt(int i) {
		this.i = i;
	}

	public long getLong() {
		return l;
	}

	public void setLong(long l) {
		this.l = l;
	}

	public float getFloat() {
		return f;
	}

	public void setFloat(float f) {
		this.f = f;
	}

	public double getDouble() {
		return d;
	}

	public void setDouble(double d) {
		this.d = d;
	}

	public String getString() {
		return s;
	}

	public void setString(String s) {
		this.s = s;
	}

}
