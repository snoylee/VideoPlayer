/**
 * Copyright @ jxl
 */
package com.vcooline.li.videoplayer.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * @author kingjxl2006@126.com
 * @since 2012-10-16 上午11:18:21
 */
public class FragmentVPAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragments; 
	private FragmentManager fm;
	public FragmentVPAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
		super(fm);
		this.fm = fm;
		this.fragments = fragments;
	}
	
	public void setFragments(ArrayList<Fragment> fragments) {
		if(this.fragments != null){
			FragmentTransaction ft = fm.beginTransaction();
			for(Fragment f:this.fragments){
				ft.remove(f);
			}
			ft.commit();
			ft=null;
			fm.executePendingTransactions();
		}
		this.fragments = fragments;
		notifyDataSetChanged();
	}
	
	@Override  
	public int getItemPosition(Object object) {  
	    return POSITION_NONE;  
	}  

	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
}
