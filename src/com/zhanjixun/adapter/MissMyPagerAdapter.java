/**
 * 
 */
package com.zhanjixun.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * @author Imissyou
 * @Time  2015年11月23日
 *
 */
public class MissMyPagerAdapter extends PagerAdapter {
	
	
	List<View> listViews = new ArrayList<View>();
	
	/**
	 * 
	 */
	public MissMyPagerAdapter(List<View> listView) {
		this.listViews = listView;
	}
 
	/* （非 Javadoc）
	 * @todo
	 *
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return listViews.size();
	}

	/* （非 Javadoc）
	 * @todo
	 *
	 * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	
	
	

}
