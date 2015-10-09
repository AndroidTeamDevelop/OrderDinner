package com.shit.orderdinner.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	/**
	 * 验证字符串是否手机号
	 * @param str 待验证的字符串
	 * @return true：手机号；false：不是手机号
	 */
	public static boolean isTelNum(String str) {
		String check = "1[0-9]{10}";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证字符串是否邮箱
	 * @param str 待验证的字符串
	 * @return true：邮箱；false：不是邮箱
	 */
	public static boolean isEMail(String str) {
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
