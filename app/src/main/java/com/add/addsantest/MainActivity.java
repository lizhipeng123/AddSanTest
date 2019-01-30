package com.add.addsantest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Called when the activity is first created.
     */
    Button button01;
    TextView textview01;
    EditText EditText01;

    private static int op1;
    private static int op2;
    int op3;

    public static final int SOUND_EXPLOSION = 1;
    public static final int SOUND_ZHENGQUE = 2;  //zhengque =正确
    public static final int SOUND_ERROR = 2;  //错误
    private SoundPool soundPool;
    private HashMap soundPoolMap;
    private String[] strArray = new String[]{"+", "-"};
    private String strRandom;

    public void onClick(View vABB) {
        if (vABB == button01) {
            //button01.setText("vABB=button01");
            this.setTitle("加油，小朋友！");
            textview01.setText("vABB=button01");
            EditText01.setBackgroundColor(Color.WHITE);
            EditText01.setText("?");
            strRandom = getRandom(strArray);
            if (strRandom.equals("+")) {
                op3 = getRandomOp1Op2(0, 10);
            } else if (strRandom.equals("-")) {
                op3 = getRandomSub(0, 10);
            }
            //EditText01.
            textview01.setText(" " + op1 + strRandom + op2 + " = ");
            explode();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initSounds();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        //button01.setOnClickListener(this);
        button01 = new Button(this);
        button01 = (Button) findViewById(R.id.Button01);
        button01.setOnClickListener(this);

        textview01 = new TextView(this);
        textview01 = (TextView) findViewById(R.id.textview01);

        EditText01 = new EditText(this);
        EditText01 = (EditText) findViewById(R.id.EditText01);

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = -20; i <= 40; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.ic_launcher_foreground);//添加图像资源的ID
            map.put("ItemText", "" + String.valueOf(i));//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释
                lstImageItem,//数据来源
                R.layout.night_item,//night_item的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemText});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener((AdapterView.OnItemClickListener) new ItemClickListener());
    }

    //当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
    class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3//The row id of the item that was clicked
        ) {
            //在本例中arg2=arg3
            HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
            //显示所选Item的ItemText
            //setTitle((String)item.get("ItemText"));
            {
                String str_op3_FAQ = (String) item.get("ItemText");
                int op3_FAQ = Integer.parseInt(str_op3_FAQ);
                EditText01.setText(str_op3_FAQ);
                if (op3_FAQ == op3) {
                    //setTitle("OK");
                    playSound(SOUND_ZHENGQUE);
                    EditText01.setBackgroundColor(Color.GREEN);
                    Toast.makeText(MainActivity.this, "回答正确", Toast.LENGTH_SHORT).show();
                } else {
                    //setTitle("Error");
//                    playSound(SOUND_ERROR);
                    EditText01.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "回答错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.navigation_home://使用帮助
                Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home://返回
                this.finish();
                break;
        }
        return true;
    }

    /**
     * 加法
     *
     * @param start
     * @param end
     * @return
     */
    public int getRandomOp1Op2(int start, int end) {
        int i = 0;
        while (i < 20) {
            op1 = getRandom(0, 20);
            op2 = getRandom(0, 20);
            if (op1 + op2 <= 20 && op1 + op2 >= 10) break;
            i++;
        }
        return (op1 + op2);
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public int getRandomSub(double value1, double value2) {
        int i = 0;
        while (i < 20) {
            op1 = getRandom(0, 20);
            op2 = getRandom(0, 20);
            if (op1 - op2 <= -20 && op1 - op2 >= -10) break;
            i++;
        }
        return (op1 - op2);
    }

    public static int getRandom(int start, int end) {
        if (start > end || start < 0 || end < 0) {
            return -1;
        }
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    /**
     * 符号随机
     *
     * @param numArray
     * @return
     */
    private static String getRandom(String[] numArray) {
        int randow_inde = (int) (Math.random() * numArray.length);
        return numArray[randow_inde];
    }

    private void initSounds() {
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap();
        soundPoolMap.put(SOUND_EXPLOSION, soundPool.load(getBaseContext(), R.raw.boru, 1));
        soundPoolMap.put(SOUND_ZHENGQUE, soundPool.load(getBaseContext(), R.raw.zhengque, 1));
    }// 只支持wav声音格式的文件

    public void playSound(int sound) {

        AudioManager mgr = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeCurrent / streamVolumeMax;
        soundPool.play((Integer) soundPoolMap.get(sound), 1, 1, 1, 0, 1f);
    }

    public void explode() {
        playSound(SOUND_EXPLOSION);
    }
}