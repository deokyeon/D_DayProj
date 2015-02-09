package com.access.d_dayproj.app;

import com.access.d_dayproj.R;
import com.access.d_dayproj.R.id;
import com.access.d_dayproj.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        
        Button photo = (Button)findViewById(R.id.photo);
        photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),  "ToDo photo",  Toast.LENGTH_SHORT).show();
				Intent it = new Intent();
		        it.setClass(getApplicationContext(), ImageViewActivity.class);
		        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        startActivity(it);
			}
        	
        });
        Button lottery = (Button)findViewById(R.id.lottery);
        lottery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),  "lottery",  Toast.LENGTH_SHORT).show();
				Intent it = new Intent();
		        it.setClass(getApplicationContext(), EventActivity.class);
		        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        startActivity(it);
				
			}
		});

    }

}
