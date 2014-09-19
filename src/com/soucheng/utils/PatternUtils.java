package com.soucheng.utils;

import java.util.regex.Pattern;

/**
 * 校验正则表达式
 *
 * @author lichen
 */
public final class PatternUtils {

	//判断是否为手机号
	public static boolean checkMobile(String source) {
		return Pattern.matches("^\\d{11}$", source);
	}

}
