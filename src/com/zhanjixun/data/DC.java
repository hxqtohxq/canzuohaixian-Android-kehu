package com.zhanjixun.data;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;

public class DC extends DataCenter {

	private static DC dc;
	private int[] pageInfo_indexPageNum = { 1, 1, 1, 1, 1, 1 };

	private DC() {
	}

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static DC getInstance() {
		if (dc == null) {
			synchronized (DC.class) {
				if (dc == null)
					dc = new DC();
			}
		}
		return dc;
	}

	/**
	 * 用户登录
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param pw
	 */
	public void userLogin(OnDataReturnListener dataReturnListener, String id,
			String pw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		params.put("password", pw);
		getDatasFromServer(TaskTag.LOGIN, "fishshop/user_userLogin.action",
				params, dataReturnListener);
	}

	/**
	 * 用户注册请求短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void requestCodeForRegister(OnDataReturnListener dataReturnListener,
			String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.REGISTER_CODE,
				"fishshop/user_smsCodeOfRegister.action", params,
				dataReturnListener);
	}

	/**
	 * 用户登录
	 * 
	 * @param dataReturnListener
	 * @param phone
	 * @param checkCode
	 * @param password
	 */
	public void register(OnDataReturnListener dataReturnListener, String phone,
			String checkCode, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", phone);
		params.put("verifyCode", checkCode);
		params.put("password", password);
		getDatasFromServer(TaskTag.REGISTER, "fishshop/user_addUser.action",
				params, dataReturnListener);
	}

