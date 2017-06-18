package com.vcooline.li.videoplayer.view.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.WwComment;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.tools.UtilTool;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/9/8.
 */
public class WorkExchangeDetailAdapter extends BaseAdapter {
    private ArrayList<WwComment> wwComments = new ArrayList<WwComment>();
    private ProfileActivity activity;
    private LayoutInflater inflater;
    private AQuery aQuery;

    public WorkExchangeDetailAdapter( ProfileActivity mActivity, ArrayList<WwComment> mWwComments){
        this.activity = mActivity;
        this.wwComments = mWwComments;
        inflater = LayoutInflater.from(activity);
        aQuery = new AQuery(activity);
    }

    @Override
    public int getCount() {
        return wwComments.size();
    }

    @Override
    public WwComment getItem(int i) {
        return wwComments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(contentView == null){
            contentView = inflater.inflate(R.layout.work_exchange_comment_item,null);
            viewHolder = new ViewHolder();
            viewHolder.avatar = (ImageView) contentView.findViewById(R.id.avatar);
            viewHolder.username = (TextView) contentView.findViewById(R.id.username);
            viewHolder.commitText =(TextView) contentView.findViewById(R.id.commit_text);
            viewHolder.commitTime = (TextView) contentView.findViewById(R.id.commit_time);
            contentView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)contentView.getTag();
        }
        WwComment wwComment = getItem(i);
        viewHolder.username.setText(wwComment.getCommentatorName());
        viewHolder.commitText.setText(wwComment.getComment());
        viewHolder.commitTime.setText(UtilTool.getFormatTime(wwComment.getCreateAt()));

        return contentView;
    }

    public class ViewHolder{
        public ImageView avatar;
        public TextView username;
        public TextView commitTime;
        public TextView commitText;
    }
}
