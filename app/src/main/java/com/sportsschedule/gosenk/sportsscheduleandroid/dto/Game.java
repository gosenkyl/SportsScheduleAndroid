package com.sportsschedule.gosenk.sportsscheduleandroid.dto;

import java.util.Date;

public class Game extends BaseDto{

    private Team team;
    private Team opponentTeam;
    private Date time;
    private boolean homeFlag;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(Team opponentTeam) {
        this.opponentTeam = opponentTeam;
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
