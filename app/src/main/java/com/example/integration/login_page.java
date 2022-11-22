package com.example.integration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_page extends AppCompatActivity {

    private AppCompatButton button_home;
    private AppCompatButton button_signup;
    Dialog progressDialog;
    TextView dialog_text,adminBtn;
    EditText email_login,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email_login = findViewById(R.id.email_login);
        password = findViewById(R.id.password);

        progressDialog = new Dialog(login_page.this);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_text = progressDialog.findViewById(R.id.dialog_text);
        dialog_text.setText("logging in....");

        mAuth = FirebaseAuth.getInstance();


                         // login home
        button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateData()){
                    login();
                }
               /* Intent i = new Intent(login_page.this,MainActivity.class);
                startActivity(i);*/
            }
        });



                              // signup
        button_signup = findViewById(R.id.sign_button);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login_page.this,sign_up.class);
                startActivity(i);
            }
        });

                      //Admin button
        adminBtn=findViewById(R.id.adminBtn);
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(login_page.this,AdminLogin.class);
                startActivity(in);
            }
        });

    }

    private boolean validateData(){
        if(email_login.getText().toString().isEmpty()){
            email_login.setError("Enter user name");
            return false;
        }
        else if(password.getText().toString().isEmpty()){
            password.setError("Enter password");
            return false;
        }
        else{
            return  true;
        }
    }

    private void login(){
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email_login.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            Toast.makeText(login_page.this, "Successfully sign in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login_page.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(login_page.this, "Wrong Email-Id or password \n" + "Make sure to have an internet connection",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAffinity(login_page.this);
    }
}