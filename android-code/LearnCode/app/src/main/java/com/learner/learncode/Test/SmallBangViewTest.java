package com.learner.learncode.Test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.learner.learncode.R;
import com.learner.learncode.view.BarView;
import com.learner.learncode.view.PieView;


/**
 * Created by liting on 2016/1/4.
 */
public class SmallBangViewTest extends FragmentActivity implements View.OnClickListener {

    SmallBang bangView ;
    TextView tvBang ;
    Button btnBang ;
    ImageView imgBang ;
    BarView mBarView ;
    PieView mPieView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smallbang);

        tvBang = (TextView)findViewById(R.id.tv_bangview);
        btnBang = (Button)findViewById(R.id.btn_bangview);
        imgBang = (ImageView)findViewById(R.id.img_bangview);
        mBarView = (BarView)findViewById(R.id.barview);
        mPieView = (PieView)findViewById(R.id.pieview);
        bangView = SmallBang.attach2Window(this);

        tvBang.setOnClickListener(this);
        imgBang.setOnClickListener(this);
        btnBang.setOnClickListener(this);
        final boolean[] auto = {true};
        final Handler handler = new Handler() ;
        final Runnable task = new Runnable() {
            @Override
            public void run() {

                if(auto[0]) {
                    handler.postDelayed(this, 1000);
                    mPieView.setProgress(mPieView.getProgresss() + 10);
                    if(mPieView.getProgresss() >= 100){
                        auto[0] = false ;
                        mPieView.setVisibility(View.GONE);
                    }
                }

            }
        } ;
        handler.postDelayed(task, 300);

//        mBarView.setmProgress(40);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_bangview:
                bangView.bang(tvBang);
                break;
            case R.id.img_bangview:
                bangView.bang(imgBang);
                break;
            case R.id.btn_bangview:
                bangView.bang(btnBang);
                break;
        }
    }
}
