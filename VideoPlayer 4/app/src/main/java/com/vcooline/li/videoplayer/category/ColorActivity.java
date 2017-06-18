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
import com.vcooline.li.videoplayer.view.LatestFragment;
import com.vcooline.li.videoplayer.view.SketchFragment;
import com.vcooline.li.videoplayer.view.secai.CJingwuFragment;
import com.vcooline.li.videoplayer.view.secai.CTouxiangFragment;
import com.vcooline.li.videoplayer.view.secai.CfengjingFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.HashMap;

public class ColorActivity extends FragmentActivity implements View.OnClickListener {

    private static final String[] CONTENT = new String[]{"最近更新", "理论课", "静物",
            "头像","风景"};
    private ViewPager pager;
    private TabPageIndicator indicator;
    //fragment informations
    private Fragment currentFragment;
    private HashMap<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

    private Fragment latestFragmet,lilunkeFragment,jingwuyFragment,
            touxiangFragment,fengjingFragment;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

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
//                return  latestFragmet = SketchFragment.newInstance("02","00",null,false);
                return new LatestFragment();
            }else if(position == 1){
                if(lilunkeFragment == null) {
                    lilunkeFragment = SketchFragment.newInstance("02", "06", null, false);
                }
                return lilunkeFragment;
            }else if(position == 2){
                if(jingwuyFragment == null){
                    jingwuyFragment = new CJingwuFragment();
                }
                return jingwuyFragment;
            }else if(position == 3){
                if(touxiangFragment == null){
                    touxiangFragment = new CTouxiangFragment();
                }
                return touxiangFragment;
            }else{
                if(fengjingFragment == null){
                    fengjingFragment = new CfengjingFragment();
                }
                return fengjingFragment;
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
