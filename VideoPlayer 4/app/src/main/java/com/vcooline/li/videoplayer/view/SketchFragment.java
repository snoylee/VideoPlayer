package com.vcooline.li.videoplayer.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetSketchAction;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.view.viewadapter.SketchAdapter;

import java.util.ArrayList;


public class SketchFragment extends Fragment {

    private Activity activity;
    private GridView gridView;
    private RelativeLayout noResult;
    private RelativeLayout loadingView;
    private SketchAdapter sketchAdapter;
    private GetSketchAction getSketchAction;
    private GetSketchAction.GetSketchCallback getSketchCallback;

    private String materialType,materialClasses,materialSubClasses;
    private boolean isMediaImage = false;
    private boolean isMultiClick = false;
    private boolean loadFinished = false;
    private boolean isLoading = false;
    private int page = 1;


    //"materialType":"素描","materialClasses":"几何体","materialSubClasses":"组合","isMediaImage":"1"
    public static SketchFragment newInstance(String mMaterialType,String materialClasses,
                                             String mMaterialSubClasses,boolean mIsMediaImage) {
        SketchFragment fragment = new SketchFragment();
        fragment.materialType = mMaterialType;
        fragment.materialClasses = mMaterialSubClasses;
        fragment.materialClasses = materialClasses;
        fragment.isMediaImage = mIsMediaImage;
        return fragment;
    }

    public SketchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sketch, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        noResult = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        loadingView = (RelativeLayout) view.findViewById(R.id.load_lay);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        sketchAdapter = new SketchAdapter(activity,isMediaImage);
        gridView.setAdapter(sketchAdapter);

        getSketchCallback = new GetSketchAction.GetSketchCallback() {


            @Override
            public void getCacheBack(ArrayList<SketchBean> resultList) {
                if (resultList.size() > 0) {
                    loadingView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                    noResult.setVisibility(View.GONE);
                    sketchAdapter.setVideoList(resultList);
                    sketchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void callBackSuccess(ArrayList<SketchBean> resultList) {
                Log.v("lijing", "fragment call back");
                loadingView.setVisibility(View.GONE);
                isLoading = false;
                if (resultList.size() > 0) {
                    page++;
                    gridView.setVisibility(View.VISIBLE);
                    sketchAdapter.getVideoList().addAll(resultList);
                    sketchAdapter.notifyDataSetChanged();
                }else{
                    loadFinished = true;
                    if(page == 1) {
                        noResult.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void callbackError(int status, String errorMsg) {
                loadingView.setVisibility(View.GONE);
                if(page == 1) {
                    noResult.setVisibility(View.VISIBLE);
                }
            }
        };
        getSketchAction = new GetSketchAction(activity, getSketchCallback);
        getDatas(1);


        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if(lastInScreen == totalItemCount && !loadFinished && page >1 && !isLoading){
                    getDatas(page);
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
        });

    }

    public void getDatas(int page){
        isLoading = true;
        getSketchAction.getSketchData(materialType,materialClasses, materialSubClasses, false, page);
        Log.v("lijing","get data page is "+ page);
    }

}
