package com.zhanjixun.data;

import android.os.Environment;

import com.zhanjixun.domain.User;

public class Constants {

	public static final String host = "http://192.168.1.107:8080";

	public static final String JSON_STATE = "serviceResult";

	public static final String JSON_MESSAGE = "resultInfo";

	public static final String XML_USER_FILE = "userInfo";

	public static final String XML_USER_ID = "userName";

	public static final String XML_USER_PW = "userPassword";

	public static final String XML_CAR_FILE = "carInfo";

	public static final String XML_CAR_KEY = "Json";
	
	public static String HOME_DIR = Environment.getExternalStorageDirectory()
			+ "/OeacnTable";
	public static String CACHE_DIR = HOME_DIR + "/cache";

	public static User user;
}
