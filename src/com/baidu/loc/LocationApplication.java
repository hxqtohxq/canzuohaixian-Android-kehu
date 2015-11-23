package com.baidu.loc;

import android.app.Application;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.zhanjixun.utils.LogUtils;

public class LocationApplication extends Application {
	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;
	public TextView mLocationResult;
	public boolean isLocated = false;

	@Override
	public void onCreate() {
		super.onCreate();
		mLocationClient = new LocationClient(this.getApplicationContext());
		mLocationClient.registerLocationListener(new MyLocationListener());
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			String addrStr = location.getAddrStr();
			if (addrStr != null) {
				LogUtils.i(addrStr);
				if (addrStr.contains("��")) {
					addrStr = addrStr.substring(addrStr.indexOf("��") + 1);
				}
				if (addrStr.contains("ʡ")) {
					addrStr = addrStr.substring(addrStr.indexOf("ʡ") + 1);
				}
				logMsg(addrStr);
				mLocationClient.stop();
			} else {
				logMsg("��λʧ��");
			}
		}
	}
	
	

	public boolean isLocated() {
		return isLocated;
	}



	public void setLocated(boolean isLocated) {
		this.isLocated = isLocated;
	}

	public LocationClient getmLocationClient() {
		return mLocationClient;
	}

	public MyLocationListener getmMyLocationListener() {
		return mMyLocationListener;
	}
	
	public void logMsg(String str) {
		try {
			if (mLocationResult != null)
				mLocationResult.setText(str);
				setLocated(true);
				//Log.i("bb", "set isLocated "+isLocated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ȡ��֮ǰע��Ķ�λ��������
	public void cancelLocation(){
		mLocationClient.unRegisterLocationListener(mMyLocationListener);
	}
}
