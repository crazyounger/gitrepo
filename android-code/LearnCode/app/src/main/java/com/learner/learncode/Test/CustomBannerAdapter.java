package com.learner.learncode.Test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.learner.learncode.R;
import com.learner.learncode.view.banner.BannerAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by liting on 2016/1/11.
 */
public class CustomBannerAdapter extends BannerAdapter {

    private  Context mContext;
//    private int[] imgResIds = new int[]{
//            R.drawable.mv,R.drawable.mv1,
//            R.drawable.mv2,R.drawable.mv3,
//            R.drawable.mv1,R.drawable.mv2,
//            R.drawable.mv3
//    };

     private String[] imgUrls = new String[]{
             "http://img6.hao123.com/data/1_b61f930feef1b5edb08943e896656842_0",
             "http://img.hao123.com/data/1_439f35228ddfd3beb0f2f8652cbce838_510",
             "http://img.hao123.com/data/1_2c85b144ea208b2610ae398b77fe5076_510",
             "http://img6.hao123.com/data/1_0d9860d968544bc7dfb4445bcf553d93_510",
             "http://img.hao123.com/data/1_7a9f96aaf5d6a2faa700d0ca56e88dee_510",
             "http://img.hao123.com/data/1_a20369bb0f9567fa22f133ca5a64291f_510",
             "http://img3.hao123.com/data/1_d5ea97288b56012cf0b7b31f34ffd435_0"
     };

    public CustomBannerAdapter(){}

    public CustomBannerAdapter(Context context){
        this.mContext = context ;
    }

    @Override
    protected View getView(LayoutInflater inflater, int position) {
        ImageView imgView = new ImageView(mContext);
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imgView.setImageResource(imgResIds[position]);
        Picasso.with(mContext).load(imgUrls[position]).into(imgView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        return imgView;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
