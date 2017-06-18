package com.vcooline.li.videoplayer.action;

import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.WorksExchange;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Trace on 2014/9/1.
 */
public class GetWorkExchangeAction {
    private AQuery aq = null;
    private Context activity;
    private GetWorkExchangeCallback callback;

    public GetWorkExchangeAction(Context mActivity,GetWorkExchangeCallback mCallback){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.callback = mCallback;
    }

    public void getWorkExchange(){
        String callUrl = UrlTool.WORKEXCHANGE;
        HashMap<String, String> params = new HashMap<String, String>();
        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object != null && !object.equals("")) {
                    Log.v("lijing",object.toString());
                    JSONUtil jsonUtil = new JSONUtil(object);
                    int callbackStatus = jsonUtil.optInteger("status");
                    if(callbackStatus == 1){
                        try {
                            JSONArray jsonArray = (JSONArray) object.get("result");
                            ArrayList<WorksExchange> worksExchanges = new ArrayList<WorksExchange>();
                            for(int i=0; i<jsonArray.length(); i++){
                                worksExchanges.add(WorksExchange.getWorkExchangeByJson((JSONObject)jsonArray.get(i)));
                            }

                            callback.callBackSuccess(worksExchanges);
                        }catch (Exception E){
                            E.printStackTrace();
                        }

                    }else{
                        callback.callbackError(callbackStatus,jsonUtil.optString("error_message"));
                    }
                }
            }
        };



        ajaxCallback.setTimeout(10*1000);
        aq.ajax(callUrl,params,JSONObject.class,ajaxCallback);
    }


    public interface GetWorkExchangeCallback{
        public void callBackSuccess(ArrayList<WorksExchange> resultList);
        public void callbackError(int status, String errorMsg);
    }
}
