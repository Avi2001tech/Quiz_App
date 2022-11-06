package com.example.integration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageSlider imageSlider;
    Toolbar toolbar;
    DrawerLayout drawer_lay;
    NavigationView nav_view;
    BottomNavigationView bottom_bar;
    ImageView sport,history,culture,gk,science;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawer_lay = findViewById(R.id.drawer_lay);
        nav_view = findViewById(R.id.nav_view);
        bottom_bar = findViewById(R.id.bottom_bar);
        sport = findViewById(R.id.sports_pic);
        history = findViewById(R.id.history_pic);
        culture=findViewById(R.id.culture_pic);
        gk=findViewById(R.id.current_affairs_pic);
        science=findViewById(R.id.science_pic);


        // click images to move to activity
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s ="SPORTS";
                Intent i = new Intent(MainActivity.this,before_quiz_start.class);
                i.putExtra("EXTRA_DATA",s );
                startActivity(i);

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s ="HISTORY";
                Intent i = new Intent(MainActivity.this,before_quiz_start.class);
                i.putExtra("EXTRA_DATA",s );
                startActivity(i);

            }
        });

        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s ="CULTURE & GEOGRAPHY";
                Intent i = new Intent(MainActivity.this,before_quiz_start.class);
                i.putExtra("EXTRA_DATA",s );
                startActivity(i);

            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s ="SCIENCE";
                Intent i = new Intent(MainActivity.this,before_quiz_start.class);
                i.putExtra("EXTRA_DATA",s );
                startActivity(i);

            }
        });

        gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s ="CURRENT AFFAIRS";
                Intent i = new Intent(MainActivity.this,before_quiz_start.class);
                i.putExtra("EXTRA_DATA",s );
                startActivity(i);

            }
        });





                                                         // For toolbar setting
        setSupportActionBar(toolbar);
        // setActionBar(toolbar); for normal or old toolbar
        toolbar.setTitle("JhatpatQuiz");
        toolbar.setSubtitle("Enrich your Knowledge");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer_lay,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawer_lay.addDrawerListener(toggle);
        toggle.syncState();


                                                      //  for imageslider setting
        imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://i.imgur.com/Efj4sDs.jpeg","GN Saibaba to go for urgent appeal in SC this saturday", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.imgur.com/qp5jGOG.jpeg","Roger Fedrer departs", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.imgur.com/MS8mjDQ.png","A glimpse of our tradition", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.imgur.com/oy4a1aH.jpeg","Scientist create Robo plant which grows using water themself", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.imgur.com/VA2LH2i.jpeg","1989 - 2020, alittle effort which leads to revolution", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);



                           //for Bottom navigation
        bottom_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.nav_home){
                    // do nothing
                } else if(id == R.id.nav_challenge){
                    Intent intent = new Intent(MainActivity.this, Challenge.class);
                    startActivity(intent);
                } else if(id == R.id.nav_leaderboard){
                    Intent intent = new Intent(MainActivity.this, Leaderboard.class);
                    startActivity(intent);
                } else if(id == R.id.nav_create){
                    Intent intent = new Intent(MainActivity.this, create_SplashScreen.class);
                    startActivity(intent);
                }else{//avtar
                    Intent intent = new Intent(MainActivity.this, profile_page.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        bottom_bar.setSelectedItemId(R.id.nav_home);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}