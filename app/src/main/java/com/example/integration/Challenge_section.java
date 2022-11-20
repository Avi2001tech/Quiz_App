package com.example.integration;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Challenge_section#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Challenge_section extends Fragment {

    Spinner spinner_1,spinner_2,spinner_3;
    EditText display_name;
    RadioGroup difficulty,choose;

    String[] category ={"-Select any one-","Science","History","Current-Affairs","Sports","Culture & Geography"};
    String[] age ={"-Select any one- ","4-7 years","8-12 years","13-18 years","18+"};
//    String[] time ={"-Select any one- ","12-1 pm","1-2 pm","3-4 pm","4-5 pm","5-6 pm","6-7 pm","7-8 pm","8-9 pm","9-10 pm","10-11 pm","11-11:59 pm",
//            "12-1 am","1-2 am","3-4 am","4-5 am","5-6 am","6-7 am","7-8 am","8-9 am","9-10 am","10-11 am","11-11:59 am"};
    String[] time = {"-Select any one-", "2 min", "5 min", "7 min", "10 min"};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Challenge_section() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Challenge_section.
     */
    // TODO: Rename and change types and number of parameters
    public static Challenge_section newInstance(String param1, String param2) {
        Challenge_section fragment = new Challenge_section();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);
        AppCompatButton submit = view.findViewById(R.id.Submit);
        spinner_1 = view.findViewById(R.id.spinner_1);
        spinner_2 = view.findViewById(R.id.spinner_2);
        spinner_3 = view.findViewById(R.id.spinner_3);
        display_name = view.findViewById(R.id.display_name);
        difficulty  = view.findViewById(R.id.difficulty);
        choose = view.findViewById(R.id.choose);

                                  //spinner 1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,category){
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
                    Toast.makeText(getActivity(), value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                                //spinner 2
        ArrayAdapter<String> adapter_age = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,age){
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
                    Toast.makeText(getActivity(), value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                                         //spinner 3
        ArrayAdapter<String> adapter_time = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,time){
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
        adapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_3.setAdapter(adapter_time);
        spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                if(i>=1){
                    Toast.makeText(getActivity(), value +" is selected", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int difficultyId = difficulty.getCheckedRadioButtonId();
                int chooseId = choose.getCheckedRadioButtonId();
                String s = display_name.getText().toString();

                if(TextUtils.isEmpty(s)) {
                    display_name.setError("This field cannot be empty");
                    display_name.requestFocus();
                    return;
                }
                else if ((spinner_1.getSelectedItemPosition() <= 0)){
                    Toast.makeText(getActivity(), "Topic can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if((spinner_2.getSelectedItemPosition()<=0)){
                    Toast.makeText(getActivity(), "Select age group", Toast.LENGTH_SHORT).show();
                }
                else if((spinner_3.getSelectedItemPosition()<=0)){
                    Toast.makeText(getActivity(), "Select time for your challenge", Toast.LENGTH_SHORT).show();
                }
                else if(difficultyId == -1){
                    Toast.makeText(getActivity(), "Select difficulty", Toast.LENGTH_SHORT).show();
                }
                else if(chooseId == -1){
                    Toast.makeText(getActivity(), "choose yes or no", Toast.LENGTH_SHORT).show();
                }
                else{
                    display_name.setText(s);
                    Toast.makeText(getActivity(), "We will update you with your request", Toast.LENGTH_SHORT).show();
                }
              //  Toast.makeText(getActivity(), "We will update you with your request", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}