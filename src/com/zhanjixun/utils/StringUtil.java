package com.zhanjixun.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			return false;
		}
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}

	public static String encryptPhoneNumber(String phoneNum) {
		if (phoneNum == null || phoneNum.length() != 11) {
			throw new IllegalArgumentException("�����ֻ�����ʧ�ܣ������ʽ����");
		} else {
			return phoneNum.substring(0, 3) + "******"
					+ phoneNum.substring(7, 11);
		}
	}

	public static void main(String args) {
		System.out.println(encryptPhoneNumber("18312687412"));
	}

	public static boolean isEmptyString(String str) {
		if (str == null || str.equals("") || str.equals("null"))
			return true;
		return false;
	}

	/**
	 * ��ȡ�ַ���md5
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digs = md.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for (byte b : digs) {
				int a = b & 0xff;
				if (a < 16)
					sb.append("0");
				sb.append(Integer.toHexString(a));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	/**
	 * ת����md5�ļ���
	 * 
	 * @param dir
	 * @param name
	 * @return
	 */
	public static String toMDFileName(String dir, String name) {
		StringBuffer sb = new StringBuffer();
		sb.append(dir).append(File.separator).append(MD5(name))
				.append(name.substring(name.indexOf(".")));
		return sb.toString();
	}
}
