package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.InformationBean;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Trace on 2014/8/23.
 */
public class GetInformationAction {
    private AQuery aq;
    private Activity activity;
    private GetInfoBack getInfoBack;

    public GetInformationAction(Activity mActivity,GetInfoBack mGetInfoBack){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.getInfoBack = mGetInfoBack;
    }


    public void getInfo(){
//        String url = UrlTool.INFORMATION + "?id=" +id;
        String url = UrlTool.TESTINFORMATION;
        HashMap<String, String> params = new HashMap<String, String>();;
        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object!= null && !object.equals("")){
                    Log.v("lijing", object.toString());
                    JSONUtil jsonUtil = new JSONUtil(object);
                    int callbackStatus = jsonUtil.optInteger("status");
                    if(callbackStatus == 1) {
                        try {
                            ArrayList<InformationBean> infoBeans = new ArrayList<InformationBean>();
                            JSONArray jsonArray = object.getJSONArray("result");
                            for(int i=0; i<jsonArray.length(); i++ ){
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                infoBeans.add(getInfoByJson(jobj));
                            }
                            getInfoBack.actionSuccessBack(infoBeans);
                        }catch(Exception e){

                        }
                    }else{
                        getInfoBack.actionErrorBack(jsonUtil.optString("error_message"));
                    }
                }else{
                    getInfoBack.actionErrorBack("请求超时");
                }
            }
        };
        ajaxCallback.setTimeout(10*1000);

        aq.ajax(url,params,JSONObject.class,ajaxCallback);
    }

    private InformationBean getInfoByJson(JSONObject jsonObject){
        InformationBean informationBean = new InformationBean();
        JSONUtil jsonUtil = new JSONUtil(jsonObject);
        informationBean.setId(jsonUtil.optInteger("id"));
        informationBean.setType(jsonUtil.optString("inforType"));
        informationBean.setTitle(jsonUtil.optString("inforTitle"));
        informationBean.setContent(jsonUtil.optString("inforContent"));
        informationBean.setAppenddix(jsonUtil.optString("inforAppendix"));
        informationBean.setDownload(jsonUtil.optString("inforDownload"));
        informationBean.setCreateTime(jsonUtil.optLong("createAt"));
        informationBean.setUpdateTime(jsonUtil.optLong("updateAt"));
        return informationBean;
    }


    public interface GetInfoBack{
        public void actionSuccessBack(ArrayList<InformationBean> informationBeanArrayList);
        public void actionErrorBack(String errorMsg);
    }
}
