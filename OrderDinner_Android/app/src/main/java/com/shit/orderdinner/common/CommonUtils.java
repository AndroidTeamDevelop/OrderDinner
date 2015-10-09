package com.shit.orderdinner.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by LUXIN on 2015/9/14.
 */
public class CommonUtils {

    /** 根据key获取meta data*/
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String metaValue = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != applicationInfo) {
                metaData = applicationInfo.metaData;
            }
            if (null != metaData) {
                metaValue = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return metaValue;
    }

    /** 根据id获取图片*/
    public static Bitmap getBitmapById(Context context, int resId) {
        ImageLoader loader = ImageLoader.getInstance();
        Bitmap bmp = loader.getBitmapFromMemoryCache("" + resId);
        if(bmp == null) {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inJustDecodeBounds = false;
            InputStream is = context.getResources().openRawResource(resId);
            bmp = BitmapFactory.decodeStream(is, null, opt);
            loader.addBitmapToMemoryCache("" + resId, bmp);
        }
        return bmp;
    }

    /** 执行Post请求*/
    public static void doPostRequest(Context context, String url, Response.Listener<JSONObject> listener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(new JsonObjectRequest(Request.Method.POST, url,listener, null));
        requestQueue.start();
    }
}
