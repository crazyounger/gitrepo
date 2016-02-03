package com.certusnet.icity.mobile.secret;

import java.security.MessageDigest;

/**
 * @author lig
 *
 */
public class Algorithm {
	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFormattedText(byte[] digest) {
		StringBuffer buffer = new StringBuffer();
		for (byte b : digest) {
			buffer.append(String.format("%2x", b));
		}
		return buffer.toString();
	}

}
