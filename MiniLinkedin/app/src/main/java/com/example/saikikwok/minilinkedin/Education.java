package com.example.saikikwok.minilinkedin;

import java.util.Date;
import java.util.List;

/**
 * Created by saikikwok on 8/24/16.
 */

public class Education {

    private String school;
    private Date startdate;
    private Date enddate;
    private List<String> courses;

    public Education(String school, Date startdate, Date enddate, List<String> courses) {
        this.school = school;
        this.startdate = startdate;
        this.enddate = enddate;
        this.courses = courses;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
