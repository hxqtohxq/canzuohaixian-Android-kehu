package com.zhanjixun.data;

import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

import com.zhanjixun.domain.User;

public class Constants {

	// public static final String host = "http://www.earltech.cn:8080";

	public static final String host = "http://192.168.1.107:8080";

	public static final String JSON_STATE = "serviceResult";

	public static final String JSON_MESSAGE = "resultInfo";

	public static final String XML_USER_FILE = "XML_USER_FILE";

	public static final String XML_USER_ID = "XML_USER_ID";

	public static final String XML_USER_PW = "XML_USER_PW";

	public static final String XML_CAR_FILE = "XML_CAR_FILE";

	public static final String XML_CAR_KEY = "XML_CAR_KEY";

	public static final String XML_USER_HEAD = "XML_USER_HEAD";

	public static final String XML_USER_NAME = "XML_USER_NAME";

	public static final String XML_USER_PHONE = "XML_USER_PHONE";

	public static String HOME_DIR = Environment.getExternalStorageDirectory()
			+ "/fishshop";

	public static String CACHE_DIR = HOME_DIR + "/cache";

	public static User user;

	public static final Map<String, Object> serverErrorMap = new HashMap<String, Object>() {
		{
			put(Constants.JSON_STATE, "false");
			put(Constants.JSON_MESSAGE, "服务器连接错误！");
		}
	};
	public static final Map<String, Object> jsonErrorMap = new HashMap<String, Object>() {
		{
			put(Constants.JSON_STATE, "false");
			put(Constants.JSON_MESSAGE, "JSON数据解析错误！");
		}
	};
}
