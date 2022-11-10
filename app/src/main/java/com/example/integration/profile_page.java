package com.example.integration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_page extends AppCompatActivity {
    Button sign_out;
    Button update;
    LinearLayout display;
   // TextInputEditText display_name,display_email,display_phone_no,future_purpose_1,future_purpose_2,future_purpose_3;
    TextView user_name,user_description;
    EditText display_n,display_email,display_phone_no,status;
    FloatingActionButton fab;
    CircleImageView imgUser;
    Dialog progressDialog;
    TextView dialog_text;

    FirebaseAuth mAuth;
    FirebaseFirestore dbroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        sign_out = findViewById(R.id.sign_out);
        update = findViewById(R.id.update);

        ImageView back_button =findViewById(R.id.back_button);
        display = findViewById(R.id.display);

        user_name = findViewById(R.id.email_login);
        user_description = findViewById(R.id.user_name_small);
        display_n = findViewById(R.id.display_name);
        display_email = findViewById(R.id.display_email);
        display_phone_no = findViewById(R.id.display_phone_no);
        status = findViewById(R.id.status);
        imgUser = findViewById(R.id.user_pic);

        progressDialog = new Dialog(profile_page.this);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_text = progressDialog.findViewById(R.id.dialog_text);
        dialog_text.setText("Signning out ....");

        mAuth = FirebaseAuth.getInstance();

        dbroot = FirebaseFirestore.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
//        userId = "raman61";
        DocumentReference document = dbroot.collection("users").document(userId);
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            user_name.setText(documentSnapshot.getString("fullName") + " " + documentSnapshot.getString("username"));
                            user_description.setText(documentSnapshot.getString("email"));

                        } else {
                            Toast.makeText(profile_page.this, "User not Found !!", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(profile_page.this, "Failed to fetch datta", Toast.LENGTH_LONG).show();
                    }
                });


   //     future_purpose_1 = findViewById(R.id.future_purpose_1);
   //     future_purpose_2 = findViewById(R.id.future_purpose_2);
   //     future_purpose_3 = findViewById(R.id.future_purpose_3);

  //
   //     String display_f1 = future_purpose_1.getText().toString();
   //     String display_f2 = future_purpose_2.getText().toString();
   //     String display_f3 = future_purpose_3.getText().toString();


 //       future_purpose_1.setText(display_f1);
 //       future_purpose_2.setText(display_f2);
 //       future_purpose_3.setText(display_f3);


        //back button
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_page.this.finish();
            }
        });

                          // sign out functionality
        sign_out=findViewById(R.id.sign_out);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder exitDialog = new AlertDialog.Builder(profile_page.this);
                exitDialog.setTitle("Exit");
                exitDialog.setIcon(R.drawable.exit_quiz);
                exitDialog.setMessage("Are you sure want to Sign out?");
                exitDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.show();
                        mAuth.signOut();
                        Intent intent = new Intent(profile_page.this,login_page.class);
                        startActivity(intent);
                        finish();
                    }
                });

                exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(profile_page.this, "Great Decision !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                exitDialog.show();

            }
        });


                         // Update functionality
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(display.getVisibility() == View.GONE){
                    display.setVisibility(View.VISIBLE);
                    Toast.makeText(profile_page.this, "You can update your profile", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s = display_n.getText().toString();
                    String display_e = display_email.getText().toString();
                    String display_p = display_phone_no.getText().toString();
                    String display_status = status.getText().toString();


                    SharedPreferences pref = getSharedPreferences("display",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("str",s);
                    editor.putString("email",display_e);
                    editor.putString("phone_no",display_p);
                    editor.putString("status",display_status);
                    editor.apply();

                    display_n.setText(s);
                    user_name.setText(s);
                    user_description.setText(display_status);
                    display_email.setText(display_e);
                    display_phone_no.setText(display_p);
                    status.setText(display_status);
                    Toast.makeText(profile_page.this, "Your profile is updated now", Toast.LENGTH_SHORT).show();
                    display.setVisibility(View.GONE);
                }

            }
        });

        SharedPreferences getShared = getSharedPreferences("display",MODE_PRIVATE);
        String val = getShared.getString("str","User name");
        String val2 = getShared.getString("email","sample_email@gmail.com");
        String val3 = getShared.getString("phone_no","9876543210");
        String val4 = getShared.getString("status","Your status will be displayed here");
        display_n.setText(val);
        user_name.setText(val);
        user_description.setText(val4);
        display_email.setText(val2);
        display_phone_no.setText(val3);
        status.setText(val4);



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