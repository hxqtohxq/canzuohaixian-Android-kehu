package com.zhanjixun.data;

import android.view.View;
import android.widget.ImageView;

import com.zhanjixun.net.AsyncImageTask;

public class ImageCenter {
	private static ImageCenter imageCenter;

	private ImageCenter() {
	}

	public static ImageCenter getInstance() {
		return imageCenter == null ? new ImageCenter() : imageCenter;
	}

	public void setForeground(String url, ImageView view) {
		new AsyncImageTask(url, view, AsyncImageTask.SET_BITMAP_FOREGROUND);
	}

	public void setBackground(String url, View view) {
		new AsyncImageTask(url, view, AsyncImageTask.SET_BITMAP_BACKGROUND);
	}
}
