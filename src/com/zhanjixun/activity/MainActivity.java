package com.zhanjixun.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.data.Constants;
import com.zhanjixun.fragment.CarFragment;
import com.zhanjixun.fragment.HomeFragment;
import com.zhanjixun.fragment.MeFragment;
import com.zhanjixun.fragment.OrderFragment;
import com.zhanjixun.utils.GsonUtil;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.SPUtil;

public class MainActivity extends FragmentActivity {

	private HomeFragment homeFragment;
	private CarFragment carFragment;
	private OrderFragment orderFragment;
	private MeFragment meFragment;

	private ImageButton imgBtn_home;
	private ImageButton imgBtn_car;
	private ImageButton imgBtn_order;
	private ImageButton imgBtn_me;

	private TextView text_home;
	private TextView text_car;
	private TextView text_order;
	private TextView text_me;
	//private boolean locationState = false;
	
	public Handler hanler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Log.i("bb", "receve a message");
				updateAdvertisment(homeFragment.getViewPager());
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		carTextData();
	}

	private void carTextData() {
		GsonUtil gson = new GsonUtil();
		gson.put("sellerName", "海格号");
		List<Map<String, String>> goodsList = new ArrayList<Map<String, String>>();
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("goodName", "秋刀鱼");
		map.put("goodPicUrl", "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png");
		map.put("goodSize", "中等大小");
		map.put("goodPirce", "Y15.00/kg");
		map.put("goodBuyNumber", "5");
		goodsList.add(map);
		gson.put("goodsList", goodsList);
		SPUtil.saveString(getApplicationContext(), Constants.XML_CAR_FILE, Constants.XML_CAR_KEY, gson.toString());
	}

	private void initView() {

		// 初始化导航栏控件
		imgBtn_home = (ImageButton) findViewById(R.id.main_btn_home);
		imgBtn_car = (ImageButton) findViewById(R.id.main_btn_car);
		imgBtn_order = (ImageButton) findViewById(R.id.main_btn_order);
		imgBtn_me = (ImageButton) findViewById(R.id.main_btn_me);

		text_home = (TextView) findViewById(R.id.main_text_home);
		text_car = (TextView) findViewById(R.id.main_text_car);
		text_order = (TextView) findViewById(R.id.main_text_order);
		text_me = (TextView) findViewById(R.id.main_text_me);

		updateNavigationBarBackground(0);
		updateContentFragment(0);
	}

	// 导航栏监听事件
	public void onClick(View v) {
		String tag = (String) v.getTag();
		LogUtils.v(tag);

		int index = 0;
		if (tag.equals("HOME")) {
			index = 0;
		} else if (tag.equals("CAR")) {
			index = 1;
		} else if (tag.equals("ORDER")) {
			index = 2;
		} else if (tag.equals("ME")) {
			index = 3;
		}
		updateNavigationBarBackground(index);
		updateContentFragment(index);
	}

	private void updateNavigationBarBackground(int index) {
		LogUtils.v(index + "");
		imgBtn_home.setImageResource(R.drawable.activity_main_home_normal);
		imgBtn_car.setImageResource(R.drawable.activity_main_car_normal);
		imgBtn_order.setImageResource(R.drawable.activity_main_order_normal);
		imgBtn_me.setImageResource(R.drawable.activity_main_me_normal);

		text_home.setTextColor(getResources().getColor(R.color.theme_normal));
		text_car.setTextColor(getResources().getColor(R.color.theme_normal));
		text_order.setTextColor(getResources().getColor(R.color.theme_normal));
		text_me.setTextColor(getResources().getColor(R.color.theme_normal));

		switch (index) {
		case 0:
			imgBtn_home.setImageResource(R.drawable.activity_main_home_pressed);
			text_home.setTextColor(getResources().getColor(R.color.theme));
			break;
		case 1:
			imgBtn_car.setImageResource(R.drawable.activity_main_car_pressed);
			text_car.setTextColor(getResources().getColor(R.color.theme));
			break;
		case 2:
			imgBtn_order
					.setImageResource(R.drawable.activity_main_order_pressed);
			text_order.setTextColor(getResources().getColor(R.color.theme));
			break;
		case 3:
			imgBtn_me.setImageResource(R.drawable.activity_main_me_pressed);
			text_me.setTextColor(getResources().getColor(R.color.theme));
			break;

		default:
			break;
		}
	}

	private void updateContentFragment(int index) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		switch (index) {
		case 0:
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
			}
			ft.replace(R.id.main_fragment_content, homeFragment);
			ft.commit();
			return;
		case 1:
			if (carFragment == null) {
				carFragment = new CarFragment();
			}
			ft.replace(R.id.main_fragment_content, carFragment);
			ft.commit();
			return;

		case 2:
			if (orderFragment == null) {
				orderFragment = new OrderFragment();
			}
			ft.replace(R.id.main_fragment_content, orderFragment);
			ft.commit();
			return;
		case 3:
			if (meFragment == null) {
				meFragment = new MeFragment();
			}
			ft.replace(R.id.main_fragment_content, meFragment);
			ft.commit();
			return;
		default:
			return;
		}
	}
	
	private void updateAdvertisment(ViewPager viewPager){
		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
	}
}
