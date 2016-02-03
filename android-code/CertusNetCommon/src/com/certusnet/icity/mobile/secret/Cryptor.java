package com.certusnet.icity.mobile.secret;

/**
 * �ӽ��� Created on 2012-10-23����4:37:46
 * 
 * @author lig
 * 
 */
public interface Cryptor {
	/**
	 * 
	 * @param ciphertext
	 * @return original
	 */
	String decrypt(String ciphertext, String key);

	/**
	 * 
	 * @param original
	 * @return ciphertext
	 */
	String encrypt(String original, String key);
}
