package com.learner.learncode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.learner.learncode.R;

/**
 * Created by liting on 2015/12/18.
 */
public class Fragment1 extends Fragment implements View.OnClickListener {

    private static final String TAG = "Fragment1";
    String name = "" ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"onViewCreated");
        Button mTvView = (Button) view.findViewById(R.id.bt_homecontent);
        mTvView.setText("Fragment1");
        Log.i(TAG, name);
        mTvView.setOnClickListener(this);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
        name = "Jim";

    }

    @Override
    public void onClick(View v) {
//        Fragment fragment2 = new Fragment2();
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction() ;
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top);

//        transaction.replace(android.R.id.content, fragment2);
//        transaction.addToBackStack("fragment2");


        Fragment fragment;
        FragmentManager manager = getActivity().getSupportFragmentManager() ;
        fragment = manager.findFragmentByTag("fragment2") ;
        if( fragment !=null){
            manager.beginTransaction().hide(this).show(fragment).commit();
        }else{
            fragment = new Fragment2();
            manager.beginTransaction().add(android.R.id.content,fragment,"fragment2").commit();
        }
    }


}
