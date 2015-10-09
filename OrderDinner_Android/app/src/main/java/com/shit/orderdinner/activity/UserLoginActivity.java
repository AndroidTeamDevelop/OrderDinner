package com.shit.orderdinner.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shit.orderdinner.R;
import com.shit.orderdinner.common.Constants;
import com.shit.orderdinner.common.HttpClient;

import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by LUXIN on 2015/9/24.
 */
public class UserLoginActivity extends Activity{

    private static String TAG = UserLoginActivity.class.getSimpleName();

    // 上下文
    private UserLoginActivity activity;

    // 控件
    private TextView text_user_register;
    // 返回键
    private ImageView img_return;
    // 账号输入框
    private EditText edit_user_identity;
    // 文本清除1
    private ImageView img_text_clear1;
    // 密码输入框
    private EditText edit_user_pwd;
    // 文本清除2
    private ImageView img_text_clear2;
    // 输入模式切换
    private CheckBox check_inputmode_switch;
    // 登录按钮
    private Button btn_login;
    // 忘记密码
    private TextView text_forget_pwd;
    // 短信验证登录
    private TextView text_message_login;
    // 微信登录
    private TextView text_weixin_login;
    // QQ登录
    private TextView text_qq_login;
    // 微博登录
    private TextView text_weibo_login;

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
        // 返回到前一个画面
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 跳转到注册画面
        text_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UserRegisterActivity.class);
                startActivity(intent);
            }
        });
        // 设置账号输入框
        edit_user_identity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length == 0) {
                    img_text_clear1.setVisibility(View.INVISIBLE);
                } else {
                    img_text_clear1.setVisibility(View.VISIBLE);
                }
            }
        });
        // 输入密码
        edit_user_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length == 0) {
                    img_text_clear2.setVisibility(View.INVISIBLE);
                } else {
                    img_text_clear2.setVisibility(View.VISIBLE);
                }
            }
        });
        // 设置文字清除
        img_text_clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_text_clear1.setVisibility(View.INVISIBLE);
                edit_user_identity.setText("");
            }
        });
        img_text_clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_text_clear2.setVisibility(View.INVISIBLE);
                edit_user_pwd.setText("");
            }
        });
        // 设置输入模式
        check_inputmode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO 改变输入模式
            }
        });
        // 设置登录按钮
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // input check
                if(!doInputCheck()) {
                    return;
                }

                String user_identity = edit_user_identity.getText().toString();
                String user_pwd = edit_user_pwd.getText().toString();

                String url = Constants.HTTP_URL_AUTHORITY + Constants.ACTION_USER_LOGIN + "?"
                        + Constants.PARAM_USER_IDENTITY + "=" + user_identity + "&"
                        + Constants.PARAM_USER_PWD + "=" + user_pwd;

                HttpClient.init(activity);
                HttpClient.doPostRequest(url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i(TAG, "请求结果：" + jsonObject.toString());
                    }
                });
            }
        });
        // 忘记密码
        text_forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 忘记密码处理
            }
        });
        // 短信验证登录
        text_message_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 跳转短信验证登录画面
            }
        });
        // 微信登录
        text_weixin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 微信登录
            }
        });
        // 微博登录
        text_weibo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 微博登录
            }
        });
        // QQ登录
        text_qq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO QQ登录
            }
        });
    }

    private boolean doInputCheck() {
        String user_identity = edit_user_identity.getText().toString();
        // 用户账号不为空
        if("".equals(user_identity)) {
            Toast.makeText(activity, String.format(getResources().getString(R.string.msg_not_empty)
                    , getResources().getString(R.string.hint_user_identity)), Toast.LENGTH_SHORT).show();
            return false;
        }
        String user_pwd = edit_user_pwd.getText().toString();
        // 密码不为空
        if("".equals(user_pwd)) {
            Toast.makeText(activity, String.format(getResources().getString(R.string.msg_not_empty)
                    , getResources().getString(R.string.hint_user_pwd)), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        text_user_register = (TextView) findViewById(R.id.text_user_register);
        img_return = (ImageView) findViewById(R.id.img_return);
        edit_user_identity = (EditText) findViewById(R.id.edit_user_identity);
        img_text_clear1 = (ImageView) findViewById(R.id.img_text_clear1);
        edit_user_pwd = (EditText) findViewById(R.id.edit_user_pwd);
        img_text_clear2 = (ImageView) findViewById(R.id.img_text_clear2);
        check_inputmode_switch = (CheckBox) findViewById(R.id.check_inputmode_switch);
        btn_login = (Button) findViewById(R.id.btn_login);
        text_forget_pwd = (TextView) findViewById(R.id.text_forget_pwd);
        text_message_login = (TextView) findViewById(R.id.text_message_login);
        text_weixin_login = (TextView) findViewById(R.id.text_weixin_login);
        text_qq_login = (TextView) findViewById(R.id.text_qq_login);
        text_weibo_login = (TextView) findViewById(R.id.text_weibo_login);
    }
}
