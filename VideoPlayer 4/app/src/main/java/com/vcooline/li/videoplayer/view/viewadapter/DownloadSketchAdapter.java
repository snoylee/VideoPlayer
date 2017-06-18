package com.vcooline.li.videoplayer.view.viewadapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.AppCacheManager;
import com.vcooline.li.videoplayer.tools.ProgressWheel;
import com.vcooline.li.videoplayer.view.VideoViewPlayingActivity;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/7/31.
 */
public class DownloadSketchAdapter extends BaseAdapter {
    private ArrayList<SketchBean> videoList = new ArrayList<SketchBean>();
    private Activity activity;
    private LayoutInflater inflater;
    private AQuery aQuery;

    public DownloadSketchAdapter(Activity mActivity) {
        this.activity = mActivity;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
    }


    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int i) {
        return videoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        final VideoViewHolder viewHolder;
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.video_item2_layout, null);
            viewHolder = new VideoViewHolder();
            viewHolder.image = (ImageView) contentView.findViewById(R.id.thumbnail_image);
            viewHolder.obContainer = (RelativeLayout) contentView.findViewById(R.id.op_container);
            viewHolder.process = (ProgressWheel) contentView.findViewById(R.id.progress);
            viewHolder.play = (ImageView) contentView.findViewById(R.id.play);
            viewHolder.videoName = (TextView) contentView.findViewById(R.id.video_name);
            viewHolder.updateTime = (TextView) contentView.findViewById(R.id.update_time);
            viewHolder.fav = (ImageView) contentView.findViewById(R.id.fav);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (VideoViewHolder) contentView.getTag();
        }
        final SketchBean bean = (SketchBean) getItem(i);
        viewHolder.videoName.setText(bean.getMaterialName());
        viewHolder.fav.setVisibility(View.GONE);
        if (bean.getThumbnailPath() != null && !bean.getThumbnailPath().equals("")) {
            String imageUrl = "http://testhotel.vcooline.com/" + bean.getThumbnailPath();
            aQuery.id(viewHolder.image).image(imageUrl);
        }
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, VideoViewPlayingActivity.class);
                String source = AppCacheManager.getVideoPath(activity) + bean.getMaterialPath().replace("/", "-");
                intent.setData(Uri.parse(source));
                Bundle bundle = new Bundle();
                bundle.putSerializable("sketchBean",bean);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });

        aQuery.recycle(contentView);

        return contentView;
    }

    private class VideoViewHolder {
        public ImageView image;
        public RelativeLayout obContainer;
        public ProgressWheel process;
        public ImageView play;
        public TextView videoName;
        public TextView updateTime;
        public ImageView fav;

    }

    public ArrayList<SketchBean> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<SketchBean> videoList) {
        this.videoList = videoList;
    }

}
