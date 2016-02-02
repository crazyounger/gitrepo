package com.learner.learncode.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learner.learncode.R;

/**
 * Created by liting on 2016/1/8.
 */
public class Fragment2 extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test2,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = (TextView)view.findViewById(R.id.tv_fragment_test2);
        tv.setText("Fragment2");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOtherFragment();
            }
        });
    }

    private void goOtherFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager() ;
        FragmentTransaction ft = fm.beginTransaction() ;
        Fragment fragment = new Fragment3();
        ft.hide(this);
        ft.add(android.R.id.content, fragment, "Fragment3");
        ft.addToBackStack(null);
        ft.commit();
    }
}
