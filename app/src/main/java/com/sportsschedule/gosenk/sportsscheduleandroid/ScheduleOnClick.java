package com.sportsschedule.gosenk.sportsscheduleandroid;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ScheduleOnClick implements View.OnClickListener{

    private View fragmentView;
    private ViewGroup container;
    private LayoutInflater inflater;

    public ScheduleOnClick(LayoutInflater inflater, View fragmentView, ViewGroup container){
        this.inflater = inflater;
        this.fragmentView = fragmentView;
        this.container = container;
    }

    @Override
    public void onClick(View circleView) {

        View scheduleView = inflater.inflate(R.layout.schedule_popup, null);
        PopupWindow pw = new PopupWindow(scheduleView, 300, 300);

        TextView tv = (TextView) scheduleView.findViewById(R.id.schedule_text);
        tv.setText("TESTING!!!");

        pw.setFocusable(true);
        pw.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];
        circleView.getLocationOnScreen(location);

        pw.showAtLocation(circleView, Gravity.NO_GRAVITY, location[0], location[1] + circleView.getHeight());

    }
}
