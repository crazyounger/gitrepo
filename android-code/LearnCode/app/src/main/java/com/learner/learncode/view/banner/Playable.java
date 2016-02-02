package com.learner.learncode.view.banner;

/**
 * Created by liting on 2016/1/9.
 */
public interface Playable {

    public void playTo(int palyTo);

    public void playNext();

    public void playPrevious();

    public int getTotal();

    public int getCurrent();
}
