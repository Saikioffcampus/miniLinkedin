package com.example.saikikwok.minilinkedin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.saikikwok.minilinkedin.Util.ListUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BasicInfo basicInfo;
    private Education education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fakeData();
        setupUI();
    }

    private void setupUI() {

        TextView thenameview = ((TextView)findViewById(R.id.basic_name));
        thenameview.setText(basicInfo.getName());
        ((TextView)findViewById(R.id.email)).setText(basicInfo.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        String startdateInString = sdf.format(education.getStartdate());
        String enddateInString = sdf.format(education.getEnddate());
        String dateInString = " (" + startdateInString + " - " + enddateInString + ")";
        ((TextView)findViewById(R.id.school)).setText(education.getSchool());
        ((TextView)findViewById(R.id.school_period)).setText(dateInString);
        ((TextView)findViewById(R.id.courses)).setText(ListUtil.List2String(education.getCourses()));

    }

    private void fakeData() {
        this.basicInfo = new BasicInfo("Saiki Kwok", "saiki@xxx.com");
        List<String> courses = new LinkedList<>();
        courses.add("Computer System");
        courses.add("Algorithm");
        courses.add("Networking");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        String startdateInString = "09-2015";
        String enddateInString = "12-2017";
        try {
            Date startdate = sdf.parse(startdateInString);
            Date enddate = sdf.parse(enddateInString);
            education = new Education("NEU", startdate, enddate, courses);
        } catch (ParseException e) {
            education = new Education("NEU", new Date(), new Date(), courses);
        }


    }
}
