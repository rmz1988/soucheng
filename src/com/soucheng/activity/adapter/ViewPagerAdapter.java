package com.soucheng.activity.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * ViewPager适配器
 *
 * @author lichen
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> viewList;

    public ViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public int getCount() {
        return viewList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

}
