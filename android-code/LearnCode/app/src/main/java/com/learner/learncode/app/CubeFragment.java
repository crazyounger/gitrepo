package com.learner.learncode.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learner.learncode.app.lifecycle.IComponentContainer;
import com.learner.learncode.app.lifecycle.LifeCycleComponent;
import com.learner.learncode.app.lifecycle.LifeCycleComponentManager;
import com.learner.learncode.util.Constants;

/**
 * Created by liting on 2016/1/7.
 */
public abstract class CubeFragment extends Fragment implements ICubeFragment ,IComponentContainer{


    private static final boolean DEBUG = Constants.DEBUG;
    private LifeCycleComponentManager mComponentManager = new LifeCycleComponentManager() ;
    private Object mDataIn;
    private boolean mFirstResume = true;

    public abstract  View  createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public CubeFragmentActivity getContext(){
        return (CubeFragmentActivity)getActivity();
    }

    @Override
    public void onEnter(Object data) {
        mDataIn = data ;
        if(DEBUG){
            showStatus("onEnter");
        }
    }



    @Override
    public void onLeave() {
        if (DEBUG) {
            showStatus("onLeave");
        }
        mComponentManager.onBecomesTotallyInvisible();
    }

    @Override
    public void onBack() {
        if(DEBUG){
            showStatus("onBack");
        }
        mComponentManager.onBecomesVisibleFromTotallyInvisible();
    }

    @Override
    public void onBackWithData(Object o) {
        if(DEBUG){
            showStatus("onBack");
        }
        mComponentManager.onBecomesVisibleFromTotallyInvisible();
    }

    @Override
    public boolean processBackPressed() {
        return false;
    }

    @Override
    public void addLifeCycleComponent(LifeCycleComponent component) {

    }

    private void showStatus(String status) {
        String name = ((Object)this).getClass().getSimpleName() ;
        Log.d("cube-lifecycle",name + " : "+ status);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(DEBUG){
            showStatus("onStop");
        }
        onLeave();
    }


    /**
     * Only when Activity resume, not very precise.
     * When activity recover from partly invisible, onBecomesPartiallyInvisible will be triggered.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (!mFirstResume) {
            onBack();
        }
        if (mFirstResume) {
            mFirstResume = false;
        }
        if (DEBUG) {
            showStatus("onResume");
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (DEBUG) {
            showStatus("onAttach");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            showStatus("onCreate");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG) {
            showStatus("onActivityCreated");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG) {
            showStatus("onStart");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (DEBUG) {
            showStatus("onPause");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (DEBUG) {
            showStatus("onDestroyView");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DEBUG) {
            showStatus("onDestroy");
        }
        mComponentManager.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (DEBUG) {
            showStatus("onDetach");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (DEBUG) {
            showStatus("onCreateView");
        }
        return createView(inflater,container,savedInstanceState);
    }
}
