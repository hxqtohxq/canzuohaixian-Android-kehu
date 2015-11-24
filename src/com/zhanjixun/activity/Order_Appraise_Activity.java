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

	// �û���д������
	private TextView user_appraise;
	// �����ƺ���
	private RatingBar good_rationBar;
	// ���ʶȺ���
	private RatingBar free_rationBar;
	// �����ٶȺ���
	private RatingBar logistics_rationBar;
	// ���������� ��0-5��
	private TextView good_rationBar_number;
	// ���ʶ����� ��0-5��
	private TextView free_rationBar_number;
	// �����ٶ����� ��0-5��
	private TextView logistics_rationBar_number;
	// ��������
	private CheckBox niming_checkBox;

	// ����
	private LinearLayout good_check;
	private TextView Title1;
	private ImageView image1;
	// ����
	private LinearLayout ok_check;
	private TextView Title2;
	private ImageView image2;
	// ����
	private LinearLayout bad_check;
	private TextView Title3;
	private ImageView image3;
	// ��ƷͼƬ
	private ImageView order_image;

	/**
	 * ��һҳ�洫����������ڼ��غ��ύ����
	 */
	private String user_id;
	private String order_id;

	/**
	 * ��ȡҳ�滺������
	 */
	
	//Ҫ�ύ������ʵ����
	private Appraisal appraisal;
	// �ύ��ť
	private Button submit;
	// ���嶯��
	private LoadingDialog dialog;

	private Map<String, Object> mapData;
	
	//����TitleBar��
	private  boolean[] Btn ={true,true,true};
	
	//����ͼƬURL�ĵ�ַ
	private final String GetImageURL =" ";
	
	

	/**
	 * ��������ҳ��
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_grade);

		init();
		getIntentData();
		// ������ƷͼƬ
		if (getOrderImage() != null) {
			order_image.setImageBitmap(getOrderImage());
		}
		// �ύ����
		if (doSubmit()) {
			Toast.makeText(this, "���ύ������ʧ�ܣ�", Toast.LENGTH_LONG).show();
		}
	}
    //��ȡ�û�ID�Ͷ���ID
	private void getIntentData() {
		Intent intent = getIntent();
		String order_id =null;
			order_id = intent.getStringExtra("order_id").toString();
			Log.v("order_id", order_id + "");
		appraisal.setOrder_id(order_id);
//		appraisal.setUser_id(intent.getStringExtra("user_id"));
	}

	// ��ȡ��ƷͼƬ
	private Bitmap getOrderImage() {
		Map<String,String> params = new HashMap<String,String>();
		params.put(order_id, appraisal.getOrder_id());
		DC.getInstance().getDatasFromServer("Appraise", GetImageURL, params, this);
		return null;
	}

	/**
	 * �����ύ����
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
	 * ��ȡҳ���ύ����Ϣ��װ����������
	 * 
	 * @return map Map<String,Object>
	 */
	private Map<String, Object> getData() {

		final Map<String, Object> map = new HashMap<String, Object>();

		// ��ȡǰһ��ҳ�淢������Ϣ
		map.put("order_id", appraisal.getOrder_id());
		map.put("phone_number", appraisal.getUser_id());
		if (!StringUtil.isEmptyString(user_appraise.getText().toString())) {
			appraisal.setConmment_text(user_appraise.getText().toString());
		} else {
			Toast.makeText(this, "����д��������Ϣ", Toast.LENGTH_LONG).show();
		}
		// �Ƿ�Ϊ����
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
	 * ��ȡ������ͼƬ��ַ����ͼƬ
	 * @author Imissyou
	 * @param resultData
	 * @todo TODO
	 *
	 * @return void
	 */
	private void initData(Map<String, Object> resultData) {
//		String OrderImageURL = resultData.get("Url").toString();
	}

	// ��ʼ���ؼ�
	private void init() {
		appraisal = new Appraisal();
		submit = (Button) findViewById(R.id.order_grade_ensureBtn_submit);
		niming_checkBox = (CheckBox) findViewById(R.id.order_grade_checkBox_niming);
		order_image = (ImageView) findViewById(R.id.order_grade_orderImage);
		// ������
		good_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_zujzuc);
		good_rationBar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
                intiRating();				
			}
		});
		good_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number1);
		// ���ʶ�
		free_rationBar = (RatingBar) findViewById(R.id.order_grade_ratBar_xinxdu);
		free_rationBar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intiRating();
			}
		});
		free_rationBar_number = (TextView) findViewById(R.id.order_grade_ratBar_number2);
		// �����ٶ�
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
			//����ҳ������
			initData(result);
		} else if (Boolean.parseBoolean(result.get("serviceResult").toString()) &&
				taskTag.equals(Order_Appraise_Activity.this.toString())) {
			dialog.dismiss();
			finish();
		} else {
			Toast.makeText(this, "��������ύʧ��", Toast.LENGTH_LONG).show();
		}
	}

	/* ���� Javadoc��
	 * @todo
	 * �������� Ĭ�� ����
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
