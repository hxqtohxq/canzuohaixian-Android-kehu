package com.zhanjixun.data;

import java.util.IdentityHashMap;
import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;

public class DC extends DataCenter {
	private static DC dc;

	private DC() {
	}

	// ����ģʽ
	public static DC getInstance() {
		if (dc == null) {
			synchronized (DC.class) {
				if (dc == null)
					dc = new DC();
			}
		}
		return dc;
	}

	// ��ȡ����������Ʒ
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
