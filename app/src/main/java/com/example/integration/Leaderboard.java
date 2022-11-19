package com.example.integration;

import static com.example.integration.MainActivity.arrRank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

class StockComparator implements Comparator<RankerClass> {

    // override the compare() method
    public int compare(RankerClass s1, RankerClass s2)
    {
        if (Integer.parseInt(s1.score) >= Integer.parseInt(s2.score))
            return 1;
        else
            return -1;
    }
}

public class Leaderboard extends AppCompatActivity {
    RecyclerView rankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        rankList=findViewById(R.id.rank_list);
        rankList.setLayoutManager(new LinearLayoutManager(this));


        Collections.sort(arrRank, new StockComparator());


//        arrRank.add(new RankerClass("user123", "52"));
//        arrRank.add(new RankerClass("abcd", "5"));
//        arrRank.add(new RankerClass("fdf", "2"));
//        arrRank.add(new RankerClass("er123", "44"));
//        arrRank.add(new RankerClass("ee", "53"));

//        arrRank.add(new RankModel(R.drawable.history,"Abhishek","232","1"));
//        arrRank.add(new RankModel(R.drawable.boy,"Amit","230","2"));
//        arrRank.add(new RankModel(R.drawable.current_affairs,"Gaurav","229","3"));
//        arrRank.add(new RankModel(R.drawable.culture,"Arkatanu","221","4"));
//        arrRank.add(new RankModel(R.drawable.boy_clipart,"Ankit","219","5"));
//        arrRank.add(new RankModel(R.drawable.science,"kshitij","216","6"));
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


//    public static Comparator<RankerClass> getCompByScore()
//    {
//        Comparator comp = new Comparator<RankerClass>(){
//            @Override
//            public int compare(RankerClass s1, RankerClass s2)
//            {
//                return s1.score.compareTo(s2.score);
//            }
//        };
//        return comp;
//    }

}