package com.vcooline.li.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.vcooline.li.videoplayer.category.ColorActivity;
import com.vcooline.li.videoplayer.category.CulturalActivity;
import com.vcooline.li.videoplayer.category.DesignActivity;
import com.vcooline.li.videoplayer.category.InformationActivity;
import com.vcooline.li.videoplayer.category.PicActivity;
import com.vcooline.li.videoplayer.category.ProfileActivity;
import com.vcooline.li.videoplayer.category.QuickActivity;
import com.vcooline.li.videoplayer.category.SettingsActivity;
import com.vcooline.li.videoplayer.category.SketchActivity;

public class EntryActivity extends Activity implements View.OnClickListener {

    private ImageView sketch;
    private ImageView profile;
    private ImageView news;
    private ImageView color;
    private ImageView quick;
    private ImageView design;
    private ImageView cultural;
    private ImageView picture;
    private ImageView settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_layout2);
        profile = (ImageView) findViewById(R.id.t3);
        settings = (ImageView) findViewById(R.id.t4);
        sketch = (ImageView) findViewById(R.id.t5);
        news = (ImageView) findViewById(R.id.t12);
        color = (ImageView) findViewById(R.id.t6);
        picture = (ImageView) findViewById(R.id.t7);
        quick = (ImageView) findViewById(R.id.t9);
        design = (ImageView) findViewById(R.id.t10);
        cultural = (ImageView) findViewById(R.id.t11);

        profile.setOnClickListener(this);
        sketch.setOnClickListener(this);
        color.setOnClickListener(this);
        news.setOnClickListener(this);
        quick.setOnClickListener(this);
        design.setOnClickListener(this);
        cultural.setOnClickListener(this);
        picture.setOnClickListener(this);
        settings.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.t3:
                Intent profileIntent = new Intent(EntryActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.t4:
                Intent settingsIntent = new Intent(EntryActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;

            case R.id.t5:
                Intent sketchIntent = new Intent(EntryActivity.this, SketchActivity.class);
                startActivity(sketchIntent);
                break;
            case R.id.t6:
                Intent colorIntent = new Intent(EntryActivity.this, ColorActivity.class);
                startActivity(colorIntent);
                break;
            case R.id.t7:
                Intent picIntent = new Intent(EntryActivity.this, PicActivity.class);
                startActivity(picIntent);
                break;
            case R.id.t9:
                Intent quickIntent = new Intent(EntryActivity.this, QuickActivity.class);
                startActivity(quickIntent);
                break;
            case R.id.t10:
                 Intent designIntent = new Intent(EntryActivity.this, DesignActivity.class);
                startActivity(designIntent);
                break;
            case R.id.t11:
                Intent culturalIntent = new Intent(EntryActivity.this, CulturalActivity.class);
                startActivity(culturalIntent);
                break;
            case R.id.t12:
                Intent newshIntent = new Intent(EntryActivity.this, InformationActivity.class);
                startActivity(newshIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.entry, menu);
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
}
