package com.zhanjixun.views;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

public class MessageDialog {

	private AlertDialog dialog;

	public MessageDialog(Context context, String message) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("提示");
		builder.setMessage("\n" + message + "\n");
		builder.setPositiveButton("确定", null);
		builder.setCancelable(true);
		dialog = builder.create();
	}

	public void show() {
		if (dialog != null) {
			dialog.show();
		}
	}

	public void dissmiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
