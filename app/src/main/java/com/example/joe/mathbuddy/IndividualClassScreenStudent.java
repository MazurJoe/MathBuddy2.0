package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class IndividualClassScreenStudent extends AppCompatActivity {

    ListView completedQuizListView;
    List completedQuizList = new ArrayList();
    ArrayAdapter adapterCompletedQuiz;

    ListView activeQuizListView;
    List activeQuizList = new ArrayList();
    ArrayAdapter adapterActiveQuiz;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indevidual_class_screen_student);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar3);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Class Name"));
        }

        completedQuizListView = findViewById(R.id.completedQuizList_View);
        completedQuizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndividualClassScreenStudent.this, QuizGrade.class);
                intent.putExtra("Quiz Name", completedQuizListView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        activeQuizListView = findViewById(R.id.activeQuizList_View);
          activeQuizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(IndividualClassScreenStudent.this, AttemptQuizPopUp.class);
                 intent.putExtra("Quiz Name", activeQuizListView.getItemAtPosition(position).toString());
                 startActivity(intent);
              }
         });

        completedQuizList.add("Quiz 1");
        completedQuizList.add("Quiz 2");
        completedQuizList.add("Quiz 3");
        completedQuizList.add("Quiz 4");
        completedQuizList.add("Quiz 5");
        completedQuizList.add("Quiz 6");
        completedQuizList.add("Quiz 7");
        completedQuizList.add("Quiz 8");
        completedQuizList.add("Quiz 9");
        completedQuizList.add("Quiz 10");
        completedQuizList.add("Quiz 11");
        completedQuizList.add("Quiz 12");
        completedQuizList.add("Quiz 13");
        completedQuizList.add("Quiz 14");
        completedQuizList.add("Quiz 15");
        completedQuizList.add("Quiz 16");
        completedQuizList.add("Quiz 17");
        completedQuizList.add("Quiz 18");
        completedQuizList.add("Quiz 19");
        completedQuizList.add("Quiz 20");

        activeQuizList.add("Quiz 21");
        activeQuizList.add("Quiz 22");
        activeQuizList.add("Quiz 23");
        activeQuizList.add("Quiz 24");
        activeQuizList.add("Quiz 25");
        activeQuizList.add("Quiz 26");
        activeQuizList.add("Quiz 27");
        activeQuizList.add("Quiz 28");
        activeQuizList.add("Quiz 29");
        activeQuizList.add("Quiz 30");
        activeQuizList.add("Quiz 31");
        activeQuizList.add("Quiz 32");
        activeQuizList.add("Quiz 33");
        activeQuizList.add("Quiz 34");
        activeQuizList.add("Quiz 35");
        activeQuizList.add("Quiz 36");
        activeQuizList.add("Quiz 37");
        activeQuizList.add("Quiz 38");
        activeQuizList.add("Quiz 39");
        activeQuizList.add("Quiz 40");

        adapterCompletedQuiz = new ArrayAdapter(IndividualClassScreenStudent.this, android.R.layout.simple_list_item_1, completedQuizList);
        completedQuizListView.setAdapter(adapterCompletedQuiz);

        adapterActiveQuiz = new ArrayAdapter(IndividualClassScreenStudent.this, android.R.layout.simple_list_item_1, activeQuizList);
        activeQuizListView.setAdapter(adapterActiveQuiz);
    }
}
