package com.certusnet.icity.mobile.secret;

/**
 * 
 * Created on 2012-10-23����3:58:46
 * 
 * @author lig
 * 
 */
public class ClearTextCryptor implements Cryptor {

	@Override
	public String decrypt(String ciphertext, String key) {
		return ciphertext;
	}

	@Override
	public String encrypt(String original, String key) {
		return original;
	}

}
