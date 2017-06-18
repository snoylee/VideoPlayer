package com.vcooline.li.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vcooline.li.videoplayer.view.PdfActivity;
import com.vcooline.li.videoplayer.view.VideoViewPlayingActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


public class MyActivity extends FragmentActivity {
    private NavigationTabFragment navigationTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        navigationTabFragment = NavigationTabFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, navigationTabFragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, VideoViewPlayingActivity.class);
            String source = getSdPath() + "/xiaopingguo.avi";
            intent.setData(Uri.parse(source));
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_pdf) {
            Intent intent = new Intent(MyActivity.this, PdfActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.check_upgrade) {
//            UpdateManager updateManager = new UpdateManager(this);
//            updateManager.checkUpdate();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    encodeVideo();
                }
            }.start();


        }
        return super.onOptionsItemSelected(item);
    }


    private String getSdPath() {
        File sdcardDir = Environment.getExternalStorageDirectory();
        return sdcardDir.getPath();
    }

    private void encodeVideo() {
        String path = getSdPath() + "/lijing1.jpg";
        File videoFile = new File(path);
        String enPath = getSdPath() + "/lijing2.jpg";
        String dePath = getSdPath() + "/lijing3.jpg";
        long t1 = System.currentTimeMillis();
        copyFile(path, enPath);
        long t2 = System.currentTimeMillis() - t1;
        Log.v("lijing", "耗时" + t2);

        decopyFile(enPath, dePath);
    }


    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    if (bytesum == byteread) {
                        String message = "lijing"; // 生成个DES密钥
                        fs.write(message.getBytes());
                        fs.write(buffer);
                    } else {
                        fs.write(buffer, 0, byteread);
                    }
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }


    public void decopyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    if (bytesum == byteread) {
                        String message = "lijing"; // 生成个DES密钥
                        fs.write(buffer, 6, byteread - 6);
                    } else {
                        fs.write(buffer, 0, byteread);
                    }
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }


}
