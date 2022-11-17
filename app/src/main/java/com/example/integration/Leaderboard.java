package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {
    ArrayList<RankModel> arrRank = new ArrayList<>();
    RecyclerView rankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        rankList=findViewById(R.id.rank_list);
        rankList.setLayoutManager(new LinearLayoutManager(this));

        arrRank.add(new RankModel(R.drawable.history,"Abhishek","232","1"));
        arrRank.add(new RankModel(R.drawable.boy,"Amit","230","2"));
        arrRank.add(new RankModel(R.drawable.current_affairs,"Gaurav","229","3"));
        arrRank.add(new RankModel(R.drawable.culture,"Arkatanu","221","4"));
        arrRank.add(new RankModel(R.drawable.boy_clipart,"Ankit","219","5"));
        arrRank.add(new RankModel(R.drawable.science,"kshitij","216","6"));
//        arrRank.add(new RankModel(R.drawable.sports,"pankaj","215","7"));
//        arrRank.add(new RankModel(R.drawable.history,"saket","212","8"));
//        arrRank.add(new RankModel(R.drawable.science,"sachin","211","9"));
//        arrRank.add(new RankModel(R.drawable.culture,"shivendra","205","10"));
//        arrRank.add(new RankModel(R.drawable.current_affairs,"Rup","202","11"));
//        arrRank.add(new RankModel(R.drawable.boy,"Ritwik","198","12"));
//        arrRank.add(new RankModel(R.drawable.boy_clipart,"anarayna","195","13"));
//        arrRank.add(new RankModel(R.drawable.history,"aastik","190","14"));
//        arrRank.add(new RankModel(R.drawable.sports,"mihir","187","15"));
//        arrRank.add(new RankModel(R.drawable.current_affairs,"pratik","184","16"));
//        arrRank.add(new RankModel(R.drawable.science,"bitan","181","17"));
//        arrRank.add(new RankModel(R.drawable.boy,"ankit","176","18"));
//        arrRank.add(new RankModel(R.drawable.history,"umesh","173","19"));
//        arrRank.add(new RankModel(R.drawable.sports,"amit","170","20"));

        RecycleRankAdapter adapter = new RecycleRankAdapter(this,arrRank);
        rankList.setAdapter(adapter);



    }
}