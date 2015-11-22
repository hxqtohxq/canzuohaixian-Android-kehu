package com.zhanjixun.activity;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.adapter.GoodListAdapter;
import com.zhanjixun.data.DC;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogUtils;
import com.zhanjixun.views.LoadingDialog;

public class GoodListActivity extends Activity implements OnDataReturnListener {
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
		Log.i("cc", "GoodActivity data initing");
		/*dialog = new LoadingDialog(this);
		dialog.show();
		DC.getInstance().getGoodList(this, categoryId, pageIndex++, PAGE_SIZE);*/ 
		adapter = new GoodListAdapter(this);
		goodLv.setAdapter(adapter);
		goodLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(GoodListActivity.this,GoodDetailActivity.class));
			}
		});
	}

	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		dialog.dismiss();
		//LogUtils.d(result.toString());
		Log.i("cc", result.toString());
	}

}
