package com.access.d_dayproj.content;

public class EventDataInfo {
	private int position;
	private String value;
	private boolean state;
	private String date;
	
	public EventDataInfo() {
		position = 0;
		value = "";
		state = false;
		date = "";
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
