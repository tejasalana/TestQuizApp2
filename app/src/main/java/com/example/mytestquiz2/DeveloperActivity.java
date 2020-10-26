package com.example.mytestquiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeveloperActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView question;
    private Button btn1,btn2,btn3,btn4;
    private int score = 0;
    private String Answer = "";
    private int countOfQuestions = 0;
    private int noOfQuestions =0;
    private QuestionsProvider questionsProvider;
    private List<Integer> list = new ArrayList<>();
    private int nextQuestion = 0,totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_developer);
        Intent intent = getIntent();
        String nameOfUser = intent.getStringExtra("name");
        if(nameOfUser.equals("")){
            nameOfUser = "User";
        }
        questionsProvider = intent.getParcelableExtra("question");
        if (questionsProvider != null) {
            noOfQuestions = questionsProvider.mNoOfQuestions;
            totalQuestions = questionsProvider.mQuestions.length;
        }

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        question = findViewById(R.id.question);
        TextView name = findViewById(R.id.name);
        name.setText("Hello " + nameOfUser);

        for(int i=0; i<totalQuestions; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        updateQuestion(list.get(nextQuestion++));
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
        @Override
        public void onClick (View view) {
            if (view.getId() == R.id.button) {
                if (btn1.getText().equals(Answer)) {
                    score++;
                }
            } else if (view.getId() == R.id.button2) {
                if (btn2.getText().equals(Answer)) {
                    score++;
                }
            } else if (view.getId() == R.id.button3) {
                if (btn3.getText().equals(Answer)) {
                    score++;
                }
            } else {
                if (btn4.getText().equals(Answer)) {
                    score++;
                }
            }
            countOfQuestions++;
            checkToExit(countOfQuestions);
            updateQuestion(list.get(nextQuestion++));
        }

    private void updateQuestion(int num) {
        question.setText((countOfQuestions+1)+". "+questionsProvider.getQuestion(num));
        btn1.setText(questionsProvider.getChoice1(num));
        btn2.setText(questionsProvider.getChoice2(num));
        btn3.setText(questionsProvider.getChoice3(num));
        btn4.setText(questionsProvider.getChoice4(num));
        Answer = questionsProvider.getCorrectAnswer(num);
    }
    void checkToExit(int num){
        if(num == noOfQuestions) {
            Intent intent = new Intent(DeveloperActivity.this, ValidatorActivity.class);
            intent.putExtra("total",noOfQuestions);
            intent.putExtra("correct",score);
            intent.putExtra("wrong",(noOfQuestions-score));
            startActivity(intent);
        }
    }

}