package com.bignerdranch.android.shoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.bignerdranch.android.shoppingmall.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒钟进入主页面
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                //执行在主线程
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }
        },2000);
    }
}
