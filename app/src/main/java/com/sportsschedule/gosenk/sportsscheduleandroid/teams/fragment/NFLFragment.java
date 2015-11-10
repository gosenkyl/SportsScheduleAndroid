package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.schedule.ScheduleActivity;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHolder;

public class NFLFragment extends Fragment {

    private static final int COLUMNS = 3;

    private View nflView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(nflView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);
            TeamHolder teamHolder = TeamHolder.getInstance();
            loadNFLTeams(inflater, view, teamHolder);
            nflView = view;
        }

        return nflView;
    }

    private void loadNFLTeams(LayoutInflater inflater, View view, TeamHolder teamHolder){
        // Table of NFL teams
        TableRow row = null;
        TableLayout layout = (TableLayout) view.findViewById(R.id.team_list);

        int i = 0;
        int colCount = 0;
        for(final Team team : teamHolder.getNflTeamList()){
            if(i % 3 == 0){
                colCount = 0;
                row = new TableRow(view.getContext());
                layout.addView(row);
            }

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            LinearLayout linear = new LinearLayout(view.getContext());
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(lp);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            Bitmap img = TeamHelper.getTeamLogoCircle(getContext(), team.getLogoURL(), 0.5f);

            ImageView circ = new ImageView(view.getContext());
            circ.setLayoutParams(params);
            circ.setImageBitmap(img);

            circ.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent scheduleIntent = new Intent(v.getContext(), ScheduleActivity.class);
                    scheduleIntent.putExtra("team", team);
                    startActivity(scheduleIntent);
                }

            });

            linear.addView(circ);

            // NFL Team Name
            TextView text = new TextView(view.getContext());
            text.setLayoutParams(params);
            text.setText(team.getCity() + " \n " + team.getMascot());

            text.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            text.setTextAppearance(view.getContext(), R.style.font_thin_bold);
            text.setGravity(Gravity.CENTER);

            linear.addView(text);

            row.addView(linear);

            i++;
            colCount++;
        }

        for (int j = COLUMNS - colCount; j > 0; j--) {
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            LinearLayout linear = new LinearLayout(view.getContext());
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(lp);

            row.addView(linear);
        }
    }

}
