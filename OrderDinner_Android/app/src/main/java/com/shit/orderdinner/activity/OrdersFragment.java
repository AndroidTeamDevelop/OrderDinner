package com.shit.orderdinner.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shit.orderdinner.R;

/**
 * Created by LUXIN on 2015/9/17.
 */
public class OrdersFragment extends Fragment{

    private FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_orders, null);
        // 设置下拉刷新
        setPullToRefresh(contentView);
        // 初始化组件
        initView(contentView);
        return contentView;
    }

    private void initView(View contentView) {

    }

    private void setPullToRefresh(View contentView) {
        final PullToRefreshListView pull_to_refresh  = (PullToRefreshListView) contentView.findViewById(R.id.pull_to_refresh);
        pull_to_refresh.getRefreshableView().setEmptyView(View.inflate(activity, R.layout.layout_listview_empty, null));
        pull_to_refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
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
