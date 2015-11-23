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
    
	//�û���
	private TextView user_name;
	//�û��ֻ���
	private TextView user_phone;
	//�û���ַ
	private TextView user_address;
	//�㵥��������
	private TextView order_sun_number;
	//�������ܽ��
	private TextView order_sun_money;
	//��������Ʒ����
	private TextView order_shopName;
	//������״̬
	private TextView logistics_status;
	//������ʣ������
	private TextView logistics_surplusDay;
	//������ʣ��ʱ
	private TextView logistics_surplusHour;
	//ȷ�϶����İ�ť
	private Button ensureBtn;
	//�˷�
	private TextView logistics_money;
	//��Ʒ����item
	private ListView listView ;
	//ListView   Adapter
	private Logistics_information_ItmeAdapter myAdapter;
	
	
	
	//item���������
	private String ListURL;
	List<Map<String,Object>> listData;
	//Pageҳ����������
	private String PageURL;
	Map<String , String> pageData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_logistics__information);
		init();
		initIntentData();
		//��ȡ��������
	    pageData = getPageData();
	    listData = getListData();
		myAdapter = new Logistics_information_ItmeAdapter(this, listData);
		
		//��������
		loadPage(pageData);
		listView.setAdapter(myAdapter);
			
	}


   private void initIntentData() {
		Intent intent = getIntent();
		String order_id = intent.getStringExtra("order_id");
		Toast.makeText(this, order_id, Toast.LENGTH_LONG).show();
		
	}


/**
    * ��ȡ���Item���������
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
     * ��ʼ���ؼ�
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
     //��ȡ��������
	 private Map<String, String> getPageData() {
		 
		 Intent intent = getIntent();
		 String order_id = intent.getStringExtra("order_id");
		 Map<String,String> map = new HashMap<String, String>();
		 map.put("order_id", "order_id");
		 DC.getInstance().getDatasFromServer("order_logisitcs_pageData", PageURL, map, this);
		 
		return map;		
		 //�������� 
		}


	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		// TODO Auto-generated method stub
		
	}
}
