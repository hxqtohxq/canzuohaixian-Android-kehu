package com.zhanjixun.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.adapter.Logistics_information_ItmeAdapter;
import com.zhanjixun.data.DC;
import com.zhanjixun.interfaces.OnDataReturnListener;

public class Order_logistics_information_Activity extends Activity implements OnDataReturnListener{
    
	//用户名
	private TextView user_name;
	//用户手机号
	private TextView user_phone;
	//用户地址
	private TextView user_address;
	//点单的总数量
	private TextView order_sun_number;
	//订单的总金额
	private TextView order_sun_money;
	//订单的商品店名
	private TextView order_shopName;
	//订单的状态
	private TextView logistics_status;
	//订单的剩余日期
	private TextView logistics_surplusDay;
	//订单的剩余时
	private TextView logistics_surplusHour;
	//确认订单的按钮
	private Button ensureBtn;
	//运费
	private TextView logistics_money;
	//商品订单item
	private ListView listView ;
	//ListView   Adapter
	private Logistics_information_ItmeAdapter myAdapter;
	
	
	
	//item的填充数据
	private String ListURL;
	List<Map<String,Object>> listData;
	//Page页面的填充数据
	private String PageURL;
	Map<String , String> pageData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_logistics__information);
		init();
		initIntentData();
		//获取填充的数据
	    pageData = getPageData();
	    listData = getListData();
		myAdapter = new Logistics_information_ItmeAdapter(this, listData);
		
		//加载数据
		loadPage(pageData);
		listView.setAdapter(myAdapter);
			
	}


   private void initIntentData() {
		Intent intent = getIntent();
		String order_id = intent.getStringExtra("order_id");
		Toast.makeText(this, order_id, Toast.LENGTH_LONG).show();
		
	}


/**
    * 获取填充Item里面的数据
    * @return listData List<Map<String, Object>>
    */


	private List<Map<String, Object>> getListData() {
		return null;
	}





	private void loadPage(Map<String, String> mapData) {
		user_name.setText(mapData.get("name"));
		user_phone.setText(mapData.get("phone"));
		logistics_status.setText(mapData.get("status"));
	}





	/**
     * 初始化控件
     */
	private void init() {

		logistics_status = (TextView) findViewById(R.id.logistice_information_status_Title);
		logistics_surplusDay = (TextView) findViewById(R.id.logistice_information_status_Day);
		logistics_surplusHour = (TextView) findViewById(R.id.logistice_information_status_Hour);
		user_name = (TextView) findViewById(R.id.logistice_information_status_user_name);
		user_phone = (TextView) findViewById(R.id.logistice_information_status_user_phone);
		user_address = (TextView) findViewById(R.id.logistice_information_status_user_address);
		order_sun_number = (TextView) findViewById(R.id.logistice_information_status_shop_number);
		order_sun_money = (TextView) findViewById(R.id.logistice_information_status_shop_Allmoney);
		logistics_money = (TextView) findViewById(R.id.logistice_information_logistics_money);
		
		
		ensureBtn = (Button) findViewById(R.id.logistice_information_status_ensureBtn);
		listView = (ListView) findViewById(R.id.logistice_information_shop_item);
	}
     //获取填充的数据
	 private Map<String, String> getPageData() {
		 
		 Intent intent = getIntent();
		 String order_id = intent.getStringExtra("order_id");
		 Map<String,String> map = new HashMap<String, String>();
		 map.put("order_id", "order_id");
		 DC.getInstance().getDatasFromServer("order_logisitcs_pageData", PageURL, map, this);
		 
		return map;		
		 //测试数据 
		}


	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		// TODO Auto-generated method stub
		
	}
}
