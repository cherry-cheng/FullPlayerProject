package com.example.cherry.fullplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cherry.fullplayer.activity.AboutUsActivity;
import com.example.cherry.fullplayer.activity.DialogActivity;
import com.example.cherry.fullplayer.activity.WifiConnectHintActivity;
import com.example.cherry.fullplayer.utils.StatusBarUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cherry on 2017/10/13.
 */

public class MainActivity extends AppCompatActivity {

    private GridView gradView = null;
    private GridView search_gradview = null;
    private GridView search_result_gradview = null;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpAdapter;
    private String[] ChannelDatas = {"CCTV-1", "CCTV-2", "东方频道", "安徽卫视", "北京卫视"};
    private LinearLayout current_channel_ll = null;
    private LinearLayout search_process_ll = null;
    private LinearLayout search_result_ll = null;
    private TextView tv_current_channel = null;
    private TextView tv_search_channel = null;
    private TextView tv_search_again = null;
    private LinearLayout search_channel_ll = null;
    private Button bt_goSearch = null;
    private ImageView settingsAbout = null;
    private Handler wifiHandler = null;
    private Timer wifiTimer = null;
    private static final int DIALOG_RESULT = 99;
    private static final int DIALOG_RESULT_CALLBACK = 88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        gradView = (GridView)findViewById(R.id.gradView);
        search_gradview= (GridView) findViewById(R.id.search_gradview);
        search_result_gradview = (GridView) findViewById(R.id.search_result_gradview);
        current_channel_ll = (LinearLayout) findViewById(R.id.current_channel_ll);
        tv_current_channel = (TextView) findViewById(R.id.tv_current_channel);
        tv_search_channel = (TextView) findViewById(R.id.tv_search_channel);
        tv_search_again = (TextView) findViewById(R.id.tv_search_again);
        search_channel_ll = (LinearLayout) findViewById(R.id.search_channel_ll);
        search_process_ll = (LinearLayout) findViewById(R.id.search_process_ll);
        search_result_ll = (LinearLayout) findViewById(R.id.search_result_ll);
        bt_goSearch = (Button) findViewById(R.id.bt_goSearch);
        settingsAbout = (ImageView) findViewById(R.id.settingsAbout);
        dataList = new ArrayList<Map<String, Object>>(); // step2
        simpAdapter = new SimpleAdapter(this, getData(), R.layout.grad_item_layout,
                new String[]{"txt"}, new int[]{R.id.channel_title});
        gradView.setAdapter(simpAdapter); // step3
        search_gradview.setAdapter(simpAdapter);
        search_result_gradview.setAdapter(simpAdapter);

