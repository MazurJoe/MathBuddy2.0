package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreateActivityTeacher extends AppCompatActivity {

    EditText numberOfDecimalPlaces;
    ToggleButton toggleButton;

    CheckBox additionCheckBox;
    CheckBox subtractionCheckBox;
    CheckBox multiplicationCheckBox;
    CheckBox divisionCheckBox;

    ArrayList<Character> operators = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);

        additionCheckBox = (CheckBox) findViewById(R.id.add_checkBox);
        subtractionCheckBox = (CheckBox) findViewById(R.id.sub_checkBox);
        multiplicationCheckBox = (CheckBox) findViewById(R.id.mult_checkBox);
        divisionCheckBox = (CheckBox) findViewById(R.id.div_checkBox);

        numberOfDecimalPlaces = (EditText) findViewById(R.id.editText10);
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

    public void onClickAssign(View v) throws InterruptedException {
        EditText quizNameField = (EditText) findViewById(R.id.quiz_name);
        String quizName = quizNameField.getText().toString();
        System.out.println(quizName);
        if(quizName.matches("")) {
            emptyField("Enter a name for the quiz.");
            return;
        }

        EditText rangeFromField = (EditText) findViewById(R.id.range_from);
        if(rangeFromField.getText().toString().matches("")){
            emptyField("Enter a number in the Range From field.");
            return;
        }
        int rangeFrom = Integer.parseInt(rangeFromField.getText().toString());

        EditText rangeToField = (EditText) findViewById(R.id.range_to);
        if(rangeToField.getText().toString().matches("")){
            emptyField("Enter a number in the Range To field");
            return;
        }
        int rangeTo = Integer.parseInt(rangeToField.getText().toString());

        EditText numOfProblemsField = (EditText) findViewById(R.id.num_of_problems);
        if(numOfProblemsField.getText().toString().matches("")){
            emptyField("Enter the number of problems for the quiz.");
            return;
        }
        int numOfProblems = Integer.parseInt(numOfProblemsField.getText().toString());

        if(additionCheckBox.isChecked()){
            operators.add('+');
        }
        if(subtractionCheckBox.isChecked()){
            operators.add('-');
        }
        if(multiplicationCheckBox.isChecked()){
            operators.add('*');
        }
        if(divisionCheckBox.isChecked()){
            operators.add('/');
        }

        if(toggleButton.isChecked()){

        }
        else{

        }

        TimeUnit.SECONDS.sleep(2);
        startActivity(new Intent(CreateActivityTeacher.this, ClassesScreenTeacher.class));
    }

    public void emptyField(String string){
        Intent intent = new Intent(CreateActivityTeacher.this, CreateQuizError.class);
        intent.putExtra("Empty Field", string);
        startActivity(intent);
    }



}
