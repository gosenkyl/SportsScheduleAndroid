package com.sportsschedule.gosenk.sportsscheduleandroid.dto;

import java.util.ArrayList;
import java.util.List;

public class Team extends BaseDto /*implements Comparable<Team>*/{

    private String identifier;
    private League league;
    private String city;
    private String mascot;
    private String primaryColor;
    private String secondaryColor;

    private List<Game> schedule = new ArrayList<>();

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public List<Game> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Game> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString(){
        return this.getCity() + " " + this.getMascot();
    }

    /*@Override
    public int compareTo(Team another) {
        return (this.getCity() + " " + this.getMascot()).compareTo(another.getCity() + " " + another.getMascot());
    }*/
}
