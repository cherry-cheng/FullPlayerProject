package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.cherry.fullplayer.MainActivity;
import com.example.cherry.fullplayer.R;
import com.example.cherry.fullplayer.view.CheckBoxSample;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by cherry on 2017/12/17.
 */

public class WifiConnectHintActivity extends Activity implements View.OnClickListener {
    public static final int []RES = new int[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3};
    private TextView first_number = null, first_title = null;
    private TextView second_number = null, second_title = null;
    private TextView third_number = null, third_title =  null;
    private Button go_connection_bt = null;
    private CheckBoxSample no_hint_checkbox = null;
    private LinearLayout city_location_ll = null;
    int i = 0;
    private boolean isCheck = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_wifi_hint_layout);
        first_number = (TextView) findViewById(R.id.first_number);
        first_title = (TextView) findViewById(R.id.first_title);
        second_number = (TextView) findViewById(R.id.second_number);
        second_title = (TextView) findViewById(R.id.second_title);
        third_number = (TextView) findViewById(R.id.third_number);
        third_title = (TextView) findViewById(R.id.third_title);
        go_connection_bt = (Button) findViewById(R.id.go_connection_bt);
        go_connection_bt.setOnClickListener(this);
        no_hint_checkbox = (CheckBoxSample) findViewById(R.id.no_hint_checkbox);
        no_hint_checkbox.setOnClickListener(this);
        city_location_ll = (LinearLayout) findViewById(R.id.city_location_ll);
        city_location_ll.setOnClickListener(this);
//        mNormalBanner = (MZBannerView) findViewById(R.id.banner_normal);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<RES.length;i++){
            list.add(RES[i]);
        }

        ViewFlipper mViewFlipper = (ViewFlipper) findViewById(R.id.marquee_viewFlipper);
        LinearLayout shenzhenIndexLayout = (LinearLayout) View.inflate(this, R.layout.marquee_item_layout1, null);
        LinearLayout shangzhenIndexLayout = (LinearLayout) View.inflate(this, R.layout.marquee_item_layout2, null);
        LinearLayout cyIndexLayout = (LinearLayout) View.inflate(this, R.layout.marquee_item_layout3, null);
        mViewFlipper.addView(shenzhenIndexLayout);
        mViewFlipper.addView(shangzhenIndexLayout);
        mViewFlipper.addView(cyIndexLayout);
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("cyh--", "onAnimationStart");
                i ++;
                if (i % 3 == 0) {
                    i = 0;
                }
                changeIndicator(i);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("cyh--", "onAnimationRepeat");
            }
        });

        //获取preferences和editor对象
        preferences = getSharedPreferences("quanbo", MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void changeIndicator(int i) {
        if (i == 1) {
            second_number.setBackground(getResources().getDrawable(R.drawable.text_circle_bg));
            second_title.setTextColor(getResources().getColor(R.color.textGreenColor));

            third_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
            third_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
            first_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
            first_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
        }
        if (i == 2) {
            third_number.setBackground(getResources().getDrawable(R.drawable.text_circle_bg));
            third_title.setTextColor(getResources().getColor(R.color.textGreenColor));

            first_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
            first_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
            second_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
            second_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
        }
        if (i == 0) {
            first_number.setBackground(getResources().getDrawable(R.drawable.text_circle_bg));
            first_title.setTextColor(getResources().getColor(R.color.textGreenColor));

            second_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
            second_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
            third_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
            third_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_connection_bt:
                Log.i("cyh", "isCheck = " + isCheck);
                if (isCheck) {
                    editor.putBoolean("wifihint", true);
                    editor.commit();
                }
                Log.i("cyh", "wifihint = " + preferences.getBoolean("wifihint", false));
                startActivity(new Intent(WifiConnectHintActivity.this, MainActivity.class));
                break;
            case R.id.no_hint_checkbox:
                no_hint_checkbox.toggle();
                isCheck = !isCheck;
                break;
            case R.id.city_location_ll:
                startActivity(new Intent(WifiConnectHintActivity.this, CityActivity.class));
                break;
        }
    }
}
