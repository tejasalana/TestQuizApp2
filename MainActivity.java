package com.example.mytestquiz2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public  class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getBooleanExtra("exit",false)){
            finish();
        }

        Button btn = findViewById(R.id.startBtn);
        final EditText nameText = findViewById(R.id.editTextName);
        final QuestionsProvider questionsProvider = new QuestionsProvider();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                Intent intent = new Intent(MainActivity.this, DeveloperActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("question",  questionsProvider);
                startActivity(intent);
            }
        });
    }
}