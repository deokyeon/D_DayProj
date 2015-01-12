package com.access.d_dayproj.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.access.d_dayproj.content.EventDataInfo;

public class DbOpenHelper {
	
	public static final String DATABASE_NAME = "xproj_event.db";
	
	private static final String KEY_POSITION = "position";
	private static final String KEY_VALUE = "value";
	private static final String KEY_STATE = "state";
	private static final String KEY_DATE = "date";
	
	private static final int DATABASE_VERSION = 1;
	
	private SQLiteDatabase mDb;
	private DatabaseHelper mDbHelper;
	private Context mContext;
	
	private class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(EventDb.CreateDb._CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + EventDb.CreateDb._TABLENAME);
			onCreate(db);
		}
	}
	
	public DbOpenHelper(Context context) {
		this.mContext = context;
	}
	
	public DbOpenHelper open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext, mContext.getExternalCacheDir().getAbsolutePath() + "/" + DATABASE_NAME, null, DATABASE_VERSION);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		mDb.close(); 
	}
	
	public long insertColumn_EventData(int position, String value, boolean state, String date) {
		ContentValues values = new ContentValues();
		values.put(EventDb.CreateDb.POSITION, position);
		values.put(EventDb.CreateDb.VALUE, value);
		values.put(EventDb.CreateDb.STATE, state);
		values.put(EventDb.CreateDb.DATE, date);
		
		return mDb.insert(EventDb.CreateDb._TABLENAME, null, values);
	}
	
	public boolean updateColumn_EventData(int position, String value, boolean state, String date) {
		ContentValues values = new ContentValues();
		values.put(EventDb.CreateDb.POSITION, position);
		values.put(EventDb.CreateDb.VALUE, value);
		values.put(EventDb.CreateDb.STATE, state);
		values.put(EventDb.CreateDb.DATE, date);
		
		return mDb.update(EventDb.CreateDb._TABLENAME, values, KEY_POSITION + "=" + position, null) > 0;
	}
	
	public boolean deleteAllEventData() {
		return mDb.delete(EventDb.CreateDb._TABLENAME, null, null) > 0;
	}

	public EventDataInfo getEventData(int position) {
		EventDataInfo edi = new EventDataInfo();
		
		Cursor c = mDb.query(EventDb.CreateDb._TABLENAME, null, KEY_POSITION + "=" + position, null, null, null, null);
		c.moveToFirst();
		if(c.getCount() > 0) {
			edi.setPosition(position);
			edi.setValue(c.getString(c.getColumnIndex(KEY_VALUE)));
			edi.setState(c.getInt(c.getColumnIndex(KEY_STATE)) > 0);
			edi.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
		}
		return edi;
	}
	
	public int getEventDataSize() {
		Cursor c = mDb.query(EventDb.CreateDb._TABLENAME, null, "", null, null, null, null);
		return c.getCount();
	}
	
	public EventDataInfo getEventLastDate() {
		EventDataInfo edi = new EventDataInfo();
		
		Cursor c = mDb.query(EventDb.CreateDb._TABLENAME, null, KEY_STATE + "=1", null, null, null, KEY_DATE + " desc");
		c.moveToFirst();
		if(c.getCount() > 0) {
			edi.setPosition(c.getInt(c.getColumnIndex(KEY_POSITION)));
			edi.setValue(c.getString(c.getColumnIndex(KEY_VALUE)));
			edi.setState(c.getInt(c.getColumnIndex(KEY_STATE)) > 0);
			edi.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
		}
		return edi;
	}
}
