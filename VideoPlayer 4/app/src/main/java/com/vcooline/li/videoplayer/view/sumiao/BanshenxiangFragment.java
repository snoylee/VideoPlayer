package com.vcooline.li.videoplayer.view.sumiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.tools.TabButton;
import com.vcooline.li.videoplayer.view.SketchFragment;

public class BanshenxiangFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,nanFragment,nvFragment;
    private TabButton nan,nv;

    public BanshenxiangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_touxiang, container, false);
        nan = (TabButton) view.findViewById(R.id.nan);
        nv = (TabButton) view.findViewById(R.id.nv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("01","05", null, false);
        showChildFragment(allFragment);
        nan.setOnClickListener(this);
        nv.setOnClickListener(this);

    }

    private void showChildFragment(Fragment fragment){
        FragmentManager childFm =  getChildFragmentManager();
        FragmentTransaction ft = childFm.beginTransaction();
        if (childFm.findFragmentByTag(
                String.valueOf(fragment.hashCode())) != null) {
            ft.show(fragment);
        } else {
            ft.replace(R.id.content_lay, fragment,
                    String.valueOf(fragment.hashCode()));
            ft.show(fragment);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nan:
                nan.setSelected(true);
                nv.setSelected(false);
                if(nanFragment == null){
                    nanFragment = SketchFragment.newInstance("01","05", "09", false);
                }
                showChildFragment(nanFragment);
                break;
            case R.id.nv:
                nan.setSelected(false);
                nv.setSelected(true);
                if(nvFragment == null){
                    nvFragment = SketchFragment.newInstance("01","05", "10", false);
                }
                showChildFragment(nvFragment);
                break;
            default:
                break;
        }
    }
}
