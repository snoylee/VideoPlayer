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
import android.widget.Toast;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.CancelCollectionAction;
import com.vcooline.li.videoplayer.action.InsertCollectionAction;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.databases.SketchDBManager;
import com.vcooline.li.videoplayer.tools.AppCacheManager;
import com.vcooline.li.videoplayer.tools.ProgressWheel;
import com.vcooline.li.videoplayer.tools.download.DownloadTaskListener;
import com.vcooline.li.videoplayer.tools.download.VideoDownLoadTask;
import com.vcooline.li.videoplayer.view.VideoViewPlayingActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Trace on 2014/7/31.
 */
public class SketchAdapter extends BaseAdapter {
    private ArrayList<SketchBean> videoList = new ArrayList<SketchBean>();
    private Activity activity;
    private LayoutInflater inflater;
    private AQuery aQuery;
    private boolean isImage = false;
    public ArrayList<SketchBean> checkedItems = new ArrayList<SketchBean>();

    public SketchAdapter(Activity mActivity) {
        this.activity = mActivity;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
    }

    public SketchAdapter(Activity mActivity,boolean misImage) {
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
        if(bean.getStatus()!=null && bean.getStatus().equals("1")){
            viewHolder.fav.setImageResource(R.drawable.icon_liked);
        }else{
            viewHolder.fav.setImageResource(R.drawable.icon_like);
        }

        viewHolder.fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(bean.getStatus()!=null && bean.getStatus().equals("1")){
                    bean.setStatus("0");
                    CancelCollectionAction cancelCollectionAction = new CancelCollectionAction(activity, new CancelCollectionAction.ActionBack(){
                        @Override
                        public void actionBack(String msg) {
                        }
                    });

                    cancelCollectionAction.cancelCollection(bean.getId()+"");

                }else{

                    InsertCollectionAction insertCollectionAction = new InsertCollectionAction(activity, new InsertCollectionAction.ActionBack(){
                        @Override
                        public void actionBack(String msg) {

                        }
                    });

                    insertCollectionAction.insertCollection(bean.getId()+"");
                    bean.setStatus("1");
                }
                notifyDataSetChanged();
            }
        });


        if (bean.getThumbnailPath() != null && !bean.getThumbnailPath().equals("")) {
            String imageUrl = "http://testhotel.vcooline.com/" + bean.getThumbnailPath();
            aQuery.id(viewHolder.image).image(imageUrl);
        }else{
            viewHolder.image.setImageResource(R.drawable.no_photo);
        }
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    viewHolder.obContainer.setVisibility(View.VISIBLE);
                SketchDBManager dbManager = new SketchDBManager(activity);
                SketchBean saveBeans = dbManager.getSketchBeanById(bean.getId());
                if(saveBeans!=null){
                    viewHolder.process.setVisibility(View.GONE);
                    viewHolder.play.setVisibility(View.VISIBLE);
                }
            }
        });

        //2014-10-1图片下载的点击事件处理的方法
        viewHolder.obContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.getMaterialPath() != null && !bean.getMaterialPath().contains("[")) {
                    String url = "http://testhotel.vcooline.com/" +bean.getMaterialPath();
                    String filename = bean.getMaterialPath().replace("/", "-");
                    File target = new File(AppCacheManager.getVideoPath(activity) + filename);
                    VideoDownLoadTask task = new VideoDownLoadTask(activity, bean, new DownloadTaskListener() {
                        @Override
                        public void updateProcess(long percent) {
                            viewHolder.process.setProgress((int) percent);
                        }
                        @Override
                        public void finishDownload() {
                            viewHolder.process.setVisibility(View.GONE);
                            viewHolder.play.setVisibility(View.VISIBLE);
                            SketchDBManager dbManager = new SketchDBManager(activity);
                            dbManager.saveSketch(bean);
                        }
                        @Override
                        public void errorDownload(String error) {
                            viewHolder.process.setVisibility(View.GONE);
                            viewHolder.play.setVisibility(View.GONE);
                            Toast.makeText(activity,"下载出错",Toast.LENGTH_LONG).show();
                        }
                    });

                    task.downLoadFile();
//                    SketchDBManager dbManager = new SketchDBManager(activity);
//                    dbManager.saveSketch(bean);

                }
            }
        });

        viewHolder.play.setOnClickListener(new View.OnClickListener() {
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
