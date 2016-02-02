package com.learner.learncode.Test;

import android.os.Bundle;

import com.learner.learncode.R;
import com.learner.learncode.base.BaseActivity;

/**
 * Created by liting on 2016/1/7.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubetest_home);
        pushFragmentToBackstack(TestCubeFragment1.class,null);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragments ;
    }
}
