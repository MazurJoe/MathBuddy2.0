package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizScreenSGL extends AppCompatActivity {
    ArrayList<String> equations = new ArrayList<String>();
    TextView problem;
    public int numOfProblems;
    public int counter = 0;
    Button next_Finish;

    ArrayList<Double> answers = new ArrayList<>();

    EditText answer;
    ArrayList<Double> computedAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen_sgl);

        answer = (EditText) findViewById(R.id.editText8);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            equations = bundle.getStringArrayList("problems");
        }

        numOfProblems = equations.size()-1;

        problem = (TextView) findViewById(R.id.textView7);
        problem.setText(equations.get(0));

        next_Finish = (Button) findViewById(R.id.button14);
        next_Finish.setText("Next Question");

        computedAnswers = CreateActivitySGL.problemAnswers;
    }

    /**when a user clicks the button, the system first checks if the user answerd the question
     * if they did not, the system adds the correct answer + 10 to the arrayList that contains the users answers
     *
     * the system clears the answer field after a user answers
     *
     * the button reads "next question" up to question n-1. on question n, the button reads "finish"
     *
     * when the user clicks the button that reads "finish", the system grades all of the users answers by
     * comparing them to the correct answer
     *
     * the system then passes the grade the user got to the result screen
     *
    */
    public void onClick(View v) {
        if(answer.getText().toString().matches("") && counter < computedAnswers.size()){
            answers.add(computedAnswers.get(counter)+10);
        }
        else {
            answers.add(Double.parseDouble(answer.getText().toString()));
        }

        answer.setText("");

        counter++;

        if (counter < numOfProblems)
            problem.setText(equations.get(counter));

        else if (counter == numOfProblems) {
            problem.setText(equations.get(counter));
            next_Finish.setText("Finish");
        } else {
            int numCorrect = 0;
            for(int i = 0; i<computedAnswers.size(); i++){
                if(Comparator.numComparator(answers.get(i), computedAnswers.get(i))){
                    numCorrect++;
                }
            }
            Intent intent = new Intent(QuizScreenSGL.this, ResultScreenSGL.class);
            intent.putExtra("grade", ""+numCorrect+"/"+computedAnswers.size());
            startActivity(intent);
        }
    }
}
