package com.vcooline.li.videoplayer.view.profile.shoucang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetCollectionsAction;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.view.viewadapter.SketchAdapter;

import java.util.ArrayList;

public class FavVideoFragment extends Fragment {

    private ProfileActivity activity;
    private GetCollectionsAction getCollectionsAction;
    private GetCollectionsAction.GetCollectionCallback getCollectionCallback;
    private SketchAdapter sketchAdapter;

    private GridView gridView;
    private RelativeLayout noResult;
    private RelativeLayout loadingView;

    public FavVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sketch, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        noResult = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        loadingView = (RelativeLayout) view.findViewById(R.id.load_lay);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        sketchAdapter = new SketchAdapter(activity);
        gridView.setAdapter(sketchAdapter);

        getCollectionCallback = new GetCollectionsAction.GetCollectionCallback(){
            @Override
            public void callBackSuccess(ArrayList<SketchBean> resultList) {
                loadingView.setVisibility(View.GONE);
                if(resultList.size()>0){
                    gridView.setVisibility(View.VISIBLE);
                    sketchAdapter.setVideoList(resultList);
                    sketchAdapter.notifyDataSetChanged();
                }else{
                    noResult.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void callbackError(int status, String errorMsg) {
                loadingView.setVisibility(View.GONE);
                noResult.setVisibility(View.VISIBLE);
            }
        };
        getCollectionsAction = new GetCollectionsAction(activity,getCollectionCallback);
        getCollectionsAction.getCollections("1");
    }
}
