package com.vcooline.li.videoplayer.tools.download;

import android.app.Activity;
import android.graphics.Bitmap;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.AppCacheManager;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Trace on 2014/9/20.
 */
public class DownloadImageTask {
    private SketchBean sketchBean;
    private Activity activity;
    private AQuery aq;

    public DownloadImageTask(Activity mActivity,SketchBean mSketchBean ){
        this.activity = mActivity;
        this.sketchBean = mSketchBean;
        this.aq = new AQuery(activity);
    }

    public void downLoad(){
        if (sketchBean.getThumbnailPath() != null && !sketchBean.getThumbnailPath().equals("")) {
            String imageUrl = "http://testhotel.vcooline.com/" + sketchBean.getThumbnailPath();
            aq.ajax(imageUrl, Bitmap.class, new AjaxCallback<Bitmap>() {

                @Override
                public void callback(String url, Bitmap object, AjaxStatus status) {
                    String path = AppCacheManager.getImagePath(activity);
                    File file=new File(path, url.hashCode()+".jpg");
                    try {
                        FileOutputStream out=new FileOutputStream(file);
                        object.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
