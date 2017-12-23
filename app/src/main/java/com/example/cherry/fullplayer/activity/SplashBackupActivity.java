package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.cherry.fullplayer.MainActivity;
import com.example.cherry.fullplayer.R;

/**
 * Created by Administrator on 2017/12/15.
 */

public class SplashBackupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                MyThread.openWifi();

//                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                startActivity(new Intent(SplashBackupActivity.this, CityActivity.class));
                finish();
            }
        }.start();
    }
}
