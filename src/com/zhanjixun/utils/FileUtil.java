package com.zhanjixun.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;

import com.zhanjixun.data.Constants;

public class FileUtil {

	/**
	 * ��ȡ�ļ���·��������������򴴽�
	 * 
	 * @param dir
	 * @param name
	 * @return
	 */
	public static String getDirPath(File dir, String name) {
		File file = new File(dir, name);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	/**
	 * ��ȡ�ļ��о���·�����������򴴽�
	 * 
	 * @param dir
	 * @param name
	 * @return
	 */
	public static String getDirPath(String dir, String name) {
		File file = new File(dir, name);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	/**
	 * �����ļ���
	 * 
	 * @param dirpath
	 * @return
	 */
	public static boolean createDir(String dirpath) {
		File file = new File(dirpath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return true;
	}

	public static String getCacheFilePath(String url) {
		StringBuilder path = new StringBuilder();
		path.append(Constants.CACHE_DIR).append("/")
				.append(StringUtil.MD5(url))
				.append(url.substring(url.lastIndexOf(".")));
		return path.toString();
	}

	public static String getSelectImagePath(Context context, Intent data) {
		Uri selectedImage = data.getData();
		// Log.e(TAG, selectedImage.toString());
		if (selectedImage != null) {
			String uriStr = selectedImage.toString();
			String path = uriStr.substring(10, uriStr.length());
			if (path.startsWith("com.sec.android.gallery3d")) {
				// Log.e(TAG,
				// "It's auto backup pic path:"+selectedImage.toString());
				return null;
			}
		}
		String[] filePathColumn = { MediaColumns.DATA };
		Cursor cursor = context.getContentResolver().query(selectedImage,
				filePathColumn, null, null, null);
		cursor.moveToFirst();
		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String picturePath = cursor.getString(columnIndex);
		cursor.close();
		return picturePath;
	}

	/**
	 * �ӱ��ػ�ȡͼƬ
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap loadBitmap(String path) {

		File file = new File(path);
		if (file.exists()) {
			return BitmapFactory.decodeFile(path);
		}
		return null;
	}

	/**
	 * �ж��ļ��Ƿ����
	 * 
	 * @param path
	 * @return
	 */
	public static boolean fileIsExists(String path) {
		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public static String dirPath() {

		return null;
	}
}
