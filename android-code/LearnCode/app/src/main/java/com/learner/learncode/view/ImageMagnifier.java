package com.learner.learncode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by liting on 2016/1/5.
 *  实现点击图片任意部位，出现圆形放大镜效果
 */
public class ImageMagnifier extends ImageView{

    private PointF mPointF;
    private Matrix mMatrix;
    private Paint mPaint;
    private boolean mZooming;
    private Bitmap bitmap;
    private BitmapShader shader;
    private Paint paint;
    private float sizeMagnifier = 200;

    public ImageMagnifier(Context context){
        super(context);
        init();
    }


    public ImageMagnifier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPointF = new PointF(0,0);
        mMatrix = new Matrix();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        mPointF.x = event.getX();
        mPointF.y = event.getY();


        switch(action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mZooming = true ;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mZooming = false ;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!mZooming){
            buildDrawingCache();
        }else{
            bitmap = getDrawingCache();
            shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            paint = new Paint ();
            paint.setShader(shader);

            mMatrix.reset();
            mMatrix.postScale(2.0f, 2.0f, mPointF.x, mPointF.y);
            paint.getShader().setLocalMatrix(mMatrix);

            canvas.drawCircle(mPointF.x,mPointF.y,sizeMagnifier,paint);
        }

    }
}
