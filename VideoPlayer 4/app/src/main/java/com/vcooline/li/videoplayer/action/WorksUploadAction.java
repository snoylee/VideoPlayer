package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.content.SharedPreferences;

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
public class WorksUploadAction {
    private AQuery aq = null;
    private Activity activity;
    private UploadSuccessCallback uploadSuccessCallback;

    public WorksUploadAction(Activity mActivity, UploadSuccessCallback mUploadSuccessCallback){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.uploadSuccessCallback = mUploadSuccessCallback;
    }

    public void upload(String imageName,String imageUrl,String description){
        String url = UrlTool.WORKUPLOAD;
        SharedPreferences sharedPreferences = SharePrefs.getProfileSharePrefs(activity);
        String personId = sharedPreferences.getString(SharePrefs.USERID,"");
        String nickname = sharedPreferences.getString(SharePrefs.NICKNAME,"");

        HashMap<String, String> params = new HashMap<String, String>();

        params.put("personId",personId);
        params.put("nickName",nickname);
        params.put("imageName", imageName);
        params.put("imageUrl", imageUrl);
        params.put("description",description);

        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if(object !=null ){
//                    Toast.makeText(activity,object.toString(),Toast.LENGTH_LONG).show();
                    uploadSuccessCallback.actionSuccessBack(object);
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
