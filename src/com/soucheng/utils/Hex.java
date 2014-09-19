package com.soucheng.utils;

/**
 * 十六进制转换工具类
 *
 * @author lichen
 */
public final class Hex {

	/**
	 * byte数组转换成16进制字符串
	 *
	 * @param bytes byte数组
	 */
	public static String toHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				builder.append("0");
			}
			builder.append(hex);
		}

		return builder.toString();
	}

	/**
	 * 16进制字符串转换成byte数组
	 *
	 * @param hex 16进制字符串
	 */
	public static byte[] toBytes(String hex) {
		if ((hex.length() & 1) == 1) {
			hex = "0" + hex;
		}

		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			int pos = i * 2;
			bytes[i] = (byte) (Integer.parseInt(String.valueOf(hex.charAt(pos)), 16) << 4 |
					Integer.parseInt(String.valueOf(hex.charAt(pos + 1)), 16));
		}

		return bytes;
	}
}
