package com.sportsschedule.gosenk.sportsscheduleandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by GosenK on 10/30/2015.
 */
public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"NFL", "MLB"};

    public CollectionPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        switch(i){
            case 0:
                return new NFLFragment();
            case 1:
                return new MLBFragment();
        }

        return null;
    }

    @Override
    public int getCount(){
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int i){
        return titles[i];
    }
}
