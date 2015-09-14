package com.shit.orderdinner.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by LUXIN on 2015/9/14.
 */
public class CommonUtils {

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
}
