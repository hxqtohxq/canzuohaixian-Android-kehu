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

	// item��������
	private List<Order> listData;
	// ������
	private Context context;
	// ��ȡ�ǵڼ�ҳ�����������ݶ�̬����Btn
	private String Str;
	private String btnTitle = null;
	private String order_id;
	private int order_statu;
	private List<Map<String,Object>> orderDatail;

	/**
	 * Ĭ�Ϲ��췽��
	 * 
	 * @param context
	 *            �����Ļ�ȡ
	 * @param listData
	 *            item�������
	 */
	public OrderListAdapter(Context context, List<Order> listData) {
		this.context = context;
		this.listData = listData;
	}

	/**
	 * ѡ����ʾ��Btn
	 * 
	 * @param position��Ӧ��Item
	 * @return BtnText String
	 */
	private String selcetBtnTitle(int position) {
		/**
		 * ��ȡ����״̬ ���ݶ���״̬���Button 0(δ����) 1(δ����) 2(δ�ջ�) 3��δ����) 4(��ɶ�����
		 * 
		 */
		Order order = listData.get(position);
		this.order_statu = order.getStute();
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
			Str = "MIssss";
			break;
		}
		Log.v("mm", Str);
		return Str;
	}

	/**
	 * ��ӡ���ٸ�item.
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

	// ��дgetView����
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
		//�̵���
		 viewHolder.shopTitle_name.setText(order.getShopKeeperName());
		 //���м۸�
		 viewHolder.shop_allmoney.setText(order.getTotalprice());
		 //�˷�
		 viewHolder.shop_logistics_money.setText(order.getPostagePrice());
		 Log.d("Orderdatali", orderDatail.toString());
		 Log.v("name", orderDatail.get(0).get("goodsName").toString() + "11111");
		 viewHolder.shop_goodname.setText(orderDatail.get(0).get("goodsName").toString());
////		//������
		 Log.v("unit", orderDatail.get(0).get("unit").toString() + "");
		 viewHolder.shop_mode.setText(orderDatail.get(0).get("unit").toString());
////		// ���
		 Log.v("unit", orderDatail.get(0).get("sku").toString() + "");
		 viewHolder.shop_size.setText(orderDatail.get(0).get("sku").toString());
//		// �۸�
		 viewHolder.shop_goodprice.setText(orderDatail.get(0).get("price").toString());
		 
		// viewHolder.shop_Image.setImageBitmap(bm);
		/**
		 * �Զ�����Btn ��ֵ ����Btn��Text
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
		//Item �ĵ���¼�
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
	 * ��Ӧ�����ֲ�ͬ�¼��� �����Ӧ��ͬ�Ĵ����¼�
	 */
	private void doBtnClick(String order_Id, int opsition) {
		Log.v("state", order_statu + "");
		switch (order_statu) {
		case ORDER_CARGO:
			Str = "�����̼ҷ���";
			break;
		case ORDER_PAY:
			Str = "ȥ����";
			/**
			 * ֮��ŪToDo
			 */
			Intent intent = new Intent(this.context,
					Order_Appraise_Activity.class);
			 intent.putExtra("order_id",order_Id);
			context.startActivity(intent);
			break;
		case ORDER_SEND:
			//�鿴����
			Log.v("bbbbb", Str);

			Intent logistics_intent = new Intent(this.context,
					Order_Logisitice_Activity.class);
			 logistics_intent.putExtra("order_id",order_Id);
			Log.v("bbbbb", logistics_intent.toString());
			this.context.startActivity(logistics_intent);
			break;
		case ORDER_APPRAIASE:
			// ȥ����
			Intent appraise_intent = new Intent(this.context,
					Order_Appraise_Activity.class);
			appraise_intent.putExtra("order_id",order_Id);
			this.context.startActivity(appraise_intent);
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
		TextView shop_goodname;
		// ��Ʒ����
		TextView shop_goodnumber;
		// ��Ʒ�۸�
		TextView shop_goodprice;
		// ��Ʒ���м۸�
		TextView shop_allmoney;
		// ��Ʒ��� �еȴ�С
		TextView shop_size;
		//��Ʒ�Ƽ�  ��   ��
		TextView shop_mode;
		// ��Ʒ����������Ʒ
		TextView shop_number2;
		// ��Ʒ�˷�����
		TextView shop_logistics_money;
		// ��ť
		Button Btn;
		// ��ƷͼƬ
		ImageView shop_Image;
	}

}
