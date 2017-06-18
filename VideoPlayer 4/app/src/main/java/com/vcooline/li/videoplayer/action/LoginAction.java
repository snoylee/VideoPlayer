package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.content.SharedPreferences;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.ProfileBean;
import com.vcooline.li.videoplayer.databases.SharePrefs;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.IllegalFormatCodePointException;

/**
 * Created by Trace on 2014/8/21.
 */
public class LoginAction {
    private AQuery aq = null;
    private Activity activity;
    private LoginCallBack loginCallBack;

    public LoginAction(Activity mActivity,LoginCallBack mLoginBack){
        this.activity = mActivity;
        aq = new AQuery(activity);
        this.loginCallBack = mLoginBack;
    }

    public void login(String username, String password){
       String url = UrlTool.LOGIN;

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName",username);
        params.put("password",password);

        AjaxCallback<JSONObject> ajaxCallback = new AjaxCallback<JSONObject>(){
                            @Override
                            public void callback(String url, JSONObject object, AjaxStatus status) {
                                super.callback(url, object, status);
                                if(object!= null && !object.equals("")){
                                    try {
                                        JSONArray tempObj = (JSONArray) object.get("result");
                                        JSONObject jsonObject = (JSONObject)tempObj.get(0);
                        ProfileBean profileBean = getProfileBean(jsonObject);
                        loginCallBack.actionSuccessBack(profileBean);
                        SharedPreferences profilePrefs = SharePrefs.getProfileSharePrefs(activity);
                        profilePrefs.edit()
                                .putString(SharePrefs.USERID,profileBean.getId())
                                .putString(SharePrefs.USERNAME,profileBean.getUserName())
                                .putString(SharePrefs.NICKNAME,profileBean.getNickName())
                                .putString(SharePrefs.PASSWORD,profileBean.getUserPassword())
                                .putString(SharePrefs.ICONURL,profileBean.getHeadIconUrl())
                                .commit();
                    }catch (Exception e){
                       throw new IllegalFormatCodePointException("JSONObject is false".getBytes().length);//异常使用的情况 暂时的医用的。
                    }
                }else{
                    loginCallBack.actionErrorBack("请求失败");
                }
            }
        };
        ajaxCallback.setTimeout(10*1000);

        aq.ajax(url,params,JSONObject.class,ajaxCallback);
    }

    public interface LoginCallBack{
        public void actionSuccessBack(ProfileBean profileBean);
        public void actionErrorBack(String errorCode);
    }

    public static ProfileBean getProfileBean(JSONObject jobj){
        ProfileBean profileBean = new ProfileBean();
        JSONUtil jsonUtil = new JSONUtil(jobj);
        profileBean.setId(jsonUtil.optString("id"));
        profileBean.setNickName(jsonUtil.optString("nickName"));
        profileBean.setUserName(jsonUtil.optString("userName"));
        profileBean.setUserPassword(jsonUtil.optString("userPasswd"));
        profileBean.setHeadIconUrl(jsonUtil.optString("heanIconUrl"));
        profileBean.setIsBanned(jsonUtil.optInteger("isBanned"));
        profileBean.setBannedDate(jsonUtil.optInteger("bannedDate"));
        profileBean.setCreateTime(jsonUtil.optLong("createAt"));
        profileBean.setUpdateTime(jsonUtil.optLong("updateAt"));
        return profileBean;
    }
}
