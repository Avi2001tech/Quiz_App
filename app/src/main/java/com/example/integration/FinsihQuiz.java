package com.example.integration;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.TimeUnit;

public class FinsihQuiz extends AppCompatActivity {

    TextView resultText,total_time_taken,correctCount,wrongCount,unAttempted,exit;
    int correct, wrong;
    Toolbar toolbar;
    ImageView back_button;
    long timeTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsih_quiz);


        correct = getIntent().getIntExtra("correct",0);
        int wrong = getIntent().getIntExtra("wrong",0);
        int unattempt = 10 - correct - wrong;

        resultText = findViewById(R.id.resultText);
        toolbar=findViewById(R.id.toolbar);
        back_button=findViewById(R.id.back_button);
        exit = findViewById(R.id.exit);
        total_time_taken=findViewById(R.id.time_Taken);
        correctCount=findViewById(R.id.correct_count_text);
        wrongCount=findViewById(R.id.wrong_count_text);
        unAttempted=findViewById(R.id.unattempt_count_text);

//        backToHome = findViewById(R.id.backToHome);


        resultText.setText(correct + "");
        correctCount.setText(correct +"");
        wrongCount.setText(wrong +"");
        unAttempted.setText(unattempt + "");

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinsihQuiz.this.finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(FinsihQuiz.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        timeTaken = getIntent().getLongExtra("TimeTaken",0);
        String time = String.format("%02d:%02d min", TimeUnit.MILLISECONDS.toMinutes(timeTaken),
                TimeUnit.MILLISECONDS.toSeconds(timeTaken)
                        - TimeUnit.MINUTES.toSeconds((TimeUnit.MILLISECONDS.toMinutes(timeTaken))));

        total_time_taken.setText(time);





    }
}