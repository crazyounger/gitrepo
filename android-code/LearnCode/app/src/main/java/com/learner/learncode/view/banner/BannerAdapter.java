package com.learner.learncode.view.banner;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liting on 2016/1/11.
 */
public abstract class BannerAdapter extends PagerAdapter{


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(LayoutInflater.from(container.getContext()),position);
        container.addView(view);
        return view ;
    }

    public int getPositionForIndicator(int position){
        return position ;
    }

    protected abstract View getView(LayoutInflater inflater, int position);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
