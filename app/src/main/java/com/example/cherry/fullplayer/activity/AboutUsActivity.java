package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cherry.fullplayer.R;

/**
 * Created by Administrator on 2017/12/16.
 */

public class AboutUsActivity extends Activity implements View.OnClickListener {
    private LinearLayout Setting_ChangePwd = null;
    private LinearLayout Setting_WifiSignal = null;
    private LinearLayout Setting_ChangeDevice = null;
    private LinearLayout Setting_Aboutus = null;
    private ImageView btnBack = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        Setting_WifiSignal = (LinearLayout) findViewById(R.id.Setting_WifiSignal);
        Setting_ChangePwd = (LinearLayout) findViewById(R.id.Setting_ChangePwd);
        Setting_ChangeDevice = (LinearLayout) findViewById(R.id.Setting_ChangeDevice);
        Setting_Aboutus = (LinearLayout) findViewById(R.id.Setting_Aboutus);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        initView();
    }

    private void initView() {
        Setting_WifiSignal.setOnClickListener(this);
        Setting_ChangeDevice.setOnClickListener(this);
        Setting_ChangePwd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        Setting_Aboutus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
            case R.id.Setting_WifiSignal:
                startActivity(new Intent(AboutUsActivity.this, DialogActivity.class));
                break;
            case R.id.Setting_ChangePwd:
                startActivity(new Intent(AboutUsActivity.this, ChangePwdActivity.class));
                break;
            case R.id.Setting_ChangeDevice:
                break;
            case R.id.Setting_Aboutus:
                startActivity(new Intent(AboutUsActivity.this, QpopActivity.class));
                break;
            default:
                break;
        }
    }
}
