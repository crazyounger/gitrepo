package com.learner.learncode.Test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.learner.learncode.R;
import com.learner.learncode.app.CubeFragment;

import java.util.ArrayList;


/**
 * Created by liting on 2016/1/7.
 */
public class TestCubeFragment2 extends CubeFragment{

    private ListView mListView ;
    private String [] datas;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_cube2,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView)view.findViewById(R.id.cube2_listview);

        mListView.setOnScrollListener(new MyScrollListener());

        initDatas();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(),
                android.R.layout.simple_list_item_1,datas);
        mListView.setAdapter(adapter);
    }

    private void initDatas() {
        datas = new String[]{"str1","str2","str3","str4","str5","str1","str2","str3","str4","str5","str1","str2","str3","str4","str5"
                ,"str1","str2","str3","str4","str5","str1","str2","str3","str4","str5","str1","str2","str3","str4","str5","str1","str2","str3","str4","str5"};
    }

    class MyScrollListener implements AbsListView.OnScrollListener {
        private boolean isEnd = false ;
        private boolean isTop = false ;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(scrollState == SCROLL_STATE_IDLE){
                if(isEnd){
                    onReachEnd();
                }
                if(isTop){
                    onReachTop();
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(firstVisibleItem + visibleItemCount >= totalItemCount - 1  ){
                isEnd = true ;
            }else{
                isEnd = false ;
            }
            if(firstVisibleItem == 0 ){
                isTop = true ;
            }else{
                isTop = false ;
            }
        }
    }

    private void onReachTop() {
        Toast.makeText(getContext(),"onReachTop",Toast.LENGTH_LONG).show();
    }

    private void onReachEnd() {
        Toast.makeText(getContext(),"onReachEnd",Toast.LENGTH_LONG).show();
    }
}
