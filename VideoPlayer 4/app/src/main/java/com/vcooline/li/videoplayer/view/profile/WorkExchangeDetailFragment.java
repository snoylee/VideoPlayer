package com.vcooline.li.videoplayer.view.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.WwComment;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.tools.UtilTool;

import java.util.ArrayList;


public class WorkExchangeDetailFragment extends Fragment {
    private ProfileActivity activity;
    private String workName;
    private long updateTime;
    private ArrayList<WwComment> wwComments = new ArrayList<WwComment>();
    private ListView listView;
    private ImageView close;
    private TextView title;
    private TextView createTime;


    public static  WorkExchangeDetailFragment newInstance(String name,long time,ArrayList<WwComment> mWwComments){
        WorkExchangeDetailFragment fragment = new WorkExchangeDetailFragment();
        fragment.wwComments = mWwComments;
        return fragment;
    }

    public WorkExchangeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_work_exchange_detail, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        close = (ImageView) view.findViewById(R.id.close);
        title = (TextView) view.findViewById(R.id.title);
        createTime = (TextView) view.findViewById(R.id.create_time);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        title.setText(workName);
        createTime.setText(UtilTool.getFormatTime(updateTime));
        WorkExchangeDetailAdapter adapter = new WorkExchangeDetailAdapter(activity,wwComments);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                activity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });

    }
}
