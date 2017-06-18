package com.vcooline.li.videoplayer.view.viewadapter;

import android.app.Activity;
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

import java.util.ArrayList;

/**
 * Created by Trace on 2014/7/31.
 */
public class PicAdapter extends BaseAdapter {
    private ArrayList<SketchBean> videoList = new ArrayList<SketchBean>();
    private Activity activity;
    private LayoutInflater inflater;
    private AQuery aQuery;
    private boolean isImage = false;
    public ArrayList<SketchBean> checkedItems = new ArrayList<SketchBean>();

    public PicAdapter(Activity mActivity) {
        this.activity = mActivity;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
    }

    public PicAdapter(Activity mActivity, boolean misImage) {
        this.activity = mActivity;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
        isImage = misImage;
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
            contentView = inflater.inflate(R.layout.pic_item2_layout, null);
            viewHolder = new VideoViewHolder();
            viewHolder.image = (ImageView) contentView.findViewById(R.id.thumbnail_image);
            viewHolder.selectimg = (ImageView) contentView.findViewById(R.id.selectimg);
            viewHolder.obContainer = (RelativeLayout) contentView.findViewById(R.id.op_container);
            viewHolder.videoName = (TextView) contentView.findViewById(R.id.video_name);
            viewHolder.updateTime = (TextView) contentView.findViewById(R.id.update_time);
            viewHolder.fav = (ImageView) contentView.findViewById(R.id.fav);
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (VideoViewHolder) contentView.getTag();
        }
        final SketchBean bean = (SketchBean) getItem(i);
        viewHolder.videoName.setText(bean.getMaterialName());
        if (bean.getThumbnailPath() != null && !bean.getThumbnailPath().equals("")) {
            String imageUrl = "http://testhotel.vcooline.com/" + bean.getThumbnailPath();
            aQuery.id(viewHolder.image).image(imageUrl);
        }else{
            viewHolder.image.setImageResource(R.drawable.no_photo);
        }

        if(bean.getStatus()!=null && bean.getStatus().equals("1")){
            viewHolder.fav.setImageResource(R.drawable.icon_liked);
        }else{
            viewHolder.fav.setImageResource(R.drawable.icon_like);
        }
        if(checkedItems.contains(bean)){
            viewHolder.selectimg.setVisibility(View.VISIBLE);
            viewHolder.selectimg.bringToFront();
        }else{
            viewHolder.selectimg.setVisibility(View.GONE);
        }



        aQuery.recycle(contentView);

        return contentView;
    }

    private class VideoViewHolder {
        public ImageView image;
        public ImageView selectimg;
        public RelativeLayout obContainer;
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
