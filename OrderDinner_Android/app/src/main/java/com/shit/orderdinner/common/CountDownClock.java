package com.shit.orderdinner.common;

import android.os.CountDownTimer;

/**
 * Created by LUXIN on 2015/9/25.
 */
public class CountDownClock extends CountDownTimer{

    public interface CountDownListener {
        /**
         * 倒计时器每经过一个计时间隔调用此方法
         * @param millisUntilFinished 倒计时器的当前时间，单位毫秒
         */
        public void onTick(long millisUntilFinished);

        /**
         * 倒计时器结束时调用此方法
         */
        public void onFinish();
    }

    private CountDownListener countDownListener;

    /**
     * @param millisInFuture 表示给倒计时器设置的总时间，单位毫秒
     * @param countDownInterval 表示给倒计时设置的计时间隔，单位毫秒
     */
    public CountDownClock(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public CountDownListener getCountDownListener() {
        return countDownListener;
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.countDownListener = countDownListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(countDownListener != null) {
            countDownListener.onTick(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if(countDownListener != null) {
            countDownListener.onFinish();
        }
    }
}
