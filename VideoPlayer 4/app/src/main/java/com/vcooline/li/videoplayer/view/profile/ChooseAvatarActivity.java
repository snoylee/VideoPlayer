package com.vcooline.li.videoplayer.view.profile;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.UploadPicAction;
import com.vcooline.li.videoplayer.tools.roundedimageview.ImageRoudConer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ChooseAvatarActivity extends Activity implements View.OnClickListener{

    private static final int CHOOSE_IMAGE = 1;
    private static final int CHOOSE_CAMERA = 2;
    private ActionBar actionBar;
    private ImageView close;
    private ImageView avatar;
    private ImageView image;
    private ImageView camera;
    private Button submit;

    private InputStream avatarStream;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_avatar);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0){
                    Toast.makeText(ChooseAvatarActivity.this,"头像上传失败！",Toast.LENGTH_LONG).show();
                }else{
                    Intent result =  new Intent();
                    result.putExtra("result",msg.obj.toString());
                    ChooseAvatarActivity.this.setResult(Activity.RESULT_OK,result);
                    finish();
                }
            }
        };

        close = (ImageView) findViewById(R.id.close);
        avatar = (ImageView) findViewById(R.id.avatar);
        image = (ImageView) findViewById(R.id.image);
        camera = (ImageView) findViewById(R.id.camera);
        submit = (Button) findViewById(R.id.submit);

        close.setOnClickListener(this);
        avatar.setOnClickListener(this);
        image.setOnClickListener(this);
        camera.setOnClickListener(this);
        submit.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.choose_avatar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK ) {
            switch (requestCode) {
                case CHOOSE_CAMERA:
                    ContentResolver cr = this.getContentResolver();
                    //将保存在本地的图片取出并缩小后显示在界面上
                    Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/image.jpg");
                    Uri uriOne = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null,null));
                    try {
                        avatar.setImageBitmap(ImageRoudConer.makeRoundCorner(bitmap));
                        //Uri uriTwo = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),ImageRoudConer.makeRoundCorner(bitmap), null,null));
                        avatarStream = cr.openInputStream(uriOne);
                        submit.setBackgroundColor(Color.BLUE);
                    }catch (IOException e){
                        Log.e("Exception", e.getMessage(),e);
                    }
                    break;
                case CHOOSE_IMAGE:
                    Uri uri = data.getData();
                    Log.e("uri", uri.toString());
                    ContentResolver crOne = this.getContentResolver();
                    try {
                        Bitmap newBitmap = BitmapFactory.decodeStream(crOne.openInputStream(uri));
                        avatar.setImageBitmap(ImageRoudConer.makeRoundCorner(newBitmap));
                        //avatar.setScaleType();
                       // Uri uriThree = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),ImageRoudConer.makeRoundCorner(newBitmap), null,null));
                        avatarStream = crOne.openInputStream(uri);
                        submit.setBackgroundColor(Color.BLUE);
                    } catch (FileNotFoundException e) {
                        Log.e("Exception", e.getMessage(),e);
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close:
                finish();
                break;
            case R.id.image:
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, CHOOSE_IMAGE);
                break;
            case R.id.camera:
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                String path = Environment.getExternalStorageDirectory().toString();
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path + "test.jpg")));
//                startActivityForResult(cameraIntent, CHOOSE_CAMERA);
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(openCameraIntent, CHOOSE_CAMERA);
                break;
            case R.id.submit:
                final UploadPicAction uploadPicAction = new UploadPicAction(this);

                Thread upload = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String fileName = System.currentTimeMillis()+"avator.jpg";
                        String msgString = uploadPicAction.uploadFile(fileName,avatarStream);
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
             default:
                 break;

        }
    }
}
