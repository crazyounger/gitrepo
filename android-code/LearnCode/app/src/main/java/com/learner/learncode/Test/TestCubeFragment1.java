package com.learner.learncode.Test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.learner.learncode.R;
import com.learner.learncode.app.CubeFragment;

/**
 * Created by liting on 2016/1/7.
 */
public class TestCubeFragment1 extends CubeFragment{
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_cube1,container,false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout fl = (FrameLayout) view.findViewById(R.id.fl_cub1);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackstack(TestCubeFragment2.class,null);
            }
        });
    }
}
