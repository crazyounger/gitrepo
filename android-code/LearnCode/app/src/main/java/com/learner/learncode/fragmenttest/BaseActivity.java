package com.learner.learncode.fragmenttest;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.learner.learncode.util.Constants;

/**
 * Created by liting on 2016/1/8.
 */
public class BaseActivity extends FragmentActivity {



    public void showLog(String tag ,String msg){
        if(Constants.DEBUG){
            Log.d(tag, String.format("------------%s----------",msg));
        }
    }


}
