package com.zhanjixun.domain;

public class Address {
	/**
	 * �ջ���ַID
	 */
	private String id;
	/**
	 * �ջ���
	 */
	private String consignee;

	private String address;

	private String phone;

	private String zipCode;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            Ҫ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return consignee
	 */
	public String getConsignee() {
		return consignee;
	}

	/**
	 * @param consignee
	 *            Ҫ���õ� consignee
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            Ҫ���õ� address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            Ҫ���õ� phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            Ҫ���õ� zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
