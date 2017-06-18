package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.databases.SharePrefs;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Trace on 2014/9/12.
 */
public class CancelCollectionAction {
    private AQuery aq = null;
    private Activity activity;
    private ActionBack actionBack;

    public CancelCollectionAction(Activity mActivity, ActionBack mactionBack){
        this.activity = mActivity;
        this.actionBack = mactionBack;
        this.aq = new AQuery(activity);
    }

    public void cancelCollection(String materialId){
        String callUrl = UrlTool.CANCELCOLLECTION;
        SharedPreferences sharedPreferences = SharePrefs.getProfileSharePrefs(activity);
        String personId = sharedPreferences.getString(SharePrefs.USERID,"");
        String personName = sharedPreferences.getString(SharePrefs.USERNAME,"");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("personId",personId);
        params.put("personName",personName);
        params.put("materialId",materialId);

        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object != null && !object.equals("")) {
                    Log.v("lijing", object.toString());
                    JSONUtil jsonUtil = new JSONUtil(object);
                    int callbackStatus = jsonUtil.optInteger("status");
                    if(callbackStatus == 1){
                        actionBack.actionBack("收藏成功");
                    }else{
                        actionBack.actionBack("收藏失败");
                    }
                }
            }
        };



        ajaxCallback.setTimeout(10*1000);
        aq.ajax(callUrl,params,JSONObject.class,ajaxCallback);
    }



    public interface ActionBack{
        public void actionBack(String msg);
    }
}
