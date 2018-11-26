package com.example.joe.mathbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

public class CreateQuizError extends AppCompatActivity {
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz_error);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (.5*width), (int) (.2*height));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            error = (TextView) findViewById(R.id.textView4);
            error.setText(bundle.getString("Empty Field"));
        }
    }
}
