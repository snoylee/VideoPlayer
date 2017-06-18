package com.vcooline.li.videoplayer.view.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetWorkExchangeAction;
import com.vcooline.li.videoplayer.bean.WorksExchange;
import com.vcooline.li.videoplayer.category.ProfileActivity;

import java.util.ArrayList;

public class WorksExchangeFragment extends Fragment {
    private ListView listView;
    private RelativeLayout loading;
    private RelativeLayout noResultView;
    private ProfileActivity activity;
    private ArrayList<WorksExchange> datas = new ArrayList<WorksExchange>();
    private GetWorkExchangeAction getWorkExchangeAction;
    private GetWorkExchangeAction.GetWorkExchangeCallback getWorkExchangeCallback;
    private WorkExchangeAdapter workExchangeAdapter;

    public WorksExchangeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_works_exchange, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        loading = (RelativeLayout) view.findViewById(R.id.load_lay);
        noResultView = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        workExchangeAdapter = new WorkExchangeAdapter(activity);
        listView.setAdapter(workExchangeAdapter);

        getWorkExchangeCallback = new GetWorkExchangeAction.GetWorkExchangeCallback(){

            @Override
            public void callBackSuccess(ArrayList<WorksExchange> resultList) {
                loading.setVisibility(View.GONE);
                if(resultList!=null && resultList.size()>0){
                    listView.setVisibility(View.VISIBLE);
                    noResultView.setVisibility(View.GONE);
                    workExchangeAdapter.setWorksExchanges(resultList);
                    workExchangeAdapter.notifyDataSetChanged();
                }else{
                    listView.setVisibility(View.GONE);
                    noResultView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void callbackError(int status, String errorMsg) {
                listView.setVisibility(View.GONE);
                noResultView.setVisibility(View.VISIBLE);
                Toast.makeText(activity,errorMsg,Toast.LENGTH_LONG).show();
            }
        };

        getWorkExchangeAction = new GetWorkExchangeAction(activity,getWorkExchangeCallback);
        getWorkExchangeAction.getWorkExchange();
    }
}
