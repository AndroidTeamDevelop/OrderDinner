package com.shit.orderdinner.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by EX-BAIXUE600 on 2015/9/18.
 */
public class BannerAdapter extends PagerAdapter {
    private ArrayList<ImageView> viewlist;

    public BannerAdapter(ArrayList<ImageView> viewlist) {
        this.viewlist = viewlist;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewlist.get(position%viewlist.size()));
        return viewlist.get(position%viewlist.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewlist.get(position%viewlist.size()));
    }
}
