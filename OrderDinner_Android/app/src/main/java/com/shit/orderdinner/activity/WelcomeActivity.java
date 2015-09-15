package com.shit.orderdinner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/9/15.
 */
public class WelcomeActivity extends Activity{

    private WelcomeActivity activity;

    private ImageView img_splash_text;
    private ImageView img_splash_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = WelcomeActivity.this;
        setContentView(R.layout.activity_welcome);

        initViews();
        showViews();
    }

    private void showViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img_splash_text.setVisibility(View.VISIBLE);
                img_splash_logo.setVisibility(View.VISIBLE);
                // 执行App必要的初始化操作
                initApp();
            }
        }, 2000);
    }

    private void initApp() {
        // TODO 执行的操作待补充
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

    private void initViews() {
        img_splash_text = (ImageView) findViewById(R.id.img_splash_text);
        img_splash_logo = (ImageView) findViewById(R.id.img_splash_logo);
    }
}
