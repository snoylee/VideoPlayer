package com.vcooline.li.videoplayer.category;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.vcooline.li.videoplayer.R;

import java.util.ArrayList;

import ru.truba.touchgallery.GalleryWidget.BasePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

public class GalleryActivity extends Activity {
    private ArrayList<String> sketchBeans = new ArrayList<String>();
    private int selectedPosition = 0;
    private GalleryViewPager mViewPager;
    private ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        close = (ImageView)findViewById(R.id.close);
        mViewPager = (GalleryViewPager)findViewById(R.id.viewer);
        sketchBeans = ( ArrayList<String>)getIntent().getSerializableExtra("sketchBeans");
        selectedPosition = getIntent().getIntExtra("currentPosition",0);
        Log.w("lijing","selectedPosition is "+selectedPosition);
        close.bringToFront();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, sketchBeans);
        pagerAdapter.setOnItemChangeListener(new BasePagerAdapter.OnItemChangeListener()
        {
            @Override
            public void onItemChange(int currentPosition)
            {
            }
        });

        mViewPager = (GalleryViewPager)findViewById(R.id.viewer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(selectedPosition);
        Log.w("lijing","selectedPosition is "+selectedPosition);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
