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

public class CJingwuFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,huahuiFragment,shuiguoFragment,shucaiFragment,wenjuFragment,qitaFragment;
    private TabButton huahui,shuiguo,shucai,wenju,qita;

    public CJingwuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentc_jingwu, container, false);
        huahui = (TabButton) view.findViewById(R.id.huahui);
        shuiguo = (TabButton) view.findViewById(R.id.shuiguo);
        shucai = (TabButton) view.findViewById(R.id.shucai);
        wenju = (TabButton) view.findViewById(R.id.wenju);
        qita = (TabButton) view.findViewById(R.id.qita);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("02","07", null, false);
        showChildFragment(allFragment);
        huahui.setOnClickListener(this);
        shuiguo.setOnClickListener(this);
        shucai.setOnClickListener(this);
        wenju.setOnClickListener(this);
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
            case R.id.huahui:
                huahui.setSelected(true);
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qita.setSelected(false);
                if(huahuiFragment == null){
                    huahuiFragment = SketchFragment.newInstance("02","07","08",false);
                }
                showChildFragment(huahuiFragment);
                break;
            case R.id.shuiguo:
                huahui.setSelected(false);
                shuiguo.setSelected(true);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qita.setSelected(false);
                if(shuiguoFragment == null){
                    shuiguoFragment = SketchFragment.newInstance("02","07","03",false);
                }
                showChildFragment(shuiguoFragment);
                break;
            case R.id.shucai:
                huahui.setSelected(false);
                shuiguo.setSelected(false);
                shucai.setSelected(true);
                wenju.setSelected(false);
                qita.setSelected(false);
                if(shucaiFragment == null){
                    shucaiFragment = SketchFragment.newInstance("02","07","04",false);
                }
                showChildFragment(shucaiFragment);
                break;
            case R.id.wenju:
                huahui.setSelected(false);
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(true);
                qita.setSelected(false);
                huahui.setSelected(false);
                shuiguo.setSelected(false);
                shucai.setSelected(true);
                wenju.setSelected(false);
                qita.setSelected(false);
                if(wenjuFragment == null){
                    wenjuFragment = SketchFragment.newInstance("02","07","05",false);
                }
                showChildFragment(wenjuFragment);
                break;
            case R.id.qita:
                huahui.setSelected(false);
                shuiguo.setSelected(false);
                shucai.setSelected(false);
                wenju.setSelected(false);
                qita.setSelected(true);
                if(qitaFragment == null){
                    qitaFragment = SketchFragment.newInstance("02","07","08",false);
                }
                showChildFragment(qitaFragment);
                break;
            default:
                break;
        }
    }
}
