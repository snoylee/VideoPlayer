package com.vcooline.li.videoplayer.view.profile;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.WorkCommentsBean;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.tools.UtilTool;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/9/2.
 */
public class WorkCommentsAdapter extends BaseAdapter {
    public ArrayList<WorkCommentsBean> getWorkCommentsBeans() {
        return workCommentsBeans;
    }

    public void setWorkCommentsBeans(ArrayList<WorkCommentsBean> workCommentsBeans) {
        this.workCommentsBeans = workCommentsBeans;
    }

    private ArrayList<WorkCommentsBean> workCommentsBeans = new ArrayList<WorkCommentsBean>();
    private ProfileActivity activity;
    private LayoutInflater inflater;
    private AQuery aQuery;

    public WorkCommentsAdapter(Activity mActivity){
        this.activity = (ProfileActivity)mActivity;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
    }

    @Override
    public int getCount() {
        return workCommentsBeans.size();
    }

    @Override
    public WorkCommentsBean getItem(int i) {
        return workCommentsBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    //实际用显示的每个条目的视图 view
    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(contentView == null){
            contentView = inflater.inflate(R.layout.work_commit_item,null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) contentView.findViewById(R.id.image);
            viewHolder.workName = (TextView) contentView.findViewById(R.id.work_name);
            viewHolder.updateTime = (TextView) contentView.findViewById(R.id.update_time);
            viewHolder.comment = (TextView) contentView.findViewById(R.id.comment);
            viewHolder.viewAll = (TextView) contentView.findViewById(R.id.view_all);

            contentView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)contentView.getTag();
        }
        final WorkCommentsBean currentBean =getItem(i);
        if(currentBean.getWorkPic()!=null && !currentBean.getWorkPic().equals("")){
            String imageUrl = "http://testhotel.vcooline.com/" + currentBean.getWorkPic();
            aQuery.id(viewHolder.image).image(imageUrl);
        }

        viewHolder.workName.setText(currentBean.getWorkName());
        viewHolder.updateTime.setText(UtilTool.getFormatTime(currentBean.getUpdateTime()));
        viewHolder.comment.setText(currentBean.getCommitText());
        viewHolder.viewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                WorkCommentDetailFragment workCommentDetailFragment = WorkCommentDetailFragment.newInstance(currentBean);
                activity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                activity.showToFront(workCommentDetailFragment);
            }
        });
        return contentView;
    }

    public class ViewHolder{
        public ImageView image;
        public TextView workName;
        public TextView updateTime;
        public TextView comment;
        public TextView viewAll;
    }
}
