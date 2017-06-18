package com.trace.li.videoplayer;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.vcooline.li.videoplayer.action.GetWorkExchangeAction;
import com.vcooline.li.videoplayer.bean.WorksExchange;

import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        GetWorkExchangeAction.GetWorkExchangeCallback callback = new GetWorkExchangeAction.GetWorkExchangeCallback() {
            @Override
            public void callBackSuccess(ArrayList<WorksExchange> resultList) {

            }

            @Override
            public void callbackError(int status, String errorMsg) {

            }
        };
        GetWorkExchangeAction getWorkExchangeAction = new GetWorkExchangeAction(getContext(),callback);

        getWorkExchangeAction.getWorkExchange();
    }
}