package com.example.integration;

import static com.example.integration.selection.quizMade;
import static com.example.integration.selection.subject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class create extends AppCompatActivity {
    TextInputEditText q1, q1o1, q1o2, q1o3, q1o4, ans1;
    String q1str,q1o1str, q1o2str, q1o3str, q1o4str, ans1str;
    TextInputEditText q2, q2o1, q2o2, q2o3, q2o4, ans2;
    String q2str, q2o1str, q2o2str, q2o3str, q2o4str, ans2str;
    AppCompatButton submit;
    TextView showTotal;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String userId, challengeSubject;
//    int quizByUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        if (subject == "Science") {
            challengeSubject = "science-easy";
        }
        else if(subject =="History"){
            challengeSubject = "history-easy";
        }
        else if (subject == "Sports") {
            challengeSubject = "sports-easy";
        }
        else if (subject == "Current-Affairs") {
            challengeSubject = "gk-easy";
        }
        else{
            challengeSubject = "culture-easy";
        }

        Hooks();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q1str = q1.getText().toString().trim();
                q1o1str = q1o1.getText().toString().trim();
                q1o2str = q1o2.getText().toString().trim();
                q1o3str = q1o3.getText().toString().trim();
                q1o4str = q1o4.getText().toString().trim();
                ans1str = ans1.getText().toString().trim();

                q2str = q2.getText().toString().trim();
                q2o1str = q2o1.getText().toString().trim();
                q2o2str = q2o2.getText().toString().trim();
                q2o3str = q2o3.getText().toString().trim();
                q2o4str = q2o4.getText().toString().trim();
                ans2str = ans2.getText().toString().trim();

//                if(validate(q1,q2),q1o1.q2o2,....)

//                createNewQuiz();


                ModelClass question1 = new ModelClass(q1str, q1o1str, q1o2str, q1o3str, q1o4str, ans1str);
                ModelClass question2 = new ModelClass(q2str, q2o1str, q2o2str, q2o3str, q2o4str, ans2str);



                db =FirebaseFirestore.getInstance();
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();


                String quizMadeStr = Integer.toString(quizMade+1);
//                String nameForQuizList = "random-"+quizMadeStr;

                String nameForQuizList = challengeSubject+"-"+quizMadeStr;

//                db.collection("users").document(userId)
//                                .collection(quizMadeStr).add(question1);
//                db.collection("users").document(userId)
//                        .collection(quizMadeStr).add(question2);

                db.collection("quizdata").document(userId).
                        collection("challenge").document(challengeSubject)
                                .collection(nameForQuizList).add(question1);

                db.collection("quizdata").document(userId).
                        collection("challenge").document(challengeSubject)
                        .collection(nameForQuizList).add(question2);






                // update the quizMade by ++1

                db.collection("users").document(userId)
                        .update(
                                "quizMade", FieldValue.increment(1)
                        );

                // update the random-made-quiz by ++1

                db.collection("quizdata").document(userId).
                        collection("challenge").document(challengeSubject)
                        .update(
                                "quizcount", FieldValue.increment(1),
                                "quizlist", FieldValue.arrayUnion(nameForQuizList)
                        );



                finish();

            }
        });

















        ImageView back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create.this.finish();
            }
        });


    }



    private void Hooks() {
        q1 = findViewById(R.id.text_question_1);
        q1o1 = findViewById(R.id.text_option_1_1);
        q1o2 = findViewById(R.id.text_option_1_2);
        q1o3 = findViewById(R.id.text_option_1_3);
        q1o4 = findViewById(R.id.text_option_1_4);
        ans1 = findViewById(R.id.text_ans_1);

        q2 = findViewById(R.id.text_question_2);
        q2o1 = findViewById(R.id.text_option_2_1);
        q2o2 = findViewById(R.id.text_option_2_2);
        q2o3 = findViewById(R.id.text_option_2_3);
        q2o4 = findViewById(R.id.text_option_2_4);
        ans2 = findViewById(R.id.text_ans_2);

        submit = findViewById(R.id.submit_quiz);

        showTotal = findViewById(R.id.quiz_made_id);

    }
}