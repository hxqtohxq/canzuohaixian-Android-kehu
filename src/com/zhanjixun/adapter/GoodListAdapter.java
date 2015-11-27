package com.zhanjixun.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.domain.GoodItemBean;

public class GoodListAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<String> infos;
	private List<GoodItemBean> goods;

	public GoodListAdapter(Context context, List<GoodItemBean> goods) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.goods = goods;
	}

	@Override
	public int getCount() {
		return goods.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.category_listview_item,
					null);
			viewHolder.academicName = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_seafoodScientificName);
			viewHolder.simpleName = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_seafoodPopularName);
			viewHolder.englishName = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_seafoodEnglishName);
			viewHolder.sendPrice = (TextView) convertView
					.findViewById(R.id.id_category_listview_item_lowest_price);
			
			viewHolder.view = convertView.findViewById(R.id.id_sales);
			viewHolder.sellNumber = (TextView) viewHolder.view
					.findViewById(R.id.id_sales_salesValue);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		GoodItemBean itemBean = goods.get(position);
		Log.i("cc", "itemBean position" + position);
		viewHolder.academicName.setText(itemBean.getCategoryAcademicName());
		viewHolder.simpleName.setText(itemBean.getCategorySimpleName());
		viewHolder.englishName.setText(itemBean.getCategoryEnglishName());
		viewHolder.sellNumber.setText(itemBean.getTotalSellerNumber());
		viewHolder.sendPrice.setText(itemBean.getSendPrice() + "/"
				+ itemBean.getUnit());
		return convertView;
	}

	class ViewHolder {
		public TextView academicName;
		public TextView simpleName;
		public TextView englishName;
		public TextView sellNumber;
		public TextView sendPrice;
		public ImageView image;
		public View view;
	}
}
