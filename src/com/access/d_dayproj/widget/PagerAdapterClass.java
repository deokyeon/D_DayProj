package com.access.d_dayproj.widget;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * PagerAdapter 
 */
public class PagerAdapterClass extends PagerAdapter{

    Context mContext;
    public static int COUNT;
    private static ArrayList<String> strA;
    
    public PagerAdapterClass(Context ctx){
        //super();
    	this.mContext = ctx;
    	strA = new ArrayList<String>();
    	getListFromAsset();
    }
    
    @Override
    public int getCount() {
        return COUNT * 3;
    }
      
    @Override
        public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
    	position %= COUNT;
    	
        ImageView imageView = new ImageView(mContext);
         
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        
        Bitmap bmp = getBitmapFromAsset(position);
        imageView.setImageBitmap(bmp);
        
        ((ViewPager) container).addView(imageView, 0);
 
        return imageView;
    }
      
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    private void getListFromAsset()
    {
    	COUNT = 0;
        AssetManager assetManager = mContext.getAssets();
        String[] istr = null;
        try {
            istr = assetManager.list("");
            
            for(int i = 0; i < istr.length; i++)
            {
            	if(istr[i].contains(".jpg") || istr[i].contains(".JPG") || istr[i].contains(".JPEG") || istr[i].contains(".jpeg") || istr[i].contains(".png") || istr[i].contains(".PNG"))
            	{
            		COUNT++;
            		strA.add(istr[i]);
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Bitmap getBitmapFromAsset(int position)
    {
        AssetManager assetManager = mContext.getAssets();
        Bitmap bitmap = null;

		try {
			InputStream is = assetManager.open(strA.get(position));
			bitmap = BitmapFactory.decodeStream(is);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return bitmap;
    }
}
