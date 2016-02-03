package com.certusnet.common.util;

/**
 * 加解密算法
 * 
 * @author lig
 * 
 */
public class RC4 {
	static final byte[] key = "03a976511e2cbe3a7f26808fb7af3c05".getBytes();

	public static byte[] encrypt(byte[] source) {
		return encrypt(source, key);
	}

	public static byte[] decrypt(byte[] ciphertext) {
		return decrypt(ciphertext, key);
	}

	static byte[] encrypt(byte[] source, byte[] key) {
		int[] subKey = new int[256];
		int[] box = new int[256];
		int i = key.length;
		if ((i < 1) || (i > 256))
			throw new IllegalArgumentException(
					"key must be between 1 and 256 bytes");
		for (int j = 0; j < 256; j++) {
			subKey[j] = j;
			box[j] = key[(j % i)];
		}
		int j = 0;
		int m = 0;
		for (int k = 0; k < 256; k++) {
			j = j + subKey[k] + box[k] & 0xFF;
			m = subKey[k];
			subKey[k] = subKey[j];
			subKey[j] = m;
		}
		j = 0;
		int k = 0;

		byte[] ciphertext = new byte[source.length];
		for (int i1 = 0; i1 < source.length; i1++) {
			j = j + 1 & 0xFF;
			k = k + subKey[j] & 0xFF;
			int i2 = subKey[j];
			subKey[j] = subKey[k];
			subKey[k] = i2;
			int n = subKey[j] + subKey[k] & 0xFF;
			m = subKey[n];
			ciphertext[i1] = ((byte) (source[i1] ^ m));
		}
		return ciphertext;
	}

	static byte[] decrypt(byte[] ciphertext, byte[] keybyte) {
		return encrypt(ciphertext, keybyte);
	}
}