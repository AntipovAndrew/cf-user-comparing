package com.antipov.cf.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author antipov.
 *         01.04.2016 12:17.
 */
@JsonIgnoreProperties
public class Contest {
    private int id;
    private String name;
    private long startTimeSeconds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(long startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }
}
