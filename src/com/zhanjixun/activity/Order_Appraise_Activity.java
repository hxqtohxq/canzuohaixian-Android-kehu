package com.zhanjixun.activity;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.Appraisal;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;

@SuppressLint("ResourceAsColor")
public class Order_Appraise_Activity extends Activity implements
		OnDataReturnListener ,OnClickListener{

	// 用户填写的评论
	private TextView user_appraise;
	// 足斤足称好评
	private RatingBar good_rationBar;
	// 新鲜度好评
	private RatingBar free_rationBar;
	// 物流速度好评
	private RatingBar logistics_rationBar;
	// 足斤足称评度 （0-5）
	private TextView good_rationBar_number;
	// 新鲜度评度 （0-5）
	private TextView free_rationBar_number;
	// 物流速度评度 （0-5）
	private TextView logistics_rationBar_number;
	// 匿名评论
	private CheckBox niming_checkBox;

	// 好评
	private LinearLayout good_check;
	private TextView Title1;
	private ImageView image1;
	// 中评
	private LinearLayout ok_check;
	private TextView Title2;
	private ImageView image2;
	// 差评
	private LinearLayout bad_check;
	private TextView Title3;
	private ImageView image3;
	// 商品图片
	private ImageView order_image;

	/**
	 * 上一页面传入的数据用于加载和提交数据
	 */
	private String user_id;
	private String order_id;

	/**
	 * 获取页面缓存数据
	 */
	
	//要提交的数据实体类
	private Appraisal appraisal;
	// 提交按钮
	private Button submit;
	// 缓冲动画
	private LoadingDialog dialog;

	private Map<String, Object> mapData;
	
	//控制TitleBar的
	private  boolean[] Btn ={true,true,true};
	
	//加载图片URL的地址
	private final String GetImageURL =" ";
	
	

	/**
	 * 订单评分页面
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_grade);

		init();
		getIntentData();
		// 加载商品图片
		if (getOrderImage() != null) {
			order_image.setImageBitmap(getOrderImage());
		}
		// 提交数据
		if (doSubmit()) {
			Toast.makeText(this, "您提交的数据失败！", Toast.LENGTH_LONG).show();
		}
	}
    //获取用户ID和订单ID
	private void getIntentData() {
		Intent intent = getIntent();
		String order_id =null;
			order_id = intent.getStringExtra("order_id").toString();
			Log.v("order_id", order_id + "");
		appraisal.setOrder_id(order_id);
//		appraisal.setUser_id(intent.getStringExtra("user_id"));
	}

	// 获取商品图片
	private Bitmap getOrderImage() {
		Map<String,String> params = new HashMap<String,String>();
		params.put(order_id, appraisal.getOrder_id());
		DC.getInstance().getDatasFromServer("Appraise", GetImageURL, params, this);
		return null;
	}

	/**
	 * 处理提交数据
	 */
	private boolean doSubmit() {
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mapData = getData();
				dialog = new LoadingDialog(Order_Appraise_Activity.this);
				DC.getInstance().AddAppraise(Order_Appraise_Activity.this.toString(),
						Order_Appraise_Activity.this,
						mapData);
			}
		});
		return false;
	}

	/**
	 * 获取页面提交的信息封装成请求数据
	 * 
	 * @return map Map<String,Object>
	 */
	private Map<String, Object> getData() {

		final Map<String, Object> map = new HashMap<String, Object>();

		// 获取前一个页面发来的信息
		map.put("order_id", appraisal.getOrder_id());
		map.put("phone_number", appraisal.getUser_id());
		if (!StringUtil.isEmptyString(user_appraise.getText().toString())) {
			appraisal.setConmment_text(user_appraise.getText().toString());
		} else {
			Toast.makeText(this, "请填写完整的信息", Toast.LENGTH_LONG).show();
		}
		// 是否为匿名
		if (niming_checkBox.isClickable()) {
			appraisal.setAnonymous(true);
		} else {
			appraisal.setAnonymous(true);
		}
		map.put("conmment_text", appraisal.getConmment_text());
		map.put("conmment_weight",  appraisal.getConmment_weight());
		map.put("conmment_fresh",  appraisal.getConmment_fresh());
		map.put("conmment_speed",  appraisal.getConmment_speed());
		map.put("anonymous",  appraisal.isAnonymous());
		return map;
	}
	
	/**
	 * 获取服务器图片地址加载图片
	 * @author Imissyou
	 * @param resultData
	 * @todo TODO
	 *
	 * @return void
	 */
	private void initData(Map<String, Object> resultData) {
//		String OrderImageURL = resultData.get("Url").toString();
	}

	// 初始化控件
	private void init() {
		appraisal = new Appraisal();
		submit = (Button) findViewById(R.id.order_grade_ensureBtn_submit);
		niming_checkBox = (CheckBox) findViewById(R.id.order_grade_checkBox_niming);
		order_image = (ImageView) findViewById(R.id.order_grade_orderImage);
		// 足斤足称
		good_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_zujzuc);
		good_rationBar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                intiRating();				
			}
		});
		good_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number1);
		// 新鲜度
		free_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_xinxdu);
		free_rationBar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intiRating();
			}
		});
		free_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number2);
		// 发货速度
		logistics_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_fahuosdu);
		logistics_rationBar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                intiRating();				
			}
		});
		logistics_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number3);

		// TitleBar
		good_check = (LinearLayout) findViewById(R.id.order_grade_LinerLayout_Good);
		Title1 = (TextView) findViewById(R.id.order_grade_title1);
		image1 = (ImageView) findViewById(R.id.order_grade_image1);
		good_check.setOnClickListener(this);
		ok_check = (LinearLayout) findViewById(R.id.order_grade_LinerLayout_OK);
		Title2 = (TextView) findViewById(R.id.order_grade_title2);
		image2 = (ImageView) findViewById(R.id.order_grade_image2);
		ok_check.setOnClickListener(this);
		bad_check = (LinearLayout) findViewById(R.id.order_grade_LinerLayout_Bad);
		Title3 = (TextView) findViewById(R.id.order_grade_title3);
		image3 = (ImageView) findViewById(R.id.order_grade_image3);
		bad_check.setOnClickListener(this);
		
		
		
	}
	/**
	 * TODO
	 * 
	 * @param taskTag
	 * @param result
	 */
	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		
		if(taskTag.equals("Appraise")) {
			//加载页面数据
			initData(result);
		} else if (Boolean.parseBoolean(result.get("serviceResult").toString()) &&
				taskTag.equals(Order_Appraise_Activity.this.toString())) {
			dialog.dismiss();
			finish();
		} else {
			Toast.makeText(this, "你的评论提交失败", Toast.LENGTH_LONG).show();
		}
	}

	/* （非 Javadoc）
	 * @todo
	 * 评分种类 默认 好评
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		 Title1.setTextColor(R.color.back_ground);
		 image1.setImageResource(R.drawable.happy2_9);
		 Title2.setTextColor(R.color.back_ground);
		 image2.setImageResource(R.drawable.neutral2);
		 Title3.setTextColor(R.color.back_ground);
		 image3.setImageResource(R.drawable.sad2);
		switch (view.getId()) {
		case R.id.order_grade_LinerLayout_Good:
			if (Btn[0]) {
				Btn[0] = false;
				Btn[1] = true;
				Btn[2] = true;
				image1.setImageResource(R.drawable.happy2);
				Title1.setTextColor(R.color.theme);
				appraisal.setConmment_kind(1);
			} else {
				Title1.setTextColor(R.color.back_ground);
				image1.setImageResource(R.drawable.happy2_9);
                Btn[0] = true;
			}
			break;
		case R.id.order_grade_LinerLayout_OK:
			if (Btn[1]) {
				Btn[0] = true;
				Btn[1] = false;
				Btn[2] = true;
                Title2.setTextColor(R.color.theme);
                image2.setImageResource(R.drawable.neutral2_9);
                appraisal.setConmment_kind(0);
			} else {
				Btn[1] = true;
				Title2.setTextColor(R.color.back_ground);
				image2.setImageResource(R.drawable.neutral2);
			}
			break;
		case R.id.order_grade_LinerLayout_Bad:
			if (Btn[2]) {
				Btn[0] = true;
				Btn[1] = true;
				Btn[2] = false;
				Title3.setTextColor(R.color.theme);
				image3.setImageResource(R.drawable.sad2_9);
				appraisal.setConmment_kind(-1);
			} else{
				Btn[2] = true;
				Title3.setTextColor(R.color.back_ground);
				image3.setImageResource(R.drawable.sad2);
			}
			break;

		default:  
			Title1.setTextColor(R.color.theme);
            image1.setImageResource(R.drawable.happy2);
            appraisal.setConmment_kind(0);
			break;
		}
		intiRating();
	}

	/**
	 * @author Imissyou
	 * @todo TODO
	 *
	 * @return void
	 */
	public void intiRating() {
		appraisal.setConmment_weight(good_rationBar.getRating());
		good_rationBar_number.setText(appraisal.getConmment_weight() + "");
		appraisal.setConmment_fresh(free_rationBar.getRating());
		free_rationBar_number.setText(appraisal.getConmment_fresh() +"");
		appraisal.setConmment_speed(logistics_rationBar.getRating());
		logistics_rationBar_number.setText(appraisal.getConmment_speed() + "");
		
	}
}
