package com.zhanjixun.activity;


import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.data.DC;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;

public class Order_Appraise_Activity extends Activity implements OnDataReturnListener{
	
	//用户填写的评论
	private TextView user_appraise;
	//足斤足称好评
	private RatingBar good_rationBar;
	//新鲜度好评
	private RatingBar free_rationBar;
	//物流速度好评
	private RatingBar logistics_rationBar;
	//足斤足称评度 （0-5）
	private TextView good_rationBar_number;
	//新鲜度评度 （0-5）
	private TextView free_rationBar_number;
	//物流速度评度 （0-5）
	private TextView logistics_rationBar_number;
	//匿名评论
	private CheckBox niming_checkBox;
	
    //好评
	private LinearLayout good_check;
	//中评
	private LinearLayout ok_check;
	//差评
	private LinearLayout bad_check;
	//商品图片
	private ImageView order_image;
	
	/**
	 * 上一页面传入的数据用于加载和提交数据
	 */
	private String user_id;
	private String order_id;
	
	
	/**
	 * 获取页面缓存数据
	 */
	//评论类型
	private String conmment_kind;
	//评论文本
	private String conmment_text;
	//评分发货足斤足称
	private float conmment_weight;
	//评分发货新鲜度
	private float conmment_fresh;
	//评分发货速度
	private float conmment_speed;
	//是否匿名
	private boolean anonymous;
	
	//提交按钮
	private Button submit;
	//缓冲动画
	private LoadingDialog dialog;
	
	
	private Map<String,Object> mapData;
	/**
	 * 订单评分页面
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_grade);
		
		init();
		getIntentData();
		//加载商品图片
		if (getOrderImage() != null){
		order_image.setImageBitmap(getOrderImage());
	    }
		//提交数据
		if(doSubmit()) {
			Toast.makeText(this, "您提交的数据失败！", Toast.LENGTH_LONG).show();
		}
	}
	
	
	private void getIntentData() {
		Intent intent = getIntent();
		this.order_id = intent.getStringExtra("order_id");
		this.user_id = intent.getStringExtra("user_id");
	}


	//获取商品图片
	private Bitmap getOrderImage() {
		/**
		 * TODO----
		 */
		return null;
	}
	/**
	 * 处理提交数据
	 */
     private boolean doSubmit() {
    	submit.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				mapData = initData();
				dialog = new LoadingDialog(Order_Appraise_Activity.this);
				DC.getInstance().AddAppraise(Order_Appraise_Activity.this,mapData);
			}
		});	
		return false;
	}
    
     /**
      * 获取页面提交的信息封装成请求数据
      * @return map Map<String,Object> 
      */
     private Map<String , Object> initData() {
    	 
    	 final Map<String , Object> map = new HashMap<String, Object>();
    	
    	 //获取前一个页面发来的信息
    	 map.put("order_id", order_id);
    	 map.put("phone_number", user_id);
    	 
    	 //获取评分类型
    	 good_check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				conmment_kind = "好评";
			}
		});
    	 ok_check.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 				conmment_kind = "中评";
 			}
 		});
    	 bad_check.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 				conmment_kind ="差评";
 			}
 		});
    	 /**
    	  * 评论的留言可以不填.
    	  * 获取用户的信息
    	  */
    	 if (!StringUtil.isEmptyString(user_appraise.getText().toString())) { 
    	    conmment_text = user_appraise.getText().toString();
    	 } else if (0f != good_rationBar.getRating() 
    			 && free_rationBar.getRating()!= 0f 
    			 && logistics_rationBar.getRating() != 0f) {
    	       conmment_weight= good_rationBar.getRating();
    	       conmment_fresh= free_rationBar.getRating();
    	       conmment_speed= logistics_rationBar.getRating();
               }  else {
            	   Toast.makeText(this, "请填写完整的信息", Toast.LENGTH_LONG).show();
    	 }
    	 //是否为匿名
    	 if(niming_checkBox.isClickable()) {
    	    anonymous = true;
    	 } else {
    		anonymous =  false;
    	 }
    	 map.put("conmment_text", conmment_text);
    	 map.put("conmment_weight", conmment_weight);
    	 map.put("conmment_fresh", conmment_fresh);
    	 map.put("conmment_speed", conmment_speed);
    	 map.put("anonymous", anonymous);
    	 
    	 return map;
     }
     
     

	//初始化控件
	private void init() {
		submit = (Button) findViewById(R.id.order_grade_ensureBtn_submit);
		niming_checkBox = (CheckBox) findViewById(R.id.order_grade_checkBox_niming);
		order_image = (ImageView) findViewById(R.id.order_grade_orderImage);
		//足斤足称
		good_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_zujzuc);
		good_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number1);
		//新鲜度
		free_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_xinxdu);
		free_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number2);
		//发货速度
		logistics_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_fahuosdu);
		logistics_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number3);
		
		good_check = (LinearLayout) findViewById(R.id.order_grade_LinerLayout_Good);
		ok_check = (LinearLayout) findViewById(R.id.order_grade_LinerLayout_OK);
		bad_check = (LinearLayout) findViewById(R.id.order_grade_LinerLayout_Bad);
	}

	
	
	
	/**
	 * TODO
	 * @param taskTag
	 * @param result
	 */
	@Override
	public void onDataReturn(String taskTag, Map<String, Object> result) {
		dialog.dismiss();
		if(Boolean.parseBoolean(result.get("serviceResult").toString())) {
		finish();
		} else {
			Toast.makeText(this, "你的评论提交失败", Toast.LENGTH_LONG).show();
		}
	}
}
