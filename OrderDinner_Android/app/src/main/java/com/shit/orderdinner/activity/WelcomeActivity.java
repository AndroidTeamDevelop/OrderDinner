package com.shit.orderdinner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.shit.orderdinner.R;
import com.shit.orderdinner.common.CommonUtils;
import com.shit.orderdinner.common.Constants;

import cn.smssdk.SMSSDK;

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
                // 执行启动App的必要初始化操作
                initApp();
            }
        }, 2000);
    }

    private void initApp() {
        // TODO 执行启动App的必要初始化操作
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
                CommonUtils.getMetaValue(activity, Constants.META_BAIDU_PUSH_API_KEY));
        SMSSDK.initSDK(activity, CommonUtils.getMetaValue(activity, Constants.META_MOB_SMS_APP_KEY)
                , CommonUtils.getMetaValue(activity, Constants.META_MOB_SMS_APP_SECRET));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void initViews() {
        img_splash_text = (ImageView) findViewById(R.id.img_splash_text);
        img_splash_logo = (ImageView) findViewById(R.id.img_splash_logo);
    }
}
