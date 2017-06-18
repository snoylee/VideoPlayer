package com.vcooline.li.videoplayer.view.profile.shoucang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.tools.TabButton;

public class FavContainerFragment extends Fragment implements View.OnClickListener{

    private ProfileActivity activity;
    private FavVideoFragment favVideoFragment;

    private TabButton shiping,tupian,shuji;


    public FavContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_container, container, false);
        shiping = (TabButton)view.findViewById(R.id.shiping);
        tupian = (TabButton)view.findViewById(R.id.tupian);
        shuji = (TabButton)view.findViewById(R.id.shuji);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        favVideoFragment = new FavVideoFragment();
        showChildFragment(favVideoFragment);
        shiping.setSelected(true);
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
            case R.id.shiping:
                shiping.setSelected(true);
                tupian.setSelected(false);
                shuji.setSelected(false);
                break;
            case R.id.tupian:
                shiping.setSelected(false);
                tupian.setSelected(true);
                shuji.setSelected(false);

                break;
            case R.id.shuji:
                shiping.setSelected(false);
                tupian.setSelected(false);
                shuji.setSelected(true);

                break;
            default:
                break;

        }
    }
}
