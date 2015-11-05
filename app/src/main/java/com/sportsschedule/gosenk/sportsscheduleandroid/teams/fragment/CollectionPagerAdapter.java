package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles = {"NFL", "MLB", "NBA", "NHL"};

    public CollectionPagerAdapter(FragmentManager fm){
        super(fm);
    }

    private NFLFragment nfl;
    private MLBFragment mlb;
    private NBAFragment nba;
    private NHLFragment nhl;

    @Override
    public Fragment getItem(int i){
        switch(i){
            case 0:
                if(nfl == null)
                    nfl = new NFLFragment();
                return nfl;
            case 1:
                if(mlb == null)
                    mlb = new MLBFragment();
                return mlb;
            case 2:
                if(nba == null)
                    nba = new NBAFragment();
                return nba;
            case 3:
                if(nhl == null)
                    nhl = new NHLFragment();
                return nhl;
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
