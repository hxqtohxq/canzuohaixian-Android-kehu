package com.zhanjixun.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	public static boolean isEmptyString(String str) {
		if (str == null || str.equals("") || str.equals("null"))
			return true;
		return false;
	}

	/**
	 * 获取字符串md5
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
	 * 转换成md5文件名
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
