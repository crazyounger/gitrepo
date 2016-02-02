package com.learner.learncode.app;

/**
 * Created by liting on 2016/1/7.
 *
 * proivde some method to make fragment act like activity in backstack *
 * <p>
 *     when a fragment become invisible totally , {@link #onLeave} will be called
 * </p> *
 * <p>
 *     when a fragment become visible from totally invisible ,{@link #onBack} or {@link #onBackWithData(Object)}
 *     will be called
 * </p>
 */
public interface ICubeFragment {

    /**
     * pass the data from {pass the data from {@link CubeFragmentActivity}to this fragment}
     * @param data
     */
    public void onEnter(Object data);

    public void onLeave();

    public void onBack();

    public void onBackWithData(Object o);

    /**
     * process the back press logic
     * return true if the backpress logic has been processed and should stay in current view
     * @return
     */
    public boolean processBackPressed();
}
