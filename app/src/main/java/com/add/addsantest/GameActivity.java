package com.add.addsantest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.add.addsantest.view.GameView;

/**
 * 作者：created by pc
 * 时间：created by 2019/1/29 16:09
 */
public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = findViewById(R.id.gameview);
    }
}
