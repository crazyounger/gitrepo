package com.learner.learncode.base;

import com.learner.learncode.R;
import com.learner.learncode.app.XActivity;

/**
 * Created by liting on 2016/1/7.
 */
public class BaseActivity extends XActivity {

    @Override
    protected String getCloseWarning() {
        return getString(R.string.close_warning);
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }
}
