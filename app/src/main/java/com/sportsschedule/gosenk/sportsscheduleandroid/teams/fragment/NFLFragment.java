package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.INFLListener;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.NFLAsyncTask;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;

import java.util.List;

public class NFLFragment extends Fragment implements INFLListener {

    private View nflView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(nflView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);

            NFLAsyncTask task = new NFLAsyncTask(this);
            task.execute();

            nflView = view;
        }

        return nflView;
    }

    @Override
    public void onCompleted(List<Team> nflTeamList){

        nflView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        TeamHelper.displayTeams(nflView, nflTeamList);

    }

}
