package com.example.model;

import java.sql.*;

public class Activity {
    private String activity;
    private Time startTime;
    private Time endTime;
    private Date date;

    public Time getEndTime() {
		return endTime;
	}

	public Date getDate() {
		return date;
	}

    public String getActivity() {
        return activity;
    }

    public Time getStartTime() {
        return startTime;
    }
    
 // Public constructor
    public Activity(String activity, Time startTime, Time endTime, Date date) {
        this.activity = activity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }
}
