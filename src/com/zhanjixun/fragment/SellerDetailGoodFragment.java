package com.zhanjixun.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.activity.SellerDetailActivity;
import com.zhanjixun.adapter.SellerGoodsListAdapter;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.GoodItemBean;
import com.zhanjixun.interfaces.OnDataReturnListener;

public class SellerDetailGoodFragment extends Fragment implements
		OnDataReturnListener {

	private boolean isGoodInited = false;
	private int page = 1;
	private int items = 7;
	private List<GoodItemBean> goodBeans = new ArrayList<GoodItemBean>();
	private ListView listGoods;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_seller_detail_goods,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
	}

	private void initView() {
		listGoods = (ListView) getActivity().findViewById(
				R.id.id_seller_detail_goods_listview);
	}

	private void initData() {
		String shopId = ((SellerDetailActivity) getActivity()).getShopId();
		Log.i("bb", "shopId:" + shopId);
		if (shopId != null) {
			DC.getInstance().getSellerGoods(this, shopId, page++, items);
			Log.i("dd", "sellerdetailGoodfragnebt page:" + page);
		} else {
			Toast.makeText(getActivity(), "获取商品失败", Toast.LENGTH_SHORT).show();
			return;
		}
	}

	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		Log.i("bb", "seller goods:\n" + result);
		JSONObject object = new JSONObject(result);
		GoodItemBean goodItemBean;
		JSONObject resultParam;
		try {
			resultParam = object.getJSONObject("resultParm");
			JSONArray array = resultParam.getJSONArray("goodsList");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsObject = array.getJSONObject(i);
				goodItemBean = new GoodItemBean();
				goodItemBean.setCategorySimpleName(jsObject
						.getString("goodsName"));
				goodItemBean.setSpecification(jsObject.getString("skuString"));
				goodItemBean.setTotalSellerNumber(jsObject
						.getString("sellNumber"));
				goodItemBean.setSendPrice(jsObject.getString("price"));
				goodItemBean.setUnit(jsObject.getString("unit"));
				goodBeans.add(goodItemBean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			if (goodBeans.size() > 0) {
				SellerGoodsListAdapter adapter = new SellerGoodsListAdapter(
						getActivity(), goodBeans);
				listGoods.setAdapter(adapter);
			} else {
				Toast.makeText(getActivity(), "获取商品失败", Toast.LENGTH_SHORT)
						.show();
				return;
			}
		}
	}
}
