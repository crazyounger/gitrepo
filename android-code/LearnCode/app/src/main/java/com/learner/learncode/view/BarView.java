package com.learner.learncode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liting on 2016/1/5.
 */
public class BarView extends View {


    private Paint mOuterPaint;
    private Paint mInnerPaint;
    private float mBoundGap;
    private RectF mInnerBound;
    private float mProgress;
    private float mRadius;
    private RectF mBound;
    private static final int DEFAULT_MAX_PROGRESS = 100 ;
    private int mMax = DEFAULT_MAX_PROGRESS;

    public BarView(Context context){
        super(context);
        init();
    }

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarView(Context context ,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        mOuterPaint = new Paint();
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeWidth(dp2px(2));
        mOuterPaint.setColor(Color.WHITE);
        mOuterPaint.setAntiAlias(true);

        mInnerPaint = new Paint();
        mInnerPaint.setStyle(Paint.Style.FILL);
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(Color.WHITE);

        mBoundGap = dp2px(5);
        mInnerBound = new RectF(mBoundGap,mBoundGap,(getWidth()-mBoundGap)* mProgress / mMax ,getHeight()-mBoundGap);

        mRadius = dp2px(10);
        mBound = new RectF();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float padding = dp2px(5);
        mBound.set(padding,padding,w-padding,h-padding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthDimension = (int)dp2px(100);
        int heightDimension = (int)dp2px(20);
        setMeasuredDimension(widthDimension, heightDimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mBound, mRadius, mRadius, mOuterPaint);
        canvas.drawRoundRect(mInnerBound, mRadius, mRadius, mInnerPaint);
    }

    public void setmProgress(float mProgress) {
        this.mProgress = mProgress;
        mInnerBound.set(mBoundGap, mBoundGap, (getWidth() - mBoundGap) * mProgress / mMax, getHeight() - mBoundGap);
        invalidate();
    }

    public void setMax(int max){
        this.mMax = max ;
    }

    private float dp2px(int dp) {
        float px = 0 ;
        float density = getContext().getResources().getDisplayMetrics().density ;
        px = dp * density ;
        return px;
    }

    public float getProgresss(){
        return this.mProgress ;
    }
}
