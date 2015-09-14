package com.shit.orderdinner.activity;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/9/11.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "fziB22jDYG9kyt87T6yYNwLB");

    }
}
