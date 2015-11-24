package com.zhanjixun.data;

import java.util.IdentityHashMap;
import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;

public class DC extends DataCenter {

	private static DC dc;

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
	}

	/**
	 * 找回密码请求短信验证码
	 * 
	 * @param dataReturnListener
	 * @param id
	 */
	public void requestCodeForFindPassword(
			OnDataReturnListener dataReturnListener, String id) {

		// requestMsgcode
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
		// checkMsgCode
	}

	public void findPassword(OnDataReturnListener dataReturnListener,
			String id, String pw) {
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
	
	
	/**
	 * 添加评论
	 * @param dataReturnListener
	 * @param order_id
	 * @param phone_number
	 * @param conmment_kind
	 * @param conmment_text
	 * @param conmment_weight
	 * @param conmment_fresh
	 * @param conmment_speed
	 * @param anonymous
	 */
	public void AddAppraise(OnDataReturnListener dataReturnListener,String order_id,String phone_number,
			String conmment_kind,String conmment_text,
			float conmment_weight ,float conmment_fresh ,
			float conmment_speed , boolean anonymous) {
		Map<String , String> params = new IdentityHashMap<String,String>();
		params.put(new String("order_id"), order_id);
		params.put(new String("phone_number"), phone_number);
		params.put(new String("conmment_kind"), conmment_kind);
		params.put(new String("conmment_text"), conmment_text);
		params.put(new String("conmment_weight"), conmment_weight + "");
		params.put(new String("conmment_fresh"), conmment_fresh + "");
		params.put(new String("conmment_speed"), conmment_speed +"");
		params.put(new String("anonymous"),String.valueOf(anonymous));
		
		/**
		 * 接口还没呢
		 */
		
		getDatasFromServer(TaskTag.GOOD_LIST,
				"fishshop/comment_addComment.action", params,
				dataReturnListener);
	}
	
	public void AddAppraise(String taskTag,OnDataReturnListener dataReturnListener,Map<String,Object> submitData) {
		Map<String , String> params = new IdentityHashMap<String,String>();
		params.put(new String("order_id"), submitData.get("order_id").toString());
		
		params.put(new String("phone_number"), submitData.get("user_id").toString());
		params.put(new String("conmment_kind"), submitData.get("conmment_kind").toString());
		params.put(new String("conmment_text"), submitData.get("conmment_text").toString());
		params.put(new String("conmment_weight"), submitData.get("conmment_weight").toString());
		params.put(new String("conmment_fresh"), submitData.get("conmment_fresh").toString());
		params.put(new String("conmment_speed"), submitData.get("conmment_speed").toString());
		params.put(new String("anonymous"),submitData.get("anonymous").toString());
		
		
		/**
		 * TODO 还接口没有
		 */
		getDatasFromServer(taskTag,
				"fishshop/comment_addComment.action", params,
				dataReturnListener);
	}
}
