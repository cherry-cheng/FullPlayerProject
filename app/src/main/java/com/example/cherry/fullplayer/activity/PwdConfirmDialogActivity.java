package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.cherry.fullplayer.R;

/**
 * Created by cherry on 2017/12/17.
 */

public class PwdConfirmDialogActivity extends Activity {
    private TextView tv_ok = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.pwdconfirm_dialog);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new TvOKLisetner());
    }

    private class TvOKLisetner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //上传修改密码操作
            finish();
        }
    }
}
