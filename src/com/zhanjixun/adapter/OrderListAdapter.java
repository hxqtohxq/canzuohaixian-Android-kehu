package com.zhanjixun.adapter;

import java.util.List;
import java.util.Map;

import com.zhanjixun.R;
import com.zhanjixun.activity.Order_Appraise_Activity;
import com.zhanjixun.activity.Order_Logisitice_Activity;
import com.zhanjixun.activity.Order_information_Activity;
import com.zhanjixun.domain.Order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class OrderListAdapter extends BaseAdapter {

	public static final int ORDER_SEND = 0;
	public static final int ORDER_PAY = 1;
	public static final int ORDER_CARGO = 2;
	public static final int ORDER_APPRAIASE = 3;

	// item填充的数据
	private List<Order> listData;
	// 上下文
	private Context context;
	// 获取是第几页传进来的数据动态加载Btn
	private String Str;
	private String btnTitle = null;
	private String order_id;
	private int order_statu;
	private List<Map<String,Object>> orderDatail;

	/**
	 * 默认构造方法
	 * 
	 * @param context
	 *            上下文获取
	 * @param listData
	 *            item填充数据
	 */
	public OrderListAdapter(Context context, List<Order> listData) {
		this.context = context;
		this.listData = listData;
	}

	/**
	 * 选择显示的Btn
	 * 
	 * @param position对应的Item
	 * @return BtnText String
	 */
	private String selcetBtnTitle(int position) {
		/**
		 * 获取订单状态 根据订单状态填充Button 0(未付款) 1(未发货) 2(未收货) 3（未评分) 4(完成订单）
		 * 
		 */
		Order order = listData.get(position);
		this.order_statu = order.getStute();
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
			Str = "MIssss";
			break;
		}
		Log.v("mm", Str);
		return Str;
	}

	/**
	 * 打印多少个item.
	 */
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int opsition) {
		return (Order)listData.get(opsition);
	}

	@Override
	public long getItemId(int opsition) {
		return opsition;
	}

	// 重写getView方法
	@Override
	public View getView(final int opsition, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		Order order = listData.get(opsition);
		Log.v("order", order.toString() +"");
		orderDatail = order.getOrdersDetail();
		Log.v("list", orderDatail.toString() + "");
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(this.context).inflate(
					R.layout.item_order_home_all, null);

			viewHolder.Btn = (Button) convertView
					.findViewById(R.id.order_home_item_Btn);
			viewHolder.shop_Image = (ImageView) convertView
					.findViewById(R.id.order_home_item_shop_image);
			viewHolder.shop_allmoney = (TextView) convertView
					.findViewById(R.id.order_home_item_allmoney);
			viewHolder.shop_logistics_money = (TextView) convertView
					.findViewById(R.id.order_home_logistics_money);
			viewHolder.shop_goodname = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_goodname);
			viewHolder.shop_goodprice = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_goodprice);
			viewHolder.shop_goodnumber = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_goodnumber);
			viewHolder.shop_number2 = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_number2);
			viewHolder.shopTitle_name = (TextView) convertView
					.findViewById(R.id.order_home_item_shopTitle_name);
			viewHolder.shop_mode = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_mode);
			viewHolder.shop_size = (TextView) convertView
					.findViewById(R.id.order_home_item_shop_size);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			convertView.setTag(viewHolder);
		}
		//商店名
		 viewHolder.shopTitle_name.setText(order.getShopKeeperName());
		 //所有价格
		 viewHolder.shop_allmoney.setText(order.getTotalprice());
		 //运费
		 viewHolder.shop_logistics_money.setText(order.getPostagePrice());
		 Log.d("Orderdatali", orderDatail.toString());
		 Log.v("name", orderDatail.get(0).get("goodsName").toString() + "11111");
		 viewHolder.shop_goodname.setText(orderDatail.get(0).get("goodsName").toString());
////		//斤与条
		 Log.v("unit", orderDatail.get(0).get("unit").toString() + "");
		 viewHolder.shop_mode.setText(orderDatail.get(0).get("unit").toString());
////		// 规格
		 Log.v("unit", orderDatail.get(0).get("sku").toString() + "");
		 viewHolder.shop_size.setText(orderDatail.get(0).get("sku").toString());
//		// 价格
		 viewHolder.shop_goodprice.setText(orderDatail.get(0).get("price").toString());
		 
		// viewHolder.shop_Image.setImageBitmap(bm);
		/**
		 * 自动加载Btn 的值 加载Btn的Text
		 */
		Log.v("mm", opsition + "");
		this.order_id = order.getOrder_id();
		btnTitle = selcetBtnTitle(opsition);
		Log.v("mm", "OK");
		viewHolder.Btn.setText(btnTitle);
		viewHolder.Btn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				doBtnClick(order_id , opsition);
			}
		});
		//Item 的点击事件
		convertView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context,Order_information_Activity.class);
				intent.putExtra("Order_id",order_id);
				context.startActivity(intent);
			}
		});

		return convertView;
	}

	/**
	 * 对应的四种不同事件。 处理对应不同的触发事件
	 */
	private void doBtnClick(String order_Id, int opsition) {
		Log.v("state", order_statu + "");
		switch (order_statu) {
		case ORDER_CARGO:
			Str = "提醒商家发货";
			break;
		case ORDER_PAY:
			Str = "去付款";
			/**
			 * 之后弄ToDo
			 */
			Intent intent = new Intent(this.context,
					Order_Appraise_Activity.class);
			 intent.putExtra("order_id",order_Id);
			context.startActivity(intent);
			break;
		case ORDER_SEND:
			//查看物流
			Log.v("bbbbb", Str);

			Intent logistics_intent = new Intent(this.context,
					Order_Logisitice_Activity.class);
			 logistics_intent.putExtra("order_id",order_Id);
			Log.v("bbbbb", logistics_intent.toString());
			this.context.startActivity(logistics_intent);
			break;
		case ORDER_APPRAIASE:
			// 去评价
			Intent appraise_intent = new Intent(this.context,
					Order_Appraise_Activity.class);
			appraise_intent.putExtra("order_id",order_Id);
			this.context.startActivity(appraise_intent);
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
		TextView shop_goodname;
		// 商品数量
		TextView shop_goodnumber;
		// 商品价格
		TextView shop_goodprice;
		// 商品所有价格
		TextView shop_allmoney;
		// 商品规格 中等大小
		TextView shop_size;
		//商品计件  斤   条
		TextView shop_mode;
		// 商品数量所有商品
		TextView shop_number2;
		// 商品运费数量
		TextView shop_logistics_money;
		// 按钮
		Button Btn;
		// 商品图片
		ImageView shop_Image;
	}

}
