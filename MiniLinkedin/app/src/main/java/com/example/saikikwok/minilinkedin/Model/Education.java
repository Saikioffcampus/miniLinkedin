package com.example.saikikwok.minilinkedin.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by saikikwok on 8/24/16.
 */

public class Education implements Parcelable {

    private String id;
    private String school;
    private Date startdate;
    private Date enddate;
    private List<String> courses;

    public Education() {
        this.id = UUID.randomUUID().toString();
    }

    public Education(String school, Date startdate, Date enddate, List<String> courses) {
        this.id = UUID.randomUUID().toString();
        this.school = school;
        this.startdate = startdate;
        this.enddate = enddate;
        this.courses = courses;
    }

    private Education(Parcel in) {
        this.id = in.readString();
        this.school = in.readString();
        this.startdate = new Date(in.readLong());
        this.enddate = new Date(in.readLong());
        this.courses = in.createStringArrayList();
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel parcel) {
            return new Education(parcel);
        }

        @Override
        public Education[] newArray(int i) {
            return new Education[i];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(school);
        parcel.writeLong(startdate.getTime());
        parcel.writeLong(enddate.getTime());
        parcel.writeStringList(courses);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
