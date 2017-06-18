package com.vcooline.li.videoplayer.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.GetSketchAction;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.bean.VideoBean;
import com.vcooline.li.videoplayer.category.GalleryActivity;
import com.vcooline.li.videoplayer.tools.download.DownloadImageTask;
import com.vcooline.li.videoplayer.view.viewadapter.PicAdapter;

import java.util.ArrayList;


public class PicturesFragment extends Fragment {

    private Activity activity;
    private GridView gridView;
    private RelativeLayout contentLay;
    private RelativeLayout bottomLay;
    private Button select_all;
    private Button download;
    private RelativeLayout noResult;
    private RelativeLayout loadingView;
    private ArrayList<VideoBean> datas = new ArrayList<VideoBean>();
    private PicAdapter picAdapter;
    private GetSketchAction getSketchAction;
    private GetSketchAction.GetSketchCallback getSketchCallback;

    private String materialType,materialClasses,materialSubClasses;
    private boolean isMediaImage = false;
    private boolean isMultiClick = false;


    //"materialType":"素描","materialClasses":"几何体","materialSubClasses":"组合","isMediaImage":"1"
    public static PicturesFragment newInstance(String mMaterialType,String materialClasses,
                                             String mMaterialSubClasses,boolean mIsMediaImage) {
        PicturesFragment fragment = new PicturesFragment();
        fragment.materialType = mMaterialType;
        fragment.materialClasses = mMaterialSubClasses;
        fragment.materialClasses = materialClasses;
        fragment.isMediaImage = mIsMediaImage;
        return fragment;
    }

    public PicturesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        contentLay = (RelativeLayout) view.findViewById(R.id.content_lay);
        bottomLay = (RelativeLayout) view.findViewById(R.id.bottom_lay);
        download = (Button) view.findViewById(R.id.download);
        select_all = (Button) view.findViewById(R.id.select_all);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        noResult = (RelativeLayout) view.findViewById(R.id.noresult_lay);
        loadingView = (RelativeLayout) view.findViewById(R.id.load_lay);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        picAdapter = new PicAdapter(activity,isMediaImage);
        gridView.setAdapter(picAdapter);

        getSketchCallback = new GetSketchAction.GetSketchCallback() {
            @Override
            public void getCacheBack(ArrayList<SketchBean> resultList) {

                if (resultList.size() > 0) {
                    loadingView.setVisibility(View.GONE);
                    contentLay.setVisibility(View.VISIBLE);
                    picAdapter.setVideoList(resultList);
                    picAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void callBackSuccess(ArrayList<SketchBean> resultList) {
                Log.v("lijing", "fragment call back");
                loadingView.setVisibility(View.GONE);
                if (resultList.size() > 0) {
                    contentLay.setVisibility(View.VISIBLE);
                    picAdapter.setVideoList(resultList);
                    picAdapter.notifyDataSetChanged();
                }else{
                    noResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void callbackError(int status, String errorMsg) {
                loadingView.setVisibility(View.GONE);
                noResult.setVisibility(View.VISIBLE);
//                Toast.makeText(activity,errorMsg,Toast.LENGTH_SHORT).show();
            }
        };
        getSketchAction = new GetSketchAction(activity, getSketchCallback);
        getDatas(1);

        select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picAdapter.checkedItems.clear();
                picAdapter.checkedItems.addAll(picAdapter.getVideoList());
            }
        });

        download.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                 for(int i=0; i<picAdapter.checkedItems.size(); i++){
                     DownloadImageTask task = new DownloadImageTask(activity,picAdapter.checkedItems.get(i));
                     task.downLoad();
                 }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!isMultiClick) {
                    Intent intent = new Intent(activity, GalleryActivity.class);
                    ArrayList<String> datas = new ArrayList<String>();
                    for (int j = 0; j < picAdapter.getVideoList().size(); j++) {
                        String imageUrl = "http://testhotel.vcooline.com/" + picAdapter.getVideoList().get(j).getThumbnailPath();
                        datas.add(imageUrl);
                    }
                    intent.putExtra("sketchBeans", datas);
                    intent.putExtra("currentPosition", i);
                    Log.w("lijing", "selectedPosition before is " + i);
                    activity.startActivity(intent);
                }else{
                    SketchBean sketchBean = (SketchBean)picAdapter.getItem(i);
                    if(picAdapter.checkedItems.contains(sketchBean)){
                        picAdapter.checkedItems.remove(sketchBean);
                    }else{
                        picAdapter.checkedItems.add(sketchBean);
                    }
                    if(picAdapter.checkedItems.size() == 0){
                        isMultiClick = false;
                        bottomLay.setVisibility(View.GONE);
                    }
                    picAdapter.notifyDataSetChanged();
                }
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                isMultiClick = true;
                bottomLay.setVisibility(View.VISIBLE);
                SketchBean sketchBean = (SketchBean)picAdapter.getItem(i);
                picAdapter.checkedItems.add(sketchBean);
                picAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void getDatas(int page){
        getSketchAction.getSketchData(materialType,materialClasses, materialSubClasses, false, page);
    }

}
