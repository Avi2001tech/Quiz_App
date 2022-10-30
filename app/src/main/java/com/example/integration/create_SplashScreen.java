package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class create_SplashScreen extends AppCompatActivity {
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DbQuery.our_firestore = FirebaseFirestore.getInstance();


        lottie = findViewById(R.id.lottie_create);
        //AppName.animate().alpha(1).getInterpolator();
        lottie.animate().setDuration(2700).setStartDelay(0);
        lottie.animate().getInterpolator();
        lottie.playAnimation();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(create_SplashScreen.this,selection.class);
                startActivity(i);
                finish();
            }
        },1500);
    }
}