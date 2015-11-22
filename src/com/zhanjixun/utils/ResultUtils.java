package com.zhanjixun.utils;

import java.util.HashMap;
import java.util.Map;

import com.zhanjixun.data.Constants;

public class ResultUtils {
	public static Map<String, Object> serverErrorMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_STATE, "0");
		map.put(Constants.JSON_MESSAGE, "���������Ӵ���");
		return map;
	}

	public static Map<String, Object> jsonErrorMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.JSON_STATE, "0");
		map.put(Constants.JSON_MESSAGE, "JSON���ݽ�������");
		return map;
	}
}
