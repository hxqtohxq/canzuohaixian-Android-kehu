package com.zhanjixun.views;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog {
	private ProgressDialog progressDialog;

	public LoadingDialog(Context context) {
		progressDialog = new ProgressDialog(context);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage("ÕýÔÚ¼ÓÔØ...");
	}

	public LoadingDialog(Context context, String message) {
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(message);
		progressDialog.setCanceledOnTouchOutside(false);
	}

	public void show() {
		if (progressDialog != null) {
			progressDialog.show();
		}
	}

	public void setMessage(String message) {
		progressDialog.setMessage(message);
	}

	public boolean dismiss() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			return true;
		}
		return false;
	}

}
