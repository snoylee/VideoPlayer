package com.vcooline.li.videoplayer.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetLatestUpdateAction;
import com.vcooline.li.videoplayer.bean.GroupBean;
import com.vcooline.li.videoplayer.tools.view.SectionTitleListView;
import com.vcooline.li.videoplayer.view.viewadapter.LatestAdapter;

import java.util.ArrayList;


public class LatestFragment extends Fragment {

    private Activity activity;
    private SectionTitleListView gridView;
    private RelativeLayout noResult;
    private RelativeLayout loadingView;
    private LatestAdapter latestAdapter;
    private GetLatestUpdateAction getLatestUpdateAction;
    private GetLatestUpdateAction.GetLatestCallback getLatestCallback;
    private ArrayList<GroupBean> datas = new ArrayList<GroupBean>();


    private String materialType;
    private boolean isMediaImage = false;
    private boolean isMultiClick = false;
    private boolean loadFinished = false;
    private boolean isLoading = false;
    private int page = 1;


    //"materialType":"素描","materialClasses":"几何体","materialSubClasses":"组合","isMediaImage":"1"
    public static LatestFragment newInstance(String mMaterialType) {
        LatestFragment fragment = new LatestFragment();
        fragment.materialType = mMaterialType;
        return fragment;
    }

    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        gridView = (SectionTitleListView) view.findViewById(R.id.section_list);
        noResult = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        loadingView = (RelativeLayout) view.findViewById(R.id.load_lay);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        latestAdapter = new LatestAdapter(activity,datas,gridView);
        gridView.setAdapter(latestAdapter);

        getLatestCallback = new GetLatestUpdateAction.GetLatestCallback() {
            @Override
            public void getCacheBack(ArrayList<GroupBean> resultList) {
                if(resultList.size()>0){
                    loadingView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                    noResult.setVisibility(View.GONE);
                    datas = resultList;
                    latestAdapter.setGroupList(datas);
                    gridView.invalidate();
                    for (int i = 0; i < latestAdapter.getGroupCount(); i++) {
                        gridView.expandGroup(i);
                    }
                    latestAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void callBackSuccess(ArrayList<GroupBean> resultList) {
                loadingView.setVisibility(View.GONE);
                if(resultList.size()>0){
                    gridView.setVisibility(View.VISIBLE);
                    noResult.setVisibility(View.GONE);
                    datas = resultList;
                    latestAdapter.setGroupList(datas);
                    gridView.invalidate();
                    for (int i = 0; i < latestAdapter.getGroupCount(); i++) {
                        gridView.expandGroup(i);
                    }

                    latestAdapter.notifyDataSetChanged();

                }else{
                    noResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void callbackError(int status, String errorMsg) {

            }
        };

        getLatestUpdateAction = new GetLatestUpdateAction(activity,getLatestCallback);
        getLatestUpdateAction.getLatest();

    }
}
