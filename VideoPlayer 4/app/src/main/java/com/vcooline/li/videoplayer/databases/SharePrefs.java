package com.vcooline.li.videoplayer.databases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Trace on 2014/8/31.
 */
public class SharePrefs {

    public static final String USERID ="userid";
    public static final String USERNAME = "username";
    public static final String NICKNAME = "nickName";
    public static final String PASSWORD = "password";
    public static final String ICONURL = "iconUrl";

    public static SharedPreferences getProfileSharePrefs(Context context){
        SharedPreferences userInfo = context.getSharedPreferences("user_info", 0);
        return userInfo;
    }
}
