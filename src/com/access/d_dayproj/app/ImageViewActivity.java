package com.access.d_dayproj.app;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.access.d_dayproj.R;
import com.access.d_dayproj.widget.PagerAdapterClass;

public class ImageViewActivity extends Activity implements OnClickListener{
	
	private MediaPlayer music;
	private Button btn_music;
	private ImageView iv_setting;
	
	private int mBtn_musicId;
	private int mIv_settingId;
	
	private ViewPager mPager;
	private LinearLayout mPageMark;
	private int mPrevPosition;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.imageview_activity);
        
        music = MediaPlayer.create(this, R.raw.second);
        music.setLooping(true);
        
        btn_music = (Button) findViewById(R.id.btn_music);
        mPageMark = (LinearLayout)findViewById(R.id.page_mark);
        
        btn_music.setOnClickListener(this);
        
        mBtn_musicId = btn_music.getId();

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
        mPager.setCurrentItem(PagerAdapterClass.COUNT);
        mPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override public void onPageSelected(int position) {
				if(position < PagerAdapterClass.COUNT)
					mPager.setCurrentItem(position+PagerAdapterClass.COUNT, false);
				else if(position >= PagerAdapterClass.COUNT*2)	 
					mPager.setCurrentItem(position - PagerAdapterClass.COUNT, false);
				else {	
					position -= PagerAdapterClass.COUNT;
					mPageMark.getChildAt(mPrevPosition).setBackgroundResource(R.drawable.page_not);
					mPageMark.getChildAt(position).setBackgroundResource(R.drawable.page_select);
					mPrevPosition = position;
				}
			}
			@Override public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {}
			@Override public void onPageScrollStateChanged(int state) {}
		});

		initPageMark();
		
		setPage();
	}
    
    private void setPage() {
    	iv_setting = (ImageView) findViewById(R.id.iv_setting);
    	iv_setting.setOnClickListener(this);
    	iv_setting.setBackgroundResource(R.drawable.setting);;
    	mIv_settingId = iv_setting.getId();
    	
    	iv_setting.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){ 
					iv_setting.setBackgroundResource(R.drawable.setting_press);
				}
                if(event.getAction() == MotionEvent.ACTION_UP){ 
                	iv_setting.setBackgroundResource(R.drawable.setting);
                }
				return false;
			}
		});
    }
    
  	private void initPageMark(){
  		for(int i=0; i<PagerAdapterClass.COUNT; i++)
  		{
  			ImageView iv = new ImageView(getApplicationContext());
  			iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

  			if(i==0)
  				iv.setBackgroundResource(R.drawable.page_select);
  			else
  				iv.setBackgroundResource(R.drawable.page_not);

  			mPageMark.addView(iv);
  		}
  		mPrevPosition = 0;
  	}
    
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int mId = v.getId();
		
		if(mId == mBtn_musicId)
		{
			if(music.isPlaying()){
				music.stop();
				try {
					music.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				btn_music.setText("Music Start");
			} else {
				music.start();
				btn_music.setText("Music Stop");
			}
		}
		else if(mId == mIv_settingId)
		{
			if(music.isPlaying()){
				music.stop();
				try {
					music.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				btn_music.setText("Music Start");
			}
			
			Toast.makeText(getApplicationContext(), "Setting Click", Toast.LENGTH_SHORT).show();
//			Intent it = new Intent(this, ThirdActivity.class);
//			startActivity(it);
		}
	}

	@Override
    public void onResume()
    {
    	super.onResume();
    	
    	if(!music.isPlaying()){
			music.start();
			btn_music.setText("Music Stop");
		}
    }
    
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    
    	if(music.isPlaying()){
			music.stop();
			try {
				music.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			btn_music.setText("Music Start");
		}
    }
}
