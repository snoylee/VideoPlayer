package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.databases.SharePrefs;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Trace on 2014/9/7.
 */
public class GetCollectionsAction {
    private AQuery aq = null;
    private Activity activity;
    private GetCollectionCallback getCollectionCallback;

    public GetCollectionsAction(Activity mActivity,GetCollectionCallback mGetCollectionCallback){
        this.activity = mActivity;
        this.getCollectionCallback = mGetCollectionCallback;
        this.aq = new AQuery(activity);
    }

    public void getCollections(String type){
        String callUrl = UrlTool.MYCOLLECTION;
        SharedPreferences sharedPreferences = SharePrefs.getProfileSharePrefs(activity);
        String personId = sharedPreferences.getString(SharePrefs.USERID,"");
        String personName = sharedPreferences.getString(SharePrefs.USERNAME,"");
//        callUrl += "?personId="+personId+"&personName="+personName;
        HashMap<String, String> params = new HashMap<String, String>();;
        params.put("personId",personId);
        params.put("personName",personName);
        params.put("isMaterialInfo",type);

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
                            JSONArray array1 = object.getJSONArray("result");
                            JSONObject object1 = (JSONObject) array1.get(0);
                            JSONArray array2 = object1.getJSONArray("materialInfoList");
                            ArrayList<SketchBean> resultList = new ArrayList<SketchBean>();
                            for(int i=0;i<array2.length();i++){
                                resultList.add(SketchBean.getSketchBeanByJson((JSONObject)array2.get(i)));
                            }
                            getCollectionCallback.callBackSuccess(resultList);
                        }catch (Exception E){

                        }

                    }else{
                        getCollectionCallback.callbackError(callbackStatus,jsonUtil.optString("error_message"));
                    }
                }
            }
        };



        ajaxCallback.setTimeout(10*1000);
        aq.ajax(callUrl,params,JSONObject.class,ajaxCallback);
    }


    public interface GetCollectionCallback {
        public void callBackSuccess(ArrayList<SketchBean> resultList);
        public void callbackError(int status, String errorMsg);
    }
}
