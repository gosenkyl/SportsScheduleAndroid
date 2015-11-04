package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.PopupWindow;


import com.astuetz.PagerSlidingTabStrip;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment.CollectionPagerAdapter;
import com.sportsschedule.gosenk.sportsscheduleandroid.R;

public class TeamsActivity extends AppCompatActivity {

    /* Action Bar
    * http://stackoverflow.com/questions/27726759/actionbar-setnavigationmodeactionbar-navigation-mode-tabs-deprecated
    * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CollectionPagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

    /*@Override
    public void onBackPressed(){

    }*/

}
