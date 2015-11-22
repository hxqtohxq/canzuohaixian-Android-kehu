package com.zhanjixun.utils;

import android.content.Context;

public class ImageSizeUtil {
	// dipת����
	public static int DipToPixels(Context context, float dip) {
		return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
	}

	// ����תdip
	public static float PixelsToDip(Context context, int Pixels) {
		return Pixels / context.getResources().getDisplayMetrics().density;
	}
}
