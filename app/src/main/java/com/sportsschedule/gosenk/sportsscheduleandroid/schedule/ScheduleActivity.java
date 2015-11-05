package com.sportsschedule.gosenk.sportsscheduleandroid.schedule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Opponent;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;

import java.text.SimpleDateFormat;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Team team = (Team) getIntent().getSerializableExtra("team");

        TextView scheduleHeader = (TextView) findViewById(R.id.schedule_header);
        scheduleHeader.setText(team.getCity() + " " + team.getMascot() + " Schedule");

        TableLayout scheduleTable = (TableLayout) findViewById(R.id.schedule_table);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

        TableLayout.LayoutParams rowParam = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );

        TableLayout.LayoutParams dateParam = new TableLayout.LayoutParams(
                0,
                TableLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        TableLayout.LayoutParams oppParam = new TableLayout.LayoutParams(
                0,
                TableLayout.LayoutParams.WRAP_CONTENT, 2.0f);

        for(Opponent opp : team.getScheduleList()){
            TableRow row = new TableRow(this);
            row.setLayoutParams(rowParam);

            TextView week = new TextView(this);
            week.setTextSize(R.dimen.schedule_item);
            week.setTextAppearance(getApplicationContext(), R.style.font_thin_bold);
            week.setTextColor(getResources().getColor(R.color.shade3));
            week.setLayoutParams(dateParam);

            TextView opponent = new TextView(this);
            opponent.setTextSize(R.dimen.schedule_item);
            opponent.setTextAppearance(getApplicationContext(), R.style.font_thin_bold);
            week.setTextColor(getResources().getColor(R.color.shade3));
            opponent.setLayoutParams(oppParam);

            String gameDate = "";
            try{
                if(!opp.getCity().equals("BYE"))
                    gameDate = sdf2.format(sdf.parse(opp.getDay()));
            } catch(Exception e){
                e.printStackTrace();
            }

            week.setText(gameDate);

            String vs;
            if(!opp.getCity().equals("BYE")){
                vs = opp.getCity() + " " + opp.getMascot();
            } else {
                vs = "Bye";
            }

            opponent.setText(vs);

            row.addView(week);
            row.addView(opponent);

            scheduleTable.addView(row);
        }


    }

}
