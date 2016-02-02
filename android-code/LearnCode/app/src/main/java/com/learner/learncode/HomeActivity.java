package com.learner.learncode;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learner.learncode.fragment.BottomFragment;
import com.learner.learncode.fragment.TopFragment;

import java.util.ArrayList;

/**
 * Created by liting on 2015/12/17.
 */
public class HomeActivity extends FragmentActivity{

    private ViewPager viewPager ;
    private PagerAdapter mPagerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button view = (Button)findViewById(R.id.bt_test);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,FragmentTestActivity.class));
//                  mulitAnimFragment();
            }
        });
    }

    private void mulitAnimFragment(){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction() ;
        transaction.setCustomAnimations(R.anim.slide_in_top,R.anim.slide_outtop);
        transaction.show(new TopFragment());
        transaction.setCustomAnimations(R.anim.slide_inbottom, R.anim.slide_out_bottom);
        transaction.show(new BottomFragment());
        transaction.commit();
    }

    private void initViews() {
        viewPager = (ViewPager)findViewById(R.id.home_content);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for(int i = 0 ;i < 5 ;i++){
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt("position", i);
            fragment.setArguments(args);
            fragments.add(fragment);
        }


        mPagerAdapter = new HomePagerAadapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0);

        viewPager.setOffscreenPageLimit(1);
    }

     class HomePagerAadapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments ;

        public HomePagerAadapter(FragmentManager fm ,ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments ;
        }

        @Override
        public Fragment getItem(int position) {
            if(fragments!=null && fragments.size()>0){
                return fragments.get(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }
}
