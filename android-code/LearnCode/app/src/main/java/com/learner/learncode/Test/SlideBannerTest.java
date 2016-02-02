package com.learner.learncode.Test;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.learner.learncode.R;
import com.learner.learncode.view.FlowLayout;
import com.learner.learncode.view.banner.SlideBanner;


/**
 * Created by liting on 2016/1/11.
 */
public class SlideBannerTest extends FragmentActivity {

    private CustomBannerAdapter mBannerAdapter ;
    private SlideBanner mSlideBanner ;
    private FlowLayout mFlowLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidebanner);

        mSlideBanner = (SlideBanner)findViewById(R.id.my_slidebanner);
        mBannerAdapter = new CustomBannerAdapter(this);
        mSlideBanner.setBannerAdapter(mBannerAdapter);
        mSlideBanner.setDotNum(mBannerAdapter.getCount());
        mSlideBanner.beginPlay();

        Intent intent = getIntent() ;
        if(intent != null){
            Uri uri = intent.getData() ;
            if(uri != null){
                String age = uri.getQueryParameter("age") ;
                String name = uri.getQueryParameter("name");
                Toast.makeText(this, "age : " + age + "  name : "+name, Toast.LENGTH_SHORT).show();
            }
        }
        initFlowLayout();
    }

    private void initFlowLayout() {
        String [] ars = new String[]{
          "hello","sorry","good bye","mother", "father","sister","computer sience" ,"this is a good student",
                "TestCubeFragment is not a Activity ,but is a Fragment" ,"bye bye ","fdsafdefds","tttttttt"

        };
        mFlowLayout = (FlowLayout)findViewById(R.id.flowlayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        for(int i = 0 ; i < ars.length;i++){
            TextView tv = new TextView(this);
            tv.setPadding(10, 10, 10, 10);
            tv.setTextColor(Color.GREEN);
            tv.setTextSize(18f);
//            tv.setGravity(Gravity.CENTER);
            tv.setText(ars[i]);
            mFlowLayout.addView(tv);
        }
    }
}
