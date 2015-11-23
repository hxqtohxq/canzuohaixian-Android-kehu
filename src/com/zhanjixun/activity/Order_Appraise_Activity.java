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
	
	//�û���д������
	private TextView user_appraise;
	//�����ƺ���
	private RatingBar good_rationBar;
	//���ʶȺ���
	private RatingBar free_rationBar;
	//�����ٶȺ���
	private RatingBar logistics_rationBar;
	//���������� ��0-5��
	private TextView good_rationBar_number;
	//���ʶ����� ��0-5��
	private TextView free_rationBar_number;
	//�����ٶ����� ��0-5��
	private TextView logistics_rationBar_number;
	//��������
	private CheckBox niming_checkBox;
	
    //����
	private LinearLayout good_check;
	//����
	private LinearLayout ok_check;
	//����
	private LinearLayout bad_check;
	//��ƷͼƬ
	private ImageView order_image;
	
	/**
	 * ��һҳ�洫����������ڼ��غ��ύ����
	 */
	private String user_id;
	private String order_id;
	
	
	/**
	 * ��ȡҳ�滺������
	 */
	//��������
	private String conmment_kind;
	//�����ı�
	private String conmment_text;
	//���ַ���������
	private float conmment_weight;
	//���ַ������ʶ�
	private float conmment_fresh;
	//���ַ����ٶ�
	private float conmment_speed;
	//�Ƿ�����
	private boolean anonymous;
	
	//�ύ��ť
	private Button submit;
	//���嶯��
	private LoadingDialog dialog;
	
	
	private Map<String,Object> mapData;
	/**
	 * ��������ҳ��
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_grade);
		
		init();
		getIntentData();
		//������ƷͼƬ
		if (getOrderImage() != null){
		order_image.setImageBitmap(getOrderImage());
	    }
		//�ύ����
		if(doSubmit()) {
			Toast.makeText(this, "���ύ������ʧ�ܣ�", Toast.LENGTH_LONG).show();
		}
	}
	
	
	private void getIntentData() {
		Intent intent = getIntent();
		this.order_id = intent.getStringExtra("order_id");
		this.user_id = intent.getStringExtra("user_id");
	}


	//��ȡ��ƷͼƬ
	private Bitmap getOrderImage() {
		/**
		 * TODO----
		 */
		return null;
	}
	/**
	 * �����ύ����
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
      * ��ȡҳ���ύ����Ϣ��װ����������
      * @return map Map<String,Object> 
      */
     private Map<String , Object> initData() {
    	 
    	 final Map<String , Object> map = new HashMap<String, Object>();
    	
    	 //��ȡǰһ��ҳ�淢������Ϣ
    	 map.put("order_id", order_id);
    	 map.put("phone_number", user_id);
    	 
    	 //��ȡ��������
    	 good_check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				conmment_kind = "����";
			}
		});
    	 ok_check.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 				conmment_kind = "����";
 			}
 		});
    	 bad_check.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 				conmment_kind ="����";
 			}
 		});
    	 /**
    	  * ���۵����Կ��Բ���.
    	  * ��ȡ�û�����Ϣ
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
            	   Toast.makeText(this, "����д��������Ϣ", Toast.LENGTH_LONG).show();
    	 }
    	 //�Ƿ�Ϊ����
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
     
     

	//��ʼ���ؼ�
	private void init() {
		submit = (Button) findViewById(R.id.order_grade_ensureBtn_submit);
		niming_checkBox = (CheckBox) findViewById(R.id.order_grade_checkBox_niming);
		order_image = (ImageView) findViewById(R.id.order_grade_orderImage);
		//������
		good_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_zujzuc);
		good_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number1);
		//���ʶ�
		free_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_xinxdu);
		free_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number2);
		//�����ٶ�
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
			Toast.makeText(this, "��������ύʧ��", Toast.LENGTH_LONG).show();
		}
	}
}
