package com.learner.learncode.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.learner.learncode.R;

/**
 * Created by liting on 2016/1/8.
 */
public class HomeFragmentActivity extends BaseActivity{

    private static final String TAG = "HomeFragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttesst_home);
        if(savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment1 = new Fragment1();
            transaction.add(R.id.fragment_container, fragment1, "Fragment1");
            transaction.commit();
        }
        showLog(TAG, "onCreate");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showLog(TAG,"onAttachedToWindow");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog(TAG,"AonStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog(TAG,"onDestroy");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        showLog(TAG,"onDeachedFromWindow");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        showLog(TAG,"onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        showLog(TAG,"onSaveInstanceState");
    }


}
