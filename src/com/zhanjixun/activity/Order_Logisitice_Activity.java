package com.zhanjixun.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.adapter.TimelineAdapter;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.interfaces.OnDataReturnListener;

/**
 * ��������ҳ��
 * 
 * @author Imissyou
 *
 */
public class Order_Logisitice_Activity extends BackActivity implements OnDataReturnListener {

	// ����������Ϣ
	private ListView listView;
	// ��ƷͼƬ
	private ImageView order_image;
	// ����״̬
	private TextView logistice_status;
	// ������˾
	private TextView logistice_company;
	// �������
	private TextView logistice_code;
	// �����绰
	private TextView logistice_phone;

	// ҳ��hear������
	private Map<String, Object> mapData;

	private TimelineAdapter timeAdapter;
	private List<Map<String, Object>> mapdata;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_logistics_state_information);
		init();
		mapData = getData();
		loadData(mapData);
		listView.setAdapter(timeAdapter);
	}

	private boolean loadData(Map<String, Object> data) {
		if (null != data) {
			logistice_code.setText(data.get("code").toString());
			logistice_phone.setText(data.get("phone").toString());
			logistice_status.setText(data.get("status").toString());
			logistice_company.setText(data.get("company").toString());
			// order_image.setImageResource(data.get("order_image").hashCode());

			return true;
		}
		return false;
	}

	/**
	 * ��ȡҳ�����Ϣ Ϊ���������׼��
	 */
	private Map<String, Object> getData() {
        
//		DC.getInstance().getLogistics(this, postid, type);
		// Test
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", "13763012723");
		map.put("code", "1234567890");
		map.put("status", "���ڷ�����");
		map.put("company", "Բͨ���");
		// map.put("order_image", R.drawable.maoxia);
		return map;
	}

	/**
	 * ��ʼ���ؼ�.
	 */
	private void init() {
		listView = (ListView) findViewById(R.id.logistice_informantion_listview);
		order_image = (ImageView) findViewById(R.id.logistics_state_information_Orderimage);
		logistice_status = (TextView) findViewById(R.id.logistics_state_information);
		logistice_code = (TextView) findViewById(R.id.logistics_status_information_code);
		logistice_company = (TextView) findViewById(R.id.order_logistics_information_company);
		logistice_phone = (TextView) findViewById(R.id.logistics_status_information_telPhone);
		timeAdapter = new TimelineAdapter(this, getListData());
	}

	private List<Map<String, Object>> getListData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String,String> params = new HashMap<>();
		String type = "zhongtong";
		String postid =  "719121392152";
		params.put("type", "zhongtong");
		params.put("postid", "719121392152");
//		DC.getInstance().getLogistics("Logistics", params, this);
		DC.getInstance().getLogistics(this, postid, type);
        
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", "2015-05-20 10:15");
		map.put("title", "���������ռ�");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("time", "2015-05-21 9:25");
		map.put("title", "�뿪���ڣ������Ϻ�");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("time", "2015-05-22 12:55");
		map.put("title", "�Ϻ��ֽ���ɢ����");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("time", "2015-05-25 9:30");
		map.put("title", "����ȡ���");
		list.add(map);
		
		return list;
	}

	/* ���� Javadoc��
	 * @todo
	 *
	 * @see com.zhanjixun.interfaces.OnDataReturnListener#onDataReturn(java.lang.String, java.util.Map)
	 */
	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		this.mapdata = (List<Map<String, Object>>) result.get("data");
		Log.v("mm", mapdata.isEmpty() + mapdata.toString() + "33");
	}

}
