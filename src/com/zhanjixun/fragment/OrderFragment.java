package com.zhanjixun.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.LoginActivity;
import com.zhanjixun.adapter.MyPagerAdapter;
import com.zhanjixun.adapter.OrderListAdapter;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.Order;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.ScreenUtil;

@SuppressLint({ "ResourceAsColor", "CutPasteId", "InflateParams" })
public class OrderFragment extends Fragment implements OnDataReturnListener {
	private TextView t1;
	private TextView t2;
	private TextView t3;
	private TextView t4;
	private TextView t5;
	private ImageView cursor;
	private ViewPager pager;
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	private ListView listView_all;
	private ListView listView_waitSend;
	private ListView listView_waitCargo;
	private ListView listView_waitAppraise;
	private ListView listView_waitpay;
	// ��Ʒ�б�
	private List<Order> Order_listData;
	private String UserId;

	// ��ȡ�û����ж�����URL
	public static String All_ORDER = "ORDER_GOODS";
	public static String WAIT_PAY = "WAIT_PAY";
	public static String WAIT_CARGO = "WAIT_CARGO";
	public static String WAIT_SEND = "WAIT_SEND";
	public static String APPRIAISE = "APPRIAISE";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Order_listData = new ArrayList<Order>();
		
		initViews();
		initData();
	}

	@SuppressLint("InflateParams")
	private void initViews() {
		// TextView
		t1 = (TextView) getActivity().findViewById(R.id.fragment_order_all);
		t2 = (TextView) getActivity().findViewById(R.id.fragment_order_waitPay);
		t3 = (TextView) getActivity()
				.findViewById(R.id.fragment_order_waitSend);
		t4 = (TextView) getActivity().findViewById(
				R.id.fragment_order_waitCargo);
		t5 = (TextView) getActivity().findViewById(
				R.id.fragment_order_waitAppraise);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
		t4.setOnClickListener(new MyOnClickListener(3));
		t5.setOnClickListener(new MyOnClickListener(4));

		// ImageView
		cursor = (ImageView) getActivity()
				.findViewById(R.id.image_order_cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.link)
				.getWidth();// ��ȡͼƬ���

		int screenW = ScreenUtil.getWidth(getActivity());// ��ȡ�ֱ��ʿ��
		offset = (screenW / 5 - bmpW) / 2;// ����ƫ����
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// ���ö�����ʼλ��

		// ViewPage
		pager = (ViewPager) getActivity().findViewById(
				R.id.order_home_viewPager);
		ArrayList<View> list = new ArrayList<View>();
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.order_home_listview_t1, null);
		View view1 = LayoutInflater.from(getActivity()).inflate(
				R.layout.order_home_listview_t1, null);
		View view2 = LayoutInflater.from(getActivity()).inflate(
				R.layout.order_home_listview_t1, null);
		View view3 = LayoutInflater.from(getActivity()).inflate(
				R.layout.order_home_listview_t1, null);
		View view4 = LayoutInflater.from(getActivity()).inflate(
				R.layout.order_home_listview_t1, null);

		listView_all = (ListView) view
				.findViewById(R.id.order_home_listView_T1);
		listView_waitpay = (ListView) view1
				.findViewById(R.id.order_home_listView_T1);
		listView_waitCargo = (ListView) view2
				.findViewById(R.id.order_home_listView_T1);
		listView_waitSend = (ListView) view3
				.findViewById(R.id.order_home_listView_T1);
		listView_waitAppraise = (ListView) view4
				.findViewById(R.id.order_home_listView_T1);

		// ���view��ViewPager����
		list.add(view);
		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);
		pager.setAdapter(new MyPagerAdapter(list));
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}


	/**
	 * ��ʼ������
	 * 
	 * @author Imissyou TODO ������
	 * @return void
	 */
	private void initData() {
		this.UserId = Constants.user.getId();
		//�û�idΪ�����ص�¼
		if(UserId.equals(null) && UserId == null) {
			Intent intent = new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
		} else {
		Log.v("miss UserId------->", UserId);
	    //�������ж���ҳ��
		DC.getInstance().getAllOrder(All_ORDER, this,UserId );
		//����ȴ�����ҳ��
		DC.getInstance().getAllOrder(WAIT_CARGO, this, UserId);
		//����ȵȸ���ҳ��
		DC.getInstance().getAllOrder(WAIT_PAY, this, UserId);
		//����ȴ�����ҳ��
		DC.getInstance().getAllOrder(WAIT_SEND, this, UserId);
		//����ȴ�����ҳ��
		DC.getInstance().getAllOrder(APPRIAISE, this, UserId);
		}
	}

	private void setTextViewBg(int index) {
		t1.setTextColor(0xaa000000);
		t2.setTextColor(0xaa000000);
		t3.setTextColor(0xaa000000);
		t4.setTextColor(0xaa000000);
		t5.setTextColor(0xaa000000);
		switch (index) {
		case 0:
			t1.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 1:
			t2.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 2:
			t3.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 3:
			t4.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 4:
			t5.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		default:
			break;
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
		int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����
		int third = one * 3;
		int four = one * 4;

		@Override
		public void onPageSelected(int i) {
			Animation animation = null;
			switch (i) {
			case 0:
				animation = new TranslateAnimation(one * currIndex, 0, 0, 0);
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else {
					animation = new TranslateAnimation(one * currIndex, one, 0,
							0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else {
					animation = new TranslateAnimation(one * currIndex, two, 0,
							0);
				}
				break;
			case 3:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, third, 0, 0);
				} else {
					animation = new TranslateAnimation(one * currIndex, third,
							0, 0);
				}
				break;
			case 4:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, four, 0, 0);
				} else {
					animation = new TranslateAnimation(one * currIndex, four,
							0, 0);
				}
				break;
			}
			currIndex = i;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);
			cursor.startAnimation(animation);
			setTextViewBg(i);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			pager.setCurrentItem(index);
			setTextViewBg(index);
		}
	}

	@SuppressWarnings("unchecked")
	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		Log.v("OrderData", result.toString() + "mm");
		if (result != null || result.size() != 0 || result.isEmpty()) {
			Map<String, Object> map = (Map<String, Object>) result
					.get("resultParm");
			Log.v("BB", map.toString() + " b");

			List<Map<String, Object>> listData = (List<Map<String, Object>>) map
					.get("ordersList");
			setListData(listData, taskTag);
			Log.v("BB", listData.toString() + "");
		}
	};

	/**
	 * װ������List<Order>
	 * 
	 * @author Imissyou
	 * @param listData
	 * @todo TODO ���غ��޷�����ҳ��
	 *
	 * @return List<Order>
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void setListData(List<Map<String, Object>> listData, String taskTag) {
		Map<String, Object> listMap = new HashMap<String, Object>();
		List<Order> orderlist = new ArrayList<Order>();
		for (int i = 0; i < listData.size(); i++) {
			Order order = new Order();
			listMap = listData.get(i);
			Log.v("set", listMap.toString() + "bb");
			String state = listMap.get("state").toString();
			Log.v("state", state + "state");
			order.setStute(Integer.parseInt(state));
			order.setOrder_id(listMap.get("ordersId").toString());
			order.setOrdersDetail((List<Map<String, Object>>) listMap
					.get("ordersDetail"));
			Log.v("listData", listMap.get("ordersDetail") + "null");
			order.setUser_Id(listMap.get("userId").toString());
			order.setShopKeeperName(listMap.get("shopKeeperName").toString());
			order.setShopId(listMap.get("shopId").toString());
			order.setPostagePrice(listMap.get("postagePrice").toString());
			order.setTotalprice(listMap.get("totalprice").toString());
			orderlist.add(order);
		}
		Log.d("listData---->", orderlist.toString()+"MMM");
		if (orderlist == null && orderlist.size() == 0) {
			return ;
		}
		Log.v("loadPage", orderlist.toString());
		//�Զ�����ָ��ҳ�������
		switch (taskTag) {
		case "ORDER_GOODS":
			//���ж���ҳ��
			listView_all.setAdapter(new OrderListAdapter(getActivity(),
					orderlist));
			break;
		case "WAIT_PAY":
			// ������ҳ��
			listView_waitpay.setAdapter(new OrderListAdapter(getActivity(),
					orderlist));
			break;
		case "WAIT_CARGO":
			// ������ҳ��
			listView_waitCargo.setAdapter(new OrderListAdapter(getActivity(),
					orderlist));
			break;
		case "WAIT_SEND":
			// ������ҳ��
			listView_waitSend.setAdapter(new OrderListAdapter(getActivity(),
					orderlist));
			break;
		case "APPRIAISE":
			// ������ҳ��
			listView_waitAppraise.setAdapter(new OrderListAdapter(getActivity(),
					orderlist));
			break;
		default:
			listView_all.setAdapter(new OrderListAdapter(getActivity(),
					orderlist));
			break;
		}		
		Log.v("order_list", orderlist.toString() + "");
	}
}