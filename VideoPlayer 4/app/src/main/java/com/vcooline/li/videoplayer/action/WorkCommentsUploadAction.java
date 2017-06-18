package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.databases.SharePrefs;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Trace on 2014/9/4.
 */
public class WorkCommentsUploadAction {
    private AQuery aq = null;
    private Activity activity;
    private UploadSuccessCallback uploadSuccessCallback;

    public WorkCommentsUploadAction(Activity mActivity,UploadSuccessCallback mUploadSuccessCallback){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.uploadSuccessCallback = mUploadSuccessCallback;
    }

    public void upload(String workName,String workPic){
        String url = UrlTool.WORKCOMMENTSUPLOAD;
        SharedPreferences sharedPreferences = SharePrefs.getProfileSharePrefs(activity);
        String personId = sharedPreferences.getString(SharePrefs.USERID,"");
        String personName = sharedPreferences.getString(SharePrefs.USERNAME,"");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("workName", workName);
        params.put("workPic",workPic);
        params.put("personId",personId);
        params.put("personName",personName);

        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object !=null ){
                    Toast.makeText(activity,object.toString(),Toast.LENGTH_LONG).show();
                }
            }
        };
        ajaxCallback.setTimeout(10*1000);
        aq.ajax(url, params, JSONObject.class,ajaxCallback );
    }





    public interface UploadSuccessCallback{
        public void actionSuccessBack(JSONObject json);
        public void actionErrorBack(String errorCode);
    }
}
