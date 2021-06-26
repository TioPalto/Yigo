package com.citys.xufan.yigotest.adapter;

import android.content.Context;
import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Context context;

    public ViewPagerAdapter(Context context, List<View> views) {
        this.views = views;
        this.context = context;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(views.get(position));

        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
