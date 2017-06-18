package com.vcooline.li.videoplayer.category;

import android.os.Bundle;
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
import com.vcooline.li.videoplayer.view.BlankFragment;
import com.vcooline.li.videoplayer.view.InformationFragment;
import com.viewpagerindicator.TabPageIndicator;
/*
* 添加了五个这样子的模块的，这个主要用的就是vierPager形式进行的配置的
* */
public class InformationActivity extends FragmentActivity {

   private static final String[] CONTENT = new String[]{"高考动态", "高考备考", "大学简介", "专业介绍", "美术考点","美术考题","招生简介","专业合格线","录取查询","院校录取线"};


    private ViewPager pager;
    private TabPageIndicator indicator;

    private InformationFragment informationFragment1;
    private BlankFragment blankFragment;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        NotNetWorkTool.checkNetWork(this);

        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);

        FragmentPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
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
        getMenuInflater().inflate(R.menu.information, menu);
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

    //具体的现实每个模块的具体显示的样子
    class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if(position == 0) {
                return InformationFragment.newInstance("1");
            }else if(position==1){
                return InformationFragment.newInstance("2");
            }else if(position==2){
                return InformationFragment.newInstance("3");
            }else if(position==3){
                return InformationFragment.newInstance("4");
            }else if(position==4){
                return InformationFragment.newInstance("5");
            }else if(position==5) {
                return InformationFragment.newInstance("6");
            }else if(position==5) {
                return InformationFragment.newInstance("7");
            }else if(position==7) {
                return InformationFragment.newInstance("8");
            }else if(position==8) {
                return InformationFragment.newInstance("9");
            }else{
                return InformationFragment.newInstance("10");
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
