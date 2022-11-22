package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class AdminSection extends AppCompatActivity {
    TabLayout tab;
    ViewPager view_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_section);

        TextView exit = findViewById(R.id.exit_adminPanel);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSection.this.finish();
            }
        });

        tab = findViewById(R.id.tab);
        view_page = findViewById(R.id.view_page);

        ViewPagerAdminAdapter adapter = new ViewPagerAdminAdapter(getSupportFragmentManager());
        view_page.setAdapter(adapter);
        tab.setupWithViewPager(view_page);

    }
}