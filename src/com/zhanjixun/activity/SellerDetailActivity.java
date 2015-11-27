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
	private int offset = 0;// 动画图片偏移量
	private int bmpW;// 动画图片宽度
	
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
		
		shopName = (TextView) findViewById(R.id.id_seller_detail_sellerName);	//商家名
		shopName.setText(getIntent().getStringExtra("title"));
		shipPort = (TextView) findViewById(R.id.id_seller_detail_shipPort);		//靠岸口
		returnTime = (TextView) findViewById(R.id.id_seller_detail_returnTime);		//靠岸时间
		creditValue = (TextView) findViewById(R.id.id_seller_detail_creditValue);	//信用值
		
		tv_goods.setOnClickListener(new MyClickListener(0));
		tv_comments.setOnClickListener(new MyClickListener(1));
		tv_sellers.setOnClickListener(new MyClickListener(2));
		
		shopId = getIntent().getStringExtra("shopId");
		
		// 获取图片宽度
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.link).getWidth();
		int screenW = ScreenUtil.getWidth(this);// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
		
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
