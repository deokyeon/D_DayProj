package com.access.d_dayproj.database;

import android.provider.BaseColumns;

public class EventDb {
	private int mPosition;
	private String mValue;
	private boolean mState;
	private String mDate;
	
	public static final class CreateDb implements BaseColumns {
		public static final String POSITION = "position";
		public static final String VALUE = "value";
		public static final String STATE = "state";
		public static final String DATE = "date";
		public static final String _TABLENAME = "event_table";
		public static final String _CREATE = "create table " + _TABLENAME + "("
				+ _ID + " integer primary key autoincrement, "
				+ POSITION + " integer not null , "
				+ VALUE + " text not null , "
				+ STATE + " boolean not null , "
				+ DATE + " date );";
	}
	
	public EventDb() {
	}
	
	public EventDb(int position, String value, boolean state, String date) {
		this.mPosition = position;
		this.mValue = value;
		this.mState = state;
		this.mDate = date;
	}
	
	public void setPosition(int position) {
		this.mPosition = position;
	}
	public int getPosition() {
		return mPosition;
	}
	
	public void setValue(String value) {
		this.mValue = value;
	}
	public String getValue() {
		return mValue;
	}
	
	public void setState(boolean state) {
		this.mState = state;
	}
	public boolean getState() {
		return mState;
	}
	
	public void setDate(String date) {
		this.mDate = date;
	}
	public String getDate() {
		return mDate;
	}
}
