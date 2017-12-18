package com.example.cherry.fullplayer.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cherry.fullplayer.R;
import com.example.cherry.fullplayer.mzbanner.MZBannerView;
import com.example.cherry.fullplayer.mzbanner.holder.MZHolderCreator;
import com.example.cherry.fullplayer.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by cherry on 2017/12/17.
 */

public class WifiConnectHintActivity extends Activity {
    private MZBannerView mNormalBanner;
    public static final int []RES = new int[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3};
    private TextView first_number = null, first_title = null;
    private TextView second_number = null, second_title = null;
    private TextView third_number = null, third_title =  null;
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
        mNormalBanner = (MZBannerView) findViewById(R.id.banner_normal);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<RES.length;i++){
            list.add(RES[i]);
        }
        mNormalBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mNormalBanner.setIndicatorVisible(true);
        mNormalBanner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("cyh11", "--" + position + "--" + positionOffset);
                Log.e("cyhcyh","----->addPageChangeLisnter:"+position + "positionOffset:"+positionOffset+ "positionOffsetPixels:"+positionOffsetPixels);
                if (position == 2 && positionOffset > 0.5) {
                    first_number.setBackground(getResources().getDrawable(R.drawable.text_circle_bg));
                    first_title.setTextColor(getResources().getColor(R.color.textGreenColor));

                    second_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
                    second_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
                    third_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
                    third_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
                }

                if (position == 0 && positionOffset > 0.5) {
                    second_number.setBackground(getResources().getDrawable(R.drawable.text_circle_bg));
                    second_title.setTextColor(getResources().getColor(R.color.textGreenColor));

                    third_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
                    third_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
                    first_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
                    first_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
                }

                if (position == 1 && positionOffset > 0.5) {
                    third_number.setBackground(getResources().getDrawable(R.drawable.text_circle_bg));
                    third_title.setTextColor(getResources().getColor(R.color.textGreenColor));

                    first_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
                    first_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
                    second_number.setBackground(getResources().getDrawable(R.drawable.text_circle_grey_gb));
                    second_title.setTextColor(getResources().getColor(R.color.colorGreyLittle));
                }

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG,"addPageChangeLisnter:"+position);
                Log.i("cyh33", "addPageChange = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.i("cyh22", "state = " + state);
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mNormalBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mNormalBanner.start();
    }
}
