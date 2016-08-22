package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.os.AsyncTask;

import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;

import java.util.List;

public class NFLAsyncTask extends AsyncTask<Void, Void, List<Team>>{

    private INFLListener listener;

    public NFLAsyncTask(INFLListener listener){
       this.listener = listener;
    }

    @Override
    protected List<Team> doInBackground(Void... params){
        boolean success = TeamHolder.loadNFLTeams();
        List<Team> nflTeamList = TeamHolder.getNflTeamList();
        return nflTeamList;
    }

    @Override
    protected void onPostExecute(List<Team> nflTeamList){
        listener.onCompleted(nflTeamList);
    }

}
