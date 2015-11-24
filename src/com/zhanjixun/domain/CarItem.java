package com.zhanjixun.domain;

import java.util.Map;

public class CarItem {
	private Seller seller;
	private Map<Good, Integer> goods;
	private double finalPrice;

	/**
	 * @return seller
	 */
	public Seller getSeller() {
		return seller;
	}

	/**
	 * @param seller
	 *            Ҫ���õ� seller
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
	 * @return goods
	 */
	public Map<Good, Integer> getGoods() {
		return goods;
	}

	/**
	 * @param goods
	 *            Ҫ���õ� goods
	 */
	public void setGoods(Map<Good, Integer> goods) {
		this.goods = goods;
	}

	/**
	 * @return finalPrice
	 */
	public double getFinalPrice() {
		return finalPrice;
	}

	/**
	 * @param finalPrice
	 *            Ҫ���õ� finalPrice
	 */
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
}
