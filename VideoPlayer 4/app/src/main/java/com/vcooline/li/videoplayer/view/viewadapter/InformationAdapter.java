package com.vcooline.li.videoplayer.view.viewadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.InformationBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Trace on 2014/8/23.
 */
public class InformationAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<InformationBean> informationBeans = new ArrayList<InformationBean>();

    public InformationAdapter(Activity mActivity){
        this.activity = mActivity;
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return informationBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return informationBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(contentView == null){
            contentView = inflater.inflate(R.layout.information_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.container = contentView.findViewById(R.id.container);
            viewHolder.title = (TextView) contentView.findViewById(R.id.title);
            viewHolder.time  =  (TextView)contentView.findViewById(R.id.time);
            contentView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) contentView.getTag();
        }
        InformationBean currentBean = (InformationBean) getItem(i);
        viewHolder.title.setText(currentBean.getTitle());
        Date date = new Date(currentBean.getUpdateTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        viewHolder.time.setText(sdf.format(date));
        return contentView;
    }

    public ArrayList<InformationBean> getInformationBeans() {
        return informationBeans;
    }

    public void setInformationBeans(ArrayList<InformationBean> informationBeans) {
        this.informationBeans = informationBeans;
    }

    public class ViewHolder{
        public View container;
        public TextView title;
        public TextView time;
    }
}
