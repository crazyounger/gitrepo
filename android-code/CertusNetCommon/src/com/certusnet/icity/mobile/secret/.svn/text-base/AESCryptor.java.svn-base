package com.certusnet.icity.mobile.secret;

import com.certusnet.icity.mobile.secret.jni.CryptoUtils;

public class AESCryptor implements Cryptor {

	@Override
	public String decrypt(String ciphertext, String key) {
		try {
			return CryptoUtils.decrypt(0, ciphertext, key);
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String encrypt(String original, String key) {
		return CryptoUtils.encrypt(0, original, key);
	}
}
