package com.vcooline.li.videoplayer.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;

/**
 * Created by Trace on 2014/9/13.
 */
public class NotNetWorkTool {

    public static void showNoNetworkToast(Context context){
        Toast toast = Toast.makeText(context,
                "请检查你的网络连接", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setPadding(10,30,10,30);
        imageCodeProject.setImageResource(R.drawable.no_network);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    public static boolean checkNetWork(Context context){
        ConnectivityManager con=(ConnectivityManager)context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = false;
        boolean internet= false;
        try {
            wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
            internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        }catch (Exception e){

        }
        if(wifi|internet){
            //执行相关操作
            return true;
        }else{
            showNoNetworkToast( context);
            return false;
        }
    }
}
