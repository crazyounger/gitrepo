package com.learner.learncode;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by liting on 2015/12/17.
 */
public class HomeFragment extends Fragment{

    private static final String TAG = "HomeFragment";
    private int position = -1 ;
    private TextView tvContent;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments() ;
        if(args!=null){
            position = args.getInt("position");
        }
        Log.i(TAG, "onCreateView " + position);
        if(rootView == null) {
            Log.i(TAG, "rootView is null" );
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        return rootView ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated " + position);
      

        tvContent = (TextView)view.findViewById(R.id.bt_homecontent);

        tvContent.setText("" + position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach " + position);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated " + position);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart" + position);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume" + position);
    }



    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause " + position);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop " + position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView " + position);
        if(null != rootView){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        Log.i(TAG,"onUserVisibleHint  " + isVisibleToUser);

        int i = position ;
    }
}
