package com.add.addsantest.pick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.add.addsantest.R;

import java.util.List;

/**
 * 查看大图
 * 作者：created by pc
 * 时间：created by 2019/1/7 16:04
 */
public class PicLookActivity extends AppCompatActivity {
    PhotoViewPager image_piclook;

    private MyDetailImageAdapter imageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piclook);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        image_piclook = findViewById(R.id.image_piclook);
//        List<String> stringslist = getIntent().getStringArrayListExtra("picimages");
        int sposition = getIntent().getIntExtra("position", 0);
        imageAdapter = new MyDetailImageAdapter(sposition, this);
        image_piclook.setAdapter(imageAdapter);
        image_piclook.setCurrentItem(sposition, false);
        System.out.print("1");
    }
}
