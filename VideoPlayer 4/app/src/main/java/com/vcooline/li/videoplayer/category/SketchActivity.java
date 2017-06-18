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
import com.vcooline.li.videoplayer.view.sumiao.BanshenxiangFragment;
import com.vcooline.li.videoplayer.view.sumiao.JihetiFragment;
import com.vcooline.li.videoplayer.view.sumiao.JingwuFragment;
import com.vcooline.li.videoplayer.view.sumiao.TouxiangFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.HashMap;

public class SketchActivity extends FragmentActivity implements View.OnClickListener {

    private static final String[] CONTENT = new String[]{"最近更新", "几何体", "静物",
            "石膏头像","头像","半身像"};
    private ViewPager pager;
    private TabPageIndicator indicator;
    //fragment informations
    private Fragment currentFragment;
    private HashMap<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

    private Fragment latestFragmet,jihetiFragment,jingwuyFragment,shigaolFragment,
            touxiangFragment,banshengxiangFragment;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketch);

        NotNetWorkTool.checkNetWork(this);
        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        back = (ImageView)findViewById(R.id.back);
        FragmentPagerAdapter adapter = new SketchPageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

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
        public android.support.v4.app.Fragment getItem(int position) {
            if(position==0){
                // 最近更新
                if(latestFragmet == null){
//                    latestFragmet = SketchFragment.newInstance("01","00",null,false);
                    latestFragmet = new LatestFragment();
                }
                return  latestFragmet;
            }else if(position == 1){
                //几何体
                if(jihetiFragment == null){
                    jihetiFragment = new JihetiFragment();
                }
                return jihetiFragment;
            }else if(position == 2){
                if(jingwuyFragment == null){
                    jingwuyFragment = new JingwuFragment();
                }
                return jingwuyFragment;
            }else if(position == 3){
                if(shigaolFragment == null){
                    shigaolFragment =  SketchFragment.newInstance("01","03",null,false);
                }
                return shigaolFragment;
            }else if(position == 4){
                if(touxiangFragment == null){
                    touxiangFragment = new TouxiangFragment();
                }
                return touxiangFragment;
            }else{
                if(banshengxiangFragment == null){
                    banshengxiangFragment = new BanshenxiangFragment();
                }
                return banshengxiangFragment;
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
