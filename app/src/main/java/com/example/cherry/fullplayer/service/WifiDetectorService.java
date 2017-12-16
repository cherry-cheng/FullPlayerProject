package com.example.cherry.fullplayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/12/16.
 */

public class WifiDetectorService extends IntentService {
    public WifiDetectorService() {
        super("WifiDetectorService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
