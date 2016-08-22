package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.os.AsyncTask;

import java.util.List;

import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;

public class MLBAsyncTask extends AsyncTask<Void, Void, List<Team>>{

    private IMLBListener listener;

    public MLBAsyncTask(IMLBListener listener){
       this.listener = listener;
    }

    @Override
    protected List<Team> doInBackground(Void... params){
        boolean success = TeamHolder.loadMLBTeams();
        List<Team> mlbTeamList = TeamHolder.getMlbTeamList();
        return mlbTeamList;
    }

    @Override
    protected void onPostExecute(List<Team> mlbTeamList){
        listener.onCompleted(mlbTeamList);
    }

}
