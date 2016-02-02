package com.learner.learncode.app;

import android.os.Bundle;
import android.util.Log;

import com.learner.learncode.app.lifecycle.IComponentContainer;
import com.learner.learncode.app.lifecycle.LifeCycleComponent;
import com.learner.learncode.app.lifecycle.LifeCycleComponentManager;
import com.learner.learncode.util.Constants;

/**
 * Created by liting on 2016/1/7.
 *
 * manager the components when move from a lifetime to another
 */
public abstract  class XActivity extends  CubeFragmentActivity implements IComponentContainer {

    private LifeCycleComponentManager mComponentContainer = new LifeCycleComponentManager();

    private static final boolean DEBUG = Constants.DEBUG;

    @Override
    protected void onRestart() {
        super.onRestart();
        mComponentContainer.onBecomesVisibleFromTotallyInvisible();
        if (DEBUG) {
            showStatus("onRestart");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mComponentContainer.onBecomesPartiallyInvisible();
        if (DEBUG) {
            showStatus("onPause");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mComponentContainer.onBecomesVisibleFromTotallyInvisible();
        if (DEBUG) {
            showStatus("onResume");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            showStatus("onCreate");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mComponentContainer.onBecomesTotallyInvisible();
        if (DEBUG) {
            showStatus("onStop");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mComponentContainer.onDestroy();
        if (DEBUG) {
            showStatus("onDestroy");
        }
    }

    @Override
    public void addLifeCycleComponent (LifeCycleComponent component) {
        mComponentContainer.addLifeCycleComponent(component);
    }

    private void showStatus(String status) {
        final String[] className = ((Object) this).getClass().getName().split("\\.");
        Log.d("cube-lifecycle", String.format("%s %s", className[className.length - 1], status));
    }
}
