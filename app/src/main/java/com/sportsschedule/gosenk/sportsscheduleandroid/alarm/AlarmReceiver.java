package com.sportsschedule.gosenk.sportsscheduleandroid.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Opponent;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){

        Team team = (Team) intent.getSerializableExtra("team");
        Opponent opponent = (Opponent) intent.getSerializableExtra("opponent");

        Intent notificationIntent = new Intent(context, ScheduleAlarm.class);
        notificationIntent.putExtra("team", team);
        notificationIntent.putExtra("opponent", opponent);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), notificationIntent, 0);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new Notification.Builder(context)
                .setContentTitle(team.getCity() + " " + team.getMascot())
                .setContentText(team.getCity() + " " + team.getMascot() + " vs " + opponent.getCity() + " " + opponent.getMascot() + " " + opponent.getDay() + " " + opponent.getTime())
                .setSound(soundUri)
                .setLights(Color.LTGRAY, 1000, 500)
                .setSmallIcon(R.drawable.ic_controller)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true).build();

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(0, notification);

    }

}
