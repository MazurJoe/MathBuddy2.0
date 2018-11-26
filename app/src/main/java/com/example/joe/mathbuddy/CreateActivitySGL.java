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
        EditText quizNameField = (EditText) findViewById(R.id.editText7);
        String quizName = quizNameField.getText().toString();
        System.out.println(quizName);
        if(quizName.matches("")) {
            emptyField("Enter a name for the quiz.");
            return;
        }

        EditText rangeFromField = (EditText) findViewById(R.id.editText3);
        if(rangeFromField.getText().toString().matches("")){
            emptyField("Enter a number in the Range From field.");
            return;
        }
        int rangeFrom = Integer.parseInt(rangeFromField.getText().toString());

        EditText rangeToField = (EditText) findViewById(R.id.editText4);
        if(rangeToField.getText().toString().matches("")){
            emptyField("Enter a number in the Range To field");
            return;
        }
        int rangeTo = Integer.parseInt(rangeToField.getText().toString());

        EditText numOfProblemsField = (EditText) findViewById(R.id.editText5);
        if(numOfProblemsField.getText().toString().matches("")){
            emptyField("Enter the number of problems for the quiz.");
            return;
        }


        startActivity(new Intent(CreateActivitySGL.this, QuizScreenSGL.class));
    }

    public void emptyField(String string){
        Intent intent = new Intent(CreateActivitySGL.this, CreateQuizError.class);
        intent.putExtra("Empty Field", string);
        startActivity(intent);
    }
}
