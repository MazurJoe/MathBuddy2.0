package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View v){
        EditText userText = (EditText) findViewById(R.id.editText);

        String nameOfUser = userText.getText().toString();

        if(nameOfUser.matches("teacher")){
            startActivity(new Intent(Login.this, ClassesScreenTeacher.class));
        }
        else if(nameOfUser.matches("student")){
            startActivity(new Intent(Login.this, ClassesScreenStudent.class));
        }
        else if(nameOfUser.matches("SGL")){
            startActivity(new Intent(Login.this, CreateActivitySGL.class));

        }


    }
}
