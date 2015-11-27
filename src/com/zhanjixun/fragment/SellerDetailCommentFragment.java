package com.zhanjixun.fragment;

import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhanjixun.R;
import com.zhanjixun.interfaces.OnDataReturnListener;

public class SellerDetailCommentFragment extends Fragment implements OnDataReturnListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_seller_detail_comments, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	
	private void initData() {
	}

	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		
	}
}
