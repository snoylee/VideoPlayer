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
import com.viewpagerindicator.TabPageIndicator;

import java.util.HashMap;

public class DesignActivity extends FragmentActivity implements View.OnClickListener {

    private static final String[] CONTENT = new String[]{"最近更新", "公共课", "装饰画",
            "创意图形","海报招贴","创意速写","字体设计","设计素描","设计色彩"};
    private ViewPager pager;
    private TabPageIndicator indicator;
    //fragment informations
    private Fragment currentFragment;
    private HashMap<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

    private SketchFragment latestFragmet,jihetiFragment,jingwuyFragment,shigaolFragment,
            touxiangFragment,banshengxiangFragment;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

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
//                return  latestFragmet = SketchFragment.newInstance("04","00",null,false);
                return new LatestFragment();
            }else if(position == 1){
                //几何体
                return jihetiFragment = SketchFragment.newInstance("04","12",null,false);
            }else if(position == 2){
                return jingwuyFragment = SketchFragment.newInstance("04","13",null,false);
            }else if(position == 3){
                return shigaolFragment =  SketchFragment.newInstance("04","14",null,false);
            }else if(position == 4){
                return touxiangFragment = SketchFragment.newInstance("04","15",null,false);
            }else{
                return banshengxiangFragment = new SketchFragment();
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
