package com.access.d_dayproj.database;

import android.content.Context;

import com.access.d_dayproj.Config;


public class DdayPreference {
	private static String key_event_last_date = "key_event_last_date";
	
	public static void setEventLastDate(Context context, String date) {
		context.getSharedPreferences(Config.PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putString(key_event_last_date, date).commit();
	}
	public static String getEventLastDate(Context context) {
		return context.getSharedPreferences(Config.PREFERENCE_NAME, Context.MODE_PRIVATE).getString(key_event_last_date, "");
	}
}
