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
	 *            要设置的 seller
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
	 *            要设置的 goods
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
	 *            要设置的 finalPrice
	 */
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
}
