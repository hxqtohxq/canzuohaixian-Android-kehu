package com.zhanjixun.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhanjixun.R;
import com.zhanjixun.adapter.TimelineAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 物流详情页面
 * 
 * @author Imissyou
 *
 */
public class Order_Logisitice_Activity extends Activity {

	// 物流详情信息
	private ListView listView;
	// 商品图片
	private ImageView order_image;
	// 物流状态
	private TextView logistice_status;
	// 物流公司
	private TextView logistice_company;
	// 物流编号
	private TextView logistice_code;
	// 物流电话
	private TextView logistice_phone;

	// 页面hear的数据
	private Map<String, Object> mapData;

	private TimelineAdapter timeAdapter;

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
	 * 获取页面的信息 为数据填充做准备
	 */
	private Map<String, Object> getData() {

		// Test
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", "13763012723");
		map.put("code", "1234567890");
		map.put("status", "正在发货中");
		map.put("company", "圆通快递");
		// map.put("order_image", R.drawable.maoxia);
		return map;
	}

	/**
	 * 初始化控件.
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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", "2015-05-20 10:15");
		map.put("title", "深圳龙华收件");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("time", "2015-05-21 9:25");
		map.put("title", "离开深圳，发往上海");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("time", "2015-05-22 12:55");
		map.put("title", "上海浦江集散中心");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("time", "2015-05-25 9:30");
		map.put("title", "已收取快件");
		list.add(map);
		return list;
	}

}
