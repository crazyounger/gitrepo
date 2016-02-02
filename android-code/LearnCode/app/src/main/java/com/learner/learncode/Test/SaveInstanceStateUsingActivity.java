package com.learner.learncode.Test;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.learner.learncode.fragmenttest.CustomDialog;

import java.util.ArrayList;

/**
 * Created by liting on 2016/1/8.
 */
public class SaveInstanceStateUsingActivity extends ListActivity {


    private ArrayList<String> mDatas;
    private CustomDialog mShowDialog;
    private LoadDataSyncTask mLoadDataSyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(),"onCreate");
        initData(savedInstanceState);
    }

    private void initData(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            mDatas = savedInstanceState.getStringArrayList("data");
        }
        if(mDatas == null){
            mLoadDataSyncTask = new LoadDataSyncTask();
            mLoadDataSyncTask.execute();
        }else {
            initAdapter();
        }

    }

    private void initAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDatas);
        setListAdapter(adapter);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(getClass().getSimpleName(), "onSaveInstanceState");
        outState.putStringArrayList("data",mDatas);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.d(getClass().getSimpleName(),"onRestoreInstanceState");
    }

     private class LoadDataSyncTask extends AsyncTask<Void,Void,Void> {

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             Toast.makeText(SaveInstanceStateUsingActivity.this, "onPreExecute", Toast.LENGTH_SHORT).show();
         }

         @Override
         protected Void doInBackground(Void... params) {
             mDatas = new ArrayList<>();
             for(int i = 0 ;i < 15 ;i++){
                 mDatas.add("Data" + i );
             }
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             return null ;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
             initAdapter();
         }
     }
}
