package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cherry.fullplayer.R;
import com.example.cherry.fullplayer.view.CheckBoxSample;

public class DialogActivity extends Activity {

    private CheckBoxSample no_hint_checkbox = null;
    private TextView tv_back = null;
    private boolean isCheck = false;
    private static final int DIALOG_RESULT_CALLBACK = 88;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity);
        no_hint_checkbox = (CheckBoxSample) findViewById(R.id.no_hint_checkbox);
        tv_back = (TextView) findViewById(R.id.tv_back);
        initView();
    }

    private void initView() {
        no_hint_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_hint_checkbox.toggle();
                isCheck = !isCheck;
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck) {
//                    Toast.makeText(DialogActivity.this,
//                            "isCheck = " + isCheck, Toast.LENGTH_SHORT)
//                            .show();
                    //点了不再提醒，需要取消MainActivity定时器
                    setResult(DIALOG_RESULT_CALLBACK);
                    finish();
                } else {
                    finish();
                }
            }
        });
    }

}
