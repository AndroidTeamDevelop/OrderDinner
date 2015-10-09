package com.shit.orderdinner.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/10/9.
 */
public class LoadingDialog extends Dialog{

    private static LoadingDialog dialog = null;

    private LoadingDialog(Context context) {
        super(context);
    }

    public static LoadingDialog createDialog(Context context) {
        dialog = new LoadingDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return dialog;
    }
}
