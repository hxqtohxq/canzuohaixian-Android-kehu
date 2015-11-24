package com.zhanjixun.adapter;

import java.util.List;
import java.util.Map;



import com.zhanjixun.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 物流详情信息之
 * @author Imissyou
 *
 */
public class Logistics_information_ItmeAdapter extends BaseAdapter {
	//item的数据.
	private List<Map<String,Object>> listData;
	//获取上下文
	private Context context;
	     
	//默认构造方法
    public Logistics_information_ItmeAdapter(Context context,
    		List<Map<String,Object>> listData) {
		this.context = context;
		this.listData = listData;
	}
	
	
	
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int opsition) {
		return listData.get(opsition);
	}

	@Override
	public long getItemId(int opsition) {
		return opsition;
	}

	@Override
	public View getView(int opsition, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context)
					.inflate(R.layout.item_order_home_all, null);
//			viewHolder.item_shop_image = convertView.findViewById(R.id.);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			convertView.setTag(viewHolder);
		}
		return convertView;
	}
    
	
	
	final static class ViewHolder {
		TextView item_shop_title;
		TextView item_shop_name;
		TextView item_shop_price;
		TextView item_shop_size;
		TextView item_shop_number;
		ImageView item_shop_image;
	}

}
