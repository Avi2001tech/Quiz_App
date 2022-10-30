package com.example.integration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_page extends AppCompatActivity {
    Button sign_out;
    Button update;
   // TextInputEditText display_name,display_email,display_phone_no,future_purpose_1,future_purpose_2,future_purpose_3;
    TextView user_name,user_small_name;
    EditText display_n;
    FloatingActionButton fab;
    CircleImageView imgUser;
    Dialog progressDialog;
    TextView dialog_text;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        sign_out = findViewById(R.id.sign_out);
        update = findViewById(R.id.update);

        user_name = findViewById(R.id.email_login);
        user_small_name = findViewById(R.id.user_name_small);
        display_n = findViewById(R.id.display_name);
        imgUser = findViewById(R.id.user_pic);

        progressDialog = new Dialog(profile_page.this);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_text = progressDialog.findViewById(R.id.dialog_text);
        dialog_text.setText("Signning out ....");

        mAuth = FirebaseAuth.getInstance();
    //    display_name = findViewById(R.id.display_name);
   //     display_email = findViewById(R.id.display_email);
   //     display_phone_no = findViewById(R.id.display_phone_no);
   //     future_purpose_1 = findViewById(R.id.future_purpose_1);
   //     future_purpose_2 = findViewById(R.id.future_purpose_2);
   //     future_purpose_3 = findViewById(R.id.future_purpose_3);

   //     String display_n = display_name.getText().toString();
   //     String display_e = Objects.requireNonNull(display_email.getText()).toString();
  //      String display_p = display_phone_no.getText().toString();
   //     String display_f1 = future_purpose_1.getText().toString();
   //     String display_f2 = future_purpose_2.getText().toString();
   //     String display_f3 = future_purpose_3.getText().toString();

       // user_name.setText(display_n);
       // user_small_name.setText(display_n);
  //      display_name.setText(display_n);
 //       display_email.setText(display_e);
 //       display_phone_no.setText(display_p);
 //       future_purpose_1.setText(display_f1);
 //       future_purpose_2.setText(display_f2);
 //       future_purpose_3.setText(display_f3);

                          // sign out functionality
        sign_out=findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                mAuth.signOut();
                Intent intent = new Intent(profile_page.this,login_page.class);
                startActivity(intent);
                finish();
            }
        });


                         // Update functionality
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = display_n.getText().toString();

                SharedPreferences pref = getSharedPreferences("display_name",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("str",s);
                editor.apply();

                display_n.setText(s);
                user_name.setText(s);
                user_small_name.setText(s);
                Toast.makeText(profile_page.this, "Your profile is updated now", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences getShared = getSharedPreferences("display_name",MODE_PRIVATE);
        String val = getShared.getString("str","User name");
        display_n.setText(val);
        user_name.setText(val);
        user_small_name.setText(val);



                      //  floatingaction bar
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(profile_page.this)
               // for only gallery         .galleryOnly()
                        //  for camera only  .cameraOnly()
                      //  .crop()	    			//Crop image(Optional), Check Customization for more option
                      //  .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            imgUser.setImageURI(uri);
            //Image Uri will not be null for RESULT_OK

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }

    }
}