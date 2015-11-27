package com.zhanjixun.data;

import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.net.AsyncHttpTask;

public class DataCenter {
	public void getDatasFromServer(String taskTag, String url,
			Map<String, String> params, OnDataReturnListener dataReturnListener) {

		new AsyncHttpTask(taskTag, dataReturnListener).execute(Constants.host
				+ "/" + url, params);

	}

	public void getLogistics(String taskTag, Map<String, String> params,
			OnDataReturnListener dataReturnListener) {
		// http://www.kuaidi100.com/query?type=zhongtong&postid=719121392152&id=1
		new AsyncHttpTask(taskTag, dataReturnListener).execute(
				"http://www.kuaidi100.com/query", params);

	}
}
