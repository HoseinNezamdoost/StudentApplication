package com.hosein.nzd.studentapplication.model;

import com.google.gson.annotations.SerializedName;

public class Student {

    private long id;

    @SerializedName("firstName")
    private String firstname;

    @SerializedName("lastName")
    private String lastname;

    private String course;
    private String score;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
