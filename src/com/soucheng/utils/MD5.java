package com.soucheng.utils;

import java.security.MessageDigest;

/**
 * md5加密
 *
 * @author lichen
 */
public final class MD5 {

	private static MessageDigest digest;

	private MD5() {

	}

	public static synchronized String encrypt(String source) {
		try {
			if (digest == null) {
				digest = MessageDigest.getInstance("MD5");
			}

			return Hex.toHex(digest.digest(source.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
