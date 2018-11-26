package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultScreenSGL extends AppCompatActivity {
    String grade;
    TextView gradeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_sgl);

        gradeView = (TextView) findViewById(R.id.textView8);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            gradeView.setText("You got "+bundle.getString("grade")+" correct!");
        }
    }

    public void onClick(View v){
        startActivity(new Intent(ResultScreenSGL.this, QuizGadesScreenSGL.class));
    }
}
