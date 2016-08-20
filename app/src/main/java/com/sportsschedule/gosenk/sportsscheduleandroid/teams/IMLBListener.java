package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import java.util.List;

import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;

public interface IMLBListener {
    void onCompleted(List<Team> mlbTeamList);
}
