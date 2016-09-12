package com.wisplu.wisplu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

public class welcome extends Activity {
    private ImageView im_welcome;
    private AnimationDrawable animationDrawable;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:
                    animationDrawable.start();
                    break;
                case 2:
                    Intent intent = new Intent(welcome.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                default:
                    break;
            }

        }

        ;

    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        im_welcome = (ImageView) findViewById(R.id.welcom);
        im_welcome.setBackgroundResource(R.drawable.welcome);

        animationDrawable = (AnimationDrawable) im_welcome.getBackground();

        // 一開始執行動畫 handler延遲執行動畫
        handler.sendEmptyMessageDelayed(1, 100);
        //延遲7秒 结束跳转到MainActivity
        handler.sendEmptyMessageDelayed(2, 7000);



    }
}
