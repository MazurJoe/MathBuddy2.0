package com.example.joe.mathbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RemoveStudentPopUp extends AppCompatActivity {
    TextView removeStudentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_student_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (.7*width), (int) (.7*height));

        removeStudentText = (TextView) findViewById(R.id.textView6);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            removeStudentText.setText(bundle.get("Student to Remove").toString());
        }
    }
}
