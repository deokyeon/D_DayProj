package com.access.d_dayproj.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.access.d_dayproj.Config;
import com.access.d_dayproj.R;


public class CertifyActivity extends Activity {
	private Context mContext;
	private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certity_activity);
        mContext = this;
        
        confirm = (Button)findViewById(R.id.btn_certify_confirm);
        
        confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePicker dp = (DatePicker)findViewById(R.id.date_certify);
				int day = dp.getDayOfMonth();
				int month = dp.getMonth()+1; //Bug : January=0
				int year = dp.getYear();
				
				if(day == Config.certity_date_day && month == Config.certify_date_month && year == Config.certify_date_year) {
					Toast.makeText(mContext,  "first step Pass",  Toast.LENGTH_SHORT).show();
					
					Intent it = new Intent();
			        it.setClass(getApplicationContext(), Certify2Activity.class);
			        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        startActivity(it);
			        
			        finish();
				}
			}
		});
    }
}