        gradView.setOnItemClickListener(new GradViewItemClickListener());
        search_gradview.setOnItemClickListener(new GradViewProcessItemClickListener());
        search_result_gradview.setOnItemClickListener(new GradViewItemClickListener());
        initView();
        wifiTimer = new Timer();
        startWifiTimer();
    }

    private void initView() {
        tv_current_channel.setOnClickListener(new currentChannelClickListner());
        tv_search_channel.setOnClickListener(new searchChannelClickListener());
        bt_goSearch.setOnClickListener(new goSearchButtonLisner());
        tv_search_again.setOnClickListener(new tvSearchAgainLisner());
        settingsAbout.setOnClickListener(new ivSettingsListenr());
    }

    private class ivSettingsListenr implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
        }
    }
    //开启定时器
    private void startWifiTimer() {
        Message msg = new Message();
        msg.what = 5;
        mHandler.sendMessage(msg);
/*        wifiTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
*//*                wifiInfo = wifiManager.getConnectionInfo();
                //获得信号强度值
                level = wifiInfo.getRssi();
                //根据获得的信号强度发送信息
                if (level <= 0 && level >= -50) {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else if (level < -50 && level >= -70) {
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } else if (level < -70 && level >= -80) {
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                } else if (level < -80 && level >= -100) {
                    Message msg = new Message();
                    msg.what = 4;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 5;
                    handler.sendMessage(msg);
                }*//*
                Message msg = new Message();
                msg.what = 5;
                mHandler.sendMessage(msg);
            }
        }, 1000, 5000);*/

/*        // 使用Handler实现UI线程与Timer线程之间的信息传递,每5秒告诉UI线程获得wifiInto
        wifiHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
*//*                switch (msg.what) {
                    // 如果收到正确的消息就获取WifiInfo，改变图片并显示信号强度
                    case 1:
                        wifi_image.setImageResource(R.drawable.single4);
                        Toast.makeText(MainActivity.this,
                                "信号强度：" + level + " 信号最好", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case 2:
                        wifi_image.setImageResource(R.drawable.single3);
                        Toast.makeText(MainActivity.this,
                                "信号强度：" + level + " 信号较好", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case 3:
                        wifi_image.setImageResource(R.drawable.single2);
                        Toast.makeText(MainActivity.this,
                                "信号强度：" + level + " 信号一般", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case 4:
                        wifi_image.setImageResource(R.drawable.single1);
                        Toast.makeText(MainActivity.this,
                                "信号强度：" + level + " 信号较差", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case 5:
                        wifi_image.setImageResource(R.drawable.single0);
                        Toast.makeText(MainActivity.this,
                                "信号强度：" + level + " 无信号", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    default:
                        //以防万一
                        wifi_image.setImageResource(R.drawable.single0);
                        Toast.makeText(MainActivity.this, "无信号",
                                Toast.LENGTH_SHORT).show();
                }*//*
            }

        };*/
    }

    private class tvSearchAgainLisner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            search_result_ll.setVisibility(View.GONE);
            search_process_ll.setVisibility(View.VISIBLE);
            new MyThread().start();
        }
    }

    private class goSearchButtonLisner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            search_channel_ll.setVisibility(View.GONE);
            search_process_ll.setVisibility(View.VISIBLE);

            new MyThread().start();
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Message message = Message.obtain();
                    message.what = 1;
                    mHandler.sendMessage(message);
                }
            }, 1500);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                String infoStr = (String)msg.obj;
                search_process_ll.setVisibility(View.GONE);
                search_result_ll.setVisibility(View.VISIBLE);
            } else if (msg.what == 5) {
                startActivityForResult(new Intent(MainActivity.this, DialogActivity.class), DIALOG_RESULT);
            }
        }
    };

    private class searchChannelClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            tv_current_channel.setTextColor(getResources().getColor(R.color.textBlackColor));
            tv_search_channel.setTextColor(getResources().getColor(R.color.textGreenColor));
            current_channel_ll.setVisibility(View.GONE);
            search_process_ll.setVisibility(View.GONE);
            search_channel_ll.setVisibility(View.VISIBLE);
        }
    }

    private class currentChannelClickListner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (search_process_ll.getVisibility() == View.VISIBLE) {
                //当正在搜索频道时，当前频道不可点击
            } else {
                tv_search_channel.setTextColor(getResources().getColor(R.color.textBlackColor));
                tv_current_channel.setTextColor(getResources().getColor(R.color.textGreenColor));
                search_channel_ll.setVisibility(View.GONE);
                search_process_ll.setVisibility(View.GONE);
                search_result_ll.setVisibility(View.GONE);
                current_channel_ll.setVisibility(View.VISIBLE);
            }
        }
    }

    //GradView点击事件
    private class GradViewItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this,"哦啊，点中了哦",Toast.LENGTH_LONG).show();
        }
    }

    private class GradViewProcessItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //搜索频道的过程中不准点击对应的频道跳转到播放器
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
/*        if (requestCode == DIALOG_RESULT && requestCode == DIALOG_RESULT_CALLBACK) {
            wifiTimer.cancel();
            Log.i("cyh")
            Toast.makeText(MainActivity.this,
                    "wifitimer 取消", Toast.LENGTH_SHORT)
                    .show();
        }*/
        if (resultCode == DIALOG_RESULT_CALLBACK) {
            Log.i("cyh", "反悔了");
            if (requestCode == DIALOG_RESULT) {
                Log.i("cyh", "确实返回了");
                Toast.makeText(MainActivity.this,
                        "wifitimer 取消", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

}
