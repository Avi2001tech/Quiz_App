package com.example.integration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AdminSection extends AppCompatActivity {
    TabLayout tab;
    ViewPager view_page;
    Dialog progressDialog;
    TextView dialog_text;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_section);

        progressDialog = new Dialog(AdminSection.this);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_text = progressDialog.findViewById(R.id.dialog_text);
        dialog_text.setText("Signning out ....");


        mAuth = FirebaseAuth.getInstance();

        TextView exit = findViewById(R.id.exit_adminPanel);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder exitDialog = new AlertDialog.Builder(AdminSection.this);
                exitDialog.setTitle("Exit");
                exitDialog.setIcon(R.drawable.exit_quiz);
                exitDialog.setMessage("Are you sure want to Sign out?");
                exitDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.show();
                        mAuth.signOut();
                        Intent intent = new Intent(AdminSection.this,login_page.class);
                        startActivity(intent);
                        finish();
                    }
                });

                exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AdminSection.this, "Great Decision !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();
                //AdminSection.this.finish();
            }
        });

        tab = findViewById(R.id.tab);
        view_page = findViewById(R.id.view_page);

        ViewPagerAdminAdapter adapter = new ViewPagerAdminAdapter(getSupportFragmentManager());
        view_page.setAdapter(adapter);
        tab.setupWithViewPager(view_page);

    }
}