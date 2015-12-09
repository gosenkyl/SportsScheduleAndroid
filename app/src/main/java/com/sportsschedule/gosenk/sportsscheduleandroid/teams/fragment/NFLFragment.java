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
            TeamHelper.loadTeams(view, teamHolder.getNflTeamList());
            nflView = view;

            view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }

        return nflView;
    }

}
