package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizScreen extends AppCompatActivity {

    ArrayList<String> equations = new ArrayList<String>();
    TextView problem;
    public int numOfProblems;
    public int counter = 0;
    Button next_Finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        equations.add("17 + 9");
        equations.add("13 + 1");
        equations.add("3 + 7");
        equations.add("6 + 1");
        equations.add("9 + 0");
        equations.add("14 + 11");

        numOfProblems = equations.size()-1;

        problem = (TextView) findViewById(R.id.textView7);
        problem.setText(equations.get(0));

        next_Finish = (Button) findViewById(R.id.next_Finish);
        next_Finish.setText("Next Question");


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
    public void onClick(View v){
        counter++;

        if(counter < numOfProblems)
            problem.setText(equations.get(counter));

        else if(counter == numOfProblems){
            problem.setText(equations.get(counter));
            next_Finish.setText("Finish");
        }

        else{
            startActivity(new Intent(QuizScreen.this, ResultScreen.class));
        }

    }
}
