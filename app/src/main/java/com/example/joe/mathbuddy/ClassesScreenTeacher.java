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


        //waits for a class in the list to be clicked so it may pass the correct data
        classesListView = findViewById(R.id.classesList_View);
        classesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassesScreenTeacher.this, IndividualClassScreenTeacher.class);
                intent.putExtra("Class Name", classesListView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });


        //Stub data for demo
        classesList.add("Frist Period Math");
        classesList.add("Second Period Math");
        classesList.add("Third Period Math");
        classesList.add("Fourth Period Math");
        classesList.add("Fith Period Math");


        adapter = new ArrayAdapter(ClassesScreenTeacher.this, android.R.layout.simple_list_item_1, classesList);
        classesListView.setAdapter(adapter);


    }

    //create class pop Up on click
    public void newClassOnClick(View v){
        startActivity(new Intent(ClassesScreenTeacher.this,ClassPopUp.class));

    }
}
