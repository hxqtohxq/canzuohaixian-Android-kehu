package com.baidu.loc;

import android.app.Application;
import android.widget.TextView;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.zhanjixun.activity.MainActivity;
import com.zhanjixun.utils.LogUtils;

public class LocationApplication extends Application {
	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;
	public TextView mLocationResult;
	public MainActivity activity;

	@Override
	public void onCreate() {
		super.onCreate();
		mLocationClient = new LocationClient(this.getApplicationContext());
		mLocationClient.registerLocationListener(new MyLocationListener());
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			Address address = location.getAddress();
			try {
				StringBuffer buffer = new StringBuffer();
				if (address.city != null) {
					buffer.append(address.city);
				}
				if (address.district != null) {
					buffer.append(address.district);
				}
				if (address.street != null) {
					buffer.append(address.street);
				}
				if (address.streetNumber != null) {
					buffer.append(address.streetNumber);
				}
				String addressStr = buffer.toString();
				if (addressStr != null) {
					LogUtils.v(addressStr);
					logMsg(addressStr);
					activity.setLocation(addressStr);
					mLocationClient.stop();
				}
			} catch (Exception e) {
				logMsg("¶¨Î»Ê§°Ü");
			}
		}
	}

	public void logMsg(String str) {
		try {
			if (mLocationResult != null) {
				mLocationResult.setText(str);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
