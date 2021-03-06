package com.access.d_dayproj.app;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.access.d_dayproj.Config;
import com.access.d_dayproj.R;
import com.access.d_dayproj.Utility;
import com.access.d_dayproj.R.drawable;
import com.access.d_dayproj.R.id;
import com.access.d_dayproj.R.layout;
import com.access.d_dayproj.R.string;
import com.access.d_dayproj.content.EventDataInfo;
import com.access.d_dayproj.database.DbOpenHelper;
import com.access.d_dayproj.database.DdayPreference;
import com.access.d_dayproj.sms.SMS;

public class EventActivity extends Activity {
	private Context mContext;
	private GridView mGridView;
	//private ArrayList<String> mItems;
	private boolean isAvailable;
	
	private DbOpenHelper mDbOpenHelper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity);
        mContext = this;
        
        startService(new Intent("com.access.d_dayproj.musicservice"));

        if(!Config.isRelease) {
            String _dbPath = Utility.getCacheFolder(mContext) + "/" + DbOpenHelper.DATABASE_NAME;
            File _dbFile = new File(_dbPath);

            if(Utility.getFileSize(_dbPath) != -1 ) {
            	_dbFile.delete();
            }
        }
        
        String dbPath = Utility.getCacheFolder(mContext) + "/" + DbOpenHelper.DATABASE_NAME;
        File dbFile = new File(dbPath);

        mDbOpenHelper = new DbOpenHelper(mContext);
        mDbOpenHelper.open();
        
        if(mDbOpenHelper.getEventDataSize() <= 0) {
        	for(int i = 0; i < 50; i++) {
            	mDbOpenHelper.insertColumn_EventData(i, i+1+" "+"선물", false, "");
            }
        } else {
        	if(!Config.isRelease) {
        		for(int i = 0; i < 50; i++) {
                	mDbOpenHelper.insertColumn_EventData(i, i+1+" "+"선물", false, "");
                }
            }
        }
        
        mGridView = (GridView)findViewById(R.id.event_grid);
        mGridView.setAdapter(new gridAdapter());
    }
    
    public class gridAdapter extends BaseAdapter {
    	LayoutInflater inflater; 
    	
    	public gridAdapter() {
    		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	}
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDbOpenHelper.getEventDataSize();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mDbOpenHelper.getEventData(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.event_item, parent,	false);
			}

			final EventDataInfo info = mDbOpenHelper.getEventData(position);

			final ImageView iv = (ImageView) convertView.findViewById(R.id.iv_event_item_image);
			
			if (info.getState() == false) { // not open state..
				iv.setImageDrawable(getResources().getDrawable(R.drawable.icon_postit));
				iv.setOnClickListener(new OnClickListener() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String strLastDate = DdayPreference.getEventLastDate(mContext);
						if(!strLastDate.isEmpty() && strLastDate.compareTo("")!=0) {
							String day;
							String month;
							String year;
							
							String hour;
							String minute;
							String second;
							
							String tempStr = strLastDate;
							year = tempStr.substring(0, 4);
							month = tempStr.substring(6, 8);
							day = tempStr.substring(10, 12);
							
//							hour = tempStr.substring(14, 16);
//							minute = tempStr.substring(18, 20);
//							second = tempStr.substring(22, 24);
//						
//							Calendar lastDate = Calendar.getInstance();
//							lastDate.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), 
//									Integer.parseInt(hour), Integer.parseInt(minute), Integer.parseInt(second));
//							
//							long diff_Result = 0;
//							diff_Result = (Calendar.getInstance().getTimeInMillis() - lastDate.getTimeInMillis()) / 1000;
//							diff_Result = diff_Result / Config.RetryTime;
							
							Calendar lastDate = Calendar.getInstance();
							lastDate.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 
									0, 0, 0);
							
							long now = System.currentTimeMillis();
							Date date = new Date(now);
							
							Calendar currDate = Calendar.getInstance();
							currDate.set(date.getYear()+1900, date.getMonth()+1, date.getDate(), 
									0, 0, 0);
							
							long diff_Result = 0;
							diff_Result = (currDate.getTimeInMillis() - lastDate.getTimeInMillis()) / 1000;
							diff_Result = diff_Result / Config.RetryTime;
							
							Log.i(mContext.getClass().getName(), "날짜 차이 = " + diff_Result);
							
							if(diff_Result <= 0) {
								// 하루가 지나기 전
								Toast.makeText(mContext, R.string.event_cant_send, Toast.LENGTH_SHORT).show();
								return;
							}
						}

						iv.setImageDrawable(getResources().getDrawable(R.drawable.icon_check));
						Toast.makeText(mContext, "value : " + info.getValue(),Toast.LENGTH_SHORT).show();

						String strCurDate = Utility.getCurrentDate();

						mDbOpenHelper.updateColumn_EventData(position,info.getValue(), true, strCurDate);
						DdayPreference.setEventLastDate(mContext, strCurDate);
						Log.i("AAA", "preference = " + strCurDate);
						iv.setOnClickListener(null);
						iv.setEnabled(false);
						
						SMS sms = new SMS(mContext);
						sms.sendSMS(Config.phoneNumber, info.getValue() + getResources().getString(R.string.event_sms_message));
						
						//String myPhoneNum = Utility.getMyPhoneNumber(mContext);
						//sms.sendSMS(myPhoneNum, info.getValue() + "을(를) 획득하였습니다. 축하합니다!");
					}
				});
			} else {
				iv.setOnClickListener(null);
				iv.setEnabled(false);
				iv.setImageDrawable(getResources().getDrawable(R.drawable.icon_check));
			}
			return convertView;
		}
	}
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	stopService(new Intent("com.access.d_dayproj.musicservice"));
    }
}
