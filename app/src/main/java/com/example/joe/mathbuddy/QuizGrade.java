package com.example.joe.mathbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

public class QuizGrade extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_grade);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (.4*width), (int) (.3*height));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Quiz Name"));
        }
    }
}
