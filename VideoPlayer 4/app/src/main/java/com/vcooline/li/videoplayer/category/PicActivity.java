package com.vcooline.li.videoplayer.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.tools.NotNetWorkTool;
import com.vcooline.li.videoplayer.view.PicturesFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.HashMap;

public class PicActivity extends FragmentActivity implements View.OnClickListener {

    private static final String[] CONTENT = new String[]{"最近更新", "素描", "色彩",
            "速写","设计","创作","照片","出版图书"};
    private ViewPager pager;
    private TabPageIndicator indicator;
    //fragment informations
    private Fragment currentFragment;
    private HashMap<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

    private Fragment latestFragmet,sumiaoFragment,secaiFragment,suxieFragment,
    shejiFragment,chuangzuoFragment,zhaopianFragment,tushuFragment;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        NotNetWorkTool.checkNetWork(this);
        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        FragmentPagerAdapter adapter = new SketchPageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onClick(View view) {

    }


    class SketchPageAdapter extends FragmentPagerAdapter {
        public SketchPageAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                // 最近更新
                return  latestFragmet = PicturesFragment.newInstance("06", "00", null, true);
            }else if(position == 1){
                //几何体
                return sumiaoFragment = PicturesFragment.newInstance("06","30",null,true);
            }else if(position == 2){
                return secaiFragment = PicturesFragment.newInstance("06","31",null,true);
            }else if(position == 3){
                return suxieFragment =  PicturesFragment.newInstance("06","32",null,true);
            }else if(position == 4){
                return shejiFragment = PicturesFragment.newInstance("06","33",null,true);
            }else if(position == 5){
                return chuangzuoFragment = PicturesFragment.newInstance("06","34",null,true);
            }else if(position == 6){
                return zhaopianFragment = PicturesFragment.newInstance("06","35",null,true);
            }else{
                return tushuFragment = PicturesFragment.newInstance("06","36",null,true);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}
