package com.add.addsantest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.add.addsantest.pick.PicLookActivity;

/**
 * 作者：created by pc
 * 时间：created by 2019/1/28 16:49
 */
public class InstructionsActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ImageView imageDefault = findViewById(R.id.image_default);
        ImageView imageSelect = findViewById(R.id.image_select);
        ImageView imageError = findViewById(R.id.image_error);
        ImageView imageCorrect = findViewById(R.id.image_correct);
        imageDefault.setOnClickListener(this);
        imageSelect.setOnClickListener(this);
        imageError.setOnClickListener(this);
        imageCorrect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_default:
                Intent intent = new Intent(InstructionsActivity.this,PicLookActivity.class);
                intent.putExtra("position",0);
                startActivity(intent);
                break;
            case R.id.image_select:
                Intent intent1 = new Intent(InstructionsActivity.this,PicLookActivity.class);
                intent1.putExtra("position",1);
                startActivity(intent1);
                break;
            case R.id.image_error:
                Intent intent2 = new Intent(InstructionsActivity.this,PicLookActivity.class);
                intent2.putExtra("position",2);
                startActivity(intent2);
                break;
            case R.id.image_correct:
                Intent intent3 = new Intent(InstructionsActivity.this,PicLookActivity.class);
                intent3.putExtra("position",3);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
