package com.example.integration;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class FinsihQuiz extends AppCompatActivity {

    TextView resultText;
    int correct, wrong;
    Button backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finsih_quiz);


        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);
        resultText = findViewById(R.id.resultText);
        backToHome = findViewById(R.id.backToHome);

        resultText.setText(correct + "/"+ "10");

//        winnerText = findViewById(R.id.winnerText);
//        db = FirebaseFirestore.getInstance();
//        db.collection("qqq").document("q1");
//
//
//        DocumentReference docRef = db.collection("qqq").document("q1");
//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                ModelClass qn = documentSnapshot.toObject(ModelClass.class);
//                winnerText.setText(qn.getoA());
//            }
//        });



//        winnerText.setText(news);


        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinsihQuiz.this, MainActivity.class);
                startActivity(i);
            }
        });








    }
}