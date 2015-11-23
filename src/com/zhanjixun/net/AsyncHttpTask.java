package com.zhanjixun.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import android.content.Intent;
import android.os.AsyncTask;

import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.JsonUtil;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.ResultUtils;
import com.zhanjixun.utils.StringUtil;

public class AsyncHttpTask extends AsyncTask<Object, Intent, String> {

	private String taskTag;

	private OnDataReturnListener dataReturnListener;

	public AsyncHttpTask(String taskTag, OnDataReturnListener dataReturnListener) {
		super();
		this.taskTag = taskTag;
		this.dataReturnListener = dataReturnListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Object... executeParams) {
		String url = (String) executeParams[0];
		@SuppressWarnings("unchecked")
		Map<String, String> parames = (Map<String, String>) executeParams[1];
		try {
			return HttpConnection.doPOSTMethod(url, parames);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		LogUtils.d(result);
		Map<String, Object> resultMap = null;
		if (StringUtil.isEmptyString(result)) {
			resultMap = ResultUtils.serverErrorMap();
		} else {
			try {
				resultMap = JsonUtil.getJosn(result);
			} catch (Exception e) {
				resultMap = ResultUtils.jsonErrorMap();
			}
		}
		this.dataReturnListener.onDataReturn(taskTag, resultMap);
	}
}
