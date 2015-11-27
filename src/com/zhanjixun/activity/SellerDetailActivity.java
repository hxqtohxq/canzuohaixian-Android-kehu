package com.zhanjixun.activity;

import java.util.ArrayList;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.adapter.FragmentViewPagerAdapter;
import com.zhanjixun.fragment.SellerDetailCommentFragment;
import com.zhanjixun.fragment.SellerDetailGoodFragment;
import com.zhanjixun.fragment.SellerDetailSellerFragment;
import com.zhanjixun.utils.ScreenUtil;

public class SellerDetailActivity extends FragmentActivity {

	private TextView tv_goods;
	private TextView tv_comments;
	private TextView tv_sellers;
	private TextView title,shopName,shipPort,returnTime,creditValue;

	private ImageView cursor;
	private ViewPager pager;
	private int offset = 0;// ����ͼƬƫ����
	private int bmpW;// ����ͼƬ���
	
	private ArrayList<Fragment> fragmentList;
	private SellerDetailGoodFragment goodFragment;
	private SellerDetailCommentFragment commentFragment;
	private SellerDetailSellerFragment sellerFragment;
	private FragmentViewPagerAdapter myPagerAdapter;

	private String shopId;
	
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
		title = (TextView) findViewById(R.id.text_sellerActivity_title);
		title.setText(getIntent().getStringExtra("title"));
		
		shopName = (TextView) findViewById(R.id.id_seller_detail_sellerName);	//�̼���
		shopName.setText(getIntent().getStringExtra("title"));
		shipPort = (TextView) findViewById(R.id.id_seller_detail_shipPort);		//������
		returnTime = (TextView) findViewById(R.id.id_seller_detail_returnTime);		//����ʱ��
		creditValue = (TextView) findViewById(R.id.id_seller_detail_creditValue);	//����ֵ
		
		tv_goods.setOnClickListener(new MyClickListener(0));
		tv_comments.setOnClickListener(new MyClickListener(1));
		tv_sellers.setOnClickListener(new MyClickListener(2));
		
		shopId = getIntent().getStringExtra("shopId");
		
		// ��ȡͼƬ���
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.link).getWidth();
		int screenW = ScreenUtil.getWidth(this);// ��ȡ�ֱ��ʿ��
		offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
		
		fragmentList = new ArrayList<Fragment>();
		goodFragment = new SellerDetailGoodFragment();
		commentFragment = new SellerDetailCommentFragment();
		sellerFragment = new SellerDetailSellerFragment();
		fragmentList.add(goodFragment);
		fragmentList.add(commentFragment);
		fragmentList.add(sellerFragment);
		
		myPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), 
				pager, fragmentList, offset, bmpW, cursor);
		pager.setAdapter(myPagerAdapter);
		pager.setCurrentItem(0);
	}
	
	public void onBack(View v){
		this.finish();
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

	public String getShopId() {
		return shopId;
	}
}
