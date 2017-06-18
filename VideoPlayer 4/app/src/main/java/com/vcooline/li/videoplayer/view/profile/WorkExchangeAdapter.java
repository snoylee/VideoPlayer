package com.vcooline.li.videoplayer.view.profile;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.WorksExchange;
import com.vcooline.li.videoplayer.bean.WwComment;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.tools.UtilTool;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/9/2.
 */
public class WorkExchangeAdapter extends BaseAdapter {
    public ArrayList<WorksExchange> getWorksExchanges() {
        return worksExchanges;
    }

    public void setWorksExchanges(ArrayList<WorksExchange> worksExchanges) {
        this.worksExchanges = worksExchanges;
    }

    private ArrayList<WorksExchange> worksExchanges = new ArrayList<WorksExchange>();
    private ProfileActivity activity;
    private LayoutInflater inflater;
    private AQuery aQuery;

    public WorkExchangeAdapter(Activity mActivity){
        this.activity = (ProfileActivity)mActivity;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
    }

    @Override
    public int getCount() {
        return worksExchanges.size();
    }

    @Override
    public WorksExchange getItem(int i) {
        return worksExchanges.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(contentView == null){
            contentView = inflater.inflate(R.layout.work_exchange_item,null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) contentView.findViewById(R.id.image);
            viewHolder.workName = (TextView) contentView.findViewById(R.id.work_name);
            viewHolder.updateTime = (TextView) contentView.findViewById(R.id.update_time);
            viewHolder.viewAll = (TextView) contentView.findViewById(R.id.view_all);
            viewHolder.add = (TextView) contentView.findViewById(R.id.add);
            viewHolder.exchangeContent = (LinearLayout) contentView.findViewById(R.id.exchange_content);


            contentView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)contentView.getTag();
        }
        final WorksExchange currentBean =getItem(i);
        if(currentBean.getImageUrl()!=null && !currentBean.getImageUrl().equals("")){
            String imageUrl = "http://testhotel.vcooline.com/" + currentBean.getImageUrl();
            aQuery.id(viewHolder.image).image(imageUrl);
        }

        viewHolder.workName.setText(currentBean.getImageName());
        viewHolder.updateTime.setText(UtilTool.getFormatTime(currentBean.getCreateAt()));


        viewHolder.viewAll.setText("共"+currentBean.getWwComments().size()+"条评论>>>");
        viewHolder.viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkExchangeDetailFragment fragment = WorkExchangeDetailFragment.newInstance(currentBean.getImageName(), currentBean.getCreateAt(),
                        currentBean.getWwComments());
                activity.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                activity.showToFront(fragment);
            }
        });
        if(currentBean.getWwComments().size() == 0){
            viewHolder.exchangeContent.removeAllViews();
            viewHolder.exchangeContent.setVisibility(View.GONE);
        }else{
            viewHolder.exchangeContent.removeAllViews();
            viewHolder.exchangeContent.setVisibility(View.VISIBLE);
            getWorkExchangeCommentViews(viewHolder.exchangeContent,currentBean.getWwComments());
        }
        return contentView;
    }


    private void getWorkExchangeCommentViews(LinearLayout exchangeContent,ArrayList<WwComment> wwComments){
       int size = 0;
        if(wwComments.size()<3){
            size = wwComments.size();
        }else{
            size = 3;
        }
        for(int i=0; i<size; i++){
            RelativeLayout wwCommentView = (RelativeLayout) inflater.inflate(R.layout.work_exchange_comment_item,null);
            ImageView avatar = (ImageView) wwCommentView.findViewById(R.id.avatar);
            TextView username = (TextView) wwCommentView.findViewById(R.id.username);
            TextView updateTime = (TextView) wwCommentView.findViewById(R.id.commit_time);
            TextView commentTxt = (TextView) wwCommentView.findViewById(R.id.commit_text);
            WwComment wwComment = wwComments.get(i);
            username.setText(wwComment.getCommentatorName());
            updateTime.setText(UtilTool.getFormatTime(wwComment.getCreateAt()));
            commentTxt.setText(wwComment.getComment());
            exchangeContent.addView(wwCommentView);
        }
    }
    public class ViewHolder{
        public ImageView image;
        public TextView workName;
        public TextView updateTime;
        public TextView viewAll;
        public TextView add;
        public LinearLayout exchangeContent;
    }
}
