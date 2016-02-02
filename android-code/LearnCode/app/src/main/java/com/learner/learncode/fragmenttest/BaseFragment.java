package com.learner.learncode.fragmenttest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learner.learncode.util.Constants;

/**
 * Created by liting on 2016/1/8.
 */
public class BaseFragment extends Fragment {

    public void showLog(String tag ,String msg){
        if(Constants.DEBUG){
            Log.d(tag, String.format("------------%s----------",msg));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        showLog(this.getClass().getSimpleName(),"onAttach/context");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog(this.getClass().getSimpleName(),"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLog(this.getClass().getSimpleName(),"onActivityCreated");

    }

    @Override
    public void onStart() {
        super.onStart();
        showLog(this.getClass().getSimpleName(),"onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        showLog(this.getClass().getSimpleName(),"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        showLog(this.getClass().getSimpleName(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        showLog(this.getClass().getSimpleName(), "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showLog(this.getClass().getSimpleName(), "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showLog(this.getClass().getSimpleName(), "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showLog(this.getClass().getSimpleName(),"onDetach");
    }
}
