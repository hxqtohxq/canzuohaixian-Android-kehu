/**
 * 
 */
package com.zhanjixun.domain;

import java.util.List;
import java.util.Map;

/**
 * @author Imissyou
 * @Time  2015年11月24日
 *
 */
public class Order {
	//订单ID
	private String order_id;
	//用户ID
	private String user_Id;
	//订单总价
	private String totalprice;
	//订单状态
	private int stute;
	//店铺名
	private String shopKeeperName;
	//运费
	private String postagePrice;
	//商店ID
	private String shopId;
	
	
	//商店List
	private List<Map<String,Object>> ordersDetail;

	/**
	 * 订单ID
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id 要设置的 订单ID
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	/**
	 * 用户ID
	 * @return user_Id
	 */
	public String getUser_Id() {
		return user_Id;
	}

	/**
	 * @param user_Id 要设置的 用户ID
	 */
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	/**
	 * 订单总价
	 * @return totalprice
	 */
	public String getTotalprice() {
		return totalprice;
	}

	/**
	 * @param totalprice 要设置的 订单总价
	 */
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * 订单状态
	 * @return stute
	 */
	public int getStute() {
		return stute;
	}

	/**
	 * @param stute 要设置的 订单状态
	 */
	public void setStute(int stute) {
		this.stute = stute;
	}

	/**
	 * @return shopKeeperName
	 */
	public String getShopKeeperName() {
		return shopKeeperName;
	}

	/**
	 * @param shopKeeperName 要设置的 shopKeeperName
	 */
	public void setShopKeeperName(String shopKeeperName) {
		this.shopKeeperName = shopKeeperName;
	}

	/**
	 * @return postagePrice
	 */
	public String getPostagePrice() {
		return postagePrice;
	}

	/**
	 * @param postagePrice 要设置的 postagePrice
	 */
	public void setPostagePrice(String postagePrice) {
		this.postagePrice = postagePrice;
	}

	/**
	 * @return 商店ID
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId 要设置的商店ID
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return ordersDetail
	 */
	public List<Map<String, Object>> getOrdersDetail() {
		return ordersDetail;
	}

	/**
	 * @param ordersDetail 要设置的 ordersDetail
	 */
	public void setOrdersDetail(List<Map<String, Object>> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}
	
	
	

}
