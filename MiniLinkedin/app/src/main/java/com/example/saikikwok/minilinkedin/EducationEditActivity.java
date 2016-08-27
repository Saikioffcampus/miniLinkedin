package com.example.saikikwok.minilinkedin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.saikikwok.minilinkedin.Model.Education;
import com.example.saikikwok.minilinkedin.Util.DateUtil;
import com.example.saikikwok.minilinkedin.Util.ListUtil;

import java.util.Arrays;

/**
 * Created by saikikwok on 8/26/16.
 */

public class EducationEditActivity extends AppCompatActivity{

    protected static final String KEY_EDUCATION = "education";
    private Education education;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        education = getIntent().getParcelableExtra(KEY_EDUCATION);
        if (education != null) {
            setupUI();
        }
        setTitle(education == null? "Add Education" : "Edit Education");
    }

    private void setupUI() {
        ((EditText)findViewById(R.id.education_edit_school)).setText(education.getSchool());
        ((EditText)findViewById(R.id.education_edit_start_date)).setText(DateUtil.date2String(education.getStartdate()));
        ((EditText)findViewById(R.id.education_edit_end_date)).setText(DateUtil.date2String(education.getEnddate()));
        ((EditText)findViewById(R.id.education_edit_courses)).setText(ListUtil.List2StringInTextView(education.getCourses()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    private void saveAndExit() {
        if (education == null) {
            education = new Education();
        }
        education.setSchool(((EditText)findViewById(R.id.education_edit_school)).getText().toString());
        education.setStartdate(DateUtil.string2Date(((EditText)findViewById(R.id.education_edit_start_date)).getText().toString()));
        education.setEnddate(DateUtil.string2Date(((EditText)findViewById(R.id.education_edit_end_date)).getText().toString()));
        education.setCourses(Arrays.asList((((EditText) findViewById(R.id.education_edit_courses)).getText().toString()).split("\n")));

        Intent resInt = new Intent();
        resInt.putExtra(KEY_EDUCATION, education);
        setResult(Activity.RESULT_OK, resInt);
        finish();
    }
}
