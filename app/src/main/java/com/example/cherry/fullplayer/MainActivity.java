package com.example.cherry.fullplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.cherry.fullplayer.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cherry on 2017/10/13.
 */

public class MainActivity extends AppCompatActivity {

    private GridView gradView = null;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpAdapter;
    private String[] ChannelDatas = {"CCTV-1", "CCTV-2", "东方频道", "安徽卫视", "北京卫视", "北京卫视", "北京卫视", "北京卫视", "北京卫视", "北京卫视", "北京卫视"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.MIUISetStatusBarLightMode11(this, true); //指针对小米手机改变状态栏字体颜色
//        StatusBarUtil.setStatusBarColor(this, R.color.statusGrey);
        setContentView(R.layout.layout_main);
//        StatusBarUtil.StatusBarLightMode(this, StatusBarUtil.StatusBarLightMode(this));
//        StatusBarUtil.MIUISetStatusBarLightMode(getWindow(), true);
        gradView = (GridView)findViewById(R.id.gradView);
        dataList = new ArrayList<Map<String, Object>>(); // step2
        simpAdapter = new SimpleAdapter(this, getData(), R.layout.grad_item_layout,
                new String[]{"txt"}, new int[]{R.id.channel_title});
        gradView.setAdapter(simpAdapter); // step3

        gradView.setOnItemClickListener(new GradViewItemClickListener());
    }

    //GradView点击事件
    private class GradViewItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this,"哦啊，点中了哦",Toast.LENGTH_LONG).show();
        }
    }


    private List<Map<String, Object>> getData() {
        for (int i=0; i<ChannelDatas.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("txt", ChannelDatas[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
