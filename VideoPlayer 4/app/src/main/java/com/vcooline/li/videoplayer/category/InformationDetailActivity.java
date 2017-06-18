package com.vcooline.li.videoplayer.category;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcooline.li.videoplayer.R;
import com.vcooline.li.videoplayer.bean.InformationBean;

public class InformationDetailActivity extends Activity {

    private ImageView back;
    private TextView title;
    private TextView content;
    private InformationBean informationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.content_title);
        content = (TextView) findViewById(R.id.content_info);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        informationBean = (InformationBean) getIntent().getSerializableExtra("informationBean");
        title.setText(informationBean.getTitle());
        content.setText(informationBean.getContent());
    }
}
