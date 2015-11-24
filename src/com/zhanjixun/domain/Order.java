/**
 * 
 */
package com.zhanjixun.domain;

import java.util.List;
import java.util.Map;

/**
 * @author Imissyou
 * @Time  2015��11��24��
 *
 */
public class Order {
	//����ID
	private String order_id;
	//�û�ID
	private String user_Id;
	//�����ܼ�
	private String totalprice;
	//����״̬
	private int stute;
	//������
	private String shopKeeperName;
	//�˷�
	private String postagePrice;
	//�̵�ID
	private String shopId;
	
	
	//�̵�List
	private List<Map<String,Object>> ordersDetail;

	/**
	 * ����ID
	 * @return order_id
	 */
	public String getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id Ҫ���õ� ����ID
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	/**
	 * �û�ID
	 * @return user_Id
	 */
	public String getUser_Id() {
		return user_Id;
	}

	/**
	 * @param user_Id Ҫ���õ� �û�ID
	 */
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	/**
	 * �����ܼ�
	 * @return totalprice
	 */
	public String getTotalprice() {
		return totalprice;
	}

	/**
	 * @param totalprice Ҫ���õ� �����ܼ�
	 */
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	/**
	 * ����״̬
	 * @return stute
	 */
	public int getStute() {
		return stute;
	}

	/**
	 * @param stute Ҫ���õ� ����״̬
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
	 * @param shopKeeperName Ҫ���õ� shopKeeperName
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
	 * @param postagePrice Ҫ���õ� postagePrice
	 */
	public void setPostagePrice(String postagePrice) {
		this.postagePrice = postagePrice;
	}

	/**
	 * @return �̵�ID
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId Ҫ���õ��̵�ID
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
	 * @param ordersDetail Ҫ���õ� ordersDetail
	 */
	public void setOrdersDetail(List<Map<String, Object>> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}
	
	
	

}
