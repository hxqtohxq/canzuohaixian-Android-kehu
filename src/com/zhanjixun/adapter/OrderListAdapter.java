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
	
	// item��������
	private List<Map<String, Object>> listData;
	// ������
	private Context context;
	// ��ȡ�ǵڼ�ҳ�����������ݶ�̬����Btn
	private int pageNumber;
	private String Str;
	private String btnTitle;
	private int order_statu;
	
	

	/**
	 * Ĭ�Ϲ��췽��
	 * 
	 * @param context
	 *            �����Ļ�ȡ
	 * @param listData
	 *            item�������
	 */
	public OrderListAdapter(Context context,
			List<Map<String, Object>> listData, int pageNunber) {
		this.context = context;
		this.listData = listData;
		this.pageNumber = pageNunber;
	}

	/**
	 * ѡ����ʾ��Btn
	 * @param position��Ӧ��Item
	 * @return BtnText String 
	 */
	private String selcetBtnTitle(int position) {
		/**
		 * ��ȡ����״̬
		 * ���ݶ���״̬���Button
		 * 0(δ����) 1(δ����) 2(δ�ջ�) 3��δ����) 4(��ɶ����� 
		 * 
		 */
		String order_status = listData.get(position).get("state").toString();
		order_statu = Integer.getInteger(order_status).intValue();
		switch (order_statu) {
		case ORDER_CARGO:
			Str = "�����̼ҷ���";
			break;
		case ORDER_PAY:
            Str = "ȥ����";
			break;
		case ORDER_SEND:
			Str = "�鿴����";
			break;
		case ORDER_APPRAIASE:
           Str = "ȥ����";
			break;
		default:
			Toast.makeText(context, "���ڷ�Χ��", Toast.LENGTH_SHORT).show();
			break;
		}
		return Str;
	}

	/**
	 * ��ӡ���ٸ�item.
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

	// ��дgetView����
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
		 * ����ͼƬ  --> TODO
		 */
//		viewHolder.shop_Image.setImageBitmap(bm);
		/**
		 *  �Զ�����Btn ��ֵ
		 *  ����Btn��Text
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
     * ��Ӧ�����ֲ�ͬ�¼���
     * �����Ӧ��ͬ�Ĵ����¼�
     */
	private void doBtnClick(String btnTitle,int opsition) {
		switch (order_statu) {
		case ORDER_CARGO:
			Str = "�����̼ҷ���";
			break;
		case ORDER_PAY:
            Str = "ȥ����";
            /**
             * ֮��ŪToDo
             */
            Intent intent = new Intent(context, Order_logistics_information_Activity.class);
            intent.putExtra("order_id", listData.get(opsition).get("order_id").toString());
            context.startActivity(intent);
			break;
		case ORDER_SEND:
			Str = "�鿴����";
			Intent logistics_intent = new Intent(context, Order_logistics_information_Activity.class);
			logistics_intent.putExtra("order_id", listData.get(opsition).get("order_id").toString());
            context.startActivity(logistics_intent);
			break;
		case ORDER_APPRAIASE:
			Intent appraise_intent = new Intent(context ,Order_Appraise_Activity.class);
			appraise_intent.putExtra("order_id", listData.get(opsition).get("order_id").toString());
			appraise_intent.putExtra("user_id", listData.get(opsition).get("phone_number").toString());
           Str = "ȥ����";
			break;
		default:
			Toast.makeText(context, "���ڷ�Χ��", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	final static class ViewHolder {
		// ��Ʒ��������
		TextView shopTitle_name;
		// ��Ʒ��
		TextView shop_name;
		// ��Ʒ����
		TextView shop_number;
		// ��Ʒ�۸�
		TextView shop_price;
		// ��Ʒ���м۸�
		TextView shop_allmoney;
		// ��Ʒ���
		TextView shop_size;
		// ��Ʒ����2
		TextView shop_number2;
		// ��Ʒ�˷�����
		TextView shop_logistics_money;
		// ��ť
		Button Btn;
		// ��ƷͼƬ
		ImageView shop_Image;
	}

}
