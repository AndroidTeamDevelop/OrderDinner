package com.shit.orderdinner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/9/24.
 */
public class UserLoginActivity extends Activity{

    // 上下文
    private UserLoginActivity activity;

    // 控件
    private TextView text_user_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = UserLoginActivity.this;
        setContentView(R.layout.activity_user_login);

        // 初始化组件
        initView();
        // 设置监听
        setListeners();
    }

    private void setListeners() {
        // 跳转到注册画面
        text_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UserRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        text_user_register = (TextView) findViewById(R.id.text_user_register);
    }
}
