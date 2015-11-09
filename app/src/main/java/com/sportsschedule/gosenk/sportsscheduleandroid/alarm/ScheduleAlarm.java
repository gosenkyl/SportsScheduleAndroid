package com.sportsschedule.gosenk.sportsscheduleandroid.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ScheduleAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "ALARM TEST!!!", Toast.LENGTH_LONG).show();

    }
}
