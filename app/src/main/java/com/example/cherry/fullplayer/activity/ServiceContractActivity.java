package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.cherry.fullplayer.R;

/**
 * Created by Administrator on 2017/12/23.
 */

public class ServiceContractActivity extends Activity {

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_contract_layout);
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/clause.html");
    }
}
