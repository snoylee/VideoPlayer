package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.AppCacheManager;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.SketchItemCacheBean;
import com.vcooline.li.videoplayer.tools.UrlTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Trace on 2014/8/18.
 */
public class GetSketchAction {
    private AQuery aq = null;
    private Activity activity;
    private GetSketchCallback getSketchCallback;
    private int perPage = 12;

    public GetSketchAction(Activity mActivity, GetSketchCallback mGetSketchCallback) {
        this.activity = mActivity;
        aq = new AQuery(activity);
        getSketchCallback = mGetSketchCallback;
    }

    public void getSketchData(final String type, final String classes, final String subClasses, boolean isImage,final int page) {
        String url = UrlTool.QUERYMATERIAL + "?material_type=" + type + "&material_classes=" + classes;
        if (subClasses != null && !subClasses.equals("")) {
            url += "&material_sub_classes=" + subClasses;
        }
        if(isImage){
           url += "&is_media_image=1";
        }

        if (page == 1) {
            url += "&start=" + 1 + "&limit=" + perPage;
        } else {
            url += "&start=" + (page - 1) * perPage + "&limit=" + perPage;
        }
        Log.v("lijing","get sketch url " +url);

        if(page == 1) {
            ArrayList<SketchBean> sketchBeans = getLoaclSketchBean(type, classes, subClasses);
            if (sketchBeans != null && sketchBeans.size() > 0) {
                getSketchCallback.getCacheBack(sketchBeans);
            }
        }

        aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if (object != null && !object.equals("")) {
                    JSONUtil jsonUtil = new JSONUtil(object);
                    int callbackStatus = jsonUtil.optInteger("status");
                    if (callbackStatus == 1) {
                        try {
                            JSONArray jsonArray = (JSONArray) object.get("result");
                            ArrayList<SketchBean> resultList = new ArrayList<SketchBean>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jobj = jsonArray.getJSONObject(i);
                                resultList.add(SketchBean.getSketchBeanByJson(jobj));
                            }
                            getSketchCallback.callBackSuccess(resultList);
                            if(page == 1) {
                                savetLoaclSketchBean(type, classes, subClasses, resultList);
                            }
                        } catch (Exception e) {

                        }
                    } else {
                        getSketchCallback.callbackError(callbackStatus, jsonUtil.optString("error_message"));
                    }
                }
            }
        });

    }

    public ArrayList<SketchBean> getLoaclSketchBean(String type, String classes, String subClasses){
        ArrayList<SketchBean> localSketchBeans = new ArrayList<SketchBean>();
        String fileName;
        if(subClasses == null){
            fileName  = type+"-"+classes+".tmp";
        }else{
            fileName  = type+"-"+classes+"-"+subClasses+".tmp";
        }
        String filePath = AppCacheManager.getSketchBeanPath(activity) + fileName;
        File cacheFile = new File(filePath);
        if(cacheFile.exists()){
            Object obj = AppCacheManager.getCacheData(filePath);
            if(obj !=null && obj instanceof SketchItemCacheBean){
                SketchItemCacheBean cacheBean = (SketchItemCacheBean) obj;
                localSketchBeans = cacheBean.sketchBeans;
            }
        }
        return localSketchBeans;
    };

    public void savetLoaclSketchBean(String type, String classes, String subClasses,ArrayList<SketchBean> localSketchBeans){
        String fileName;
        if(subClasses == null){
            fileName  = type+"-"+classes+".tmp";
        }else{
            fileName  = type+"-"+classes+"-"+subClasses+".tmp";
        }

        String filePath = AppCacheManager.getSketchBeanPath(activity) + fileName;
        SketchItemCacheBean cacheBean = new SketchItemCacheBean();
        cacheBean.sketchBeans = localSketchBeans;
        AppCacheManager.cacheData(filePath,cacheBean);
    }


    public interface GetSketchCallback {
        public void getCacheBack(ArrayList<SketchBean> resultList);

        public void callBackSuccess(ArrayList<SketchBean> resultList);

        public void callbackError(int status, String errorMsg);
    }
}
