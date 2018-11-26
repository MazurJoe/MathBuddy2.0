package com.example.joe.mathbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class QuizGrade extends AppCompatActivity {

    Toolbar toolbar;
    int position;
    TextView quizGrade;
    String quizScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_grade);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (.5*width), (int) (.4*height));

        quizGrade = (TextView) findViewById(R.id.grade);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Quiz Name"));
            position = bundle.getInt("Position");
        }

        //Fake code for Demo
        switch (position){
            case 0:
                quizScore = "11/15";
                break;
            case 1:
                quizScore = "4/10";
                break;
            case 2:
                quizScore = "20/20";
                break;
            case 3:
                quizScore = "22/25";
                break;
            case 4:
                quizScore = "8/15";
                break;
            case 5:
                quizScore = "5/5";
                break;
            case 6:
                quizScore = "13/15";
                break;
            case 7:
                quizScore = "10/10";
                break;
            case 8:
                quizScore = "18/20";
                break;
            case 9:
                quizScore = "20/30";
                break;
            case 10:
                quizScore = "2/5";
                break;
            case 11:
                quizScore = "8/10";
                break;
            case 12:
                quizScore = "14/15";
                break;
            case 13:
                quizScore = "17/25";
                break;
            case 14:
                quizScore = "15/15";
                break;
            case 20:
                    quizScore = "71.21%";
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setTitle("Average Grade");
                    break;
        }

        quizGrade.setText(quizScore);
    }
}
