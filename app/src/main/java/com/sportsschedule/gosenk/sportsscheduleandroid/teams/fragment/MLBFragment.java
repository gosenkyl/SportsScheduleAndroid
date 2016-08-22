package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.IMLBListener;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.MLBAsyncTask;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHelper;

import java.util.List;

public class MLBFragment extends Fragment implements IMLBListener {

    private View mlbView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(mlbView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);

            MLBAsyncTask task = new MLBAsyncTask(this);
            task.execute();

            mlbView = view;
        }

        return mlbView;
    }

    @Override
    public void onCompleted(List<Team> mlbTeamList){

        mlbView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        TeamHelper.displayTeams(mlbView, mlbTeamList);

    }

}
