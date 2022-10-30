package com.example.integration;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;


public class splashScreen extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    TextView AppName;
    LottieAnimationView lottie;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AppName = findViewById(R.id.AppName);
        lottie = findViewById(R.id.lottie);
        mAuth = FirebaseAuth.getInstance();


        /*AppName.animate().setDuration(3100).setStartDelay(0);
        // AppName.animate().alpha(0);
        AppName.animate().alpha(0).translationYBy(Float.parseFloat("80")).scaleX(1.3F).scaleY(1.3F).getInterpolator();
        //AppName.animate().alpha(1).getInterpolator();*/

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        AppName.setAnimation(anim);


        lottie.animate().setDuration(2700).setStartDelay(0);
        lottie.animate().getInterpolator();
        lottie.playAnimation();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if(mAuth.getCurrentUser()!=null){
                    Intent i = new Intent(splashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(splashScreen.this,login_page.class);
                    startActivity(i);
                    finish();
                }

            }
        },3500);
    }
}