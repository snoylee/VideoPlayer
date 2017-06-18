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

public class JingwuFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,shuiguoragment,shucaiFragment,wenjuFragment,qiminFragment,shenghuoFragment,qitaFragment;
    private TabButton shuiguo,shucai,wenju,qimin,shenghuo,qita;

    public JingwuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jingwu, container, false);
        shuiguo = (TabButton) view.findViewById(R.id.shuiguo);
        shucai = (TabButton) view.findViewById(R.id.shucai);
        wenju = (TabButton) view.findViewById(R.id.wenju);
        qimin = (TabButton) view.findViewById(R.id.qimin);
        shenghuo = (TabButton) view.findViewById(R.id.shenghuo);
        qita = (TabButton) view.findViewById(R.id.qita);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("01","02", null, false);
        showChildFragment(allFragment);
        shuiguo.setOnClickListener(this);
        shucai.setOnClickListener(this);
        wenju.setOnClickListener(this);
        qimin.setOnClickListener(this);
        shenghuo.setOnClickListener(this);
        qita.setOnClickListener(this);


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
            case R.id.shuiguo:
                shuiguo.setSelected(true);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qimin.setSelected(false);
                shenghuo.setSelected(false);
                qita.setSelected(false);
                if(shuiguoragment == null){
                    shuiguoragment = SketchFragment.newInstance("01", "02", "03", false);
                }
                showChildFragment(shuiguoragment);
                break;
            case R.id.shucai:
                shuiguo.setSelected(false);
                shucai.setSelected(true);
                wenju.setSelected(false);
                qimin.setSelected(false);
                shenghuo.setSelected(false);
                qita.setSelected(false);
                if(shucaiFragment == null){
                    shucaiFragment = SketchFragment.newInstance("01", "02", "04", false);
                }
                showChildFragment(shucaiFragment);
                break;
            case R.id.wenju:
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(true);
                qimin.setSelected(false);
                shenghuo.setSelected(false);
                qita.setSelected(false);
                if(wenjuFragment == null){
                    wenjuFragment = SketchFragment.newInstance("01", "02", "05", false);
                }
                showChildFragment(wenjuFragment);
                break;
            case R.id.qimin:
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qimin.setSelected(true);
                shenghuo.setSelected(false);
                qita.setSelected(false);
                if(qiminFragment == null){
                    qiminFragment = SketchFragment.newInstance("01", "02", "06", false);
                }
                showChildFragment(qiminFragment);
                break;
            case R.id.shenghuo:
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qimin.setSelected(false);
                shenghuo.setSelected(true);
                qita.setSelected(false);
                if(shenghuoFragment == null){
                    shenghuoFragment = SketchFragment.newInstance("01", "02", "07", false);
                }
                showChildFragment(shenghuoFragment);
                break;
            case R.id.qita:
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qimin.setSelected(false);
                shenghuo.setSelected(false);
                qita.setSelected(true);
                if(qitaFragment == null){
                    qitaFragment = SketchFragment.newInstance("01", "02", "07", false);
                }
                showChildFragment(qitaFragment);
                break;
            default:
                break;
        }
    }
}
