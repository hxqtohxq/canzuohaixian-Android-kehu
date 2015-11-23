package com.zhanjixun.adapter;

import java.util.List;
import java.util.Map;

import com.zhanjixun.R;
import com.zhanjixun.activity.Order_Appraise_Activity;
import com.zhanjixun.activity.Order_logistics_information_Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderListAdapter extends BaseAdapter {

	public static final int ORDER_SEND = 0;
	public static final int ORDER_PAY = 1;
	public static final int ORDER_CARGO = 2;
	public static final int ORDER_APPRAIASE = 3;
	
	// item填充的数据
	private List<Map<String, Object>> listData;
	// 上下文
	private Context context;
	// 获取是第几页传进来的数据动态加载Btn
	private int pageNumber;
	private String Str;
	private String btnTitle;
	private int order_statu;
	
	

	/**
	 * 默认构造方法
	 * 
	 * @param context
	 *            上下文获取
	 * @param listData
	 *            item填充数据
	 */
	public OrderListAdapter(Context context,
			List<Map<String, Object>> listData, int pageNunber) {
		this.context = context;
		this.listData = listData;
		this.pageNumber = pageNunber;
	}

	/**
	 * 选择显示的Btn
	 * @param position对应的Item
	 * @return BtnText String 
	 */
	private String selcetBtnTitle(int position) {
		/**
		 * 获取订单状态
		 * 根据订单状态填充Button
		 * 0(未付款) 1(未发货) 2(未收货) 3（未评分) 4(完成订单） 
		 * 
		 */
		String order_status = listData.get(position).get("state").toString();
		order_statu = Integer.getInteger(order_status).intValue();
		switch (order_statu) {
		case ORDER_CARGO:
			Str = "提醒商家发货";
			break;
		case ORDER_PAY:
            Str = "去付款";
			break;
		case ORDER_SEND:
			Str = "查看物流";
			break;
		case ORDER_APPRAIASE:
           Str = "去评价";
			break;
		default:
			Toast.makeText(context, "不在范围内", Toast.LENGTH_SHORT).show();
			break;
		}
		return Str;
	}

	/**
	 * 打印多少个item.
	 */
	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int opsition) {
		return listData.get(opsition);
	}

	@Override
	public long getItemId(int opsition) {
		return opsition;
	}

	// 重写getView方法
	@Override
	public View getView(final int opsition, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_order_home_all, null);

			viewHolder.Btn = (Button) convertView
					.findViewById(R.id.order_home_item_lookUpLogistice_Btn);
			viewHolder.shop_Image = (ImageView) convertView
					.findViewById(R.id.order_home_item_shop_image);
			viewHolder.shop_allmoney = (TextView) convertView
					.findViewById(R.id.order_home_item_allmoney);
			viewHolder.shop_logistics_money = (TextView) convertView
					.findViewById(R.id.order_home_logistics_money);
			viewHolder.shop_name = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_name);
			viewHolder.shop_price = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_price);
			viewHolder.shop_number = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_number);
			viewHolder.shop_number2 = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_number2);
			viewHolder.shopTitle_name = (TextView) convertView
					.findViewById(R.id.order_home_item_shopTitle_name);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			convertView.setTag(viewHolder);
		}
		viewHolder.shopTitle_name.setText(listData.get(opsition)
				.get("shopKeeperName").toString());
		viewHolder.shop_name.setText(listData.get(opsition).get("shop_name")
				.toString());
		viewHolder.shop_number.setText(listData.get(opsition)
				.get("shop_number").toString());

		viewHolder.shop_price.setText(listData.get(opsition).get("shop_price")
				.toString());
		viewHolder.shopTitle_name.setText(listData.get(opsition)
				.get("ShopTitle").toString());

		viewHolder.shopTitle_name.setText(listData.get(opsition)
				.get("ShopTitle").toString());
		viewHolder.shopTitle_name.setText(listData.get(opsition)
				.get("ShopTitle").toString());
		viewHolder.shopTitle_name.setText(listData.get(opsition)
				.get("ShopTitle").toString());

		viewHolder.shopTitle_name.setText(listData.get(opsition)
				.get("ShopTitle").toString());
		/**
		 * 加载图片  --> TODO
		 */
//		viewHolder.shop_Image.setImageBitmap(bm);
		/**
		 *  自动加载Btn 的值
		 *  加载Btn的Text
		 *  
		 */
		
//		btnTitle = selcetBtnTitle(opsition);
//		viewHolder.Btn.setText(btnTitle);
//		viewHolder.Btn.invalidate();
		viewHolder.Btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				doBtnClick(btnTitle,opsition);
			}

			
		});

		return convertView;
	}
	
	
    /**
     * 对应的四种不同事件。
     * 处理对应不同的触发事件
     */
	private void doBtnClick(String btnTitle,int opsition) {
		switch (order_statu) {
		case ORDER_CARGO:
			Str = "提醒商家发货";
			break;
		case ORDER_PAY:
            Str = "去付款";
            /**
             * 之后弄ToDo
             */
            Intent intent = new Intent(context, Order_logistics_information_Activity.class);
            intent.putExtra("order_id", listData.get(opsition).get("order_id").toString());
            context.startActivity(intent);
			break;
		case ORDER_SEND:
			Str = "查看物流";
			Intent logistics_intent = new Intent(context, Order_logistics_information_Activity.class);
			logistics_intent.putExtra("order_id", listData.get(opsition).get("order_id").toString());
            context.startActivity(logistics_intent);
			break;
		case ORDER_APPRAIASE:
			Intent appraise_intent = new Intent(context ,Order_Appraise_Activity.class);
			appraise_intent.putExtra("order_id", listData.get(opsition).get("order_id").toString());
			appraise_intent.putExtra("user_id", listData.get(opsition).get("phone_number").toString());
           Str = "去评价";
			break;
		default:
			Toast.makeText(context, "不在范围内", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	final static class ViewHolder {
		// 商品店名标题
		TextView shopTitle_name;
		// 商品名
		TextView shop_name;
		// 商品数量
		TextView shop_number;
		// 商品价格
		TextView shop_price;
		// 商品所有价格
		TextView shop_allmoney;
		// 商品规格
		TextView shop_size;
		// 商品数量2
		TextView shop_number2;
		// 商品运费数量
		TextView shop_logistics_money;
		// 按钮
		Button Btn;
		// 商品图片
		ImageView shop_Image;
	}

}
