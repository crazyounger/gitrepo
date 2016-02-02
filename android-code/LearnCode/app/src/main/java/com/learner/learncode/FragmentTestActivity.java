package com.learner.learncode;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.learner.learncode.fragment.Fragment1;

/**
 * Created by liting on 2015/12/18.
 */
public class FragmentTestActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment fragment;
        FragmentManager manager = getSupportFragmentManager() ;
        fragment = manager.findFragmentByTag("fragment1") ;
        if( fragment !=null){
            manager.beginTransaction().show(fragment).commit();
        }else{
            fragment = new Fragment1();
            manager.beginTransaction().add(android.R.id.content,fragment,"fragment1").commit();
        }

    }
}
