package com.certusnet.icity.mobile.secret;

import com.certusnet.icity.mobile.secret.jni.CryptoUtils;

public class DES3Cryptor implements Cryptor {

	@Override
	public String decrypt(String ciphertext, String key) {
		return CryptoUtils.decrypt(1, ciphertext, key);
	}

	@Override
	public String encrypt(String original, String key) {
		return CryptoUtils.encrypt(1, original, key);
	}

}
