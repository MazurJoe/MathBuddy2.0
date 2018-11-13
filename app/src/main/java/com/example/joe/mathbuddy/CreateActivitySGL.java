package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class CreateActivitySGL extends AppCompatActivity {

    EditText numberOfDecimalPlaces;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sgl);

        numberOfDecimalPlaces = (EditText) findViewById(R.id.editText6);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    numberOfDecimalPlaces.setVisibility(View.VISIBLE);
                }
                else{
                    numberOfDecimalPlaces.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onClickStartQuiz(View v){
        startActivity(new Intent(CreateActivitySGL.this, QuizScreen.class));
    }
}
