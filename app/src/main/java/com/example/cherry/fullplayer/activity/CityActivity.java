package com.example.cherry.fullplayer.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.cherry.fullplayer.MainActivity;
import com.example.cherry.fullplayer.R;
import com.example.cherry.fullplayer.adapter.MyAdapter;
import com.example.cherry.fullplayer.view.WaveSideBarView;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/12/15.
 */

public class CityActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener, EasyPermissions.PermissionCallbacks {
    private RecyclerView recyclerView;
    private WaveSideBarView waveSideBarView;
    private Button button;
    private int number = -1;
    private LinearLayoutManager layoutManager;

    private final int RC_SETTINGS_SCREEN = 125;
    private final int RC_LOCATION_CONTACTS_PERM = 124;
    private AMapLocationClient mLocationClient;
    private TextView choosed_city = null;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        choosed_city = (TextView) findViewById(R.id.choosed_city);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ivSettingsClickListener());
        final MyAdapter adapter = new MyAdapter(CityActivity.this);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(CityActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        waveSideBarView = (WaveSideBarView) findViewById(R.id.waveView);
        waveSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                if (number == -1) {
                    number = layoutManager.findLastVisibleItemPosition() -layoutManager.findFirstVisibleItemPosition();
                }
                int pos;
                if(adapter.getLetterPosition(letter)<number){
                    pos = adapter.getLetterPosition(letter);
                }else {
                    pos = adapter.getLetterPosition(letter)+number-1;
                    if (pos > adapter.getItemCount()) {
                        pos = adapter.getItemCount();
                    }
                }
                recyclerView.scrollToPosition(pos);
            }
        });
        //获取preferences和editor对象
        preferences = getSharedPreferences("quanbo", MODE_PRIVATE);
        editor = preferences.edit();
        initData();
        initLocation();
    }

    @Override
    public void onItemClick(String cityName) {
//        button.setBackgroundColor(getResources().getColor(R.color.button_color));
        Log.i("cyh", "cyh cityName = " + cityName);
    }

    private class ivSettingsClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (preferences.getBoolean("wifihint", false)) {
                startActivity(new Intent(CityActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(CityActivity.this, WifiConnectHintActivity.class));
            }
        }
    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    private void initLocation() {
        //高德定位
        Log.i("cyh", "高德地图定位");
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = extractLocation(city, district);
                    } else {
                        //定位失败
                    }
                }
            }
        });

        //权限判断
        if (EasyPermissions.hasPermissions(this, perms)) {

            mLocationClient.startLocation();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, "定位需要相关权限",
                    RC_LOCATION_CONTACTS_PERM, perms);
        }
    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    private void initData() {
        choosed_city.setText("上海市");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        choosed_city.setText("上海市");
    }


    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, getString(R.string.rationale_ask_again))
                    .setTitle(getString(R.string.title_settings_dialog))
                    .setPositiveButton(getString(R.string.setting))
                    .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
        choosed_city.setText("无法定位，请手动选择");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SETTINGS_SCREEN) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, R.string.returned_from_app_settings_to_activity, Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * 提取出城市或者县
     *
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district) {
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }
}
