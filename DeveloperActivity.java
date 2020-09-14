package com.example.mytestquiz2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class DeveloperActivity extends AppCompatActivity {

    TextView question,name;
    Button btn1,btn2,btn3,btn4;
    int score = 0;
    String Answer = "";
    int countOfQuestions = 0;
    int noOfQuestions =0;
    QuestionsProvider questionsProvider;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        //r = new Random();
        Intent intent = getIntent();
        final String nameOfUser = intent.getStringExtra("name");
        questionsProvider = intent.getParcelableExtra("question");
        noOfQuestions = questionsProvider.mNoOfQuestions;

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        question = findViewById(R.id.question);
        name = findViewById(R.id.name);
        name.setText("Hello "+nameOfUser);


        updateQuestion(r.nextInt(questionsProvider.mQuestions.length));
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                countOfQuestions++;
                if(btn1.getText().equals(Answer)){
                    score++;
                }
                checkToExit(countOfQuestions);
                updateQuestion(r.nextInt(questionsProvider.mQuestions.length));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                countOfQuestions++;
                if(btn2.getText().equals(Answer)){

                    score++;
                }
                checkToExit(countOfQuestions);
                updateQuestion(r.nextInt(questionsProvider.mQuestions.length));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                countOfQuestions++;
                if(btn3.getText().equals(Answer)){

                    score++;
                }
                checkToExit(countOfQuestions);
                updateQuestion(r.nextInt(questionsProvider.mQuestions.length));
            }
        });

        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                countOfQuestions++;
                if(btn4.getText().equals(Answer)){
                    score++;

                }
                checkToExit(countOfQuestions);
                updateQuestion(r.nextInt(questionsProvider.mQuestions.length));
            }
        });
    }

    private void updateQuestion(int num)
    {
        question.setText((countOfQuestions+1)+". "+questionsProvider.getQuestion(num));
        btn1.setText(questionsProvider.getChoice1(num));
        btn2.setText(questionsProvider.getChoice2(num));
        btn3.setText(questionsProvider.getChoice3(num));
        btn4.setText(questionsProvider.getChoice4(num));
        Answer = questionsProvider.getCorrectAnswer(num);
    }
    void checkToExit(int num){
        if(num == noOfQuestions)
        {
            Intent intent = new Intent(DeveloperActivity.this, ValidatorActivity.class);
            intent.putExtra("total",noOfQuestions);
            intent.putExtra("correct",score);
            intent.putExtra("wrong",(noOfQuestions-score));
            startActivity(intent);
        }
    }
}