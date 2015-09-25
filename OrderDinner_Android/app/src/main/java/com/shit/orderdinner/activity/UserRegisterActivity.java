package com.shit.orderdinner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shit.orderdinner.R;
import com.shit.orderdinner.common.Constants;

/**
 * Created by LUXIN on 2015/9/25.
 */
public class UserRegisterActivity extends Activity{

    // 上下文
    private UserRegisterActivity activity;

    // 控件
    // 返回键
    private ImageView img_return;
    // 输入手机号
    private EditText edit_user_tel;
    // 输入邀请码
    private EditText edit_invite_code;
    // 文本清除1
    private ImageView img_text_clear1;
    // 文本清除2
    private ImageView img_text_clear2;
    // 邀请码帮助
    private ImageView img_help;
    // 注册下一步
    private Button btn_register_next_step;
    // 服务协议
    private LinearLayout layout_service_agreemnet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = UserRegisterActivity.this;
        setContentView(R.layout.activity_user_register);

        // 初始化组件
        initView();
        // 设置监听
        setListeners();
    }

    private void setListeners() {
        // 返回到前一个画面
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置手机号输入框
        edit_user_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length == 0) {
                    img_text_clear1.setVisibility(View.INVISIBLE);
                } else {
                    img_text_clear1.setVisibility(View.VISIBLE);
                }
                btn_register_next_step.setEnabled((length == 11));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 设置邀请码输入框
        edit_invite_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    img_text_clear2.setVisibility(View.INVISIBLE);
                } else {
                    img_text_clear2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 设置文本清除
        img_text_clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_user_tel.setText("");
            }
        });
        img_text_clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_invite_code.setText("");
            }
        });

        // 邀请码说明帮助
        img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 设置注册下一步
        btn_register_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO 验证手机号是否已注册

                // 如果该手机号已注册，弹出对话框

                // 如果该手机号尚未注册，请求发送手机验证码，跳转到注册码验证画面
                Intent intent = new Intent(activity, IdentifyingCodeActivity.class);
                intent.putExtra(Constants.INTENT_USER_TEL, edit_user_tel.getText().toString());
                startActivity(intent);
            }
        });

        // 设置服务协议
        layout_service_agreemnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        img_return = (ImageView) findViewById(R.id.img_return);
        edit_user_tel = (EditText) findViewById(R.id.edit_user_tel);
        edit_invite_code = (EditText) findViewById(R.id.edit_invite_code);
        img_text_clear1 = (ImageView) findViewById(R.id.img_text_clear1);
        img_text_clear2 = (ImageView) findViewById(R.id.img_text_clear2);
        img_help = (ImageView) findViewById(R.id.img_help);
        btn_register_next_step = (Button) findViewById(R.id.btn_register_next_step);
        layout_service_agreemnet = (LinearLayout) findViewById(R.id.layout_service_agreemnet);
    }
}
