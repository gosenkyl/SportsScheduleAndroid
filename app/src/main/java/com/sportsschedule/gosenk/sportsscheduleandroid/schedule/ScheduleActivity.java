package com.sportsschedule.gosenk.sportsscheduleandroid.schedule;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.alarm.AlarmReceiver;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Opponent;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

        Team team = (Team) getIntent().getSerializableExtra("team");

        // Header
        TextView scheduleHeader = (TextView) findViewById(R.id.schedule_header);
        scheduleHeader.setText(team.toString() + " Schedule");

        // Image
        // Temp, replace with white background, no circle

        Bitmap img = TeamHelper.getTeamLogoCircle(getApplicationContext(), team.getLogoURL(), 0.5f);


        ImageView circ = (ImageView) findViewById(R.id.team_image);
        //circ.setLayoutParams(params);
        circ.setImageBitmap(img);

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


        for(Opponent opp : team.getScheduleList()) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(tableParams);
            row.setPadding(0, 5, 0, 5);

            scheduleTable.addView(row);

            TextView week = new TextView(this);

            String gameDate = "";
            try {
                if (!opp.getCity().equals("BYE"))
                    gameDate = sdf2.format(sdf.parse(opp.getDay()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            week.setText(gameDate);

            week.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.schedule_item));
            week.setTextAppearance(getApplicationContext(), R.style.font_thin_bold);
            week.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            week.setLayoutParams(rowParams1);

            TextView opponent = new TextView(this);

            String vs;
            if (!opp.getCity().equals("BYE")) {
                vs = opp.getCity() + " " + opp.getMascot();
            } else {
                vs = "Bye";
            }

            opponent.setText(vs);

            opponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.schedule_item));
            opponent.setTextAppearance(getApplicationContext(), R.style.font_thin_bold);
            opponent.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            opponent.setLayoutParams(rowParams2);

            row.addView(week);
            row.addView(opponent);

            if (!opp.getCity().equals("BYE")){
                ImageView alarm = new ImageView(this);
                alarm.setImageDrawable(getResources().getDrawable(R.drawable.alarm));
                alarm.setLayoutParams(rowParams1);

                alarm.setOnClickListener(new ScheduleAlarm(team, opp));

                row.addView(alarm);
            }

        }

    }

    private class ScheduleAlarm implements View.OnClickListener{

        private Team team;
        private Opponent opponent;

        public ScheduleAlarm(Team team, Opponent opponent){
            this.team = team;
            this.opponent = opponent;
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
                            alarmIntent.putExtra("opponent", opponent);
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
