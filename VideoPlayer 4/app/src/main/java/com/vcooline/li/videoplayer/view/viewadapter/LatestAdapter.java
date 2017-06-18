package com.vcooline.li.videoplayer.view.viewadapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.GroupBean;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.view.CategoryGridView;
import com.vcooline.li.videoplayer.tools.view.SectionTitleListView;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/9/21.
 */
public class LatestAdapter extends BaseExpandableListAdapter implements
        SectionTitleListView.IphoneTreeHeaderAdapter {
    private Activity activity;
    private AQuery aQuery;

    public ArrayList<GroupBean> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<GroupBean> groupList) {
        this.groupList = groupList;
    }

    private ArrayList<GroupBean> groupList = new ArrayList<GroupBean>();
    private SectionTitleListView sectionTitleListView;

    public LatestAdapter(Activity mActivity,ArrayList<GroupBean> mGroupList,
                         SectionTitleListView mSectionTitleListView){
        this.activity = mActivity;
        this.aQuery = new AQuery(activity);
        this.groupList = mGroupList;
        this.sectionTitleListView = mSectionTitleListView;
    }


    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i).title;
    }

    @Override
    public Object getChild(int i, int i2) {
        return groupList.get(i).childrenList;
    }

    @Override
    public long getGroupId(int i) {
        return i * 100;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i * 100 + i2;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.section_title_view, null);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.txt);
        groupName.setText(groupList.get(i).title);
        Log.v("lijing",groupList.get(i).title);
        convertView.setClickable(false);
        return convertView;
    }

    @Override
    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        if(i<groupList.size()) {
            return getPopularCategoriesView(groupList.get(i).childrenList);
        }else{
            return null;
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    @Override
    public int getTreeHeaderState(int groupPosition, int childPosition) {
        final int childCount = getChildrenCount(groupPosition);
        if (childPosition == childCount - 1) {
            return PINNED_HEADER_PUSHED_UP;
        } else if (childPosition == -1
                && !sectionTitleListView.isGroupExpanded(groupPosition)) {
            return PINNED_HEADER_GONE;
        } else {
            return PINNED_HEADER_VISIBLE;
        }
    }

    @Override
    public void configureTreeHeader(View header, int groupPosition, int childPosition, int alpha) {

    }

    @Override
    public void onHeadViewClick(int groupPosition, int status) {

    }

    @Override
    public int getHeadViewClickStatus(int groupPosition) {
        return 0;
    }





    public View getPopularCategoriesView(ArrayList<SketchBean> items) {
        View view = LayoutInflater.from(activity).inflate(
                R.layout.latest_gridview, null);
        GridView gridView = (CategoryGridView) view.findViewById(R.id.GridView_toolbar);
        gridView.setOnLongClickListener(null);
        SketchAdapter sketchAdapter = new SketchAdapter(activity);
        sketchAdapter.setVideoList(items);
        gridView.setAdapter(sketchAdapter);
        return view;
    }
}
