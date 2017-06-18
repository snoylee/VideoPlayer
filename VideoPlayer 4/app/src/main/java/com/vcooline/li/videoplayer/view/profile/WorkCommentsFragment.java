package com.vcooline.li.videoplayer.view.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetWorksCommentsAction;
import com.vcooline.li.videoplayer.bean.WorkCommentsBean;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.databases.SharePrefs;

import java.util.ArrayList;


public class WorkCommentsFragment extends Fragment {

    private ListView listView;
    private RelativeLayout loading;
    private RelativeLayout noResultView;
    private ImageView upload;
    private ProfileActivity activity;
    private GetWorksCommentsAction getWorksCommentsAction;
    private GetWorksCommentsAction.GetCommentsCallback callback;
    private ArrayList<WorkCommentsBean> datas = new ArrayList<WorkCommentsBean>();
    private WorkCommentsAdapter workCommentsAdapter;

    public WorkCommentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_work_comments, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        loading = (RelativeLayout) view.findViewById(R.id.load_lay);
        noResultView = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        upload = (ImageView) view.findViewById(R.id.upload);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        workCommentsAdapter = new WorkCommentsAdapter(activity);
        listView.setAdapter(workCommentsAdapter);


        SharedPreferences prefs = SharePrefs.getProfileSharePrefs(activity);
        String userId = prefs.getString(SharePrefs.USERID,"");
        String username = prefs.getString(SharePrefs.USERNAME,"");
        callback = new GetWorksCommentsAction.GetCommentsCallback(){
            @Override
            public void callbackSuccess(ArrayList<WorkCommentsBean> workCommentsBeans) {
                loading.setVisibility(View.GONE);
                if(workCommentsBeans.size()>0){
                    listView.setVisibility(View.VISIBLE);
                    noResultView.setVisibility(View.GONE);
                    workCommentsAdapter.setWorkCommentsBeans(workCommentsBeans);
                    workCommentsAdapter.notifyDataSetChanged();
                }else{
                    listView.setVisibility(View.GONE);
                    noResultView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void actionErrorBack(String errorCode) {
                loading.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                noResultView.setVisibility(View.VISIBLE);
            }
        };
        getWorksCommentsAction = new GetWorksCommentsAction(activity,callback);
        getWorksCommentsAction.getComments(userId+"",username);

        upload.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                WorkCommentUploadFragment workCommentUploadFragment = new WorkCommentUploadFragment();
                activity.showWorkCommentUpload(workCommentUploadFragment);
            }
        });

    }
}
