package com.antipov.cf.comparing;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ContestInfo {

    private int index;
    private int contestId;
    private String contestName;
    private String firstHandle;
    private int firstPlace;
    private String secondHandle;
    private int secondPlace;
    private DateTime dateTime;

    public ContestInfo(int contestId,
                       String contestName,
                       String firstHandle,
                       int firstPlace,
                       String secondHandle,
                       int secondPlace,
                       int index,
                       DateTime dateTime) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.firstHandle = firstHandle;
        this.firstPlace = firstPlace;
        this.secondHandle = secondHandle;
        this.secondPlace = secondPlace;
        this.index = index;
        this.dateTime = dateTime;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getFirstHandle() {
        return firstHandle;
    }

    public void setFirstHandle(String firstHandle) {
        this.firstHandle = firstHandle;
    }

    public int getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(int firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondHandle() {
        return secondHandle;
    }

    public void setSecondHandle(String secondHandle) {
        this.secondHandle = secondHandle;
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(int secondPlace) {
        this.secondPlace = secondPlace;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTrClass() {
        if(firstPlace > secondPlace) return "lose";
        else if(firstPlace < secondPlace) return "win";
        else return "draw";
    }

    public String getDateAsString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm");
        return dateTimeFormat.print(dateTime);
    }

}
