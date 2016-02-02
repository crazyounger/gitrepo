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
public class PieView extends View{

    private Paint mWhitePaint;
    private Paint mGrayPaint;
    private RectF mBound;
    private int mMax = 100;
    private int mProgress = 0 ;

    public PieView(Context context){
        super(context);
        init();

    }
    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWhitePaint.setStrokeWidth(dp2px(0.3f));
        mWhitePaint.setColor(Color.GRAY);

        mGrayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGrayPaint.setStyle(Paint.Style.STROKE);
        mGrayPaint.setColor(Color.GRAY);
        mGrayPaint.setStrokeWidth(dp2px(1f));

        mBound = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float padding = dp2px(4);
        mBound.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthDimension = (int)dp2px(40) ;
        setMeasuredDimension(widthDimension, widthDimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int angle = mProgress * 360 / mMax ;
        canvas.drawArc(mBound, 270, angle, true, mWhitePaint);
        int padding = (int)dp2px(4);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - padding, mGrayPaint);

    }

    private float dp2px(float dp) {
        float px = 0 ;
        float density = getContext().getResources().getDisplayMetrics().density;
        px = dp * density ;
        return px;
    }

    public void setMax(int max) {
        this.mMax = max;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        invalidate();
    }

    public int getProgresss() {
        return this.mProgress;
    }
}
