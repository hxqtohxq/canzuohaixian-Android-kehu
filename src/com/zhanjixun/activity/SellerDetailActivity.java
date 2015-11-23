package com.zhanjixun.activity;

import java.util.ArrayList;

import com.zhanjixun.R;
import com.zhanjixun.fragment.SellerDetailCommentFragment;
import com.zhanjixun.fragment.SellerDetailGoodFragment;
import com.zhanjixun.fragment.SellerDetailSellerFragment;
import com.zhanjixun.utils.ScreenUtil;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SellerDetailActivity extends FragmentActivity {

	private TextView tv_goods;
	private TextView tv_comments;
	private TextView tv_sellers;

	private ImageView cursor;
	private ViewPager pager;
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	
	private ArrayList<Fragment> fragmentList;
	private MyPagerAdapter myPagerAdapter;
	private MyOnPageChangeListener myPagerListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seller_detail);
		initView();
	}

	private void initView() {
		tv_goods = (TextView) findViewById(R.id.id_seller_detail_goods);
		tv_comments = (TextView) findViewById(R.id.id_seller_detail_comment);
		tv_sellers = (TextView) findViewById(R.id.id_seller_detail_seller);
		cursor = (ImageView) findViewById(R.id.image_seller_detail_cursor);
		pager = (ViewPager) findViewById(R.id.id_seller_detail_viewpager);
		
		tv_goods.setOnClickListener(new MyClickListener(0));
		tv_comments.setOnClickListener(new MyClickListener(1));
		tv_sellers.setOnClickListener(new MyClickListener(2));
		// ��ȡͼƬ���
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.link).getWidth();
		int screenW = ScreenUtil.getWidth(this);// ��ȡ�ֱ��ʿ��
		offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
		
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new SellerDetailGoodFragment());
		fragmentList.add(new SellerDetailCommentFragment());
		fragmentList.add(new SellerDetailSellerFragment());
		
		myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragmentList);
		pager.setAdapter(myPagerAdapter);
		pager.setCurrentItem(0);
		
		myPagerListener = new MyOnPageChangeListener();
		pager.setOnPageChangeListener(myPagerListener);
	}
	
	class MyClickListener implements View.OnClickListener{

		int index = 0;
		
		public MyClickListener(int i) {
			index = i;
		}
		
		@Override
		public void onClick(View v) {
			pager.setCurrentItem(index);
		}
		
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
		int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����

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
					animation = new TranslateAnimation(one * currIndex, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else {
					animation = new TranslateAnimation(one * currIndex, two, 0, 0);
				}
				break;
			}
			currIndex = i;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		ArrayList<Fragment> fragments;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fglist) {
			super(fm);
			fragments = fglist;

		}

		@Override
		public Fragment getItem(int arg0) {
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}
	}
}
