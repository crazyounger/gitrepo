package com.learner.learncode.fragmenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.learner.learncode.R;

/**
 * Created by liting on 2016/1/8.
 */
public class Fragment1 extends  BaseFragment {

    public static final int RESULT_EVALUTE = 0x112;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //可以在Activity被回收时，保存Fragment中的数据
//        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        showLog(this.getClass().getSimpleName(), "onCreateView");
        return inflater.inflate(R.layout.fragment_test_cube1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout fl = (FrameLayout)view.findViewById(R.id.fl_cub1);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                goOtherFragment();
//                alertDialog();
                alertCustomDialog();
            }

        });


    }

    private void alertDialog() {
        EvaluateDialog dialog = new EvaluateDialog();
        dialog.setTargetFragment(this,RESULT_EVALUTE);
        dialog.show(getActivity().getSupportFragmentManager(), "EvaluteDialog");
    }

    private void alertCustomDialog(){
        CustomDialog dialog = new CustomDialog() ;
        dialog.show(getActivity().getSupportFragmentManager(),"EvaluteDialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_EVALUTE && resultCode == Activity.RESULT_OK && data !=null){
            String result = data.getStringExtra("result");
            Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
        }

    }

    private void goOtherFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager() ;
        FragmentTransaction ft = fm.beginTransaction() ;
        Fragment fragment = new Fragment2();
        ft.hide(this);
        ft.add(android.R.id.content, fragment, "Fragment2");
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.frament_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fragment_menu_delete:
                showLog("Fragment1","delete menu");
                return true ;
            case R.id.fragment_menu_save:
                showLog("Fragment1","save menu");
                return true ;
            case R.id.fragment_menu_share:
                showLog("Fragment","share menu");
                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
