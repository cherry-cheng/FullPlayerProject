package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cherry.fullplayer.R;

/**
 * Created by cherry on 2017/12/17.
 */

public class QpopActivity extends Activity {
    private ImageView btnBack = null;
    private LinearLayout service_ll = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qpop_activity);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new BackOnClickLisner());
        service_ll = (LinearLayout) findViewById(R.id.service_ll);
        service_ll.setOnClickListener(new ServiceOnClickLisetner());
    }

    private class ServiceOnClickLisetner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            startActivity(new Intent(QpopActivity.this, ServiceContractActivity.class));
        }
    }

    private class BackOnClickLisner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }
}
