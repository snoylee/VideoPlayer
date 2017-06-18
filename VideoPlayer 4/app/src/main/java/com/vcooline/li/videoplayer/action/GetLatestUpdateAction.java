package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.GroupBean;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.AppCacheManager;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Trace on 2014/9/21.
 */
public class GetLatestUpdateAction {
    private AQuery aq;
    private Activity activity;
    private GetLatestCallback getLatestCallback;

    public GetLatestUpdateAction(Activity mActivity,GetLatestCallback mGetLatestCallback){
        this.activity = mActivity;
        this.getLatestCallback = mGetLatestCallback;
        this.aq = new AQuery(activity);
    }

    public void getLatest(){
        String callUrl = UrlTool.GETLATEST;


        //get cache datas
        ArrayList<GroupBean> groupBeans = getLastCacheData();
        if(groupBeans!=null && groupBeans.size()>0){
            getLatestCallback.getCacheBack(groupBeans);
        }

        HashMap<String, String> params = new HashMap<String, String>();
        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object != null && !object.equals("")) {
                    Log.v("lijing", object.toString());
                    JSONUtil jsonUtil = new JSONUtil(object);
                    int callbackStatus = jsonUtil.optInteger("status");
                    if(callbackStatus == 1){
                        try {
                            JSONArray jsonArray = (JSONArray) object.get("result");
                            ArrayList<SketchBean> resultList = new ArrayList<SketchBean>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                resultList.add(SketchBean.getSketchBeanByJson(jobj));
                            }
                            //数据分组
                            ArrayList<String> groupTitle = new ArrayList<String>();
                            for(int i=0; i<resultList.size(); i++){
                                if(!groupTitle.contains(resultList.get(i).getMaterialSubClasses())){
                                    groupTitle.add(resultList.get(i).getMaterialSubClasses());
                                }
                            }

                            ArrayList<GroupBean> groupBeans = new ArrayList<GroupBean>();
                            for(int j=0; j<groupTitle.size(); j++){
                                GroupBean groupBean = new GroupBean();
                                groupBean.title = groupTitle.get(j);
                                for(int t =0; t<resultList.size(); t++){
                                    if(resultList.get(t).getMaterialSubClasses()!= null
                                            && resultList.get(t).getMaterialSubClasses().equals(groupBean.title)){
                                        groupBean.childrenList.add(resultList.get(t));
                                    }
                                }
                                groupBeans.add(groupBean);
                            }
                            getLatestCallback.callBackSuccess(groupBeans);
                            cacheData(groupBeans);
                        }catch (Exception e){

                        }

                    }else{
                        getLatestCallback.callbackError(callbackStatus,jsonUtil.optString("error_message"));
                    }
                }
            }
        };
        ajaxCallback.setTimeout(10*1000);
        aq.ajax(callUrl,params,JSONObject.class,ajaxCallback);
    }


    public ArrayList<GroupBean> getLastCacheData(){
        ArrayList<GroupBean> groupBeans = new ArrayList<GroupBean>();
        String fileName = "latest.tmp";
        String filePath = AppCacheManager.getSketchBeanPath(activity) + fileName;
        File cacheFile = new File(filePath);
        if(cacheFile.exists()){
            Object obj = AppCacheManager.getCacheData(filePath);
            if(obj !=null){
                groupBeans = (ArrayList<GroupBean>) obj;
            }
        }
        return groupBeans;
    }

    public void cacheData(ArrayList<GroupBean> groupBeans){
        String fileName = "latest.tmp";
        String filePath = AppCacheManager.getSketchBeanPath(activity) + fileName;
        AppCacheManager.cacheData(filePath,groupBeans);
    }

    public interface GetLatestCallback {
        public void getCacheBack(ArrayList<GroupBean> resultList);
        public void callBackSuccess(ArrayList<GroupBean> resultList);
        public void callbackError(int status, String errorMsg);
    }
}
