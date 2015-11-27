package com.zhanjixun.domain;

import android.content.Context;

import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.SPUtil;

public class User {

	private String id;

	private String phone;

	private String password;

	private String name;

	private String headURL;

	public static boolean saveUserInfo(Context context, User u) {
		if (u == null) {
			return false;
		} else {
			SPUtil.saveString(context, Constants.XML_USER_FILE,
					Constants.XML_USER_ID, u.getId());
			SPUtil.saveString(context, Constants.XML_USER_FILE,
					Constants.XML_USER_PHONE, u.getPhone());
			SPUtil.saveString(context, Constants.XML_USER_FILE,
					Constants.XML_USER_PW, u.getPassword());
			SPUtil.saveString(context, Constants.XML_USER_FILE,
					Constants.XML_USER_NAME, u.getName());
			SPUtil.saveString(context, Constants.XML_USER_FILE,
					Constants.XML_USER_HEAD, u.getHeadURL());
			return true;
		}
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            要设置的 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return headURL
	 */
	public String getHeadURL() {
		return headURL;
	}

	/**
	 * @param headURL
	 *            要设置的 headURL
	 */
	public void setHeadURL(String headURL) {
		this.headURL = headURL;
	}

}
