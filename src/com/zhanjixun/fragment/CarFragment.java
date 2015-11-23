package com.zhanjixun.fragment;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.ImageSizeUtil;
import com.zhanjixun.utils.JsonUtil;
import com.zhanjixun.utils.SPUtil;

public class CarFragment extends Fragment {

	private LinearLayout goodLinear;
	private CheckBox sellerName;
	private TextView priceTv;
	private Button commitBtn;
	private ImageView editImg;
	private LinearLayout baseLayout;
	private String carJson;
	private Map<String, Object> order;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		carJson = SPUtil.getString(getActivity(), Constants.XML_CAR_FILE,
				Constants.XML_CAR_KEY, "");
		if (carJson.equals("")) {
			return inflater.inflate(R.layout.fragment_car_empty, container,
					false);
		} else {
			return inflater.inflate(R.layout.fragment_car, container, false);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!carJson.equals("")) {
			initViews();
			try {
				order = JsonUtil.getJosn(carJson);
				sellerName.setText(order.get("sellerName") + "");

				@SuppressWarnings("unchecked")
				List<Map<String, String>> goods = (List<Map<String, String>>) order
						.get("goodsList");
				for (Map<String, String> map : goods) {
					addGood(map);
					if (goods.indexOf(map) != goods.size() - 1) {
						addLine();
					}
				}
				setPrice();
			} catch (JSONException e) {

			}
		}
	}

	private void initViews() {
		goodLinear = (LinearLayout) getActivity().findViewById(
				R.id.linear_car_goods);
		sellerName = (CheckBox) getActivity().findViewById(
				R.id.checkBox_car_sellerName);
		editImg = (ImageView) getActivity().findViewById(R.id.image_car_edit);
		priceTv = (TextView) getActivity().findViewById(R.id.text_car_price);
		commitBtn = (Button) getActivity().findViewById(R.id.btn_car_commit);

		editImg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	private void setPrice() {
	}

	private void addLine() {
		View v = new View(getActivity());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 1);
		v.setBackgroundColor(getActivity().getResources().getColor(
				R.color.page_bg));
		v.setLayoutParams(lp);
		v.setPadding(0, 0, ImageSizeUtil.DipToPixels(getActivity(), 10),
				ImageSizeUtil.DipToPixels(getActivity(), 10));
		goodLinear.addView(v);
	}

	private void addGood(Map<String, String> map) {

	}

}
