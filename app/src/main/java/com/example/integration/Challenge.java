package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class Challenge extends AppCompatActivity {

    TabLayout tab;
    ViewPager view_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        ImageView back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Challenge.this.finish();
            }
        });

        tab = findViewById(R.id.tab);
        view_page = findViewById(R.id.view_page);

        ViewPagerChallengerAdapter adapter = new ViewPagerChallengerAdapter(getSupportFragmentManager());
        view_page.setAdapter(adapter);

        tab.setupWithViewPager(view_page);
    }
}