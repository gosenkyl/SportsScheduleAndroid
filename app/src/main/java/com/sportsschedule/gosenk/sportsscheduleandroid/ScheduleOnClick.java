package com.sportsschedule.gosenk.sportsscheduleandroid;


import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleOnClick implements View.OnClickListener{

    private LayoutInflater inflater;
    private Team team;
    private View fragmentView;

    public ScheduleOnClick(LayoutInflater inflater, View fragmentView, Team team){
        this.inflater = inflater;
        this.fragmentView = fragmentView;
        this.team = team;
    }

    @Override
    public void onClick(View circleView) {

        View scheduleView = inflater.inflate(R.layout.schedule_popup, null);

        PopupWindow pw = new PopupWindow(scheduleView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        ColorDrawable cd = new ColorDrawable();
        cd.setColor(fragmentView.getResources().getColor(R.color.white));

        pw.setBackgroundDrawable(cd);

        ListView schedList = (ListView) scheduleView.findViewById(R.id.schedule_list);

        List<String> schedItems = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

        for(Opponent opp : team.getScheduleList()){

            String gameDate = "";
            try{
                if(!opp.getCity().equals("BYE"))
                    gameDate = sdf2.format(sdf.parse(opp.getDay()));
            } catch(Exception e){
                e.printStackTrace();
            }

            String tv;
            if(!opp.getCity().equals("BYE")){
                tv = opp.getCity() + " " + opp.getMascot() + " " + gameDate;
            } else {
                tv = "Bye";
            }

                schedItems.add(tv);
        }

        ArrayAdapter<String> adpt = new ArrayAdapter<String>(fragmentView.getContext(), R.layout.schedule_item, schedItems);
        schedList.setAdapter(adpt);

        pw.showAtLocation(circleView, Gravity.CENTER, 0, 0);

    }
}
