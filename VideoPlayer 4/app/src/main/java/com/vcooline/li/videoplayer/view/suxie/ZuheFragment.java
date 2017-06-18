package com.vcooline.li.videoplayer.view.suxie;

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

public class ZuheFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,shenghuoFragment,yundongFragment,qitaFragment;
    private TabButton shenghuo,yundong,qita;

    public ZuheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zuhe, container, false);
        shenghuo = (TabButton) view.findViewById(R.id.shenghuo);
        yundong = (TabButton) view.findViewById(R.id.yundong);
        qita = (TabButton) view.findViewById(R.id.qita);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("03","11", null, false);
        showChildFragment(allFragment);
        shenghuo.setOnClickListener(this);
        yundong.setOnClickListener(this);
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
            case R.id.shenghuo:
                shenghuo.setSelected(true);
                yundong.setSelected(false);
                qita.setSelected(false);
                if(shenghuoFragment == null){
                    shenghuoFragment = SketchFragment.newInstance("03","11","18",false);
                }
                showChildFragment(shenghuoFragment);
                break;
            case R.id.yundong:
                shenghuo.setSelected(false);
                yundong.setSelected(true);
                qita.setSelected(false);
                if(yundongFragment == null){
                    yundongFragment = SketchFragment.newInstance("03","11","19",false);
                }
                showChildFragment(yundongFragment);
                break;
            case R.id.qita:
                shenghuo.setSelected(false);
                yundong.setSelected(false);
                qita.setSelected(true);
                if(qitaFragment == null){
                    qitaFragment = SketchFragment.newInstance("03","11","08",false);
                }
                showChildFragment(qitaFragment);
                break;
            default:
                break;
        }
    }
}
