package com.access.d_dayproj;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;

public class Utility {
	public static boolean externalMemoryAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	public static String getCacheFolder(Context context) {
		String path;
		if(externalMemoryAvailable()) {
			path = context.getExternalCacheDir().getAbsolutePath() + "/";
			File file = new File(path);
			
			if(!file.exists()) {
				file.mkdirs();
			}
		} else {
			return null;
		}
		return path;
	}
	
	public static long getFileSize(String path) {
		File f = new File(path);
		if(f.exists()) {
		} else {
			return -1;
		}
		return f.length();
	}
	
	public static String getCurrentDate() {
		long now = System.currentTimeMillis();
		Date date = new Date(now);
		SimpleDateFormat curDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return curDate.format(date);
	}
	
	public static String getMyPhoneNumber(Context context) {
		TelephonyManager telManager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
		String phoneNum = telManager.getLine1Number();
		return phoneNum;
	}
}
