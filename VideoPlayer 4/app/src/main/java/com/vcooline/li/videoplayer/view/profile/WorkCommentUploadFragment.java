package com.vcooline.li.videoplayer.view.profile;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.UploadPicAction;
import com.vcooline.li.videoplayer.action.WorksUploadAction;
import com.vcooline.li.videoplayer.category.ProfileActivity;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class WorkCommentUploadFragment extends Fragment {

    public static final int CHOOSE_UP_IMAGE = 10003;
    private ProfileActivity activity;
    private Button cancel;
    private Button ok;
    private ImageView image;
    private EditText comment;
    private Button add;
    private InputStream imageStream;
    private Handler handler;


    private WorksUploadAction workCommentsUploadAction;

    public WorkCommentUploadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work_comment_upload, container, false);
        cancel = (Button) view.findViewById(R.id.cancel);
        ok = (Button) view.findViewById(R.id.ok);
        add = (Button) view.findViewById(R.id.add);
        image = (ImageView) view.findViewById(R.id.image);
        comment = (EditText) view.findViewById(R.id.comment);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (ProfileActivity)getActivity();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0){
                    Toast.makeText(activity,"图片上传失败！",Toast.LENGTH_LONG).show();
                }else{
                    workCommentsUploadAction.upload("10.jpg", "http://ly498001203.eicp.net/yikao/upload/10.jpg", comment.getText().toString());
                }
            }
        };

        workCommentsUploadAction = new WorksUploadAction(activity,
                new WorksUploadAction.UploadSuccessCallback(){
                    @Override
                    public void actionSuccessBack(JSONObject json) {
                        activity.hiddenWorkCommentUpload();
                    }

                    @Override
                    public void actionErrorBack(String errorCode) {

                    }
                });

        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                activity.startActivityForResult(intent, CHOOSE_UP_IMAGE);
            }
        });

        ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String text = comment.getText().toString();
                if(!text.equals("") && imageStream != null) {
                    final UploadPicAction uploadPicAction = new UploadPicAction(activity);
                    Thread upload = new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            String fileName = System.currentTimeMillis()+"comment.jpg";
                            String msgString = uploadPicAction.uploadFile(fileName,imageStream);
                            if(msgString.equals(UploadPicAction.UPLOADEXCEPTION)){
                                Message msg1 = handler.obtainMessage();
                                msg1.what = 0 ;
                                handler.sendMessage(msg1);
                            }else{
                                Message msg2 = handler.obtainMessage();
                                msg2.what = 1;
                                msg2.obj = msgString;
                                handler.sendMessage(msg2);
                            }
                        }
                    };
                    upload.start();
//                    workCommentsUploadAction.upload("10.jpg", "http://ly498001203.eicp.net/yikao/upload/10.jpg", text);
                }else{
                    Toast.makeText(activity,"请输入相关内容",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.hiddenWorkCommentUpload();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == activity.RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = activity.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                /* 将Bitmap设定到ImageView */
                image.setImageBitmap(bitmap);
                imageStream = cr.openInputStream(uri);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
