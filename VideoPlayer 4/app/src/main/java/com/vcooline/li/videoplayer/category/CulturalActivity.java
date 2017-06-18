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
import com.vcooline.li.videoplayer.view.SketchFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.HashMap;

public class CulturalActivity extends FragmentActivity implements View.OnClickListener {

    private static final String[] CONTENT = new String[]{"语文", "英语", "数学",
            "政治","历史","地理","物理","化学","生物"};
    private ViewPager pager;
    private TabPageIndicator indicator;
    //fragment informations
    private Fragment currentFragment;
    private HashMap<String, Fragment> fragmentMap = new HashMap<String, Fragment>();

    private SketchFragment yuwenFragmet,yingyuFragment,shuxueFragment,zhengzhiFragment,
            lishiFragment,diliFragment,wuliFragment,huaxueFragment,shengwuFragment;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caltural);

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


//        private SketchFragment yuwenFragmet,yingyuFragment,shuxueFragment,zhengzhiFragment,
//                lishiFragment,diliFragment,wuliFragment,huaxueFragment,shengwuFragment;
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return  yuwenFragmet = SketchFragment.newInstance("05","21",null,false);
            }else if(position == 1){
                return yingyuFragment = SketchFragment.newInstance("05","22",null,false);
            }else if(position == 2){
                return shuxueFragment = SketchFragment.newInstance("05","23",null,false);
            }else if(position == 3){
                return zhengzhiFragment =  SketchFragment.newInstance("05","24",null,false);
            }else if(position == 4){
                return lishiFragment = SketchFragment.newInstance("05","25",null,false);
            }else if(position == 5){
                return diliFragment = SketchFragment.newInstance("05","26",null,false);
            }else if(position == 6){
                return wuliFragment = SketchFragment.newInstance("05","27",null,false);
            }else if(position == 7){
                return huaxueFragment = SketchFragment.newInstance("05","28",null,false);
            }else{
                return shengwuFragment = SketchFragment.newInstance("05","29",null,false);
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
