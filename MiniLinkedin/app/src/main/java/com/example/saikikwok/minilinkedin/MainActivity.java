package com.example.saikikwok.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.saikikwok.minilinkedin.Model.BasicInfo;
import com.example.saikikwok.minilinkedin.Model.Education;
import com.example.saikikwok.minilinkedin.R;
import com.example.saikikwok.minilinkedin.Util.ListUtil;
import com.example.saikikwok.minilinkedin.Util.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_EDUCATION_EDIT = 100;

    private static final String RESUME_INFO_EDUCATIONS = "educations";

    private BasicInfo basicInfo;
    private List<Education> educations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        fakeData();
        loadData();
        setupUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_EDUCATION_EDIT && resultCode == Activity.RESULT_OK) {
            Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
            updateEducation(education);
        }
    }

    private void updateEducation(Education education) {
        boolean found = false;
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (TextUtils.equals(e.getId(), education.getId())) {
                educations.set(i, education);
                found = true;
                break;
            }
        }
        if (!found) {
            educations.add(education);
        }
        ModelUtils.save(this, RESUME_INFO_EDUCATIONS, educations);
        setupEducation();
    }

    private void setupEducation() {
        LinearLayout educationLayout = (LinearLayout)findViewById(R.id.education_section);
        educationLayout.removeAllViews();
        for (Education education : educations) {
            educationLayout.addView(getEducation(education));
        }
    }

    private View getEducation(final Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        String startdateInString = sdf.format(education.getStartdate());
        String enddateInString = sdf.format(education.getEnddate());
        String dateInString = " (" + startdateInString + " - " + enddateInString + ")";
        ((TextView)view.findViewById(R.id.school)).setText(education.getSchool());
        ((TextView)view.findViewById(R.id.school_period)).setText(dateInString);
        ((TextView)view.findViewById(R.id.courses)).setText(ListUtil.List2String(education.getCourses()));

        // pass Education instance to EducationEditActivity
        view.findViewById(R.id.edit_education_info_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDUCATION, education);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });

        return view;
    }

    private void setupUI() {
        setContentView(R.layout.activity_main);
        TextView thenameview = ((TextView)findViewById(R.id.basic_name));
        thenameview.setText(basicInfo.getName());
        ((TextView)findViewById(R.id.email)).setText(basicInfo.getEmail());
        setupEducation();

        ImageButton addEducationBtn = (ImageButton) findViewById(R.id.edit_education_btn);
        addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });
    }

    private void loadData() {
        this.basicInfo = new BasicInfo("Saiki Kwok", "saiki@xxx.com");
        List<Education> savedEducation = ModelUtils.read(this, RESUME_INFO_EDUCATIONS, new TypeToken<List<Education>>(){});
        educations = savedEducation == null? new LinkedList<Education>() : savedEducation;

    }

    private void fakeData() {
        this.basicInfo = new BasicInfo("Saiki Kwok", "saiki@xxx.com");
        this.educations = new LinkedList<>();
        Education education1 = null;
        Education education2 = null;
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
            education1 = new Education("NEU", startdate, enddate, courses);
            education2 = new Education("GDUFS", startdate, enddate, courses);
            educations.add(education1);
            educations.add(education2);
        } catch (ParseException e) {
            education1 = new Education("NEU", new Date(), new Date(), courses);
            education2 = new Education("GDUFS", new Date(), new Date(), courses);
            educations.add(education1);
            educations.add(education2);
        }


    }
}
