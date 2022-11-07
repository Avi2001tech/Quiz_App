package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FinsihQuiz extends AppCompatActivity {

    TextView resultText;
    int correct, wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsih_quiz);


        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);
        resultText = findViewById(R.id.resultText);

        resultText.setText(correct + "/"+ "10");


    }
}