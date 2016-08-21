package com.sportsschedule.gosenk.sportsscheduleandroid.dto;

import java.util.Date;

public class Game extends BaseDto{

    private Team team;
    private String opponentTeamId;
    private Date time;
    private boolean homeFlag;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getOpponentTeamId() {
        return opponentTeamId;
    }

    public void setOpponentTeamId(String opponentTeamId) {
        this.opponentTeamId = opponentTeamId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isHomeFlag() {
        return homeFlag;
    }

    public void setHomeFlag(boolean homeFlag) {
        this.homeFlag = homeFlag;
    }

}
