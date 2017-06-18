package com.vcooline.li.videoplayer.view.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.WorkCommentsBean;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.tools.UtilTool;

public class WorkCommentDetailFragment extends Fragment {
    private WorkCommentsBean comments;
    private ProfileActivity activity;
    private ImageView close;
    private TextView time;
    private TextView commentTxt;

    public static WorkCommentDetailFragment newInstance(WorkCommentsBean mComments){
        WorkCommentDetailFragment workCommentDetailFragment = new WorkCommentDetailFragment();
        workCommentDetailFragment.comments = mComments;
        return workCommentDetailFragment;
    }


    public WorkCommentDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work_comment_detail, container, false);
        time = (TextView)view.findViewById(R.id.time);
        commentTxt = (TextView) view.findViewById(R.id.comment_txt);
        close = (ImageView)view.findViewById(R.id.close);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        time.setText(UtilTool.getFormatTime(comments.getUpdateTime()));
        commentTxt.setText(comments.getCommitText());
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        });
    }
}
