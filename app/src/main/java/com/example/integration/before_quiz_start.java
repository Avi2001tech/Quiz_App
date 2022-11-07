package com.example.integration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class before_quiz_start extends AppCompatActivity {

    Spinner spinner_1,spinner_2,spinner_3;
    AppCompatButton start;
    TextView section_name;
    ImageView back_button;

    String[] category ={"-Select any one-","Science","History","Current-Affairs","Sports","Culture & Geography"};
    String[] age ={"-Select any one- ","4-7 years","8-12 years","13-18 years","18+"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_quiz_start);

        Intent intent = getIntent();
        String data = intent.getStringExtra("EXTRA_DATA");

        start = findViewById(R.id.start_quiz);
        section_name=findViewById(R.id.section_name);
        spinner_1 = findViewById(R.id.spinner_1);
        spinner_2 = findViewById(R.id.spinner_2);
        back_button=findViewById(R.id.back_button);

        section_name.setText(data);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                before_quiz_start.this.finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((spinner_1.getSelectedItemPosition() <= 0)){
                    Toast.makeText(before_quiz_start.this, "Topic can't be empty", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(before_quiz_start.this, "", Toast.LENGTH_SHORT).show();
                }
                else if((spinner_2.getSelectedItemPosition()<=0)){
                    Toast.makeText(before_quiz_start.this, "Select age group", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(before_quiz_start.this,QuestionActivity.class);
                    startActivity(i);
                }

            }
        });


        //spinner 1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(before_quiz_start.this, android.R.layout.simple_spinner_item,category){
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
                    Toast.makeText(before_quiz_start.this, value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner 2
        ArrayAdapter<String> adapter_age = new ArrayAdapter<String>(before_quiz_start.this, android.R.layout.simple_spinner_item,age){
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
                    Toast.makeText(before_quiz_start.this, value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}