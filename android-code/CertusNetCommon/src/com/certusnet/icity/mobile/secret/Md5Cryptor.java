package com.certusnet.icity.mobile.secret;

import com.certusnet.icity.mobile.secret.jni.CryptoUtils;

/**
 * Created on 2012-10-23����3:58:53
 * 
 * @author lig
 * 
 */
public class Md5Cryptor implements Cryptor {
	/**
	 * MD5不能解密
	 * 
	 * @throws IllegalStateException
	 *             ("MD5不能解密");
	 */
	@Deprecated
	@Override
	public String decrypt(String password, String key) {
		throw new IllegalStateException("MD5不能解密");
	}

	public String encrypt(String filepath) {
		return CryptoUtils.encrypt(3, filepath, null);
	}

	@Override
	public String encrypt(String original, String key) {
		return CryptoUtils.encrypt(2, original, null);
	}
}
