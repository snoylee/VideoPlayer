package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Trace on 2014/8/23.
 */
public class RegisterAction {
    private AQuery aq = null;
    private Activity activity;
    private RegisterCallBack loginCallBack;

    public RegisterAction(Activity mActivity,RegisterCallBack mLoginBack){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.loginCallBack = mLoginBack;
    }

    public void register(String nickName,String userName,String password){
        String url = UrlTool.REGISTER ;

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("nickName", nickName);
        params.put("userName",userName);
        params.put("password",password);

        AjaxCallback<JSONObject> callback =  new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if(object!= null && !object.equals("")){
                    Log.v("lijing", object.toString());
                    loginCallBack.actionSuccessBack(object);
                }else{
                    loginCallBack.actionErrorBack("请求超时");
                }
            }
        };
        callback.setTimeout(10*1000);
        aq.ajax(url, params, JSONObject.class,callback );
    }



    public interface RegisterCallBack{
        public void actionSuccessBack(JSONObject json);
        public void actionErrorBack(String errorCode);
    }
}
