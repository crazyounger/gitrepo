package com.learner.learncode.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.learner.learncode.util.Constants;

/**
 * Created by liting on 2016/1/7.
 */
public abstract  class CubeFragmentActivity extends FragmentActivity {

    private static final boolean DEBUG = Constants.DEBUG;
    private CubeFragment mCurrentFragment;
    private boolean mCloseWarned;

    protected  abstract  String getCloseWarning();

    protected  abstract  int getFragmentContainerId();

    public void pushFragmentToBackstack(Class<?> cls,Object data){
        FragmentParam param = new FragmentParam();
        param.cls = cls ;
        param.data = data ;
        goToThisFragment(param);
    }

    private void goToThisFragment(FragmentParam param) {
        //container id
        int containerId = getFragmentContainerId() ;
        Class<?> cls = param.cls ;
        if(cls == null){
            return ;
        }
        try {
            FragmentManager fm = getSupportFragmentManager() ;
            String fTag = getFragmentTag(param);
            if(DEBUG){
                showLog("befor operate,stack entry count is " + fm.getBackStackEntryCount());
            }
            CubeFragment fragment = (CubeFragment) fm.findFragmentByTag(fTag);
            if(fragment == null){
                fragment = (CubeFragment) cls.newInstance();
            }
            if(mCurrentFragment != null && mCurrentFragment != fragment){
                mCurrentFragment.onLeave();
            }
            fragment.onEnter(param.data);
            FragmentTransaction ft = fm.beginTransaction();
            if(fragment.isAdded()){
                if(DEBUG){
                    showLog(fTag + " has been added , will show again ");
                }
                ft.show(fragment);
            }else{
                if(DEBUG){
                    showLog(fTag + " is added");
                }
                ft.add(containerId,fragment,fTag);
            }
            mCurrentFragment = fragment ;
            ft.addToBackStack(fTag);
            ft.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mCloseWarned = false ;
    }

    public void goToFragment(Class<?> cls ,Object data){
        if(cls == null){
            return ;
        }
        CubeFragment fragment = (CubeFragment) getSupportFragmentManager().findFragmentByTag(cls.toString());
        if(fragment != null){
            mCurrentFragment = fragment ;
            fragment.onBackWithData(data);
        }
        getSupportFragmentManager().popBackStack(cls.toString(),0);
    }

    public void popTopFragment(Object data){
        FragmentManager fm = getSupportFragmentManager() ;
        fm.popBackStackImmediate();
        if(mCurrentFragment != null && tryToUpdateCurrentAfterPop()){
            mCurrentFragment.onBackWithData(data);
        }
    }

    public void popToRoot(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        while (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
        }
        popTopFragment(data);
    }


    private boolean tryToUpdateCurrentAfterPop() {
        FragmentManager fm = getSupportFragmentManager();
        int cnt = fm.getBackStackEntryCount();
        if (cnt > 0) {
            String name = fm.getBackStackEntryAt(cnt - 1).getName();
            Fragment fragment = fm.findFragmentByTag(name);
            if (fragment != null && fragment instanceof CubeFragment) {
                mCurrentFragment = (CubeFragment) fragment;
            }
            return true;
        }
        return false;
    }


    /**
     * process the return back logic
     * return true if back pressed event has been processed and should stay in current view
     *
     * @return
     */
    protected boolean processBackPressed() {
        return false;
    }

    @Override
    public void onBackPressed() {

        if(processBackPressed()){
            return  ;
        }
        boolean enableBackPressed = true ;
        if(mCurrentFragment != null){
            enableBackPressed = !mCurrentFragment.processBackPressed() ;
        }
        if(enableBackPressed){
            int cnt = getSupportFragmentManager().getBackStackEntryCount() ;
            if(cnt <= 1 && isTaskRoot()){
                String closeWarningHint = getCloseWarning() ;
                if(!mCloseWarned && !TextUtils.isEmpty(closeWarningHint)){
                    Toast.makeText(getApplicationContext(),closeWarningHint,Toast.LENGTH_LONG).show();
                    mCloseWarned = true ;
                }else{
                    doReturnBack();
                }
            }else{
                doReturnBack();
                mCloseWarned = false ;
            }
        }
    }

    private void doReturnBack() {
        int cnt = getSupportFragmentManager().getBackStackEntryCount() ;
        if(cnt <= 1){
            finish();
        }else{
            getSupportFragmentManager().popBackStackImmediate();
            if (tryToUpdateCurrentAfterPop() && mCurrentFragment != null) {
                mCurrentFragment.onBack();
            }
        }
    }

    private String getFragmentTag(FragmentParam param) {
        StringBuilder sb = new StringBuilder(param.cls.toString());
        return  sb.toString() ;
    }

    public void forceShowKeyboard(){
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    protected  void exitFullScreen(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    public void showKeyboardAtView(View view){
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideKeyboardForCurrentFocus(){
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null){
         manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        manager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void showLog(String s) {
        Log.d("cube-fragment", s);
    }
}
