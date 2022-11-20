package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class selection extends AppCompatActivity {
    public static String subject;
    String subjectColl;
    Spinner spinner_1;
    Spinner spinner_2;
    CheckBox easy,medium,hard;
    AppCompatButton button;
    public static int quizMade;
    TextView countGame;

    FirebaseFirestore db;
    FirebaseAuth mAuth;

    String[] category ={"-Select any one-","Science","History","Current-Affairs","Sports","Culture & Geography"};
    String[] age ={"-Select any one- ","4-7 years","8-12 years","13-18 years","18+"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        spinner_1 = findViewById(R.id.spinner_1);
        spinner_2 = findViewById(R.id.spinner_2);
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard= findViewById(R.id.hard);
        button=findViewById(R.id.next);
//        countGame = findViewById(R.id.game_count);
        quizMade=0;

//
//        db = FirebaseFirestore.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//
//        db.collection("users").document(mAuth.getCurrentUser().getUid())
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.exists()){
//                            quizMade = documentSnapshot.getLong("quizMade").intValue();
//
//                        }
//                    }
//                });



                                      // spinner_1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(selection.this, android.R.layout.simple_spinner_item,category){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = null;

                // If this is the initial dummy entry, make it hidden
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    // Pass convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                if(i>=1){
                    subject = value;
                    Toast.makeText(selection.this, value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                                      //spinner_2
        ArrayAdapter<String> adapter_age = new ArrayAdapter<String>(selection.this, android.R.layout.simple_spinner_item,age){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View v = null;

                // If this is the initial dummy entry, make it hidden
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    // Pass convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        adapter_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter_age);

        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                if(i>=1){
                    Toast.makeText(selection.this, value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (subject == "Science") {
                    subjectColl = "science-easy";
                }
                else if(subject =="History"){
                    subjectColl = "history-easy";
                }
                else if (subject == "Sports") {
                    subjectColl = "sports-easy";
                }
                else if (subject == "Current-Affairs") {
                    subjectColl = "gk-easy";
                }
                else{
                    subjectColl = "culture-easy";
                }




                String check_easy = easy.getText().toString();
                String check_medium = medium.getText().toString();
                String check_hard = hard.getText().toString();



                if ((spinner_1.getSelectedItemPosition() <= 0)){
                    Toast.makeText(selection.this, "Topic can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if((spinner_2.getSelectedItemPosition()<=0)){
                    Toast.makeText(selection.this, "Select age group", Toast.LENGTH_SHORT).show();
                }
                else if((easy.isChecked() && medium.isChecked()) || (hard.isChecked() && medium.isChecked()) || (easy.isChecked() && hard.isChecked()) || (easy.isChecked() && medium.isChecked() && hard.isChecked())){
                    Toast.makeText(selection.this, "Select any one", Toast.LENGTH_SHORT).show();
                }
                else if(easy.isChecked() || hard.isChecked() || medium.isChecked()){





                    db = FirebaseFirestore.getInstance();
                    mAuth = FirebaseAuth.getInstance();

                    db.collection("quizdata").document(mAuth.getCurrentUser().getUid())
                            .collection("challenge").document(subjectColl)
                            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if(documentSnapshot.exists()){
                                        quizMade = documentSnapshot.getLong("quizcount").intValue();

                                    }
                                }
                            });








                    Intent i = new Intent(selection.this,create.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(selection.this, "Please select difficulty level", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}