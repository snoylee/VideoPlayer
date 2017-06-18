package com.vcooline.li.videoplayer.view.profile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.bean.VideoBean;
import com.vcooline.li.videoplayer.databases.SketchDBManager;
import com.vcooline.li.videoplayer.view.viewadapter.DownloadSketchAdapter;

import java.util.ArrayList;


public class DownloadVideoFragment extends Fragment {

    private Activity activity;
    private GridView gridView;
    private RelativeLayout noResult;
    private RelativeLayout loadingView;
    private ArrayList<VideoBean> datas = new ArrayList<VideoBean>();
    private DownloadSketchAdapter sketchAdapter;

    public DownloadVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sketch, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        noResult = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        loadingView = (RelativeLayout) view.findViewById(R.id.load_lay);

        gridView.setVisibility(View.VISIBLE);
        noResult.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        SketchDBManager dbManager = new SketchDBManager(getActivity());
        ArrayList<SketchBean> beans = dbManager.getSketchBean();
        sketchAdapter = new DownloadSketchAdapter(activity);
        sketchAdapter.setVideoList(beans);
        gridView.setAdapter(sketchAdapter);
        sketchAdapter.notifyDataSetChanged();
        if(beans.size() == 0){
            gridView.setVisibility(View.GONE);
            noResult.setVisibility(View.VISIBLE);
        }


        Log.v("lijing","sketch length is "+ beans.size());
    }
}
