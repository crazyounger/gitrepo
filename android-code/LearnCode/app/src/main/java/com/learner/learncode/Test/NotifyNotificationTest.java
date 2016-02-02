package com.learner.learncode.Test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;

import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;
import com.learner.learncode.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by liting on 2016/1/14.
 */
public class NotifyNotificationTest extends FragmentActivity implements View.OnClickListener {

    private Button flipEff ;
    private Button jellyEff ;
    private Button scaleEff ;
    private Button slideinEff ;
    private Button slideontopEff ;
    private Button standardEff ;
    private Button thumbsliderEff ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        flipEff = (Button)findViewById(R.id.btn_flip_eff);
        jellyEff =(Button)findViewById(R.id.btn_jelly_eff);
        scaleEff = (Button)findViewById(R.id.btn_scale_eff);
        slideinEff = (Button)findViewById(R.id.btn_slidein_eff);
        slideontopEff = (Button)findViewById(R.id.btn_slideontop_eff);
        standardEff = (Button)findViewById(R.id.btn_standard_eff);
        thumbsliderEff = (Button)findViewById(R.id.btn_thumbslider_eff);

        flipEff.setOnClickListener(this);
        jellyEff.setOnClickListener(this);
        scaleEff.setOnClickListener(this);
        slideinEff.setOnClickListener(this);
        slideontopEff.setOnClickListener(this);
        standardEff.setOnClickListener(this);
        thumbsliderEff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_slidein_eff:
                showSlideinEff();
                break ;
            case R.id.btn_flip_eff:
                showFlipEff();
                break;
            case R.id.btn_jelly_eff:
                showJellyEff();
                break;
            case R.id.btn_scale_eff:
                showScaleEff();
                break;
            case R.id.btn_slideontop_eff:
                showSlideOnTopEff();
                break;
            case R.id.btn_standard_eff:
                showStandardEff();
                break;
            case R.id.btn_thumbslider_eff:
                showThumbSlideEff();
                break;
        }
    }

    private void showJellyEff() {
        NiftyNotificationView.build(this,"this is jelly effect", Effects.jelly,R.id.notifit_layout)
                .setIcon(R.drawable.mv).show();
//        ViewHelper.setPivotX(jellyEff, jellyEff.getWidth() / 2);
//        ViewHelper.setPivotY(jellyEff, jellyEff.getHeight() / 2);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(jellyEff,"scaleX",new float[]{0.0f,0.3f,0.5f,0.7f,0.9f,0.8f,0.9f,1.0f}).setDuration(1500),
//                                                ObjectAnimator.ofFloat(jellyEff,"scaleY",new float[]{0.0f,0.3f,0.5f,0.7f,0.9f,0.8f,0.9f,1.0f}).setDuration(1500),
//                                                ObjectAnimator.ofFloat(jellyEff,"alpha",new float[]{0.0f,1.0f}).setDuration(1500)});
//        animatorSet.start();
    }

    private void showScaleEff() {
        NiftyNotificationView.build(this,"this is scales effect", Effects.scale,R.id.notifit_layout).show();
//        ViewHelper.setPivotX(scaleEff, scaleEff.getWidth() / 2);
//        ViewHelper.setPivotY(scaleEff, scaleEff.getHeight() / 2);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(scaleEff,"translationY",new float[]{(-scaleEff.getHeight()/2),0.0f}).setDuration(1200),
//                                                ObjectAnimator.ofFloat(scaleEff,"scaleX",new float[]{0.3f,0.5f,0.7f,1.0f}).setDuration(1200),
//                                                ObjectAnimator.ofFloat(scaleEff,"scaleY",new float[]{0.3f,0.5f,0.7f,1.0f}).setDuration(1200)
//        });
//        animatorSet.start();
    }

    private void showSlideOnTopEff() {
        NiftyNotificationView.build(this,"this is slideontop effect", Effects.slideOnTop,R.id.notifit_layout).show();
    }

    private void showStandardEff() {
        NiftyNotificationView.build(this,"this is standard effect", Effects.standard,R.id.notifit_layout).show();
    }

    private void showThumbSlideEff() {
        NiftyNotificationView.build(this,"this is ThumbSlide effect", Effects.thumbSlider,R.id.notifit_layout).show();
    }

    private void showFlipEff() {
        NiftyNotificationView.build(this,"this is flip effect", Effects.flip,R.id.notifit_layout).show();
//        ViewHelper.setPivotX(flipEff, 0);
//        ViewHelper.setPivotY(flipEff, flipEff.getHeight()/2);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(flipEff,"rotationY",new float[]{0.0f,90.0f}).setDuration(2000),
//                                                ObjectAnimator.ofFloat(flipEff,"alpha",new float[]{1.0f,0.0f}).setDuration(2000)});
//        animatorSet.start();
    }

    private void showSlideinEff() {
        NiftyNotificationView.build(this,"this is slidein effect", Effects.slideIn,R.id.notifit_layout).show();
//        ViewHelper.setPivotX(slideinEff, slideinEff.getWidth() / 2);
//        ViewHelper.setPivotY(slideinEff, slideinEff.getHeight() / 2);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(slideinEff,"translationX",new float[]{-scaleEff.getWidth(),-15.0f,-8.0f,-3.0f,0.0f})});
//        animatorSet.setDuration(1300).start();
    }
}
