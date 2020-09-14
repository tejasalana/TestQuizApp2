package com.example.mytestquiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ValidatorActivity extends AppCompatActivity {
    TextView totalTextView, correctTextView, wrongTextView, resultTextView;
    Button retake,quit;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);

        Intent intent = getIntent();
        Integer totalQuestions = intent.getIntExtra("total", 0);
        Integer correctQuestions = intent.getIntExtra("correct", 0);
        Integer wrongQuestions = intent.getIntExtra("wrong", 0);


        totalTextView = findViewById(R.id.total);
        correctTextView = findViewById(R.id.correct);
        wrongTextView = findViewById(R.id.wrong);
        resultTextView = findViewById(R.id.result);
        retake = findViewById(R.id.retake);
        quit = findViewById(R.id.quit);

        if(correctQuestions >= (totalQuestions*0.5)){
            result = "PASS";
        }
        else {
            result = "FAIL";
        }

        totalTextView.setText("Total Questions: "+totalQuestions);
        correctTextView.setText("Correct Answers: "+correctQuestions);
        wrongTextView.setText("wrong Answers: "+wrongQuestions);
        resultTextView.setText("Result is : "+result);

        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValidatorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValidatorActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("exit",true);
                startActivity(intent);
            }
        });


    }
}