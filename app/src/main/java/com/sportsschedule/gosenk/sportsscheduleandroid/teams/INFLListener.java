package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;

import java.util.List;

public interface INFLListener {
    void onCompleted(List<Team> nflTeamList);
}
