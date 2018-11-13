package com.example.joe.mathbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

public class ClassPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (.7*width), (int) (.7*height));
    }

    public void createClassOnClick(View v){

        EditText className = (EditText) findViewById(R.id.className);
        String nameOfClass = className.getText().toString();

        if(nameOfClass.matches("")){
            startActivity(new Intent(ClassPopUp.this, ClassNameNullError.class));
        }
        else {
            Intent intent = new Intent(ClassPopUp.this, IndividualClassScreenTeacher.class);
            intent.putExtra("Class Name", nameOfClass);
            startActivity(intent);
        }
    }
}
