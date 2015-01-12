package com.access.d_dayproj;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.access.d_dayproj.content.EventDataInfo;
import com.access.d_dayproj.database.DbOpenHelper;

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
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						iv.setImageDrawable(getResources().getDrawable(R.drawable.icon_check));
						Toast.makeText(mContext, "value : " + info.getValue(), Toast.LENGTH_SHORT).show();

//						long now = System.currentTimeMillis();
//						Date date = new Date(now);
//						SimpleDateFormat curDate = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
//						String strCurDate = curDate.format(date);
						
						String strCurDate = Utility.getCurrentDate();
						
						mDbOpenHelper.updateColumn_EventData(position, info.getValue(), true, strCurDate);
						iv.setOnClickListener(null);
						iv.setEnabled(false);
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
}
