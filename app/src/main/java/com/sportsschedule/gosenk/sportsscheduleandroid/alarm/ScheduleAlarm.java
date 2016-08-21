package com.sportsschedule.gosenk.sportsscheduleandroid.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Game;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScheduleAlarm extends AppCompatActivity {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE MMMM dd, yyyy", Locale.US);

    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_alarm);

        Team team = (Team) getIntent().getSerializableExtra("team");
        Game game = (Game) getIntent().getSerializableExtra("game");
        Team opponent = TeamHolder.getTeamMap().get(game.getOpponentTeamId());

        ImageView teamAImg = (ImageView) findViewById(R.id.team_a_src);
        teamAImg.setImageBitmap(TeamHelper.getTeamLogoCircle(getApplicationContext(), team.getId().replace("-", "_").toLowerCase(), .75f));
        TextView teamAName = (TextView) findViewById(R.id.team_a_name);
        teamAName.setText(team.toString());

        ImageView teamBImg = (ImageView) findViewById(R.id.team_b_src);
        teamBImg.setImageBitmap(TeamHelper.getTeamLogoCircle(getApplicationContext(), team.getId().replace("-", "_").toLowerCase(), .75f));
        TextView teamBName = (TextView) findViewById(R.id.team_b_name);
        teamBName.setText(opponent.toString());

        String day = "";
        try{
            day = sdf2.format(game.getTime());
        } catch(Exception e){
            e.printStackTrace();
        }

        String time = "";
        try{
            time = sdf3.format(game.getTime());
            //Date timeDate = sdf3.parse(game.getTime();
            // Random assumption, No AM/PM in the JSON
            /*if(timeDate.after(sdf3.parse("8:59")) && timeDate.before(sdf3.parse("10:00"))){
                time = opponent.getTime() + " AM";
            } else {
                time = opponent.getTime() + " PM";
            }*/
        } catch(Exception e){
            e.printStackTrace();
        }

        TextView gameDate = (TextView) findViewById(R.id.game_date_time);
        gameDate.setText(time + " - " + day);

    }
}
