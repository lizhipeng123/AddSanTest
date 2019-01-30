package com.add.addsantest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

/**
 * 首页
 * 作者：created by pc
 * 时间：created by 2019/1/29 15:06
 */
public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cardview_start, cardview_play;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        cardview_start = findViewById(R.id.cardview_start);
        cardview_play = findViewById(R.id.cardview_play);
        cardview_start.setOnClickListener(this);
        cardview_play.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardview_start://加减法开始
                Intent intentStart = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intentStart);
                break;
            case R.id.cardview_play://小游戏
                Intent intentPlay = new Intent(StartActivity.this,GameActivity.class);
                startActivity(intentPlay);
//                Toast.makeText(StartActivity.this, "稍后添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
