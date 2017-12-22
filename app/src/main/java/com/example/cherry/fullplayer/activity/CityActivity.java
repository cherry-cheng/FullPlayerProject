package com.example.cherry.fullplayer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.cherry.fullplayer.R;
import com.example.cherry.fullplayer.adapter.MyAdapter;
import com.example.cherry.fullplayer.view.WaveSideBarView;

/**
 * Created by Administrator on 2017/12/15.
 */

public class CityActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private WaveSideBarView waveSideBarView;
    private Button button;
    private int number = -1;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        button = (Button) findViewById(R.id.button);
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
    }

    @Override
    public void onItemClick(int postion) {
        button.setBackgroundColor(getResources().getColor(R.color.button_color));
    }
}
