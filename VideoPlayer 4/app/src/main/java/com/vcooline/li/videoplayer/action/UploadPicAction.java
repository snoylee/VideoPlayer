package com.vcooline.li.videoplayer.action;

import android.app.Activity;
import android.util.Log;

import com.vcooline.li.videoplayer.tools.UrlTool;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Trace on 2014/9/3.
 */
public class UploadPicAction {
    private Activity activity;
    public static final  String UPLOADEXCEPTION = "uploadexception";

        public UploadPicAction(Activity mActivity){
            this.activity = mActivity;
        }

        /* 上传文件至Server的方法 */
        public String uploadFile(String newName,InputStream inputStream)
        {
            String actionUrl = UrlTool.FILEUPLOAD;
            String end = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            try
            {
                URL url =new URL(actionUrl);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
          /* 允许Input、Output，不使用Cache */
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
          /* 设置传送的method=POST */
                con.setRequestMethod("POST");
          /* setRequestProperty */
                con.setRequestProperty("Connection", "Keep-Alive");
                con.setRequestProperty("Charset", "UTF-8");
                con.setRequestProperty("Content-Type",
                        "multipart/form-data;boundary="+boundary);
          /* 设置DataOutputStream */
                DataOutputStream ds =
                        new DataOutputStream(con.getOutputStream());
                ds.writeBytes(twoHyphens + boundary + end);
                ds.writeBytes("Content-Disposition: form-data; " +
                        "name=\"file\";filename=\"" +
                        newName +"\"" + end);
                ds.writeBytes(end);

          /* 取得文件的FileInputStream */
//                File upFile = new File(uploadFile);
//                FileInputStream fStream = new FileInputStream(upFile);
          /* 设置每次写入1024bytes */
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];

                int length = -1;
          /* 从文件读取数据至缓冲区 */
                while((length = inputStream.read(buffer)) != -1)
                {
            /* 将资料写入DataOutputStream中 */
                    ds.write(buffer, 0, length);
                }
                ds.writeBytes(end);
                ds.writeBytes(twoHyphens + boundary + twoHyphens + end);

          /* close streams */
                inputStream.close();
                ds.flush();

          /* 取得Response内容 */
                InputStream is = con.getInputStream();
                int ch;
                StringBuffer b =new StringBuffer();
                while( ( ch = is.read() ) != -1 )
                {
                    b.append( (char)ch );
                }
                Log.v("lijing",b.toString());
          /* 将Response显示于Dialog */
          /* 关闭DataOutputStream */
                ds.close();
                return b.toString();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return UPLOADEXCEPTION;
            }
        }

}
