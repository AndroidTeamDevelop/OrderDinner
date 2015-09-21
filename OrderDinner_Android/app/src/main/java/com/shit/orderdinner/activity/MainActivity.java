package com.shit.orderdinner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.shit.orderdinner.R;
import com.shit.orderdinner.common.CommonUtils;

/**
 * Created by LUXIN on 2015/9/11.
 */
public class MainActivity extends FragmentActivity{

    private FragmentActivity activity;

    private FragmentTabHost tabhost;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment[] framents;
    private int[] tabImgIds;
    private int[] tabImgSelectedIds;
    private int[] tabTextIds;

    private int lastTab = 0;
    private int currentTab = 0;
    // 上次按返回键的时间
    private long lastBackTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = MainActivity.this;
        setContentView(R.layout.activity_main);

        init();
        intTabs();
    }

    private void init() {
        fm = activity.getSupportFragmentManager();
        framents = new Fragment[4];
        framents[0] = new TakeOutFragment();
        framents[1] = new OrdersFragment();
        framents[2] = new FoundFragment();
        framents[3] = new PersonalFragment();
        tabImgIds = new int[]{R.drawable.shopping_home_tab_take_out, R.drawable.shopping_home_tab_order, R.drawable.shopping_home_tab_found, R.drawable.shopping_home_tab_personal};
        tabImgSelectedIds = new int[]{R.drawable.shopping_home_tab_take_out_selected, R.drawable.shopping_home_tab_order_selected,
                R.drawable.shopping_home_tab_found_selected, R.drawable.shopping_home_tab_personal_selected};
        tabTextIds = new int[]{R.string.tab_take_out, R.string.tab_orders, R.string.tab_found, R.string.tab_personal};
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }

    private void intTabs() {
        tabhost.setup(activity, fm, R.id.realtabcontent);
        for(int i = 0; i <4; i++) {
            TabHost.TabSpec spec = tabhost.newTabSpec("tab" + i);
            // tab标签
            View tab_widget_view = View.inflate(this, R.layout.tab_widget, null);
            ImageView img_tab_widget = (ImageView) tab_widget_view.findViewById(R.id.img_tab_widget);
            TextView text_tab_widget = (TextView) tab_widget_view
                    .findViewById(R.id.text_tab_widget);
            img_tab_widget.setImageBitmap(CommonUtils.getBitmapById(activity, tabImgIds[i]));
            text_tab_widget.setText(getResources().getString(tabTextIds[i]));
            spec.setIndicator(tab_widget_view);
            // 添加tab
            tabhost.addTab(spec, framents[i].getClass(), null);
        }

        // tab切换监听
        tabhost.setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                // 获得当前选中的tab
                currentTab = tabhost.getCurrentTab();
                if(lastTab == currentTab) {
                    return;
                }
                lastTab = currentTab;
                // 更改tab显示
                for(int i = 0; i < 4; i++) {
                    if(i == currentTab){
                        updateTabWidget(i, true);
                    }else{
                        updateTabWidget(i, false);
                    }
                }
                updateView();
            }
        });

        // 默认选中第一个tab
        tabhost.setCurrentTab(0);
        // 更改第一个tabWidget的显示
        updateTabWidget(0, true);

    }

    private void updateView() {
        ft = fm.beginTransaction();
        Fragment fragment = framents[currentTab];
        ft.replace(R.id.realtabcontent, fragment);
        ft.commit();
    }

    private void updateTabWidget(int position, boolean selected) {
        View tab_widget_view = tabhost.getTabWidget().getChildAt(position);
        ImageView img_tab_widget = (ImageView) tab_widget_view.findViewById(R.id.img_tab_widget);
        TextView text_tab_widget = (TextView) tab_widget_view.findViewById(R.id.text_tab_widget);
        if(selected) {
            img_tab_widget.setImageBitmap(CommonUtils.getBitmapById(activity, tabImgSelectedIds[position]));
            text_tab_widget.setTextColor(getResources().getColor(R.color.text_tab_widget_color_selected));
        } else {
            img_tab_widget.setImageBitmap(CommonUtils.getBitmapById(activity, tabImgIds[position]));
            text_tab_widget.setTextColor(getResources().getColor(R.color.text_tab_widget_color));
        }
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
}