	/**
	 * 找回密码请求短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void requestCodeForFindPassword(
			OnDataReturnListener dataReturnListener, String id) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.REQUEST_MSG_CODE,
				"fishshop/user_smsCodeOfFound.action", params,
				dataReturnListener);
	}

	/**
	 * 验证码找回密码的短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param code
	 */
	public void checkMsgCode(OnDataReturnListener dataReturnListener,
			String id, String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("verifyCode", code);
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.CHECK_MSG_CODE,
				"fishshop/user_ confirmSmsVerifyCode.action", params,
				dataReturnListener);
	}

	/**
	 * 找回密码
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param pw
	 */
	public void findPassword(OnDataReturnListener dataReturnListener,
			String id, String pw) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("password", pw);
		params.put("phoneNumber", id);
		getDatasFromServer(TaskTag.FIND_PASSWORD,
				"fishshop/user_ findPassword.action", params,
				dataReturnListener);
	}

	/**
	 * 获取用户所有收货地址
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void getAddrees(OnDataReturnListener dataReturnListener, String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", id);
		getDatasFromServer(TaskTag.GET_ADDREES,
				"fishshop/getaddress_getUserAllAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 添加收货地址
	 * 
	 * @param dataReturnListener
	 * @param phone
	 * @param name
	 * @param adsPhone
	 * @param address
	 * @param mail
	 */
	public void addAddress(OnDataReturnListener dataReturnListener,
			String phone, String name, String adsPhone, String address,
			String mail) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", phone);
		params.put("userName", name);
		params.put("address", address);
		params.put("phone", adsPhone);
		params.put("postcode", mail);

		getDatasFromServer(TaskTag.ADD_ADDRESS,
				"fishshop/getaddress_addGetAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 修改收货地址
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param addressId
	 * @param name
	 * @param adsPhone
	 * @param address
	 * @param mail
	 */
	public void changeAddress(OnDataReturnListener dataReturnListener,
			String id, String addressId, String name, String adsPhone,
			String address, String mail) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("address_id", addressId);
		params.put("UserId", id);
		params.put("consignee", name);
		params.put("address", address);
		params.put("phone", adsPhone);
		params.put("zip_code", mail);
		getDatasFromServer(TaskTag.CHANGE_ADDRESS,
				"fishshop/getaddress_updateGetAddress.action", params,
				dataReturnListener);
	}

	/**
	 * 获取二级分类商品
	 * 
	 * @param dataReturnListener
	 * @param categoryId
	 * @param indexPageNum
	 * @param size
	 */
	public void getGoodList(OnDataReturnListener dataReturnListener,
			String categoryId, int indexPageNum, int size) {
		Map<String, String> params = new IdentityHashMap<String, String>();

		params.put(new String("categoryId"), categoryId);
		params.put(new String("pageInfo.indexPageNum"), indexPageNum + "");
		params.put(new String("pageInfo.size"), size + "");

		getDatasFromServer(TaskTag.GOOD_LIST,
				"fishshop/category_getNextLevelCategory.action", params,
				dataReturnListener);
	}

	// 获取商品在售商家
	public void getGoodSellerList(OnDataReturnListener dataReturnListener,
			String categoryId, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("categoryId"), categoryId);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer(TaskTag.GOOD_SELLER_LIST,
				"fishshop/category_getGoodsShops.action", params,
				dataReturnListener);
	}

	// 获取商家销售的商品
	public void getSellerGoods(OnDataReturnListener dataReturnListener,
			String shopId, int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), shopId);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer("SellerGoods",
				"fishshop/shop_getShopAllGoods.action", params,
				dataReturnListener);
	}

	// 获取热门商品
	public void getHotItems(OnDataReturnListener dataReturnListener) {

		getDatasFromServer(TaskTag.MONTH_HOT,
				"fishshop/category_getHotCategory.action", null,
				dataReturnListener);
	}

	// 获取评论
	public void getComments(OnDataReturnListener dataReturnListener, String Id,
			int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put(new String("shopId"), Id);
		params.put(new String("pageInfo.indexPageNum"), page + "");
		params.put(new String("pageInfo.size"), item + "");

		getDatasFromServer("Comments", "", params, dataReturnListener);
	}

	/**
	 * 评分接口
	 * 
	 * @author Imissyou
	 * @param taskTag
	 * @param dataReturnListener
	 * @param submitData
	 * @todo TODO
	 *
	 * @return void
	 */
	public void AddAppraise(String taskTag,
			OnDataReturnListener dataReturnListener,
			Map<String, Object> submitData) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("shopId", submitData.get("shopId").toString());

		params.put("userId", submitData.get("userId").toString());
		params.put("ordersId", submitData.get("orderId").toString());
		params.put("commentType", submitData.get("commentType").toString());

		params.put("content", submitData.get("content").toString());
		params.put("weightQuality", submitData.get("weightQuality").toString());

		params.put("freshQuality", submitData.get("freshQuality").toString());
		params.put("speedQuality", submitData.get("speedQuality").toString());
		params.put("anonymity", submitData.get("anonymity").toString());

		/**
		 * TODO 还接口没有
		 */
		getDatasFromServer(taskTag, "fishshop/comment_addComment.action",
				params, dataReturnListener);
	}

	/**
	 * 从服务器获取指定页面的
	 * 
	 * @author Imissyou
	 * @param taskTag
	 *            指定页面
	 * @param dataReturnListener
	 * @param params2
	 *
	 * @return void
	 */
	public void getAllOrder(String taskTag,
			OnDataReturnListener dataReturnListener,String userId) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		String URL = null;
		params.put("userId", userId);
		// 请求页数 每次加以1
		switch (taskTag) {
		//所以订单
		case "ORDER_GOODS":
			params.put("pageInfo.indexPageNum",
					String.valueOf(pageInfo_indexPageNum[0]++));
			URL = "fishshop/orders_getAllUserOrders.action";
			break;
			//未付款订单
		case "WAIT_PAY":
			params.put("pageInfo.indexPageNum",
					String.valueOf(pageInfo_indexPageNum[1]++));
			URL = "fishshop/orders_getUnPayOrders.action";
			break;
			//待收货订单
		case "WAIT_CARGO":
			params.put("pageInfo.indexPageNum",
					String.valueOf(pageInfo_indexPageNum[2]++));
			URL = "fishshop/orders_getUngetOrders.action";
			break;
			//未发货订单
		case "WAIT_SEND":
			params.put("pageInfo.indexPageNum",
					String.valueOf(pageInfo_indexPageNum[3]++));
			URL = "fishshop/orders_getUnSentOrders.action";
			break;
			//未评价订单
		case "APPRIAISE":
			params.put("pageInfo.indexPageNum",
					String.valueOf(pageInfo_indexPageNum[4]++));
			URL = "fishshop/orders_getUnCommentOrders.action";
			break;

		default:
			params.put("pageInfo.indexPageNum",
					String.valueOf(pageInfo_indexPageNum[0]++));
			URL = "fishshop/orders_getAllUserOrders.action";
			break;
		}
		params.put("pageInfo.size", String.valueOf(10));

		getDatasFromServer(taskTag, URL, params, dataReturnListener);
	}
    
	/**
	 * 获取订单物流信息(跟踪物流)
	 * @author Imissyou
	 * @param dataReturnListener
	 * @param postid
	 * @param type
	 * @todo TODO  还进行测试
	 *
	 * @return void
	 */
	public void getLogistics(OnDataReturnListener dataReturnListener,
			String postid, String type) {
		// http://www.kuaidi100.com/query?type=zhongtong&postid=719121392152
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("postid", postid);
		getLogistics("Logistics", params, dataReturnListener);
	}
	
	
	/**
	 *   获取指点订单的详细信息
	 * @author Imissyou
	 * @param dateReturnListener
	 * @param order_id
	 * @todo TODO    服务器那边还没完成
	 *
	 * @return void
	 */
	public void getLogistics_information(OnDataReturnListener dateReturnListener, String order_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Order_Id", order_id);
		String URL = null;
		getDatasFromServer("Order_Logistics", URL, params, dateReturnListener);
	}

	/**
	 * 获取单一点单的详细信息
	 * 
	 * @author Imissyou
	 * @param taskTag
	 * @param dataReturnListener
	 * @param params
	 *
	 * @return void
	 */
	public void getOrder_information(String taskTag,
			OnDataReturnListener dataReturnListener, Map<String, String> params) {
		String URL = null;
		getDatasFromServer(taskTag, URL, params, dataReturnListener);
	}
	
	/**
	 * 获取用户自己的评论
	 * @author Imissyou
	 * @param taskTag
	 * @param dataReturnListener
	 * @param params2
	 * @todo TODO   还没接成功
	 *
	 * @return void
	 */
	public void getMyComment(String taskTag, OnDataReturnListener dataReturnListener ,String id, 
			int page, int item) {
		Map<String, String> params = new IdentityHashMap<String, String>();
		params.put("userId", id);
		params.put("pageInfo.indexPageNum", page + "");
		params.put("pageInfo.size", item + "");
		LogUtils.d(params.toString());
		getDatasFromServer(taskTag, "fishshop/comment_getUserComment.action",
				params, dataReturnListener);
	}
}
