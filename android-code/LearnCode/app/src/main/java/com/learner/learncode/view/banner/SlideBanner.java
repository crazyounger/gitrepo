package com.learner.learncode.view.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.learner.learncode.R;
import com.learner.learncode.view.PageIndicater;

/**
 * Created by liting on 2016/1/11.
 */
public class SlideBanner extends RelativeLayout {

    private  int mIdForIndicator;
    private int mIdForViewPager ;
    private int mInternal;
    private ViewPager.OnPageChangeListener mOnPagerChangerListener;
    private BannerAdapter mBannerAdapter;
    private ViewPager mViewPager ;

    private AutoPlayer mAutoPlayer;
    private PageIndicater mPagerIndicator;
    private OnTouchListener mViewPagerTouchListener;

    private Playable playable = new Playable() {
        @Override
        public void playTo(int palyTo) {
            mViewPager.setCurrentItem(palyTo,true);
        }

        @Override
        public void playNext() {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1,true);
        }

        @Override
        public void playPrevious() {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() -1 ,true );
        }

        @Override
        public int getTotal() {
            return mBannerAdapter.getCount();
        }

        @Override
        public int getCurrent() {
            return mViewPager.getCurrentItem();
        }
    } ;


    public SlideBanner(Context context){
        super(context);
    }

    public SlideBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr =context.obtainStyledAttributes(attrs, R.styleable.SlideBanner, 0, 0);
        if(arr != null){
            mInternal = arr.getInteger(R.styleable.SlideBanner_slide_banner_internal,mInternal);
            mIdForIndicator = arr.getResourceId(R.styleable.SlideBanner_slide_banner_indicator, 0);
            mIdForViewPager = arr.getResourceId(R.styleable.SlideBanner_slide_banner_pager,0);
            arr.recycle();
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                if(mAutoPlayer != null) {
                    mAutoPlayer.pause();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(mAutoPlayer != null) {
                    mAutoPlayer.resume();
                }
                break;
            default:
                break;
        }
        if(mViewPagerTouchListener != null){
            mViewPagerTouchListener.onTouch(this, ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPagerChangerListener = listener;
    }

    public void setDotNum(int num){
        if(mPagerIndicator != null){
            mPagerIndicator.setNum(num);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewPager = (ViewPager)findViewById(R.id.slidebanner_viewpager);
        mPagerIndicator = (PageIndicater)findViewById(R.id.slidebanner_indicator);
        if(mViewPager == null){
            throw new IllegalArgumentException("ViewPager id == null");
        }

        mAutoPlayer = new AutoPlayer(playable).setPlayDirectionMode(AutoPlayer.PlayDirection.to_right).setPlayRecycleMode(AutoPlayer.PlayRecycleMode.repeat_from_start);
        mAutoPlayer.setTimeInternal(mInternal);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(mOnPagerChangerListener != null){
                    mOnPagerChangerListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(mPagerIndicator != null){
                    mPagerIndicator.setSelected(position);
                }
                if(mOnPagerChangerListener != null){
                    mOnPagerChangerListener.onPageSelected(position);
                }
                mAutoPlayer.skipNext();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(mOnPagerChangerListener != null){
                    mOnPagerChangerListener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    public void setTimeInternal(long internal){
        if(mAutoPlayer != null){
            mAutoPlayer.setTimeInternal((int)internal);
        }

    }

    public void beginPlay(){
        if(mAutoPlayer != null){
            mAutoPlayer.play();
        }
    }

    public void setBannerAdapter(BannerAdapter adapter){
        this.mBannerAdapter = adapter ;
        mViewPager.setAdapter(mBannerAdapter);
    }
}
