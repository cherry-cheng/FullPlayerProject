package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cherry.fullplayer.R;

/**
 * Created by cherry on 2017/12/17.
 */

public class QpopActivity extends Activity {
    private ImageView btnBack = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qpop_activity);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new BackOnClickLisner());
    }

    private class BackOnClickLisner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }
}
