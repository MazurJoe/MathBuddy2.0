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

public class CreateActivitySGL extends AppCompatActivity {

    EditText numberOfDecimalPlaces;
    ToggleButton toggleButton;

    CheckBox additionCheckBox;
    CheckBox subtractionCheckBox;
    CheckBox multiplicationCheckBox;
    CheckBox divisionCheckBox;

    ArrayList<Character> operators = new ArrayList<>();
    public static ArrayList<Double> problemAnswers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sgl);

        additionCheckBox = (CheckBox) findViewById(R.id.add_checkBox);
        subtractionCheckBox = (CheckBox) findViewById(R.id.sub_checkBox);
        multiplicationCheckBox = (CheckBox) findViewById(R.id.mult_checkBox);
        divisionCheckBox = (CheckBox) findViewById(R.id.div_checkBox);

        numberOfDecimalPlaces = (EditText) findViewById(R.id.num_of_decimals);
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

    //checks if all fields are filled out and creates quiz from given inputs
    //if any field is not filled out, system calls emptyField method and inputs the missing field as the argument
    public void onClickStartQuiz(View v){
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

        //if toggleButton.isChecked is true, then we need to read the number of decimal places
        //if toggleButton.isChecked is false, the equations will have integers.
        if(toggleButton.isChecked()){
            ArrayList<String> problemsStrings = new ArrayList<>();
            ArrayList<Problem> problems = RandomProblemGenerator.genBasicProbSet(Integer.parseInt(numOfProblemsField.getText().toString()),
                                            operators, 2, 2,Integer.parseInt(rangeFromField.getText().toString()),
                                            Integer.parseInt(rangeToField.getText().toString()), true,
                                            Integer.parseInt(numberOfDecimalPlaces.getText().toString()));

            for(int i = 0; i< problems.size(); i++) {
                problemsStrings.add(problems.get(i).getProblem());
            }
            Intent intentQuizScreen = new Intent(CreateActivitySGL.this, QuizScreenSGL.class);
            intentQuizScreen.putExtra("problems", problemsStrings);
            startActivity(intentQuizScreen);
        }
        else{

            //for teacher RandomGenerator.createSeed
            //Long seed = RandomGenerator.getSeed
            ArrayList<String> problemsStrings = new ArrayList<>();
            ArrayList<Problem> problems = RandomProblemGenerator.genBasicProbSet(Integer.parseInt(numOfProblemsField.getText().toString()),
                                            operators, 2, 2, Integer.parseInt(rangeFromField.getText().toString()),
                                            Integer.parseInt(rangeToField.getText().toString()), false, 1);
            for(int i = 0; i< problems.size(); i++) {
                problemsStrings.add(problems.get(i).getProblem());
                problemAnswers.add(problems.get(i).getCorrectAnswer());
            }
            Intent intentQuizScreen = new Intent(CreateActivitySGL.this, QuizScreenSGL.class);
            intentQuizScreen.putExtra("problems", problemsStrings);
            startActivity(intentQuizScreen);
        }

    }

    //displays a message saying to enter value for the field that is empty
    public void emptyField(String string){
        Intent intent = new Intent(CreateActivitySGL.this, CreateQuizError.class);
        intent.putExtra("Empty Field", string);
        startActivity(intent);
    }
}
