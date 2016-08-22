package com.sportsschedule.gosenk.sportsscheduleandroid.schedule;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.alarm.AlarmReceiver;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Game;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

    private static final SimpleDateFormat dtSdf = new SimpleDateFormat("MM/dd/yy");
    private static final SimpleDateFormat timeSdf = new SimpleDateFormat("h:mm a");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Team team = (Team) getIntent().getSerializableExtra("team");

        // Header
        TextView scheduleHeader = (TextView) findViewById(R.id.schedule_header);
        scheduleHeader.setText(team.toString() + " Schedule");

        // Image
        // Temp, replace with white background, no circle
        Bitmap img = TeamHelper.getTeamLogoCircle(getApplicationContext(), team.getId().replace("-", "_").toLowerCase(), 0.5f);

        ImageView circle = (ImageView) findViewById(R.id.team_image);
        //circ.setLayoutParams(params);
        circle.setImageBitmap(img);

        // Schedule Items
        TableLayout scheduleTable = (TableLayout) findViewById(R.id.schedule_table);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);

        TableRow.LayoutParams rowParams1 = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f);

        TableRow.LayoutParams rowParams2 = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 2f);

        if(team.getSchedule() != null) {
            for (Game game : team.getSchedule()) {
                Team opp = TeamHolder.getTeamMap().get(game.getOpponentTeamId());

                // Create a new row in the table
                TableRow row = new TableRow(this);
                row.setLayoutParams(tableParams);
                row.setPadding(0, 5, 0, 5);

                scheduleTable.addView(row);

                // Game Date Time (08/21/16 12:00 PM)
                TextView gameDateTime = new TextView(this);

                String gameDate = "";
                String gameTime = "";
                try {
                    gameDate = dtSdf.format(game.getTime());
                    gameTime = timeSdf.format(game.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                gameDateTime.setText(gameDate + "\n" + gameTime);

                gameDateTime.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.schedule_time));
                gameDateTime.setTextAppearance(getApplicationContext(), R.style.font_thin_bold);
                gameDateTime.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                gameDateTime.setLayoutParams(rowParams1);

                row.addView(gameDateTime);

                // Opponent Logo
                Bitmap opponentImg = TeamHelper.getTeamLogoCircle(getApplicationContext(), opp.getId().replace("-", "_").toLowerCase(), 0.25f);

                ImageView oppImgView = new ImageView(this);
                oppImgView.setImageBitmap(opponentImg);

                row.addView(oppImgView);

                // Opponent Text
                TextView opponent = new TextView(this);

                String vs = opp.getCity() + " " + opp.getMascot();
                opponent.setText(vs);

                opponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.schedule_item));
                opponent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                opponent.setTextAppearance(getApplicationContext(), R.style.font_thin_bold);
                opponent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                opponent.setLayoutParams(rowParams2);

                row.addView(opponent);

                // Scheduling an alarm for a game in the past doesn't make sense
                // Should we even show the games in the past? No scores... so why show it?
                Date now = new Date();

                ImageView alarm = new ImageView(this);
                alarm.setLayoutParams(rowParams1);

                if (game.getTime().after(now)) {
                    alarm.setImageDrawable(getResources().getDrawable(R.drawable.alarm));
                    alarm.setOnClickListener(new ScheduleAlarm(team, game));

                }

                row.addView(alarm);
            }
        }
    }

    /*private String getTime(String time){
        if(TextUtils.isEmpty(time)){
            return "0:00";
        }

        try {
            String[] timeParts = time.split(":");
            Integer hours = Integer.valueOf(timeParts[0]);
            // Bad assumption
            // NFL webservice doesn't give AM/PM. Believe only London games are AM and start at 9:30ish
            if(hours >=9 && hours < 12){
                return time;
            } else {
                return String.valueOf(hours + 12) + ":" + timeParts[1];
            }
        }catch(Exception e){
            e.printStackTrace();
            return "0:00";
        }
    }*/

    private class ScheduleAlarm implements View.OnClickListener{

        private Team team;
        private Game game;
        private Team opponent;

        public ScheduleAlarm(Team team, Game game){
            this.team = team;
            this.game = game;
            this.opponent = TeamHolder.getTeamMap().get(game.getOpponentTeamId());
        }

        @Override
        public void onClick(View view){

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());

            // set title
            alertBuilder.setTitle(team.getCity() + " " + team.getMascot() + " Alert");

            final Spinner sp = new Spinner(getApplicationContext());

            // TODO Determine Increments
            List<String> list = new ArrayList<String>();
            list.add("15 Seconds");
            list.add("1 Minutes");
            list.add("15 Minutes");
            list.add("30 Minutes");
            list.add("1 Hours");
            list.add("12 Hours");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_option, list);
            dataAdapter.setDropDownViewResource(R.layout.spinner_option_list);
            sp.setAdapter(dataAdapter);

            alertBuilder.setView(sp);

            // set dialog message
            alertBuilder
                    .setMessage("Click to create alert for " + opponent.getCity() + " " + opponent.getMascot())
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            String selectedItem = (String) sp.getAdapter().getItem(sp.getSelectedItemPosition());

                            String timeArr[] = selectedItem.split(" ");
                            Integer timeNum = Integer.parseInt(timeArr[0]);
                            String timeIncrement = timeArr[1];

                            int secondsToAdd = timeNum;
                            if("Seconds".equals(timeIncrement)){
                                secondsToAdd = timeNum;
                            } else if ("Minutes".equals(timeIncrement)){
                                secondsToAdd = timeNum * 60;
                            } else if("Hours".equals(timeIncrement)){
                                secondsToAdd = timeNum * 60 * 60;
                            }

                            // TODO Subtract Increment From Date/Time
                            // From NOW instead of game time for now
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.SECOND, secondsToAdd);

                            Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            alarmIntent.putExtra("team", team);
                            alarmIntent.putExtra("game", game);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                            AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

                            // Create an alarm that calls AlarmReceiver.java at this particular time
                            alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                            dialog.cancel();
                            Toast.makeText(getApplicationContext(), "Alert Created", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog and show it
            alertBuilder.create().show();

            /*public void cancelNotification(int notificationId){
                if (Context.NOTIFICATION_SERVICE!=null) {
                    String ns = Context.NOTIFICATION_SERVICE;
                    NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
                    nMgr.cancel(notificationId);
                }
            }*/


        }

    }

}
