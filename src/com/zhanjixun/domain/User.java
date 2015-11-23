package com.zhanjixun.domain;

public class User {
	
	private String phone;
	private String name;
	private String pic_url;
	private String pic_bg_url;

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
	 * @return pic_url
	 */
	public String getPic_url() {
		return pic_url;
	}

	/**
	 * @param pic_url
	 *            要设置的 pic_url
	 */
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	/**
	 * @return pic_bg_url
	 */
	public String getPic_bg_url() {
		return pic_bg_url;
	}

	/**
	 * @param pic_bg_url
	 *            要设置的 pic_bg_url
	 */
	public void setPic_bg_url(String pic_bg_url) {
		this.pic_bg_url = pic_bg_url;
	}
}
