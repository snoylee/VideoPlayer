package com.vcooline.li.videoplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcooline.li.videoplayer.view.SketchFragment;
import com.viewpagerindicator.TabPageIndicator;


public class NavigationTabFragment extends Fragment {

    private static final String[] CONTENT = new String[]{"素描", "色彩", "速写", "设计", "文化课堂", "艺术图库", "艺考咨询", "个人中心"};
    private ViewPager pager;
    private TabPageIndicator indicator;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NavigationTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationTabFragment newInstance() {
        NavigationTabFragment fragment = new NavigationTabFragment();
        return fragment;
    }

    public NavigationTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return new SketchFragment();
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
