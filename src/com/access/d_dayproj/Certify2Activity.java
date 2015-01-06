package com.access.d_dayproj;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;


public class Certify2Activity extends Activity {
	private Context mContext;
	//private 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certity2_activity);
        mContext = this;
        
        Button confirm = (Button)findViewById(R.id.btn_certify2_confirm);
        
        confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePicker dp = (DatePicker)findViewById(R.id.date_certify);
				int day = dp.getDayOfMonth();
				int month = dp.getMonth()+1; //Bug : January=0
				int year = dp.getYear();
				
				if(day == Config.certity_date_1_day && month == Config.certify_date_1_month && year == Config.certify_date_1_year) {
					Toast.makeText(mContext,  "Pass",  Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
}
