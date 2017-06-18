package com.vcooline.li.videoplayer.category;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.action.LoginAction;
import com.vcooline.li.videoplayer.bean.ProfileBean;
import com.vcooline.li.videoplayer.databases.SharePrefs;
import com.vcooline.li.videoplayer.tools.JSONUtil;
import com.vcooline.li.videoplayer.tools.NotNetWorkTool;
import com.vcooline.li.videoplayer.tools.roundedimageview.ImageRoudConer;
import com.vcooline.li.videoplayer.view.profile.ChooseAvatarActivity;
import com.vcooline.li.videoplayer.view.profile.CommentContainerFragment;
import com.vcooline.li.videoplayer.view.profile.DownloadVideoFragment;
import com.vcooline.li.videoplayer.view.profile.MyPictureLibraryFragment;
import com.vcooline.li.videoplayer.view.profile.shoucang.FavVideoFragment;
import com.vcooline.li.videoplayer.view.profile.LoginAndRegisterDialog;
import com.vcooline.li.videoplayer.view.profile.WorkCommentUploadFragment;
import com.vcooline.li.videoplayer.view.profile.WorksExchangeFragment;
import com.viewpagerindicator.TabPageIndicator;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
/*
* 此类中添加的功能模块的我的图库
* 1.添加一个framgememt 就是现实这个布局的样子的
* */
public class ProfileActivity extends FragmentActivity {

    public static final int UPLOADAVATAR = 10001;
    private static final String[] CONTENT = new String[]{"我的下载", "我的收藏", "作品点评", "作品交流","我的图库"};
    public DrawerLayout drawerLayout;
    private ViewPager pager;
    private TabPageIndicator indicator;

    private TextView login;
    private ImageView avator;

//    private TextView register;
    private AlertDialog logindialog;
    private LoginAction loginAction;

    private SharedPreferences userPrefs;
    private AQuery aQuery;

    private  FragmentPagerAdapter adapter;
    //fragment informations
    private ArrayList<Fragment> allFragments = new ArrayList<Fragment>();
    private DownloadVideoFragment downloadVideoFragment;
    private FavVideoFragment favVideoFragment;
    private CommentContainerFragment workCommentsFragment;
    private WorksExchangeFragment worksExchangeFragment;
    private MyPictureLibraryFragment myPictureLibraryFragment;

    private WorkCommentUploadFragment workCommentUploadFragment;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        NotNetWorkTool.checkNetWork(this);

        aQuery = new AQuery(this);
       // drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        login = (TextView) findViewById(R.id.login);
        avator = (ImageView) findViewById(R.id.avator);
        pager = (ViewPager) findViewById(R.id.pager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        back = (ImageView)findViewById(R.id.back);
        //init profile information
        userPrefs = SharePrefs.getProfileSharePrefs(this);
        String username = userPrefs.getString(SharePrefs.USERNAME,"");
        if(!username.equals("")){
            login.setText(username);
        }
        adapter = new ProfilePageAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //得到一个登陆的界面视图
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LoginAndRegisterDialog loginAndRegister = new LoginAndRegisterDialog(ProfileActivity.this);
                logindialog =loginAndRegister.getLoginDialog();
                logindialog.show();
            }
        });
        avator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent chooseIntent = new Intent(ProfileActivity.this, ChooseAvatarActivity.class);
//                startActivity(chooseIntent);
                startActivityForResult(chooseIntent,UPLOADAVATAR);
            }
        });
        downloadVideoFragment = new DownloadVideoFragment();
        favVideoFragment = new FavVideoFragment();
        workCommentsFragment = new CommentContainerFragment();
        worksExchangeFragment = new WorksExchangeFragment();
        myPictureLibraryFragment = new MyPictureLibraryFragment();

        allFragments.add(downloadVideoFragment);
        allFragments.add(favVideoFragment);
        allFragments.add(workCommentsFragment);
        allFragments.add(worksExchangeFragment);
        allFragments.add(myPictureLibraryFragment);//添加的这样的方式的情况

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            pager.getAdapter().notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(String usernameTxt,String passwordTxt){
        LoginAction loginAction = new LoginAction(this,new LoginAction.LoginCallBack() {
            @Override
            public void actionSuccessBack(ProfileBean profileBean) {
                if(logindialog!=null){
                    logindialog.dismiss();
                    login.setText(profileBean.getUserName());
                }
            }
            @Override
            public void actionErrorBack(String errorCode) {
                Toast.makeText(ProfileActivity.this,errorCode,Toast.LENGTH_LONG).show();
            }
        });
        loginAction.login(usernameTxt,passwordTxt);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if(requestCode == UPLOADAVATAR){
                String msg = data.getStringExtra("result");
                try {
                    JSONObject jsonObject = new JSONObject(msg);
                    JSONUtil jsonUtil = new JSONUtil(jsonObject.getJSONObject("result"));
//                    {"status":"1","result":{"savePath":"yikaopath\/1412045574229avator.jpg",
//                     "fileName":"1412045574229avator.jpg","url":"http:\/\/testhotel.vcooline.com\/yikaopath\/1412045574229avator.jpg"}}
                    String url = jsonUtil.optString("url");
//                    String fileName = jsonUtil.optString("fileName");
                   // String savePath = jsonUtil.optString("savePath");
                    userPrefs.edit().putString(SharePrefs.ICONURL,url).commit();
                    aQuery.id(avator).image(url);
                }catch (Exception e){
                }
            }
            if(requestCode == WorkCommentUploadFragment.CHOOSE_UP_IMAGE){
                workCommentsFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    class ProfilePageAdapter extends FragmentPagerAdapter {
        public ProfilePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return allFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }
        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
    //SHOW RIGHT
    public void showToFront(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().findFragmentByTag(
                    String.valueOf(fragment.hashCode())) != null) {
                ft.show(fragment);
            } else {
                ft.replace(R.id.right_container, fragment,
                        String.valueOf(fragment.hashCode()));
                ft.show(fragment);
            }
        ft.commitAllowingStateLoss();
        invalidateOptionsMenu();
    }

    public void showWorkCommentUpload(WorkCommentUploadFragment workCommentUploadFragment){
        workCommentsFragment.showUploadFragment(workCommentUploadFragment);
    }

    public void hiddenWorkCommentUpload(){
        workCommentsFragment.removeUploadFragment();
    }
}
