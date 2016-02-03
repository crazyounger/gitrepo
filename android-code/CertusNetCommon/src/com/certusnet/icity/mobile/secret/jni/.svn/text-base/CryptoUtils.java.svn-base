package com.certusnet.icity.mobile.secret.jni;

public class CryptoUtils {
	static {
		System.loadLibrary("icity-jni");
	}

	/**
	 * 
	 * @param type
	 *            0-AES 1-3DES 2-MD5
	 * @param key
	 * @param original
	 * @return
	 */
	public static native String decrypt(int type, String original, String key);

	/**
	 * 
	 * @param type
	 *            0-AES 1-3DES 2-MD5 3 文件MD5
	 * @param key
	 * @param original
	 * @return
	 */
	public static native String encrypt(int type, String original, String key);

	public static native String getReservedStr();
}
