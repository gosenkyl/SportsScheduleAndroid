package com.sportsschedule.gosenk.sportsscheduleandroid.dto;

import java.util.HashSet;
import java.util.Set;

public class League extends BaseDto{

    private Set<Team> teams = new HashSet<>(0);

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
