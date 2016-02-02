package com.learner.learncode.view.banner;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by liting on 2016/1/9.
 */
public class AutoPlayer  {


    private boolean mPause;
    private boolean mPlaying = false ;
    private int mTotal;
    private Runnable mTimerTask;
    private boolean skipNext;

    public void skipNext() {
        skipNext = true ;
    }


    public enum PlayDirection{
        to_left , to_right
    }

    public enum PlayRecycleMode{
        repeat_from_start,play_back
    }

    private Playable mPlayable ;
    private PlayRecycleMode mPlayRecycleMode = PlayRecycleMode.repeat_from_start ;
    private PlayDirection mPlayDirection = PlayDirection.to_right ;
    private int mTimeInteranl = 3000 ;

    public AutoPlayer(Playable playable){
        this.mPlayable = playable ;
    }

    public void play() {
        play(0,PlayDirection.to_right);
    }

    public void play(int start){
        play(start, PlayDirection.to_right);
    }

    private void play(int start, PlayDirection direction) {
        if(mPlaying)
            return ;
        mTotal = mPlayable.getTotal();
        if(mTotal <= 1){
            return ;
        }
        mPlaying = true ;
        playTo(start);
        final Handler handler = new Handler(Looper.myLooper());
        mTimerTask = new Runnable(){
            @Override
            public void run() {
                if(!mPause){
                    playNextFrame();
                }
                if(mPlaying){
                    handler.postDelayed(mTimerTask,mTimeInteranl);
                }
            }
        };
        handler.postDelayed(mTimerTask, mTimeInteranl);

    }

    private void playNextFrame() {
        if(skipNext){
            skipNext = false ;
            return ;
        }
        int current = mPlayable.getCurrent() ;
        if (mPlayDirection == PlayDirection.to_right) {
            if (current == mTotal - 1) {
                if (mPlayRecycleMode == PlayRecycleMode.play_back) {
                    mPlayDirection = PlayDirection.to_left;
                    playNextFrame();
                } else {
                    playTo(0);
                }
            } else {
                playNext();
            }
        } else {
            if (current == 0) {
                if (mPlayRecycleMode == PlayRecycleMode.play_back) {
                    mPlayDirection = PlayDirection.to_right;
                    playNextFrame();
                } else {
                    playTo(mTotal - 1);
                }
            } else {
                playPrevious();
            }
        }
    }

    public AutoPlayer setTimeInternal(int timeInternal){
        this.mTimeInteranl = timeInternal ;
        return this ;
    }

    public AutoPlayer setPlayDirectionMode(PlayDirection direction){
        this.mPlayDirection = direction ;
        return this;
    }

    public AutoPlayer setPlayRecycleMode(PlayRecycleMode mode){
        this.mPlayRecycleMode = mode ;
        return this ;
    }


    public void pause(){
        mPause = true ;
    }

    public void resume(){
        mPause = false ;
    }

    private void playTo(int to){
        mPlayable.playTo(to);
    }

    private void playNext(){
        mPlayable.playNext();
    }

    public void playPrevious(){
        mPlayable.playPrevious();
    }
}
