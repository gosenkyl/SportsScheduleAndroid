package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.os.AsyncTask;

import java.util.List;

public class MLBAsyncTask extends AsyncTask<Void, Void, List<Team>>{

    private IMLBListener listener;

    public MLBAsyncTask(IMLBListener listener){
       this.listener = listener;
    }

    @Override
    protected List<Team> doInBackground(Void... params){

        TeamHolder teamHolder = TeamHolder.getInstance();
        List<Team> mlbTeamList = teamHolder.getMlbTeamList();

        return mlbTeamList;
    }

    @Override
    protected void onPostExecute(List<Team> mlbTeamList){
        listener.onCompleted(mlbTeamList);
    }

}
