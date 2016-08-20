package com.sportsschedule.gosenk.sportsscheduleandroid.dto;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
