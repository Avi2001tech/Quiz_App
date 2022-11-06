package com.example.integration;

//import static com.example.integration.SplashActivity.listOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

//    CountDownTimer countDownTimer;
//    int timerValue =20;

    List<ModelClass> allQuestionsList;
    ModelClass modelclass;
    int index=0;
    int score=0;
    TextView qText, optiona, optionb, optionc, optiond;
    CardView cardOA,cardOB, cardOC, cardOD;
    ArrayList<ModelClass> listOfQ;
    TextView scoreUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Hooks();

        listOfQ =  new ArrayList<>();
        listOfQ.add(new ModelClass("Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("2222Approximately what fraction of world's populationlives in india","222","333","444","555","222"));
        listOfQ.add(new ModelClass("3333Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("4444Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("5555Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("6666Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("7777Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("8888Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("9999Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));
        listOfQ.add(new ModelClass("10101010Approximately what fraction of world's populationlives in india","1/6","1/10","1/3","1/2","1/6"));




        allQuestionsList = listOfQ;
        modelclass = listOfQ.get(index);

        setAllData();

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index++;
                scoreUpdate.setText((index+1)+"/"+"10");
                allQuestionsList = listOfQ;
                modelclass = listOfQ.get(index);
                setAllData();
                if(index+1 == 10){
                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
                }

            }
        });

        findViewById(R.id.end_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuestionActivity.this, FinsihQuiz.class);
                startActivity(i);
            }
        });


    }

    private void setAllData() {
        qText.setText(modelclass.getQuestion());
        optiona.setText(modelclass.getoA());
        optionb.setText(modelclass.getoB());
        optionc.setText(modelclass.getoC());
        optiond.setText(modelclass.getoD());
    }

    private void Hooks() {
        qText = findViewById(R.id.qText);
        optiona =findViewById(R.id.optiona);
        optionb =findViewById(R.id.optionb);
        optionc =findViewById(R.id.optionc);
        optiond =findViewById(R.id.optiond);

        cardOA =findViewById(R.id.cardA);
        cardOB =findViewById(R.id.cardB);
        cardOC =findViewById(R.id.cardC);
        cardOD =findViewById(R.id.cardD);

        scoreUpdate = findViewById(R.id.score_update);
    }


}