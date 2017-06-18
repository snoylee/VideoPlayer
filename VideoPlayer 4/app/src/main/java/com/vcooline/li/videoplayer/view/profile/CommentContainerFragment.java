package com.vcooline.li.videoplayer.view.profile;

import android.content.Intent;
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

import java.util.ArrayList;

public class CommentContainerFragment extends Fragment {

    private ProfileActivity activity;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private WorkCommentUploadFragment workCommentUploadFragment;

    public CommentContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_container, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        WorkCommentsFragment workCommentsFragment = new WorkCommentsFragment();
        fragments.add(workCommentsFragment);


        showChildFragment(fragments.get(fragments.size() - 1));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK) {
            if(requestCode == WorkCommentUploadFragment.CHOOSE_UP_IMAGE){
                workCommentUploadFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showChildFragment(Fragment fragment){
        FragmentManager childFm =  getChildFragmentManager();
        FragmentTransaction ft = childFm.beginTransaction();
        if (childFm.findFragmentByTag(
                String.valueOf(fragment.hashCode())) != null) {
            ft.show(fragment);
        } else {
            ft.replace(R.id.container, fragment,
                    String.valueOf(fragment.hashCode()));
            ft.show(fragment);
        }
        ft.commitAllowingStateLoss();
    }

    public void showUploadFragment(WorkCommentUploadFragment fragment){
        workCommentUploadFragment = fragment;
        fragments.add(fragment);
        showChildFragment(fragment);
    }

    public void removeUploadFragment(){
        if(fragments.size() == 2){
            fragments.remove(1);
        }
        showChildFragment(fragments.get(0));
    }
}
