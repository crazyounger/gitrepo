package com.learner.learncode.morphingbutton;

import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.v4.app.FragmentActivity;

/**
 * Created by liting on 2015/12/31.
 */
public class BaseActivity extends FragmentActivity{

    public int dimen(@DimenRes int resId){
        return (int)getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId){
        return getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId){
        return getResources().getInteger(resId);
    }

}
