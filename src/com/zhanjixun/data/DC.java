package com.zhanjixun.data;

import java.util.IdentityHashMap;
import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;

public class DC extends DataCenter {
	private static DC dc;

	private DC() {
	}

	// 单例模式
	public static DC getInstance() {
		if (dc == null) {
			synchronized (DC.class) {
				if (dc == null)
					dc = new DC();
			}
		}
		return dc;
	}

	// 获取二级分类商品
	public void getGoodList(OnDataReturnListener dataReturnListener,
			String categoryId, int indexPageNum, int size) {
		Map<String, String> params = new IdentityHashMap<String, String>();

		params.put(new String("categoryId"), categoryId);
		params.put(new String("pageInfo.indexPageNum"), indexPageNum + "");
		params.put(new String("pageInfo.size"), size + "");

		/*getDatasFromServer("GoodList",
				"fishshop/category_getNextLevelCategory.action", params,
				dataReturnListener); */
		getDatasFromServer("GoodList",
				"Test/index.jsp", params,
				dataReturnListener);
	}
}
