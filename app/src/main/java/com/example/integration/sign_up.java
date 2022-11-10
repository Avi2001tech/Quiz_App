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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sign_up extends AppCompatActivity {
    private AppCompatButton button_home;
    private AppCompatButton button_login;
    Dialog progressDialog;
    TextView dialog_text;
    EditText full_name,user_name,email,phone_no,password;
    String emailInp, usernameInp, fullnameInp, phoneInp, passInp;
    String passVal = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        full_name = findViewById(R.id.full_name);
        user_name = findViewById(R.id.email_login);
        email = findViewById(R.id.email);
        phone_no = findViewById(R.id.phone_no);
        password = findViewById(R.id.password);

        progressDialog = new Dialog(sign_up.this);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog_text = progressDialog.findViewById(R.id.dialog_text);
        dialog_text.setText("Registering user ....");


        mAuth = FirebaseAuth.getInstance();



                           // sign in to home
        button_home = findViewById(R.id.save_details);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullnameInp = full_name.getText().toString().trim();
                usernameInp = user_name.getText().toString().trim();
                emailInp = email.getText().toString().trim();
                phoneInp = phone_no.getText().toString().trim();
                passInp = password.getText().toString().trim();

                if(validate(fullnameInp, usernameInp, emailInp, phoneInp, passInp)){
                    signUpNewUser(fullnameInp, usernameInp, emailInp, phoneInp, passInp);
                }
                /*Intent i = new Intent(sign_up.this,MainActivity.class);
                startActivity(i);*/
            }
        });

                               // login button
        button_login = findViewById(R.id.login_button);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sign_up.this,login_page.class);
                startActivity(i);
            }
        });

                     // some instructions
        full_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(sign_up.this, "Only letters are allowed", Toast.LENGTH_SHORT).show();
            }
        });
        user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(sign_up.this, "Only letters , digits and special characters : @ # $ _ are allowed ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private boolean validate(String fullnameInp, String usernameInp, String emailInp, String phoneInp, String passInp){
//        emailS = email.getText().toString().trim();
//        fullS = full_name.getText().toString().trim();
//        passS = password.getText().toString().trim();
//        userS = user_name.getText().toString().trim();
//        phoneS = phone_no.getText().toString().trim();


        if(fullnameInp.isEmpty()){
            full_name.setError("Enter your name");
            full_name.requestFocus();

            return false;
        }

        if(usernameInp.isEmpty()){
            user_name.setError("Enter your user name");
            user_name.requestFocus();

            return  false;
        }

        if(emailInp.isEmpty()){
            email.setError("Enter your email");
            email.requestFocus();

            return false;
        }

        if(phoneInp.isEmpty()){
            phone_no.setError("Enter your phone number");
            phone_no.requestFocus();

            return false;
        }

        if(passInp.isEmpty()){
            password.setError("Enter your password");
            password.requestFocus();

            return false;
        }

        if(!passInp.matches(passVal)){
            password.setError("Password is too weak \n " + "must contains one digit from 0-9\n" +
                    "must contains one lowercase characters\n" +
                    "must contains one uppercase characters\n" +
                    "must contains one special symbols in the list \"@#$%\"\n" +
                    "match anything with previous condition checking\n" +
                    "length at least 8 characters and maximum of 20");

            password.requestFocus();

            return  false;
        }

        return true;
    }

    private void signUpNewUser(String fullnameInp, String usernameInp, String emailInp, String phoneInp, String passInp){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(emailInp, passInp )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("fullName",fullnameInp);
                            userMap.put("username", usernameInp);
                            userMap.put("email", emailInp);
                            userMap.put("phone", phoneInp);

                            db.collection("users").document(mAuth.getCurrentUser().getUid()).set(userMap);

                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            Toast.makeText(sign_up.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(sign_up.this,MainActivity.class);
                            startActivity(i);
                            sign_up.this.finish();;
                            /*FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);*/
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(sign_up.this, "Fail to sign up \n" + "Make sure to have an internet connection" ,
                                    Toast.LENGTH_SHORT).show();
                            /*updateUI(null);*/
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAffinity(sign_up.this);
    }
}