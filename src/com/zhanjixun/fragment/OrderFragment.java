package com.zhanjixun.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
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
import com.zhanjixun.adapter.OrderListAdapter;
import com.zhanjixun.utils.ScreenUtil;

@SuppressLint({ "ResourceAsColor", "CutPasteId" })
public class OrderFragment extends Fragment {
	private TextView t1;
	private TextView t2;
	private TextView t3;
	private TextView t4;
	private TextView t5;
	private ImageView cursor;
	private ViewPager pager;
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private List<Map<String,Object>> listData;
	
	private ListView listView_all;
	private ListView listView_waitSend;
	private ListView listView_waitCargo;
	private ListView listView_waitAppraise;
	private ListView listView_waitpay;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
		initData();
	}

	@SuppressLint({ "ResourceAsColor", "InflateParams" })
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
				.getWidth();// 获取图片宽度

		int screenW = ScreenUtil.getWidth(getActivity());// 获取分辨率宽度
		offset = (screenW / 5 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置

		// ViewPage
		pager = (ViewPager) getActivity().findViewById(
				R.id.order_home_viewPager);
		ArrayList<View> list = new ArrayList<View>();
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.order_home_listview_t1, null);
		View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.order_home_listview_t1, null);
		View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.order_home_listview_t1, null);
		View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.order_home_listview_t1, null);
		View view4 = LayoutInflater.from(getActivity()).inflate(R.layout.order_home_listview_t1, null);
		
		listView_all = (ListView) view.findViewById(R.id.order_home_listView_T1);
		listView_waitpay = (ListView) view1.findViewById(R.id.order_home_listView_T1);
		listView_waitCargo = (ListView) view2.findViewById(R.id.order_home_listView_T1);
		listView_waitSend = (ListView) view3.findViewById(R.id.order_home_listView_T1);
		listView_waitAppraise = (ListView) view4.findViewById(R.id.order_home_listView_T1);
		
		
		
//		//对应的 ListView 的Adapter
		//所有订单页面
		listView_all.setAdapter( new 
				OrderListAdapter(getActivity(),listData,0));
//		//待付款页面
		listView_waitpay.setAdapter(new 
				OrderListAdapter(getActivity(),listData,OrderListAdapter.ORDER_PAY));
//		//待发货页面
		listView_waitSend.setAdapter(new 
				OrderListAdapter(getActivity(),listData,OrderListAdapter.ORDER_SEND));
		//待收货页面
		listView_waitCargo.setAdapter(new 
				OrderListAdapter(getActivity(),listData, OrderListAdapter.ORDER_CARGO));
//		待评价页面
		listView_waitAppraise.setAdapter(new 
				OrderListAdapter(getActivity(),listData,OrderListAdapter.ORDER_APPRAIASE));
		
		//添加view到ViewPager里面
		list.add(view);
		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);
		pager.setAdapter(new MyPagerAdapter(list));
		pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
    
	/**
	 * 初始化数据
	 * @author Imissyou
	 *
	 * @return void
	 */
	private void initData() {

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

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量
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
			animation.setFillAfter(true);// True:图片停在动画结束位置
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
	final static class ListViewHolder {
		ListView order_listview_t1;
		ListView order_listview_t2;
		ListView order_listview_t3;
		ListView order_listview_t4;
		ListView order_listview_t5;
	}
    
	/**
	 * PagerAdpater
	 * @author Imissyou
	 * @Time  2015年11月23日
	 *
	 */
	public class MyPagerAdapter extends PagerAdapter {
		List<View> list = new ArrayList<View>();

		public MyPagerAdapter(ArrayList<View> list2) {
			this.list = list2;
		}

		//移动当前的Viewpage
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ViewPager pViewPager = ((ViewPager) container);
			pViewPager.removeView(list.get(position));
		}

	    //判断是否由对象产生页面
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		//返回当前分页数
		@Override
		public int getCount() {
			return list.size();
		}

		//返回一个对象该对象表明PagerAapter选择哪个对象放在当前的ViewPager
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			ViewPager pViewPager = ((ViewPager) arg0);
			pViewPager.addView(list.get(arg1));
			return list.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
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
	};
}