package com.vcooline.li.videoplayer.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.tools.NotNetWorkTool;
import com.vcooline.li.videoplayer.view.LatestFragment;
import com.vcooline.li.videoplayer.view.SketchFragment;
import com.vcooline.li.videoplayer.view.suxie.DanrenFragment;
import com.vcooline.li.videoplayer.view.suxie.ZuheFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.HashMap;

public class QuickActivity extends FragmentActivity implements View.OnClickListener {

    private static final String[] CONTENT = new String[]{"最近更新", "理论课", "单人速写",
            "场景速写"};
    private ViewPager pager;
    private TabPageIndicator indicator;
    //fragment informations
    private Fragment currentFragment;
    private HashMap<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

    private Fragment latestFragmet,lilunkeFragment,danrenFragment,zuheFragment;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sketch, menu);
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
//                return  latestFragmet = SketchFragment.newInstance("03","00",null,false);
                return new LatestFragment();
            }else if(position == 1){
               if(lilunkeFragment == null) {
                   lilunkeFragment = SketchFragment.newInstance("03", "06", null, false);
               }
                return  lilunkeFragment;
            }else if(position == 2){
                if(danrenFragment == null){
                    danrenFragment = new DanrenFragment();
                }
                return danrenFragment;
            }else{
                if(zuheFragment == null){
                    zuheFragment = new ZuheFragment();
                }
                return zuheFragment;
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
