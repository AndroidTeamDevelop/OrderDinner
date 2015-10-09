package com.shit.orderdinner.common;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by LUXIN on 2015/10/8.
 */
public class HttpClient {

    private static RequestQueue requestQueue;

    private HttpClient() {

    }

    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 执行post请求
     * @param url 接口地址
     * @param listener 请求回调
     */
    public static void doPostRequest(String url, Response.Listener<JSONObject> listener) {
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, url, listener, null));
        requestQueue.start();
    }
}
