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

public class DanrenFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,zhanziFragment,zuoziFragment,dunziFragment,qitaFragment;
    private TabButton zhanzi,zuozi,dunzi,qita;

    public DanrenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danren, container, false);
        zhanzi = (TabButton) view.findViewById(R.id.zhanzi);
        zuozi = (TabButton) view.findViewById(R.id.zuozi);
        dunzi = (TabButton) view.findViewById(R.id.dunzi);
        qita = (TabButton) view.findViewById(R.id.qita);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("03","10", null, false);
        showChildFragment(allFragment);
        zhanzi.setOnClickListener(this);
        zuozi.setOnClickListener(this);
        dunzi.setOnClickListener(this);
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
            case R.id.zhanzi:
                zhanzi.setSelected(true);
                zuozi.setSelected(false);
                dunzi.setSelected(false);
                qita.setSelected(false);
                if(zhanziFragment == null){
                    zhanziFragment = SketchFragment.newInstance("03","10","15",false);
                }
                showChildFragment(zhanziFragment);
                break;
            case R.id.zuozi:
                zhanzi.setSelected(false);
                zuozi.setSelected(true);
                dunzi.setSelected(false);
                qita.setSelected(false);
                if(zuoziFragment == null){
                    zuoziFragment = SketchFragment.newInstance("03","10","16",false);
                }
                showChildFragment(zuoziFragment);
                break;
            case R.id.dunzi:
                zhanzi.setSelected(false);
                zuozi.setSelected(false);
                dunzi.setSelected(true);
                qita.setSelected(false);
                if(dunziFragment == null){
                    dunziFragment = SketchFragment.newInstance("03","10","17",false);
                }
                showChildFragment(dunziFragment);
                break;

            case R.id.qita:
                zhanzi.setSelected(false);
                zuozi.setSelected(false);
                dunzi.setSelected(false);
                qita.setSelected(true);
                if(qitaFragment == null){
                    dunziFragment = SketchFragment.newInstance("03","10","08",false);
                }
                showChildFragment(dunziFragment);
                break;
            default:
                break;
        }
    }
}
