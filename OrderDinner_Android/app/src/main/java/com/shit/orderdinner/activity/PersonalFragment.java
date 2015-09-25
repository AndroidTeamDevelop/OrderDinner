package com.shit.orderdinner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/9/17.
 */
public class PersonalFragment extends Fragment{

    private FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_personal, null);
        // 设置下拉刷新
        setPullToRefresh(contentView);
        // 初始化组件
        initView(contentView);
        return contentView;
    }

    private void initView(View contentView) {
        RelativeLayout layout_userinfo = (RelativeLayout) contentView.findViewById(R.id.layout_userinfo);
        layout_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setPullToRefresh(View contentView) {
        final PullToRefreshScrollView pull_to_refresh = (PullToRefreshScrollView) contentView.findViewById(R.id.pull_to_refresh);
        // 设置此属性，否则ScrollView中的LinearrLayout不能占满屏幕
        pull_to_refresh.getRefreshableView().setFillViewport(true);
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                // TODO 执行刷新操作
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull_to_refresh.onRefreshComplete();
                    }
                }, 2000);
            }
        });
    }
}
