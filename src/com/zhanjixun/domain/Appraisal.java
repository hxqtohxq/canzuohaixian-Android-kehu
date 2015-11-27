/**
 * 
 */
package com.zhanjixun.domain;

/**
 * @author Imissyou
 * @Time  2015年11月23日
 *
 */
public class Appraisal {
	/**
	 * 获取页面缓存数据
	 */
	//用户ID
	private String User_id;
	//定单ID
	private String order_id;
	//商店ID
	private String shop_id;
	//评论类型
	private int conmment_kind;
	//评论文本
	private String conmment_text;
	//评分发货足斤足称
	private float conmment_weight;
	//评分发货新鲜度
	private float conmment_fresh;
	//评分发货速度
	private float conmment_speed;
	//是否匿名
	private int anonymous;
	
	
	/**
	 * 获取商品ID
	 * @return shop_id
	 */
	public String getShop_id() {
		return shop_id;
	}
	/*
	 * 设置商品ID
	 * @param shop_id 要设置的 shop_id
	 */
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	/**
	 * 评论类型
	 * @return conmment_kind
	 */
	public int getConmment_kind() {
		return conmment_kind;
	}
	/**
	 * 评论类型
	 * @param conmment_kind 要设置的评论类型
	 */
	public void setConmment_kind(int conmment_kind) {
		this.conmment_kind = conmment_kind;
	}
	/**
	 * 评论文本
	 * @return conmment_text
	 */
	public String getConmment_text() {
		return conmment_text;
	}
	/**
	 * @param conmment_text 要设置的评论文本
	 */
	public void setConmment_text(String conmment_text) {
		this.conmment_text = conmment_text;
	}
	/**
	 * 评分发货足斤足称
	 * @return conmment_weight
	 */
	public float getConmment_weight() {
		return conmment_weight;
	}
	/**
	 * @param conmment_weight 要设置的 评分发货足斤足称
	 */
	public void setConmment_weight(float conmment_weight) {
		this.conmment_weight = conmment_weight;
	}
	/**
	 * 评分发货新鲜度
	 * @return conmment_fresh
	 */
	public float getConmment_fresh() {
		return conmment_fresh;
	}
	/**
	 * @param conmment_fresh 要设置的评分发货新鲜度
	 */
	public void setConmment_fresh(float conmment_fresh) {
		this.conmment_fresh = conmment_fresh;
	}
	/**
	 * 评分发货速度
	 * @return conmment_speed
	 */
	public float getConmment_speed() {
		return conmment_speed;
	}
	/**
	 * @param conmment_speed 要设置的 评分发货速度
	 */
	public void setConmment_speed(float conmment_speed) {
		this.conmment_speed = conmment_speed;
	}
	/**
	 * 是否匿名
	 * @return anonymous
	 */
	public int isAnonymous() {
		return anonymous;
	}
	/**
	 * @param anonymous 要设置的是否匿名
	 */
	public void setAnonymous(int anonymous) {
		this.anonymous = anonymous;
	}

	/**
	 * @return user_id
	 */
	public String getUser_id() {
		return User_id;
	}
	/**
	 * @param user_id 要设置的 user_id
	 */
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	/**
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}
	/**
	 * @param order_id 要设置的 order_id
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
}
