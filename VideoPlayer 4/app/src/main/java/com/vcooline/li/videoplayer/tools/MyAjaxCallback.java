package com.vcooline.li.videoplayer.tools;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

/**
 * Created by Trace on 2014/8/18.
 */
public class MyAjaxCallback<T> extends AjaxCallback {
    private static int timeOutSeconds = 10;

    public MyAjaxCallback() {
        AjaxCallback<T> ajaxback = new AjaxCallback<T>() {

            @Override
            public void callback(String url, T object,
                                 AjaxStatus status) {
                // TODO Auto-generated method stub
                super.callback(url, object, status);
            }
        };

        ajaxback.setTimeout(timeOutSeconds * 1000);
    }
}
