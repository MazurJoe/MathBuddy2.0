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

public class ClassesScreenTeacher extends AppCompatActivity {

    ListView classesListView;
    List classesList = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_screen_teacher);


        classesListView = findViewById(R.id.classesList_View);
        classesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassesScreenTeacher.this, IndividualClassScreenTeacher.class);
                intent.putExtra("Class Name", classesListView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });



        classesList.add("Class 1");
        classesList.add("Class 2");
        classesList.add("Class 3");
        classesList.add("Class 4");
        classesList.add("Class 5");
        classesList.add("Class 6");
        classesList.add("Class 7");
        classesList.add("Class 8");
        classesList.add("Class 9");
        classesList.add("Class 10");
        classesList.add("Class 11");
        classesList.add("Class 12");
        classesList.add("Class 13");
        classesList.add("Class 14");
        classesList.add("Class 15");
        classesList.add("Class 16");


        adapter = new ArrayAdapter(ClassesScreenTeacher.this, android.R.layout.simple_list_item_1, classesList);
        classesListView.setAdapter(adapter);


    }

    public void newClassOnClick(View v){
        startActivity(new Intent(ClassesScreenTeacher.this,ClassPopUp.class));

    }
}
