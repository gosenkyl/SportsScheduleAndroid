package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment.CollectionPagerAdapter;
import com.sportsschedule.gosenk.sportsscheduleandroid.R;

public class TeamsActivity extends AppCompatActivity {

    /* Action Bar
    * http://stackoverflow.com/questions/27726759/actionbar-setnavigationmodeactionbar-navigation-mode-tabs-deprecated
    * http://www.android4devs.com/2014/12/how-to-make-material-design-app.html
    * Icons
    * https://materialdesignicons.com/
    *
    * */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        Toolbar toolbar = (Toolbar) findViewById(R.id.teams_toolbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CollectionPagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

}
