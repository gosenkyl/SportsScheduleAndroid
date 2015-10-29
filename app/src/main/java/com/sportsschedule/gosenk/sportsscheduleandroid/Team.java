package com.sportsschedule.gosenk.sportsscheduleandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desktop on 10/28/2015.
 */
public class Team {

    private Integer teamId;
    private String city;
    private String mascot;
    private String primaryColor;
    private String secondaryColor;
    private Double lonLocation;
    private Double latLocation;
    private String logoURL;
    private String teamAbbr;

    private List<Opponent> scheduleList = new ArrayList<Opponent>();

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public Double getLonLocation() {
        return lonLocation;
    }

    public void setLonLocation(Double lonLocation) {
        this.lonLocation = lonLocation;
    }

    public Double getLatLocation() {
        return latLocation;
    }

    public void setLatLocation(Double latLocation) {
        this.latLocation = latLocation;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }

    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public List<Opponent> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Opponent> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
