package com.shit.orderdinner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.shit.orderdinner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LUXIN on 2015/9/11.
 */
public class MainActivity extends FragmentActivity{

    // 上下文
    private FragmentActivity activity;

    // 控件
    private ViewPager viewPager;
    private FragmentTabHost tabhost;
    private LinearLayout layout_titlebar;

    // 变量
    // 上次按返回按钮的时间
    private long lastBackTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = MainActivity.this;
        setContentView(R.layout.activity_main);

        init();
        intTabs();
        initViewPager();
    }

    private void init() {
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        layout_titlebar = (LinearLayout) findViewById(R.id.layout_titlebar);
    }

    private void intTabs() {
        int[] tabImgIds = {R.drawable.tab_take_out, R.drawable.tab_orders, R.drawable.tab_found, R.drawable.tab_personal};
        int[] tabTextIds = {R.string.tab_take_out, R.string.tab_orders, R.string.tab_found, R.string.tab_personal};
        Class<?>[] fragments = {TakeOutFragment.class, OrdersFragment.class, FoundFragment.class, PersonalFragment.class};

        tabhost.setup(activity, activity.getSupportFragmentManager(), R.id.viewPager);
        for(int i = 0; i <4; i++) {
            TabHost.TabSpec spec = tabhost.newTabSpec("tab" + i);
            // tab标签
            View tab_widget_view = View.inflate(this, R.layout.tab_widget, null);
            ImageView img_tab_widget = (ImageView) tab_widget_view.findViewById(R.id.img_tab_widget);
            TextView text_tab_widget = (TextView) tab_widget_view.findViewById(R.id.text_tab_widget);
            img_tab_widget.setImageResource(tabImgIds[i]);
            text_tab_widget.setText(getResources().getString(tabTextIds[i]));
            spec.setIndicator(tab_widget_view);

            // tab 内容
            tabhost.addTab(spec, fragments[i], null);
        }
        tabhost.getTabWidget().setShowDividers(0);

        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position = tabhost.getCurrentTab();
                viewPager.setCurrentItem(position);
                switchTitle(position);
            }
        });

    }

    private void switchTitle(int position) {
        layout_titlebar.removeAllViews();
        switch (position % 4) {
            case 0:
                break;
            case 1:
                layout_titlebar.addView(View.inflate(activity, R.layout.title_bar_orders, null));
                break;
            case 2:
                layout_titlebar.addView(View.inflate(activity, R.layout.title_bar_found, null));
                break;
            case 3:
                layout_titlebar.addView(View.inflate(activity, R.layout.title_bar_personal, null));
                break;
        }
    }

    private void initViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabWidget widget = tabhost.getTabWidget();
                int oldFocusability = widget.getDescendantFocusability();
                widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                tabhost.setCurrentTab(position);
                widget.setDescendantFocusability(oldFocusability);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(new OrderDinnerFragmentsAdapter(activity.getSupportFragmentManager()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if((currentTime - lastBackTime) > 2000) {
                lastBackTime = currentTime;
                Toast.makeText(activity, R.string.msg_exit, Toast.LENGTH_SHORT).show();
            } else {
                // 退出程序
                finish();
            }
        }
        return false;
    }

    private class OrderDinnerFragmentsAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<Fragment>();

        public OrderDinnerFragmentsAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new TakeOutFragment());
            fragments.add(new OrdersFragment());
            fragments.add(new FoundFragment());
            fragments.add(new PersonalFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
