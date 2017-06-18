package com.vcooline.li.videoplayer.view.videoplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.UtilTool;

public class VideoRightFragment extends Fragment {
    private SketchBean sketchBean;

    private TextView name,username,updateTime;
    private ImageView image1,image2;

    public static VideoRightFragment newInstance(SketchBean mSketchBean){
        VideoRightFragment fragment = new VideoRightFragment();
        fragment.sketchBean = mSketchBean;
        return fragment;
    }

    public VideoRightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle args) {
        super.onSaveInstanceState(args);
        args.putSerializable("sketchBean",sketchBean);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_right, container, false);
        name = (TextView) view.findViewById(R.id.name);
        username = (TextView) view.findViewById(R.id.username);
        updateTime = (TextView) view.findViewById(R.id.update_time);
        image1 = (ImageView) view.findViewById(R.id.pic1);
        image2 = (ImageView) view.findViewById(R.id.pic2);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            sketchBean = (SketchBean)savedInstanceState.getSerializable("sketchBean");
        }
        name.setText(sketchBean.getMaterialName());
        username.setText("测试用户");
        if(sketchBean.getCreateAt()!=null && sketchBean.getCreateAt().equals("")) {
            updateTime.setText(UtilTool.getFormatTime(Long.getLong(sketchBean.getCreateAt())));
        }

    }
}
