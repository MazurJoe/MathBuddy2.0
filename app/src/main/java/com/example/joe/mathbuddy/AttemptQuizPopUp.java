package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class AttemptQuizPopUp extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_quiz_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (.6*width), (int) (.4*height));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar6);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Quiz Name"));
        }
    }

    public void onClick(View v){
        startActivity(new Intent(AttemptQuizPopUp.this, QuizScreen.class));
    }
}
