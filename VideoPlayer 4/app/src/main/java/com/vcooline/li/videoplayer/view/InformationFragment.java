package com.vcooline.li.videoplayer.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetInformationAction;
import com.vcooline.li.videoplayer.bean.InformationBean;
import com.vcooline.li.videoplayer.category.InformationDetailActivity;
import com.vcooline.li.videoplayer.view.viewadapter.InformationAdapter;

import java.util.ArrayList;

public class InformationFragment extends Fragment {

    private String id;
    private Activity activity;
    private ListView listView;
    private RelativeLayout noResult;
    private RelativeLayout loadingView;
    private GetInformationAction getInformationAction;
    private GetInformationAction.GetInfoBack getInfoBack;
    private InformationAdapter informationAdapter;


    public static InformationFragment newInstance(String mId) {
        InformationFragment fragment = new InformationFragment();
        fragment.id = mId;
        Bundle args = new Bundle();
        args.putString("id", mId);
        fragment.setArguments(args);
        return fragment;
    }
    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        noResult = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        loadingView = (RelativeLayout) view.findViewById(R.id.load_lay);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        informationAdapter = new InformationAdapter(activity);
        listView.setAdapter(informationAdapter);

        getInfoBack = new GetInformationAction.GetInfoBack(){

            @Override
            public void actionSuccessBack(ArrayList<InformationBean> infoBeans ) {
                loadingView.setVisibility(View.GONE);
                if (infoBeans.size() > 0) {
                    listView.setVisibility(View.VISIBLE);
                    informationAdapter.setInformationBeans(infoBeans);
                    informationAdapter.notifyDataSetChanged();
                }else{
                    noResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void actionErrorBack(String errorMsg) {
                loadingView.setVisibility(View.GONE);
                noResult.setVisibility(View.VISIBLE);
                Toast.makeText(activity, errorMsg, Toast.LENGTH_SHORT).show();
            }
        };

        if(id != null && !id.equals("")) {
            getInforMation(id);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  InformationBean informationBean = (InformationBean) informationAdapter.getItem(i);
                  Intent intent = new Intent(activity, InformationDetailActivity.class);
                  intent.putExtra("informationBean",informationBean);
                  activity.startActivity(intent);
            }
        });


    }

    public void getInforMation(String id){
        getInformationAction = new GetInformationAction(activity,getInfoBack);
        getInformationAction.getInfo();

    }

}
