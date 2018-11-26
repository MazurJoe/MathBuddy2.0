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
import java.util.Comparator;
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

        //gets the name of the class that was clicked on and changes text in the toolbar
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            toolbar = (Toolbar) findViewById(R.id.toolbar1);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(bundle.getString("Class Name"));
        }

        studentsListView = findViewById(R.id.studentsList_View);

        //when a student is clicked, their individual student screen is displayed
        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndividualClassScreenTeacher.this, StudentGradeTeacherView.class);
                intent.putExtra("Student Name", studentsListView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        //on long click remove student pop up is displayed
        studentsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndividualClassScreenTeacher.this, RemoveStudentPopUp.class);
                intent.putExtra("Student to Remove", "Are you sure you want to remove "+studentsListView.getItemAtPosition(position).toString()+" from the class?");
                startActivity(intent);

                return true;
            }
        });

        //fake data for demo
        studentsList.add("Kevin Smith");
        studentsList.add("Tom Berry");
        studentsList.add("Katlin Davis");
        studentsList.add("Carlos Robbins");
        studentsList.add("Damon Patterson");
        studentsList.add("Lindsay Brady");
        studentsList.add("Beth Turner");
        studentsList.add("Gerald Santiago");
        studentsList.add("Lynette Morris");
        studentsList.add("David Osborne");
        studentsList.add("Candace Cannon");
        studentsList.add("Charles Francis");
        studentsList.add("Ken Jones");
        studentsList.add("Howard Day");
        studentsList.add("Chris Rivera");
        studentsList.add("Jean George");
        studentsList.add("Dewey Mccormick");
        studentsList.add("Gail Mcguire");
        studentsList.add("Johnny Lee");
        studentsList.add("Jenna Leonard");

        adapter = new ArrayAdapter(IndividualClassScreenTeacher.this, android.R.layout.simple_list_item_1, studentsList);
        adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                return first.compareTo(second);
            }
        });
        studentsListView.setAdapter(adapter);

    }

    //if a user creates a class and then hits the back button, this prevents the create class popup from reappearing
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(IndividualClassScreenTeacher.this, ClassesScreenTeacher.class));
        }
            return true;
    }

    //displays the add student popup
    public void onClickAdd(View v){
        startActivity(new Intent(IndividualClassScreenTeacher.this, AddStudentPopUp.class));
    }

    //system goes to the create activity for teacher screen
    public void onClick(View v){
        startActivity(new Intent( IndividualClassScreenTeacher.this, CreateActivityTeacher.class));
    }
}
