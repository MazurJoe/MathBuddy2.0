package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QuizGadesScreenSGL extends AppCompatActivity {

    ListView quizListView;
    List quizList = new ArrayList();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_gades_screen_sgl);

        quizListView = findViewById(R.id.SGLQuiz_List);
        quizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(QuizGadesScreenSGL.this, QuizGrade.class);
                intent.putExtra("Quiz Name", quizListView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        quizList.add("Quiz 1");
        quizList.add("Quiz 2");
        quizList.add("Quiz 3");
        quizList.add("Quiz 4");
        quizList.add("Quiz 5");
        quizList.add("Quiz 6");
        quizList.add("Quiz 7");
        quizList.add("Quiz 8");
        quizList.add("Quiz 9");
        quizList.add("Quiz 10");
        quizList.add("Quiz 11");
        quizList.add("Quiz 12");
        quizList.add("Quiz 13");
        quizList.add("Quiz 14");
        quizList.add("Quiz 15");
        quizList.add("Quiz 16");
        quizList.add("Quiz 17");
        quizList.add("Quiz 18");
        quizList.add("Quiz 19");
        quizList.add("Quiz 20");
        quizList.add("Quiz 21");
        quizList.add("Quiz 22");
        quizList.add("Quiz 23");
        quizList.add("Quiz 24");

        adapter = new ArrayAdapter(QuizGadesScreenSGL.this, android.R.layout.simple_list_item_1, quizList);
        quizListView.setAdapter(adapter);
    }

    public void onClickCreateQuiz(View v){
        startActivity(new Intent(QuizGadesScreenSGL.this, CreateActivitySGL.class));
    }
}
