package com.vcooline.li.videoplayer.view.secai;

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

public class CfengjingFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,shuixiangFragment,xiaoxiangFragment,ziranFragment,chengshiFragment;
    private TabButton shuixiang,xiaoxiang,ziran,chengshi;

    public CfengjingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentc_fengjing, container, false);
        shuixiang = (TabButton) view.findViewById(R.id.shuixiang);
        xiaoxiang = (TabButton) view.findViewById(R.id.xiaoxiang);
        ziran = (TabButton) view.findViewById(R.id.ziran);
        chengshi = (TabButton) view.findViewById(R.id.chengshi);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("03","09", null, false);
        showChildFragment(allFragment);
        shuixiang.setOnClickListener(this);
        xiaoxiang.setOnClickListener(this);
        ziran.setOnClickListener(this);
        chengshi.setOnClickListener(this);
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
            case R.id.shuixiang:
                shuixiang.setSelected(true);
                xiaoxiang.setSelected(false);
                ziran.setSelected(false);
                chengshi.setSelected(false);
                if(shuixiangFragment == null){
                    shuixiangFragment = SketchFragment.newInstance("03","09","11",false);
                }
                showChildFragment(shuixiangFragment);
                break;
            case R.id.xiaoxiang:
                shuixiang.setSelected(false);
                xiaoxiang.setSelected(true);
                ziran.setSelected(false);
                chengshi.setSelected(false);
                if(xiaoxiangFragment == null){
                    xiaoxiangFragment = SketchFragment.newInstance("03","09","12",false);
                }
                showChildFragment(xiaoxiangFragment);
                break;
            case R.id.ziran:
                shuixiang.setSelected(false);
                xiaoxiang.setSelected(true);
                ziran.setSelected(false);
                chengshi.setSelected(false);
                if(ziranFragment == null){
                    ziranFragment = SketchFragment.newInstance("03","09","13",false);
                }
                showChildFragment(ziranFragment);
                break;

            case R.id.chengshi:
                shuixiang.setSelected(false);
                xiaoxiang.setSelected(true);
                ziran.setSelected(false);
                chengshi.setSelected(false);
                if(chengshiFragment == null){
                    chengshiFragment = SketchFragment.newInstance("03","09","14",false);
                }
                showChildFragment(chengshiFragment);
                break;
            default:
                break;
        }
    }
}
