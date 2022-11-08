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
import android.widget.Toast;

import com.google.rpc.RequestInfo;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

//    CountDownTimer countDownTimer;
//    int timerValue =20;

    List<ModelClass> allQuestionsList;
    ModelClass modelclass;
    int index=0;
    int score=0;
    int correctCount=0;
    int wrongCount=0;
    TextView qText, optiona, optionb, optionc, optiond;
    CardView cardOA,cardOB, cardOC, cardOD;
    ArrayList<ModelClass> listOfQ;
    TextView scoreUpdate;
    AppCompatButton nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Hooks();

        listOfQ =  new ArrayList<>();
        listOfQ.add(new ModelClass("Which Country won the inaugural T20 Cricket World Cup ?","West Indies","England","India","Pakistan","India"));
        listOfQ.add(new ModelClass("Which among batsman have the most International runs ?","Sir Don Bradman","Sir Vivian Richards","Sunil Gavaskar","Sachin Tendulkar","Sachin Tendulkar"));
        listOfQ.add(new ModelClass("Famous Tennis Player Roger Federer plays for which among the following nations ?","Britain","Switzerland","Portugal","Brazil","Switzerland"));
        listOfQ.add(new ModelClass("Who was awarded the Man of the match in the Cricket world cup 2011 final ?","MS Dhoni","Gautam Gambhir","Yuvraj Singh","Zaheer Khan","MS Dhoni"));
        listOfQ.add(new ModelClass("In which field Neeraj Chopra Won the Olympic Gold Medal ?","Swimming","javelin throw","Badminton","Wrestling","javelin throw"));
        listOfQ.add(new ModelClass("Which sport is described as \'the beautiful game\' ?","Cricket","Bull fighting","Badminton","football","football"));
        listOfQ.add(new ModelClass("Which country won the first ever football world cup ?","Argentina","Portugal","Uruguay","Spain","Uruguay"));
        listOfQ.add(new ModelClass("How many regulation strokes are there in Swimming ?","4","3","2","5","4"));
        listOfQ.add(new ModelClass("Term Chinaman is related to which sport ?","Football","Hockey","Golf","Cricket","Cricket"));
        listOfQ.add(new ModelClass("With which game does Davies Cup is associated ?","Hockey","Table Tennis","Lawn Tennis","Polo","Lawn Tennis"));



        allQuestionsList = listOfQ;
        modelclass = listOfQ.get(index);


        /*nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QuestionActivity.this, "You can't skip questions", Toast.LENGTH_SHORT).show();
                nextBtn.setClickable(false);
            }
        });*/
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButton();
                // nextBtn.setClickable(false);
                index++;
                scoreUpdate.setText((index+1)+"/"+"10");
                modelclass = listOfQ.get(index);
                setAllData();
                if(index+1 == 10){
                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
                }
            }
        });



        setAllData();

//        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                index++;
//                scoreUpdate.setText((index+1)+"/"+"10");
////                allQuestionsList = listOfQ;
//                modelclass = listOfQ.get(index);
//                setAllData();
//                if(index+1 == 10){
//                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
//                }
//
////                enableButton();
//
//            }
//        });

        findViewById(R.id.end_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuestionActivity.this, FinsihQuiz.class);
                i.putExtra("correct",correctCount);
                i.putExtra("wrong",wrongCount);
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
        resetColor();
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

        nextBtn = findViewById(R.id.nextBtn);
//        endQuiz = findViewById(R.id.end_quiz);
        scoreUpdate = findViewById(R.id.score_update);
    }

    public void Correct(CardView cardview){
        correctCount++;
        cardview.setCardBackgroundColor(getResources().getColor(R.color.PaleGreen));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(QuestionActivity.this, "You can't skip the questions", Toast.LENGTH_SHORT).show();
                enableButton();
               // nextBtn.setClickable(false);
                index++;
                scoreUpdate.setText((index+1)+"/"+"10");
                modelclass = listOfQ.get(index);
                setAllData();
                if(index+1 == 10){
                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
                }
//                resetColor();
            }
        });
//        nextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                index++;
//                correctCount++;
//                scoreUpdate.setText((index+1)+"/"+"10");
////                allQuestionsList = listOfQ;
//                modelclass = listOfQ.get(index);
//                setAllData();
//                resetColor();
//                if(index+1 == 10){
//                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
//                }
//
//
//
//
////                index++;
////                correctCount++;
////                modelclass=listOfQ.get(index);
////                setAllData();
////                resetColor();
//            }
//        });


    }

    public void Wrong(CardView cardview){
        wrongCount++;
        cardview.setCardBackgroundColor(getResources().getColor(R.color.Red));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButton();
               // nextBtn.setClickable(false);

                index++;
                scoreUpdate.setText((index+1)+"/"+"10");
                modelclass = listOfQ.get(index);
                setAllData();
                if(index+1 == 10){
                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
                }
//                resetColor();
            }
        });

//        nextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                wrongCount++;
//                if(index < 9){
//                    index++;
//                    modelclass=listOfQ.get(index);
//                    setAllData();
//                    resetColor();
//                } else{
//                    GameWon();
//                }
//            }
//        });


    }

//    private void GameWon(){
//        Intent i = new Intent(QuestionActivity.this, FinsihQuiz.class);
//        startActivity(i);
//    }

    public void enableButton(){
        cardOA.setClickable(true );
        cardOB.setClickable(true );
        cardOC.setClickable(true );
        cardOD.setClickable(true );
    }
    public void disableButton(){
        cardOA.setClickable(false );
        cardOB.setClickable(false );
        cardOC.setClickable(false );
        cardOD.setClickable(false );
    }

    public void resetColor(){
        cardOA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoA().equals(modelclass.getAns())){
            Correct(cardOA);
//            cardOA.setCardBackgroundColor(getResources().getColor(R.color.LawnGreen));
//            if(index < 9){
//                Correct(cardOA);
//            }
//            else{
//                GameWon();
//            }
        }
        else{
            Wrong(cardOA);
        }
    }

    public void OptionBClick(View view) {
        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoB().equals(modelclass.getAns())){
            Correct(cardOB);
//            cardOB.setCardBackgroundColor(getResources().getColor(R.color.LawnGreen));
//            if(index < 9){
//                Correct(cardOB);
//            }
//            else{
//                GameWon();
//            }
        }
        else{
            Wrong(cardOB);
        }
    }

    public void OptionCClick(View view) {
        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoC().equals(modelclass.getAns())){
            Correct(cardOC);
//            cardOC.setCardBackgroundColor(getResources().getColor(R.color.LawnGreen));
//            if(index < 9){
//                Correct(cardOC);
//            }
//            else{
//                GameWon();
//            }
        }
        else{
            Wrong(cardOC);
        }
    }

    public void OptionDClick(View view) {
        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoD().equals(modelclass.getAns())){
            Correct(cardOD);
//            cardOD.setCardBackgroundColor(getResources().getColor(R.color.LawnGreen));
//            if(index < 9){
//                Correct(cardOD);
//            }
//            else{
//                GameWon();
//            }
        }
        else{
            Wrong(cardOD);
        }

    }
}