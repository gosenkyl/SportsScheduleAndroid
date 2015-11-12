package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        setToolBar();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CollectionPagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

    }

    private void setToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.teams_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        String buttonName = null;

        switch (itemId){
            case R.id.action_search:
                buttonName = "Search";
                break;
            case R.id.action_help:
                buttonName = "Help";
                break;
            case R.id.action_settings:
                buttonName = "Settings";
                break;
        }

        Toast.makeText(getApplicationContext(), "Butotn " + buttonName, Toast.LENGTH_SHORT).show();
        return true;
    }

}
