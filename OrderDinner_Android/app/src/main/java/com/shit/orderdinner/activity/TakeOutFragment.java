package com.shit.orderdinner.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shit.orderdinner.R;
import com.shit.orderdinner.adapter.BannerAdapter;

import java.util.ArrayList;

/**
 * Created by LUXIN on 2015/9/17.
 */
public class TakeOutFragment extends Fragment {

    private FragmentActivity activity;
    private ViewPager mViewPager;
    private ArrayList<ImageView> mlist;
    private LinearLayout mLinearLayout;
    // ���ͼ�ز�?
    private int[] bannerImages = { R.drawable.welcome02, R.drawable.welcome03, R.drawable.weixin_iner_icon };
    // ԲȦ��־λ
    private int pointIndex = 0;
    // �̱߳�־
    private boolean isStop = false;
    // ViewPager�������������?
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_take_out, null);
        mViewPager = (ViewPager) contentView.findViewById(R.id.ad);
        mLinearLayout = (LinearLayout) contentView.findViewById(R.id.points);

//        LayoutInflater inflater2 = LayoutInflater.from(activity);
//        ImageView view1 = (ImageView) inflater2.inflate(R.layout.take_out_ad_item, null);
//        ImageView view2 = (ImageView) inflater2.inflate(R.layout.take_out_ad_item, null);
//        ImageView view3 = (ImageView) inflater2.inflate(R.layout.take_out_ad_item, null);
//        view1.setImageResource(R.drawable.welcome02);
//        view2.setImageResource(R.drawable.welcome03);
//        view3.setImageResource(R.drawable.weixin_iner_icon);
//        ArrayList<ImageView> views = new ArrayList<ImageView>();
//        views.add(view1);
//        views.add(view2);
//        views.add(view3);
//        viewPager.setAdapter(new BannerAdapter(views));

        initTime();
        initData();
        initAction();
        return contentView;
    }

    private void initTime() {
        // �������̣߳�2��һ�θ���Banner
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(2000);
                    activity.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();
    }

    private void initAction() {
        bannerListener = new BannerListener();
        mViewPager.setOnPageChangeListener(bannerListener);
        //ȡ�м�������Ϊ��ʼλ��
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % mlist.size());
        //��������������
        mViewPager.setCurrentItem(index);
        mLinearLayout.getChildAt(pointIndex).setEnabled(true);
    }


    private void initData() {
        mlist = new ArrayList<ImageView>();
        View view;
        ViewGroup.LayoutParams params;
        for (int i = 0; i < bannerImages.length; i++) {
            // ���ù���?
            ImageView imageView = new ImageView(activity);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(bannerImages[i]);
            mlist.add(imageView);
            // ����ԲȦ��
            view = new View(activity);
            params = new ViewGroup.LayoutParams(5, 5);
//            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.point_selecte);
            view.setLayoutParams(params);
            view.setEnabled(false);

            mLinearLayout.addView(view);
        }
        mAdapter = new BannerAdapter(mlist);
        mViewPager.setAdapter(mAdapter);
    }

    //ʵ��VierPager�������ӿ�
    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % bannerImages.length;
//            mTextView.setText(bannerTexts[newPosition]);
            mLinearLayout.getChildAt(newPosition).setEnabled(true);
            mLinearLayout.getChildAt(pointIndex).setEnabled(false);
            // ���±�־λ
            pointIndex = newPosition;

        }

    }

    @Override
    public void onDestroy() {
        // �رն�ʱ��
        isStop = true;
        super.onDestroy();
    }
}
