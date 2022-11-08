package com.example.integration;

//import static com.example.integration.SplashActivity.listOfQ;

import static com.example.integration.before_quiz_start.listOfQ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.rpc.RequestInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity {

    long timeleft;
    int flagBookMark=0;

    List<ModelClass> allQuestionsList;
    ModelClass modelclass;
    int index=0;
    int correctCount=0;
    int wrongCount=0;
    TextView qText, optiona, optionb, optionc, optiond;
    CardView cardOA,cardOB, cardOC, cardOD;
    TextView scoreUpdate,countTimer;
    AppCompatButton nextBtn;
    FloatingActionButton bookMark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Hooks();
        startTimer();

//        listOfQ.add(new ModelClass("Which Country won the inaugural T20 Cricket World Cup ?","West Indies","England","India","Pakistan","India"));
//        listOfQ.add(new ModelClass("Which among batsman have the most International runs ?","Sir Don Bradman","Sir Vivian Richards","Sunil Gavaskar","Sachin Tendulkar","Sachin Tendulkar"));
//        listOfQ.add(new ModelClass("Famous Tennis Player Roger Federer plays for which among the following nations ?","Britain","Switzerland","Portugal","Brazil","Switzerland"));
//        listOfQ.add(new ModelClass("Who was awarded the Man of the match in the Cricket world cup 2011 final ?","MS Dhoni","Gautam Gambhir","Yuvraj Singh","Zaheer Khan","MS Dhoni"));
//        listOfQ.add(new ModelClass("In which field Neeraj Chopra Won the Olympic Gold Medal ?","Swimming","javelin throw","Badminton","Wrestling","javelin throw"));
//        listOfQ.add(new ModelClass("Which sport is described as \'the beautiful game\' ?","Cricket","Bull fighting","Badminton","football","football"));
//        listOfQ.add(new ModelClass("Which country won the first ever football world cup ?","Argentina","Portugal","Uruguay","Spain","Uruguay"));
//        listOfQ.add(new ModelClass("How many regulation strokes are there in Swimming ?","4","3","2","5","4"));
//        listOfQ.add(new ModelClass("Term Chinaman is related to which sport ?","Football","Hockey","Golf","Cricket","Cricket"));
//        listOfQ.add(new ModelClass("With which game does Davies Cup is associated ?","Hockey","Table Tennis","Lawn Tennis","Polo","Lawn Tennis"));

//        allQuestionsList = listOfQ;
        modelclass = listOfQ.get(index);

        // For first Time Skip and BookMark

        callNextButton();

        bookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagBookMark == 0){
                    bookMark.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Red)));
                    flagBookMark =1;
                }
                else{
                    bookMark.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                    flagBookMark =0;
                }
                //book_mark.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Red)));
            }
        });



        setAllData();


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
        countTimer = findViewById(R.id.countTimer);
        bookMark = findViewById(R.id.book_mark);
    }

    public void Correct(CardView cardview){
        correctCount++;
        cardview.setCardBackgroundColor(getResources().getColor(R.color.PaleGreen));
        callNextButton();
    }

    public void Wrong(CardView cardview){
        wrongCount++;
        cardview.setCardBackgroundColor(getResources().getColor(R.color.Red));
        callNextButton();
    }

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
//        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoA().equals(modelclass.getAns())){
            Correct(cardOA);
        }
        else{
            Wrong(cardOA);
        }
    }

    public void OptionBClick(View view) {
//        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoB().equals(modelclass.getAns())){
            Correct(cardOB);
        }
        else{
            Wrong(cardOB);
        }
    }

    public void OptionCClick(View view) {
//        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoC().equals(modelclass.getAns())){
            Correct(cardOC);
        }
        else{
            Wrong(cardOC);
        }
    }

    public void OptionDClick(View view) {
//        nextBtn.setClickable(true);
        disableButton();
        if(modelclass.getoD().equals(modelclass.getAns())){
            Correct(cardOD);
        }
        else{
            Wrong(cardOD);
        }

    }


    private void startTimer(){
        long totalTime = 2*60*1000 + 1000;
        CountDownTimer timer = new CountDownTimer(totalTime,1000) {
            @Override
            public void onTick(long remainingTime) {
                timeleft =remainingTime;
                String time = String.format("%02d:%02d min", TimeUnit.MILLISECONDS.toMinutes(remainingTime),
                        TimeUnit.MILLISECONDS.toSeconds(remainingTime)
                                - TimeUnit.MINUTES.toSeconds((TimeUnit.MILLISECONDS.toMinutes(remainingTime))));

                countTimer.setText(time);

            }

            @Override
            public void onFinish() {
                Intent i = new Intent(QuestionActivity.this, FinsihQuiz.class);
                i.putExtra("correct",correctCount);
                i.putExtra("wrong",wrongCount);
                startActivity(i);

            }
        };

        timer.start();
    }

    private void callNextButton() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookMark.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                flagBookMark=0;
                enableButton();
                index++;
                scoreUpdate.setText((index+1)+"/"+"10");
                modelclass = listOfQ.get(index);
                setAllData();
                if(index+1 == 10){
                    findViewById(R.id.nextBtn).setVisibility(View.GONE);
                }
            }
        });
    }




}