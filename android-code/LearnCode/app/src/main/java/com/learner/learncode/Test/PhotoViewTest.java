package com.learner.learncode.Test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;

import com.learner.learncode.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by liting on 2016/1/14.
 */
public class PhotoViewTest extends FragmentActivity {

    private ImageView imgView ;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        imgView = (ImageView)findViewById(R.id.img_scale);
        imgView.setImageResource(R.drawable.mv2);

        mAttacher = new PhotoViewAttacher(imgView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mAttacher.cleanup();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
