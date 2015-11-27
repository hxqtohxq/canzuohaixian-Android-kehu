package com.zhanjixun.activity;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.JsonResultUtil;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;
import com.zhanjixun.views.ReflashListView;
import com.zhanjixun.views.RoundImageView;

public class GoodDetailActivity extends BackActivity implements
		OnDataReturnListener {

	private RelativeLayout breedWay_popMenu, sortWay_popMenu;
	private TextView breedWay_title, sortWay_title;
	private TextView simpleName, academicName, englishName;
	private String categoryId;
	private String goodName;
	private String catchWay;
	public final int page = 1;
	public final int item = 7;
	private TextView title;
	private ReflashListView list_seller;
	private LoadingDialog dialog;
	private RoundImageView image;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_good_detail);
		initView();
		initData();
	}

	private void initView() {
		image = (RoundImageView) findViewById(R.id.id_category_good_detail_seafoodImage);
		breedWay_popMenu = (RelativeLayout) findViewById(R.id.id_popup1);
		sortWay_popMenu = (RelativeLayout) findViewById(R.id.id_popup2);
		breedWay_title = (TextView) findViewById(R.id.id_breadWay_title);
		sortWay_title = (TextView) findViewById(R.id.id_sortWay_title);
		simpleName = (TextView) findViewById(R.id.id_category_good_detail_seafoodPopularName); // 俗名
		academicName = (TextView) findViewById(R.id.id_category_good_detail_seafoodScienticName); // 学名
		englishName = (TextView) findViewById(R.id.id_category_good_detail_seafoodEnglishName); // 英文名
		list_seller = (ReflashListView) findViewById(R.id.id_list_seller);
		title = (TextView) findViewById(R.id.text_gooddetailAty_title);

		initPopupMenu();
	};

	public void initListData() {
	}

	public void initData() {

		title.setText(getIntent().getStringExtra("title"));
		simpleName.setText(getIntent().getStringExtra("title"));
		academicName.setText(getIntent().getStringExtra("academicName"));
		englishName.setText(getIntent().getStringExtra("EnglishName"));
		categoryId = getIntent().getStringExtra("categoryId");

		IC.getInstance().setSrc(getIntent().getStringExtra("iamgeURL"), image);

		if (!StringUtil.isEmptyString(categoryId)) {
			dialog = new LoadingDialog(this);
			dialog.show();
			DC.getInstance().getGoodSellerList(this, categoryId, page, item);
		} else {
			LogUtils.w("categoryId为空");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		dialog.dismiss();
		if (JsonResultUtil.state(result)) {
			if (taskTag.equals(TaskTag.GOOD_SELLER_LIST)) {
				Map<String, Object> parm = (Map<String, Object>) result
						.get("resultParm");
				List<Map<String, Object>> shopList = (List<Map<String, Object>>) parm
						.get("shopList");

			}
		} else {
			new MessageDialog(this, JsonResultUtil.message(result));
		}
	}

	/**
	 * 初始化养殖方式和排序选项菜单
	 */
	public void initPopupMenu() {
		breedWay_popMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(GoodDetailActivity.this, v);
				popupMenu
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch (item.getItemId()) {
								case R.id.menu_wild:
									breedWay_title.setText("野生");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了野生", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_breed:
									breedWay_title.setText("养殖");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了养殖", Toast.LENGTH_SHORT)
											.show();
									return true;
								default:
									return false;
								}
							}
						});
				MenuInflater mi = popupMenu.getMenuInflater();
				mi.inflate(R.menu.catch_way_menu, popupMenu.getMenu());
				popupMenu.show();
			}
		});

		sortWay_popMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(GoodDetailActivity.this, v);
				popupMenu
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch (item.getItemId()) {
								case R.id.menu_comprehensive_ranking:
									sortWay_title.setText("综合排序");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了综合排序", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_comment_highest:
									sortWay_title.setText("评价最高");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了评价最高", Toast.LENGTH_SHORT)
											.show();
									return true;
								case R.id.menu_speed_fastest:
									sortWay_title.setText("速度最快");
									Toast.makeText(GoodDetailActivity.this,
											"你选择了速度最快", Toast.LENGTH_SHORT)
											.show();
									return true;
								default:
									return false;
								}
							}
						});
				MenuInflater mi = popupMenu.getMenuInflater();
				mi.inflate(R.menu.sort_way_menu, popupMenu.getMenu());
				popupMenu.show();
			}
		});
	}
}
