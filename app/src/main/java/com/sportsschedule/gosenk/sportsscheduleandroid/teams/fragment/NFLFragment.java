package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHolder;

public class NFLFragment extends Fragment {

    private static final int COLUMNS = 3;

    private View nflView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(nflView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);
            TeamHelper.loadTeams(view, TeamHolder.getNflTeamList());
            nflView = view;

            view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }

        return nflView;
    }

}
