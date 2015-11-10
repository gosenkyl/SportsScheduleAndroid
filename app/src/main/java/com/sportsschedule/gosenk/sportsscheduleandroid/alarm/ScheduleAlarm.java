package com.sportsschedule.gosenk.sportsscheduleandroid.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Opponent;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_alarm);

        Team team = (Team) getIntent().getSerializableExtra("team");
        Opponent opponent = (Opponent) getIntent().getSerializableExtra("opponent");

        ImageView teamAImg = (ImageView) findViewById(R.id.team_a_src);
        teamAImg.setImageBitmap(TeamHelper.getTeamLogoCircle(getApplicationContext(), team.getLogoURL(), .75f));
        TextView teamAName = (TextView) findViewById(R.id.team_a_name);
        teamAName.setText(team.toString());

        ImageView teamBImg = (ImageView) findViewById(R.id.team_b_src);
        teamBImg.setImageBitmap(TeamHelper.getTeamLogoCircle(getApplicationContext(), opponent.getLogoURL(), .75f));
        TextView teamBName = (TextView) findViewById(R.id.team_b_name);
        teamBName.setText(opponent.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE MMMM dd, yyyy", Locale.US);

        SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm");

        String day = "";
        try{
            day = sdf2.format(sdf.parse(opponent.getDay()));
        } catch(Exception e){
            e.printStackTrace();
        }

        String time = "";
        try{
            // Random assumption, No AM/PM in the JSON
            Date timeDate = sdf3.parse(opponent.getTime());
            if(timeDate.after(sdf3.parse("8:59")) && timeDate.before(sdf3.parse("10:00"))){
                time = opponent.getTime() + " AM";
            } else {
                time = opponent.getTime() + " PM";
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        TextView gameDate = (TextView) findViewById(R.id.game_date_time);
        gameDate.setText(time + " - " + day);

    }
}
