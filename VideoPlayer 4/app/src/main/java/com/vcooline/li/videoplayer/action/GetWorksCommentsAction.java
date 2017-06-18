package com.vcooline.li.videoplayer.action;

import android.app.Activity;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.WorkCommentsBean;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Trace on 2014/8/31.
 */
public class GetWorksCommentsAction {
    private AQuery aq;
    private Activity activity;
    private GetCommentsCallback getCommentsCallback;

    public GetWorksCommentsAction( Activity mActivity,GetCommentsCallback mGetCommentsCallback){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.getCommentsCallback = mGetCommentsCallback;
    }

    public void getComments(String personId,String personName){
        String callUrl = UrlTool.WORKCOMMENTS;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("personId",personId);
        params.put("personName",personName);
        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object != null && !object.equals("")){
                    JSONUtil jsonUtil = new JSONUtil(object);
                    int callbackStatus = jsonUtil.optInteger("status");
                    if(callbackStatus == 1){
                        try {
                            JSONArray jsonArray = (JSONArray) object.get("result");
                            ArrayList<WorkCommentsBean> workCommentsBeans = new ArrayList<WorkCommentsBean>();
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                workCommentsBeans.add(WorkCommentsBean.getWorkComment(jobj));
                            }

                            getCommentsCallback.callbackSuccess(workCommentsBeans);
                        }catch(Exception e){

                        }
                    }else{
                        getCommentsCallback.actionErrorBack(jsonUtil.optString("error_message"));
                    }
                }
            }
        };
        ajaxCallback.setTimeout(10*1000);
        aq.ajax(callUrl,params,JSONObject.class,ajaxCallback);

    }



    public interface GetCommentsCallback{
        public void callbackSuccess(ArrayList<WorkCommentsBean> workCommentsBeans);
        public void actionErrorBack(String errorCode);
    }
}
