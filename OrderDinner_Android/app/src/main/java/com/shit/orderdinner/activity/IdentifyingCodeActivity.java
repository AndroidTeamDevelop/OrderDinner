package com.shit.orderdinner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

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
    private Handler handler;
    private EventHandler eventHandler;
    private String user_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = IdentifyingCodeActivity.this;
        setContentView(R.layout.activity_identifying_code);

        // 初始化组件
        initView();
        // 初始化短信验证
        initSMS();
        // 获取短信验证
        getVerificationCode();
        // 设置监听
        setListeners();

    }

    private void getVerificationCode() {
        btn_get_identifying_code.setEnabled(false);
        btn_get_identifying_code.setText(getResources().getString(R.string.getting_identifying_code));
        SMSSDK.getVerificationCode("86", user_tel);
    }

    private void initSMS() {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                if(result == SMSSDK.RESULT_COMPLETE) {
                    switch (event) {
                        case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                            startCountDown();
                            break;
                        case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                            // TODO 验证成功，跳转到后续画面
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        eventHandler = new EventHandler() {
            /**
             * 执行SMS操作后回调
             * @param event 操作类型
             * @param result SMSSDK.RESULT_COMPLETE表示操作成功，SMSSDK.RESULT_ERROR表示操作失败
             * @param data 事件操作的结果
             */
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = handler.obtainMessage();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void startCountDown() {
        btn_get_identifying_code.setEnabled(false);
        clock = new CountDownClock(30000, 1000);
        clock.setCountDownListener(new CountDownClock.CountDownListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_get_identifying_code.setText(String.format(getResources().getString(
                        R.string.havesend_identifying_code), (int)(millisUntilFinished/1000)));
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
                getVerificationCode();
            }
        });

        // 完成注册
        btn_register_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SMSSDK.submitVerificationCode("86", user_tel, edit_indentifying_code.getText().toString());
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
        user_tel = intent.getStringExtra(Constants.INTENT_USER_TEL);
        text_user_tel.setText(user_tel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
