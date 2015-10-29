package com.sportsschedule.gosenk.sportsscheduleandroid;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TeamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        TeamHolder teamHolder = TeamHolder.getInstance(null);

        TableRow row = null;
        TableLayout layout = (TableLayout) findViewById(R.id.team_list);

        int i = 0;
        for(Team team : teamHolder.getNflTeamList()){
            if(i % 2 == 0){
                row = new TableRow(this);
                layout.addView(row);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout linear = new LinearLayout(this);

            ImageView img = new ImageView(this);
            img.setLayoutParams(params);
            img.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nfl_logo));
            linear.addView(img);

            TextView text = new TextView(this);
            text.setLayoutParams(params);
            text.setText(team.getCity() + " \n " + team.getMascot());
            text.setTextColor(Color.WHITE);
            linear.addView(text);

            row.addView(linear);

            i++;
        }

    }
}
