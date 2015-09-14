package com.shit.orderdinner.activity;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.shit.orderdinner.R;
import com.shit.orderdinner.common.CommonUtils;
import com.shit.orderdinner.common.Constants;

/**
 * Created by LUXIN on 2015/9/11.
 */
public class MainActivity extends Activity{

    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = MainActivity.this;
        setContentView(R.layout.activity_main);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
                CommonUtils.getMetaValue(activity, Constants.META_BAIDU_PUSH_API_KEY));

    }
}
