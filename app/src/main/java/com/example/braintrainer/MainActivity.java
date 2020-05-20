package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton,playAgain,button0,button1,button2,button3;
    TextView timerTextView,scoreTextView,sumTextView,resultTextView;
    ArrayList<Integer> answers=new ArrayList<>();
    int score,noOfQuestions,locationOfCorrectAnswer;
    ConstraintLayout gameLayout;

    public void playAgain(View view)
    {
        score=0;
        noOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
       new CountDownTimer(30100,1000)
       {
           @Override
           public void onTick(long l) {
               timerTextView.setText(String.valueOf(l / 1000) + "s");
           }

           @Override
           public void onFinish() {
               resultTextView.setText("Done!");
               playAgain.setVisibility(View.VISIBLE);
           }
       }.start();

    }

    public  void chooseAnswer(View view)
    {
        Log.i("button tappped",view.getTag().toString()+"");
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        newQuestion();

    }

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));

    }

    public void newQuestion()
    {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        scoreTextView=findViewById(R.id.scoreTextView);
        sumTextView=findViewById(R.id.sumTextView);
        timerTextView=findViewById(R.id.timerTextView);
        resultTextView=findViewById(R.id.resultTextView);
        playAgain=findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
