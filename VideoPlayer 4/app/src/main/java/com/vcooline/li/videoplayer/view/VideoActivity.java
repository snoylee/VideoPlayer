package com.vcooline.li.videoplayer.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.vcooline.li.videoplayer.R;

import java.io.File;

import static com.vcooline.li.videoplayer.R.id.video;

public class VideoActivity extends Activity {

    private VideoView videoView;
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = (VideoView) findViewById(video);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        /**
         * VideoView控制视频播放的功能相对较少，具体而言，它只有start和pause方法。为了提供更多的控制，
         * 可以实例化一个MediaController，并通过setMediaController方法把它设置为VideoView的控制器。
         */
        videoView.setMediaController(new MediaController(this));
        Uri videoUri = Uri.parse(getSDPath() + "/" + "VID_20140804_220815.mp4");
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    public String getSDPath() {
        File sdDir = null;
        sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        return sdDir.toString();
    }
}