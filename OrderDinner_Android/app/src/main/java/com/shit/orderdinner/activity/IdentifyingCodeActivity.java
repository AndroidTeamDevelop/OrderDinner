package com.shit.orderdinner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shit.orderdinner.R;
import com.shit.orderdinner.common.Constants;
import com.shit.orderdinner.common.CountDownClock;

/**
 * Created by LUXIN on 2015/9/25.
 */
public class IdentifyingCodeActivity extends Activity{

    // 上下文
    private IdentifyingCodeActivity activity;

    // 控件
    // 返回键
    private ImageView img_return;
    // 显示用户手机号
    private TextView text_user_tel;
    // 验证码输入框
    private EditText edit_indentifying_code;
    // 文本清除
    private ImageView img_text_clear;
    // 获取验证码
    private Button btn_get_identifying_code;
    // 完成注册
    private Button btn_register_complete;
    // 语音验证
    private LinearLayout layout_voice_indentifying;

    // 变量
    // 倒计时器
    private CountDownClock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = IdentifyingCodeActivity.this;
        setContentView(R.layout.activity_identifying_code);

        // 初始化组件
        initView();
        // 开始计时
        startCountDown();
        // 设置监听
        setListeners();

    }

    private void startCountDown() {
        btn_get_identifying_code.setEnabled(false);
        clock = new CountDownClock(30000, 1000);
        clock.setCountDownListener(new CountDownClock.CountDownListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_get_identifying_code.setText(String.format(getResources().getString(R.string.havesend_identifying_code), (int)(millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                btn_get_identifying_code.setEnabled(true);
                btn_get_identifying_code.setText(getResources().getString(R.string.reget_identifying_code));
                clock.cancel();
            }
        });
        clock.start();
    }

    private void setListeners() {
        // 返回到前一个画面
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置输入框
        edit_indentifying_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if(length == 0) {
                    img_text_clear.setVisibility(View.INVISIBLE);
                    btn_register_complete.setEnabled(false);
                } else {
                    img_text_clear.setVisibility(View.VISIBLE);
                    btn_register_complete.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 设置文本清除
        img_text_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_indentifying_code.setText("");
            }
        });

        // 获取验证码
        btn_get_identifying_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 请求获取验证码
                startCountDown();
            }
        });

        // 完成注册
        btn_register_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 验证注册码
            }
        });

        // 语音验证
        layout_voice_indentifying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 语音验证
            }
        });
    }

    private void initView() {
        img_return = (ImageView) findViewById(R.id.img_return);
        text_user_tel = (TextView) findViewById(R.id.text_user_tel);
        edit_indentifying_code = (EditText) findViewById(R.id.edit_indentifying_code);
        img_text_clear = (ImageView) findViewById(R.id.img_text_clear);
        btn_get_identifying_code = (Button) findViewById(R.id.btn_get_identifying_code);
        btn_register_complete = (Button) findViewById(R.id.btn_register_complete);
        layout_voice_indentifying = (LinearLayout) findViewById(R.id.layout_voice_indentifying);

        Intent intent = getIntent();
        text_user_tel.setText(intent.getStringExtra(Constants.INTENT_USER_TEL));
    }

}
