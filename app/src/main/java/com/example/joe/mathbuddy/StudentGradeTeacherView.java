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
import java.util.Comparator;
import java.util.List;

public class StudentGradeTeacherView extends AppCompatActivity {


    Toolbar toolbar;
    ListView quizListView;
    List quizList = new ArrayList();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade_teacher_view);

        //gets name of the student clicked on and changes the text in the toolbar
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Student Name"));
        }

        quizListView = findViewById(R.id.quizList_View);

        //displays the quiz grade of the quiz clicked on
        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(StudentGradeTeacherView.this, QuizGrade.class);
                intent.putExtra("Quiz Name", quizListView.getItemAtPosition(position).toString());
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });


        quizList.add("Addition Quiz 1");
        quizList.add("Multiplication Quiz 1");
        quizList.add("Addition Quiz 2");
        quizList.add("Subtraction Quiz 1");
        quizList.add("Subtraction Quiz 2");
        quizList.add("Division Quiz 1");
        quizList.add("Division Quiz 2");
        quizList.add("Division Quiz 3");
        quizList.add("Multiplication Quiz 2");
        quizList.add("Multiplication Quiz 3");
        quizList.add("Multiplication Quiz 4");
        quizList.add("Addition Quiz 3");
        quizList.add("Addition Quiz 4");
        quizList.add("Section Test 1");
        quizList.add("Section Test 2");

        adapter = new ArrayAdapter(StudentGradeTeacherView.this, android.R.layout.simple_list_item_1, quizList);
        adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                return first.compareTo(second);
            }
        });
            quizListView.setAdapter(adapter);
    }

    public void onClick(View v){
        Intent intent = new Intent(StudentGradeTeacherView.this, QuizGrade.class);
        intent.putExtra("Position", 20);
        startActivity(intent);
    }


}
