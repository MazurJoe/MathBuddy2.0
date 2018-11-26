package com.example.joe.mathbuddy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.Toolbar;

public class IndividualClassScreenTeacher extends AppCompatActivity {

    ListView studentsListView;
    List studentsList = new ArrayList();
    ArrayAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_class_screen);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar1);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Class Name"));
        }

        studentsListView = findViewById(R.id.studentsList_View);

        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndividualClassScreenTeacher.this, StudentGradeTeacherView.class);
                intent.putExtra("Student Name", studentsListView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        studentsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndividualClassScreenTeacher.this, RemoveStudentPopUp.class);
                intent.putExtra("Student to Remove", "Are you sure you want to remove "+studentsListView.getItemAtPosition(position).toString()+" from the class?");
                startActivity(intent);

                return true;
            }
        });



        studentsList.add("Student 1");
        studentsList.add("Student 2");
        studentsList.add("Student 3");
        studentsList.add("Student 4");
        studentsList.add("Student 5");
        studentsList.add("Student 6");
        studentsList.add("Student 7");
        studentsList.add("Student 8");
        studentsList.add("Student 9");
        studentsList.add("Student 10");
        studentsList.add("Student 11");
        studentsList.add("Student 12");
        studentsList.add("Student 13");
        studentsList.add("Student 14");
        studentsList.add("Student 15");
        studentsList.add("Student 16");
        studentsList.add("Student 17");
        studentsList.add("Student 18");
        studentsList.add("Student 19");
        studentsList.add("Student 20");
        studentsList.add("Student 21");
        studentsList.add("Student 22");
        studentsList.add("Student 23");
        studentsList.add("Student 24");

        adapter = new ArrayAdapter(IndividualClassScreenTeacher.this, android.R.layout.simple_list_item_1, studentsList);
        studentsListView.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(IndividualClassScreenTeacher.this, ClassesScreenTeacher.class));
        }
            return true;
    }

    public void onClickAdd(View v){
        startActivity(new Intent(IndividualClassScreenTeacher.this, AddStudentPopUp.class));
    }

    public void onClick(View v){
        startActivity(new Intent( IndividualClassScreenTeacher.this, CreateActivityTeacher.class));
    }
}
