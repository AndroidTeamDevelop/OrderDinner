package com.shit.orderdinner.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/10/9.
 */
public class LoadingDialog extends Dialog{

    private Context context;

    // 旋转图片
    private static ImageView img_loading;
    // 文字
    private static TextView text_message;
    private static LoadingDialog dialog = null;

    private LoadingDialog(Context context) {
        super(context);
        this.context = context;
    }

    public static LoadingDialog createDialog(Context context) {
        dialog = new LoadingDialog(context);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // dialog布局
        View mContentView = View.inflate(context, R.layout.layout_loading_dialog, null);
        img_loading = (ImageView) mContentView.findViewById(R.id.img_loading);
        text_message = (TextView) mContentView.findViewById(R.id.text_message);
        dialog.setContentView(mContentView);
        return dialog;
    }

    public LoadingDialog setMessage(String message) {
        text_message.setText(message);
        return dialog;
    }

    public LoadingDialog start() {
        // 显示dialog
        dialog.show();
        // 绑定动画
        Animation rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_loading_dialog_rotate);
        img_loading.startAnimation(rotateAnimation);
        return dialog;
    }

    public LoadingDialog stop() {
        // 清除动画
        img_loading.clearAnimation();
        // 关闭dialog
        dialog.cancel();
        return dialog;
    }
}
