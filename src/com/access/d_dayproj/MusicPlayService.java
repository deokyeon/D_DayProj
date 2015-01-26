package com.access.d_dayproj;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicPlayService extends Service {
	private String TAG = "MusicPlayService";
	public MediaPlayer mp;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent it, int startId) {
		Log.i(TAG, "Music Service onStart()");
		super.onStart(it, startId);
		
		mp = MediaPlayer.create(this, R.raw.sudam_10cm);
		mp.setLooping(true);
		mp.start();
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "Music Service onDestory()");
		super.onDestroy();
		mp.stop();
	}
}
