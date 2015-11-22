package com.zhanjixun.fragment;

import android.util.Log;

public class MyThread extends Thread {

	public Thread thread;
	public boolean isPaused;
	private Object lock;

	public MyThread() {
		lock = new Object();
		isPaused = false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

	public void onPause() {
		synchronized (lock) {
			isPaused = true;
		}
	}

	public void onResume() {
		synchronized (lock) {
			isPaused = false;
			lock.notifyAll();
		}
	}

	public void pauseThread() {
		synchronized (lock) {
			if (isPaused) {
				try {
					lock.wait();
				} catch (Exception e) {
					Log.v("MyThread", "fails");
				}
			}
		}
	}

}
