package com.vcooline.li.videoplayer.view.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.vcooline.li.videoplayer.R;

import java.io.File;

/**
 * Created by Trace on 2014/8/27.
 */
public class ChoosePicDailog {
    private Activity activity;
    public ImageView avator;
    private Button chooseFromGallery;
    private Button takePhoto;
    public static final int REQUEST_CAMERA = 1;
    public static final int SELECT_FILE = 2;

    public ChoosePicDailog(Activity mActivity){
        this.activity = mActivity;
    }

    public void showDialog(){
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.choose_avator_dialog, null);
        avator = (ImageView)layout.findViewById(R.id.avator);
        chooseFromGallery = (Button)layout.findViewById(R.id.choose1);
        takePhoto = (Button)layout.findViewById(R.id.choose2);
        chooseFromGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment
                        .getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                activity.startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                activity.startActivityForResult(
                        Intent.createChooser(intent, "Select File"),
                        SELECT_FILE);
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(layout)
                .create();
         dialog.show();
    }


}
