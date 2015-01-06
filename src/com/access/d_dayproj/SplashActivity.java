package com.access.d_dayproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        
        Button pressBtn = (Button)findViewById(R.id.btn_splash_press);
        pressBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent();
		        it.setClass(getApplicationContext(), CertifyActivity.class);
		        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        startActivity(it);
		        
		        finish();
			}
		});
    }
}
