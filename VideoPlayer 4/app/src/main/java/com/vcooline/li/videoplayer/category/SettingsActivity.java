package com.vcooline.li.videoplayer.category;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.tools.AppCacheManager;
import com.vcooline.li.videoplayer.tools.UtilTool;

import java.util.ArrayList;

import static android.view.View.OnClickListener;


public class SettingsActivity extends Activity {
    private ArrayList<String> datas = new ArrayList<String>();
    private ListView listView;
    private ImageView back;
    private Button login_Bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 所的的值将会自动保存到SharePreferences
        setContentView(R.layout.activity_settings_layout);
        listView = (ListView)findViewById(R.id.setting_list);
        back = (ImageView)findViewById(R.id.back);
        login_Bt = (Button)findViewById(R.id.Bt);
        login_Bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initDatas();
        SettingsAdapter adapter = new SettingsAdapter();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String currentItem = datas.get(i);
                if(currentItem.equals("进入安卓密码修改")){
                    Toast.makeText(SettingsActivity.this,"修改密码设置",Toast.LENGTH_SHORT).show();

                }else if(currentItem.equals("清除缓存")){
                    Toast.makeText(SettingsActivity.this,"删除缓存中",Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                             String path = AppCacheManager.getRootCacheDir(SettingsActivity.this);
                            UtilTool.deleteFolderFile(path,false);
                        }
                    });


                }else if(currentItem.equals("当前可用空间")){
                    UtilTool.showSdCardInfo(SettingsActivity.this);

                }else if(currentItem.equals("设置存储路径")){

                }else if(currentItem.equals("常见问题和使用说明")){

                }else if(currentItem.equals("新版本检测")){

                }else if(currentItem.equals("意见反馈")){

                }else if(currentItem.equals("关于我们")){

                }
            }
        });

    }


    private void initDatas(){
        datas.add("进入安卓密码修改");
        datas.add("清除缓存");
        datas.add("当前可用空间");
        datas.add("设置存储路径");
        datas.add("常见问题和使用说明");
        datas.add("新版本检测");
        datas.add("意见反馈");
        datas.add("关于我们");
    }

    public class SettingsAdapter extends BaseAdapter{
        private Activity activity;
        private LayoutInflater inflater;
        public SettingsAdapter(){
            activity = SettingsActivity.this;
            inflater = LayoutInflater.from(SettingsActivity.this);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View contentView, ViewGroup viewGroup) {
            contentView = inflater.inflate(R.layout.setting_list_item, null);
            TextView textView = (TextView)contentView.findViewById(R.id.tv_system_title);
            textView.setText(getItem(i).toString());
            if(i==0){
                contentView.setBackgroundResource(R.drawable.app_list_corner_round_top);
            }else if(i == datas.size() - 1){
                contentView.setBackgroundResource(R.drawable.app_list_corner_round_bottom);
            }else{
                contentView.setBackgroundResource(R.drawable.app_list_corner_round_center);
            }
            return contentView;
        }
    }
}
