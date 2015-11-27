package com.zhanjixun.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.zhanjixun.R;
import com.zhanjixun.data.Constants;
import com.zhanjixun.domain.User;
import com.zhanjixun.fragment.CarFragment;
import com.zhanjixun.fragment.HomeFragment;
import com.zhanjixun.fragment.MeFragment;
import com.zhanjixun.fragment.OrderFragment;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.SPUtil;

public class MainActivity extends FragmentActivity {
	private String location;
	private HomeFragment homeFragment = new HomeFragment();
	private CarFragment carFragment = new CarFragment();
	private OrderFragment orderFragment = new OrderFragment();
	private MeFragment meFragment = new MeFragment();

	private ImageButton imgBtn_home;
	private ImageButton imgBtn_car;
	private ImageButton imgBtn_order;
	private ImageButton imgBtn_me;

	private TextView text_home;
	private TextView text_car;
	private TextView text_order;
	private TextView text_me;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		getUser();
	}

	private void getUser() {
		Constants.user = new User();
		String string = SPUtil.getString(this, Constants.XML_USER_FILE,
				Constants.XML_USER_ID, "");
		Constants.user.setId(string);
		String string2 = SPUtil.getString(this, Constants.XML_USER_FILE,
				Constants.XML_USER_PHONE, "");
		Constants.user.setPhone(string2);
		String string3 = SPUtil.getString(this, Constants.XML_USER_FILE,
				Constants.XML_USER_PW, "");
		Constants.user.setPassword(string3);
		String string4 = SPUtil.getString(this, Constants.XML_USER_FILE,
				Constants.XML_USER_NAME, "");
		Constants.user.setName(string4);
		String string5 = SPUtil.getString(this, Constants.XML_USER_FILE,
				Constants.XML_USER_HEAD, "");
		Constants.user.setHeadURL(string5);
	}

	private void initView() {

		// ��ʼ���������ؼ�
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

	// �����������¼�
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@SuppressLint("HandlerLeak")
	public Handler hanler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				updateAdvertisment(homeFragment.getViewPager());
				break;
			default:
				break;
			}
		};
	};

	private void updateAdvertisment(ViewPager viewPager) {
		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
	}
}
