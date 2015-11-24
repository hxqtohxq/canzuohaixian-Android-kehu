/**
 * 
 */
package com.zhanjixun.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Imissyou
 * @Time  2015��11��23��
 *
 */
public class MissMyViewPager extends ViewPager {

	/**
	 * @param context
	 */
	public MissMyViewPager(Context context) {
		super(context);
		// TODO �Զ����ɵĹ��캯�����
	}
	public MissMyViewPager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    @Override  
    public boolean dispatchTouchEvent(MotionEvent ev) {  
        getParent().requestDisallowInterceptTouchEvent(true);//��仰������ ���߸�view���ҵĵ����¼������д�����Ҫ�谭�ҡ�    
        return super.dispatchTouchEvent(ev);  
    }  

}
