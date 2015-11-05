package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import java.io.Serializable;

/**
 * Created by Desktop on 10/28/2015.
 */
public class Opponent implements Serializable{

    private String eid;
    private String day;
    private String time;
    private String city;
    private String mascot;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
