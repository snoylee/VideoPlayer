package com.vcooline.li.videoplayer.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vcooline.li.videoplayer.R;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Trace on 2014/9/7.
 */
public class UtilTool {
    public static String getFormatTime(long timeStamp){
        Date date = new Date(timeStamp);
        SimpleDateFormat time=new SimpleDateFormat("yyyy.MM.dd");
        return time.format(date);
    }

    public static String getUtf8Url(String s){
        try {
            s = URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String getGb2312Url(String s){
        try {
            s = URLEncoder.encode(s,"gb2312");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public static void deleteFolderFile(String filePath, boolean deleteThisPath){
            File file = new File(filePath);

            if (file.isDirectory()) {// 处理目录
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFolderFile(files[i].getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {// 如果是文件，删除
                    file.delete();
                } else {// 目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }
                }
            }
    }

    public static void showSdCardInfo(Activity activity){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long blockSize = sf.getBlockSize();
            long blockCount = sf.getBlockCount();
            long availCount = sf.getAvailableBlocks();
            Log.d("", "block大小:"+ blockSize+",block数目:"+ blockCount+",总大小:"+blockSize*blockCount/(1024*1024)+"MB");
            Log.d("", "可用的block数目：:"+ availCount+",剩余空间:"+ availCount*blockSize/(1024*1024)+"MB");
            LayoutInflater inflater = activity.getLayoutInflater();
            View layout = inflater.inflate(R.layout.dailog_sdinfo, null);
            TextView total = (TextView)layout.findViewById(R.id.total_size);
            TextView shengyu = (TextView)layout.findViewById(R.id.shengyu_size);
            total.setText(blockSize*blockCount/(1024*1024)+"MB");
            shengyu.setText( availCount*blockSize/(1024*1024)+"MB");
            AlertDialog dialog = new AlertDialog.Builder(activity)
                    .setView(layout)
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create();
            dialog.show();
        }
    }
}
