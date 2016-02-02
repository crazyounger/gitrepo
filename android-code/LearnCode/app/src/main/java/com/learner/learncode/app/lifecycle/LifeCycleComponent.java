package com.learner.learncode.app.lifecycle;

import android.app.Activity;

/**
 * Created by liting on 2016/1/7.
 */
public interface LifeCycleComponent {

    /**
     * The UI become partially invisible
     * like {@link Activity#onPause()}
     */
    public void onBecomesPartiallyInvisible();

    /**
     * The UI become visible from totally or partially invisible.
     * like {@link Activity#onResume()}
     */
    public void onBecomesVisible();

    /**
     * The UI become totally invisible.
     * like {@link Activity#onStop()}
     */
    public void onBecomesTotallyInvisible();

    /**
     * The UI become visible from totally invisible .
     * like {@link Activity#onRestart()}
     */
    public void onBecomesVisibleFromTotallyInvisible();

    public void onDestroy();
}
