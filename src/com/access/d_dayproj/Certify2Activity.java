package com.access.d_dayproj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
				EditText et1 = (EditText)findViewById(R.id.et_cetrity2_1);
				EditText et2 = (EditText)findViewById(R.id.et_certify2_2);
				
				String quiz_1 = et1.getText().toString();
				String quiz_2 = et2.getText().toString();
				
				if(quiz_1.compareTo(Config.certify_quiz_1)==0 && quiz_2.compareTo(Config.certify_quez_2)==0) {
					Toast.makeText(mContext,  "Second step Pass",  Toast.LENGTH_SHORT).show();
					
					Intent it = new Intent();
			        it.setClass(getApplicationContext(), EventActivity.class);
			        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        startActivity(it);
			        
			        finish();
				}
			}
		});
    }
}
