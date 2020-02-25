package com.example.scarnesdice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int User_Overall_Score = 0;
    private int Computer_Overall_Score = 0;
    private int User_Turn_Score=0;
    private int Computer_Turn_Score = 0;
    private Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void UpdateDice(View view){
        int diceValue = rollDice();
        ImageView imageView = findViewById(R.id.imageView);
        if (diceValue==1){
            imageView.setImageResource(R.drawable.dice1);
        }else if (diceValue ==2){
            imageView.setImageResource(R.drawable.dice2);
        }else if (diceValue ==3){
            imageView.setImageResource(R.drawable.dice3);
        }else if (diceValue ==4){
            imageView.setImageResource(R.drawable.dice4);
        }else if (diceValue ==5){
            imageView.setImageResource(R.drawable.dice5);
        }else if (diceValue ==6){
            imageView.setImageResource(R.drawable.dice6);
        }else {
            throw new IndexOutOfBoundsException("roll dice is creating values outside of 1-6");
        }
        updateUserScoreText(diceValue);
    }

    public void resetGame(View view){
        User_Overall_Score = 0;
        Computer_Overall_Score = 0;
        User_Turn_Score=0;
        Computer_Turn_Score = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText(R.string.score_text);

    }

    public void holdScore(View view){
        User_Overall_Score+=User_Turn_Score;
        User_Turn_Score = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText(Html.fromHtml("Your Score: "+User_Overall_Score +"  Computer Score: "+ Computer_Overall_Score));

    }

    private void updateUserScoreText(int score){
        TextView textView = findViewById(R.id.textView);
        if(score ==1){
            User_Turn_Score=0;
            textView.setText(R.string.score_text);
        }else{
            User_Turn_Score+=score;
            textView.setText(Html.fromHtml("your turn score: " + User_Turn_Score));

        }
    }

    private int rollDice(){
        return random.nextInt(6)+1;
    }
}
