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

public class JihetiFragment extends Fragment implements  View.OnClickListener{

    private SketchFragment allFragment,dantiFragment,zuheFragment;
    private TabButton danti,zuhe;

    public JihetiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jiheti, container, false);
        danti = (TabButton) view.findViewById(R.id.danti);
        zuhe = (TabButton) view.findViewById(R.id.zuhe);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allFragment = SketchFragment.newInstance("01", "01", null, false);
        showChildFragment(allFragment);
        danti.setOnClickListener(this);
        zuhe.setOnClickListener(this);

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
            case R.id.danti:
                danti.setSelected(true);
                zuhe.setSelected(false);
                if(dantiFragment == null){
                    dantiFragment = SketchFragment.newInstance("01", "01", "01", false);
                }
                showChildFragment(dantiFragment);
                break;
            case R.id.zuhe:
                danti.setSelected(false);
                zuhe.setSelected(true);
                if(zuheFragment == null){
                    zuheFragment = SketchFragment.newInstance("01", "01", "", false);
                }
                showChildFragment(zuheFragment);
                break;
            default:
                break;
        }
    }
}
