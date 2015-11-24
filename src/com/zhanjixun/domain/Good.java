package com.zhanjixun.domain;

public class Good {

	private String name;

	private String id;

	private String pic_url;

	private double price;

	private String size;

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
	 * @return id
	 */
	public String getId() {
		return id;
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
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            要设置的 price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            要设置的 size
	 */
	public void setSize(String size) {
		this.size = size;
	}

}
