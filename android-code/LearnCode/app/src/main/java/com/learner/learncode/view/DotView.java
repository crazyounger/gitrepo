package com.learner.learncode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.learner.learncode.R;

/**
 * Created by liting on 2016/1/9.
 */
public class DotView extends LinearLayout implements  PageIndicater {

    private int mTotal;
    private OnClickListener mDotClickHandler = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v instanceof  LittleDot && mOnDotClickHandler != null){
                mOnDotClickHandler.onDotClick(((LittleDot)v).getIndex());
            }
        }
    };
    private int mCurrent;

    public interface  OnDotClickHandler{
        public void onDotClick(int position);
    }

    private int mLitterDotSize = -2 ;
    private int mDotSpan = 36 ;
    private float mDotRadius = 6f ;

    private int current = 0 ;
    private int total = 0 ;

    private int mSelectedColor = 0xFF377BEE ;
    private int mUnSelectedColor = 0xFFC5CEDB ;

    private OnDotClickHandler mOnDotClickHandler ;

    public void setmOnDotClickHandler(OnDotClickHandler onDotClickHandler){
        this.mOnDotClickHandler = onDotClickHandler ;
    }

    public DotView(Context context){
        super(context);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setGravity(Gravity.CENTER_HORIZONTAL);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DotView, 0, 0);
        if(typedArray != null){
            mDotRadius = typedArray.getDimension(R.styleable.DotView_dot_radius,mDotRadius);
            mDotSpan = (int)typedArray.getDimension(R.styleable.DotView_dot_span,mDotSpan);
            mSelectedColor = typedArray.getColor(R.styleable.DotView_dot_selected_color,mSelectedColor);
            mUnSelectedColor = typedArray.getColor(R.styleable.DotView_dot_unSelected_color,mUnSelectedColor);
            typedArray.recycle();
        }
        mLitterDotSize = (int) (mDotSpan / 2 + mDotRadius * 2);

    }

    @Override
    public int getTotal() {
        return mTotal;
    }

    @Override
    public void setSelected(int index) {
        if(index == mCurrent || index >= getChildCount() || index < 0)
            return ;
        if(mCurrent < getChildCount() && mCurrent >= 0 ){
            ((LittleDot)getChildAt(mCurrent)).setColor(mUnSelectedColor);
        }
        ((LittleDot)getChildAt(index)).setColor(mSelectedColor);
        mCurrent = index ;
    }

    @Override
    public int getCurrentIndex() {
        return mCurrent;
    }

    @Override
    public void setNum(int num) {
        if(num < 0)
            return ;
        mTotal = num ;
        removeAllViews();
        setOrientation(HORIZONTAL);
        for(int i = 0; i < num ;i++){
            LittleDot dot = new LittleDot(getContext(),i);
            dot.setLayoutParams(new LayoutParams((int) mLitterDotSize, (int) mDotRadius * 2, 1));
            dot.setClickable(true);
            if( i == 0 ){
                dot.setColor(mSelectedColor);
            }else{
                dot.setColor(mUnSelectedColor);
            }
            dot.setOnClickListener(mDotClickHandler);
            addView(dot);
        }
    }

    public void setmSelectedColor(int selectedColor){
        mSelectedColor = selectedColor;
        invalidate();
    }

    public void setUnSelectedColor(int unSelectedColor){
        mUnSelectedColor = unSelectedColor ;
        invalidate();
    }

    public void setColor(int selectedColor,int unSelectedColor){
        mSelectedColor = selectedColor ;
        mUnSelectedColor = unSelectedColor ;
        invalidate();
    }


    private class LittleDot extends View {

        private Paint mPaint;
        private int mIndex;
        private int mColor;

        public LittleDot(Context context,int index) {
            super(context);
            init(index);
        }

        private void init(int index ) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mIndex = index ;
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setColor(mColor);
            canvas.drawCircle(mLitterDotSize /2 , mDotRadius,mDotRadius,mPaint);
        }

        public void setColor(int color){
            if(mColor == color){
                return ;
            }
            this.mColor = color ;
            invalidate();
        }

        public int getColor(){
            return mColor ;
        }

        public int getIndex(){
            return mIndex ;
        }
    }
}
