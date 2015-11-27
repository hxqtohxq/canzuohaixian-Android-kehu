package com.zhanjixun.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.adapter.GoodListAdapter;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.GoodItemBean;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.views.LoadingDialog;

public class GoodListActivity extends BackActivity implements
		OnDataReturnListener {
	private static int pageIndex = 1;

	private static final int PAGE_SIZE = 7;

	public static final int FISH = 1;
	public static final int SHRIMP = 2;
	public static final int CRAD = 3;
	public static final int SHELLFISH = 4;
	public static final int SQUID = 5;
	public static final int GINSENG = 6;
	public static final int OTHERS = 7;

	private TextView titleTv;
	private ListView goodLv;
	private GoodListAdapter adapter;
	private int kind;
	private LoadingDialog dialog;
	private String categoryId;
	List<GoodItemBean> goodItemBeans = new ArrayList<GoodItemBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_list);
		initViews();
		initData();
	}

	public void onBack(View v) {
		this.finish();
	}

	private void initViews() {
		titleTv = (TextView) findViewById(R.id.text_activity_goodlist_title);
		goodLv = (ListView) findViewById(R.id.text_activity_goodlist_list);
		kind = (Integer) getIntent().getExtras().get("kind");
		switch (kind) {
		case FISH:
			titleTv.setText("鱼类");
			categoryId = "1";
			break;
		case SHRIMP:
			titleTv.setText("虾类");
			categoryId = "2";
			break;
		case CRAD:
			titleTv.setText("蟹类");
			categoryId = "3";
			break;
		case SHELLFISH:
			titleTv.setText("贝类");
			categoryId = "4";
			break;
		case SQUID:
			titleTv.setText("鱿鱼类");
			categoryId = "5";
			break;
		case GINSENG:
			titleTv.setText("参类");
			categoryId = "6";
			break;
		case OTHERS:
			titleTv.setText("其他");
			break;
		default:
			break;
		}
	}

	private void initData() {
		if (categoryId != null) {
			dialog = new LoadingDialog(this);
			dialog.show();
			DC.getInstance()
					.getGoodList(this, categoryId, pageIndex, PAGE_SIZE);
		} else {
			Toast.makeText(GoodListActivity.this, "获取商品信息失败",
					Toast.LENGTH_SHORT).show();
			return;
		}
	}

	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		dialog.dismiss();
		Log.i("cc", result + "");
		JSONObject object = new JSONObject(result);
		Log.i("cc", object + "");
		GoodItemBean itemBean;
		try {
			JSONObject resultParam = object.getJSONObject("resultParm");
			JSONArray categoryList = resultParam.getJSONArray("categoryList");
			Log.i("aa", "categoryList:" + categoryList);
			Log.i("cc", "categoryList length:" + categoryList.length());
			for (int i = 0; i < categoryList.length(); i++) {
				// Log.i("cc","add itembean:"+i);
				JSONObject re = categoryList.getJSONObject(i);
				itemBean = new GoodItemBean();
				itemBean.setCategoryAcademicName(re
						.getString("categoryAcademicName"));
				itemBean.setCategorySimpleName(re
						.getString("categorySimpleName"));
				itemBean.setCategoryEnglishName(re
						.getString("categoryEnglishName"));
				itemBean.setCategoryId(re.getString("categoryId"));
				itemBean.setTotalSellerNumber(re.getString("totalSellNumber"));
				itemBean.setSendPrice(re.getString("lowPrice"));
				itemBean.setUnit(re.getString("unit"));
				goodItemBeans.add(itemBean);
				Log.i("cc", "add itembean:" + i);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (goodItemBeans.size() > 0) {
			// handler.sendEmptyMessage(1);
			adapter = new GoodListAdapter(GoodListActivity.this, goodItemBeans);
			goodLv.setAdapter(adapter);
			goodLv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(GoodListActivity.this,
							GoodDetailActivity.class);
					intent.putExtra("title", goodItemBeans.get(position)
							.getCategorySimpleName());
					intent.putExtra("categoryId", goodItemBeans.get(position)
							.getCategoryId());
					intent.putExtra("academicName", goodItemBeans.get(position)
							.getCategoryAcademicName());
					intent.putExtra("EnglishName", goodItemBeans.get(position)
							.getCategoryEnglishName());
					intent.putExtra("lastPager", titleTv.getText().toString());
					startActivity(intent);
				}
			});
			Log.i("cc", "goods:" + goodItemBeans);
			Log.i("cc", "send message to GoodListActivity Handler");
		} else {
			Toast.makeText(GoodListActivity.this, "获取商品信息失败",
					Toast.LENGTH_SHORT).show();
			return;
		}

	}

}
