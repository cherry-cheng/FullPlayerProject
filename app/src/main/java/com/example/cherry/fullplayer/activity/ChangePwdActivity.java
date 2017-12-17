package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cherry.fullplayer.R;

/**
 * Created by cherry on 2017/12/17.
 */

public class ChangePwdActivity extends Activity implements View.OnClickListener {
    private Button bt_confirm_modify = null;
    private ImageView btnBack = null;
    private EditText inputOriginalPwd = null, inputCurrentPwd = null, inputCurrentPwd2 = null;
    private TextView deviceWifiName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd_activity);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        bt_confirm_modify = (Button) findViewById(R.id.bt_confirm_modify);
        inputOriginalPwd = (EditText) findViewById(R.id.inputOriginalPwd);
        inputCurrentPwd = (EditText) findViewById(R.id.inputCurrentPwd);
        inputCurrentPwd2 = (EditText) findViewById(R.id.inputCurrentPwd2);
        deviceWifiName = (TextView) findViewById(R.id.deviceWifiName);
        initView();
    }

    private void initView() {
        btnBack.setOnClickListener(this);
        bt_confirm_modify.setOnClickListener(this);
        inputCurrentPwd.setOnClickListener(this);
        inputCurrentPwd2.setOnClickListener(this);
        inputOriginalPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.bt_confirm_modify:
                startActivity(new Intent(ChangePwdActivity.this, PwdConfirmDialogActivity.class));
                break;
            case R.id.inputCurrentPwd:
                break;
            case R.id.inputCurrentPwd2:
                break;
            case R.id.inputOriginalPwd:
                break;
        }

    }
}
