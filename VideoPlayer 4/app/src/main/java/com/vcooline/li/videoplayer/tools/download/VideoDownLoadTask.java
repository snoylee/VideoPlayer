package com.vcooline.li.videoplayer.tools.download;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.vcooline.li.videoplayer.bean.SketchBean;
import com.vcooline.li.videoplayer.tools.AppCacheManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Trace on 2014/10/1.
 */
public class VideoDownLoadTask {
    private Activity activity;
    private SketchBean sketchBean;
    private DownloadTaskListener downloadTaskListener;
    private String remoteUrl;
    private String fileUrl;


    private long FileLength;
    private long DownedFileLength=0;
    private InputStream inputStream;
    private URLConnection connection;
    private OutputStream outputStream;



    private static final int UPDATE_DOWNLOAD = 1;
    private static final int FINISH_DOWNLOAD = 2;
    private static final int ERROR_DOWNLOAD = 3;

    private Handler handler;
    {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case UPDATE_DOWNLOAD:
                        if(downloadTaskListener!=null) {
                            Long x = (Long)msg.obj;
                            downloadTaskListener.updateProcess(x);
                        }
                        break;
                    case FINISH_DOWNLOAD:
                        if(downloadTaskListener!=null) {
                            downloadTaskListener.finishDownload();
                        }
                        break;
                    case ERROR_DOWNLOAD:
                        if(downloadTaskListener!=null) {
                            downloadTaskListener.errorDownload("视频下载失败");
                        }
                        break;
                }
            }
        };
    }

    public VideoDownLoadTask(Activity mActivity,SketchBean bean, DownloadTaskListener listener){
        this.activity = mActivity;
        this.sketchBean = bean;
        this.downloadTaskListener = listener;
        this.remoteUrl  =  "http://testhotel.vcooline.com/" + bean.getMaterialPath();
//        remoteUrl = UtilTool.getUtf8Url(remoteUrl);
        String filename = bean.getMaterialPath().replace("/", "-");
        this.fileUrl = AppCacheManager.getVideoPath(activity) + filename;
    }
//下载视频的问题
    public void downLoadFile(){
        Thread thread = new Thread(){
            public void run(){
                try {
                    File target = new File(fileUrl);
                    URL url=new URL(remoteUrl);
                    connection=url.openConnection();
                    inputStream=connection.getInputStream();

                    outputStream=new FileOutputStream(target);
                    byte [] buffer=new byte[1024];
                    FileLength=connection.getContentLength();
                    int len;
                    long updateProcess = 0;
                    while ((len = inputStream.read(buffer))!=-1) {
                        outputStream.write(buffer,0,len);
                        DownedFileLength+=len;
                        long x=(DownedFileLength*(long)100)/(long)FileLength;
                        if( ((int)x)%2 == 0 && x != updateProcess){
                            Log.v("litieshan","x is "+x );
                            Log.v("litieshan","download process is "+x);
                            Message msg = handler.obtainMessage();
                            msg.what = UPDATE_DOWNLOAD;
                            msg.obj = x;
                            handler.sendMessage(msg);
                            updateProcess = x;
                        }
                    }
                    outputStream.flush();
                    inputStream.close();
                    outputStream.close();


                     Log.v("litieshan","download finished ");
                     Message msg = handler.obtainMessage();
                     msg.what = FINISH_DOWNLOAD;
                     handler.sendMessage(msg);
                } catch (Exception e) {
                    if(downloadTaskListener!=null){
                        Log.v("litieshan","download exceptoin ");
                        Message msg = handler.obtainMessage();
                        msg.what = ERROR_DOWNLOAD;
                        handler.sendMessage(msg);
                    }
                }
            }
        };
        thread.start();
    }
}
