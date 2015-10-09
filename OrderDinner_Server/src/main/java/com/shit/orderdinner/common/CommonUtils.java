package com.shit.orderdinner.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	/**
	 * ��֤�ַ����Ƿ��ֻ���
	 * @param str ����֤���ַ���
	 * @return true���ֻ��ţ�false�������ֻ���
	 */
	public static boolean isTelNum(String str) {
		String check = "1[0-9]{10}";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * ��֤�ַ����Ƿ�����
	 * @param str ����֤���ַ���
	 * @return true�����䣻false����������
	 */
	public static boolean isEMail(String str) {
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
