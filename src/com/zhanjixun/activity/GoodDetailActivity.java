package com.zhanjixun.activity;

import com.zhanjixun.R;
import com.zhanjixun.adapter.SellerListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class GoodDetailActivity extends Activity implements OnItemClickListener {

	private TextView title;
	private ListView list_seller;
	private SellerListAdapter sellerListAdapter;

	private Spinner spinner1, spinner2;
	ArrayAdapter<String> adapter1, adapter2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_good_detail);
		initView();

	}

	private void initView() {
		spinner1 = (Spinner) findViewById(R.id.id_category_good_detail_spinnerBreedWay);
		spinner2 = (Spinner) findViewById(R.id.id_category_good_detail_spinnerSortWay);
		list_seller = (ListView) findViewById(R.id.id_list_seller);
		title = (TextView) findViewById(R.id.text_gooddetailAty_title);
		title.setText(getIntent().getStringExtra("title"));

		initSpinner();
		initListSellerView();
	};

	private void initSpinner() {
		adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				getResources().getStringArray(R.array.list_catchway));
		spinner1.setAdapter(adapter1);
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				getResources().getStringArray(R.array.list_sortway));
		spinner2.setAdapter(adapter2);
	}

	public void initListSellerView() {
		sellerListAdapter = new SellerListAdapter(this);
		list_seller.setAdapter(sellerListAdapter);
		list_seller.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//跳转到商家页面
		startActivity(new Intent(GoodDetailActivity.this, SellerDetailActivity.class));
	}
}
