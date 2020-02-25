package com.example.scarnesdice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.Button;
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

    public void userRollDice(View view){
        int diceValue = rollDice();
        updateDicePicture(diceValue);
        updateUserScoreText(diceValue);
    }

    private void updateDicePicture(int diceValue) {
        ImageView imageView = findViewById(R.id.imageView);
        int dicePic;
        if (diceValue == 1) {
            dicePic = R.drawable.dice1;
        } else if (diceValue == 2) {
            dicePic = R.drawable.dice2;
        } else if (diceValue == 3) {
            dicePic = R.drawable.dice3;
        } else if (diceValue == 4) {
            dicePic = R.drawable.dice4;
        } else if (diceValue == 5) {
            dicePic = R.drawable.dice5;
        } else if (diceValue == 6) {
            dicePic = R.drawable.dice6;
        } else {
            throw new IndexOutOfBoundsException("roll dice is creating values outside of 1-6");
        }
        imageView.setImageResource(dicePic);
    }


    public void resetGame(View view){
        User_Overall_Score = 0;
        Computer_Overall_Score = 0;
        User_Turn_Score=0;
        Computer_Turn_Score = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText(R.string.score_text);
        enableButtons();

    }

    public void holdScore(View view){
            User_Overall_Score += User_Turn_Score;
            User_Turn_Score = 0;
        TextView textView = findViewById(R.id.textView);
        textView.setText(Html.fromHtml("Your Score: "+User_Overall_Score +"  Computer Score: "+ Computer_Overall_Score));
        if (!endGame()){
            computerTurn();
        }
    }

    private void updateUserScoreText(int score){
        TextView textView = findViewById(R.id.textView);
        if(score ==1){
            User_Turn_Score=0;
            textView.setText(Html.fromHtml("Your Score: "+User_Overall_Score +"  Computer Score: "+ Computer_Overall_Score));
            computerTurn();
        }else{
            User_Turn_Score+=score;
            textView.setText(Html.fromHtml("your turn score: " + User_Turn_Score));
        }
    }

    private void computerTurn(){
        disableButtons();
        boolean computerPlay = true;
        final TextView textView = findViewById(R.id.textView);
        while(computerPlay){
            if (Computer_Turn_Score<20){
                int diceValue = rollDice();
                updateDicePicture(diceValue);
                if(diceValue ==1){
                    Computer_Turn_Score=0;
                    computerPlay= false;
                }else{
                    Computer_Turn_Score+=diceValue;
                    textView.setText(Html.fromHtml("Computer turn score: " + User_Turn_Score));
                }
            }
            else{
                Computer_Overall_Score += Computer_Turn_Score;
                Computer_Turn_Score = 0;
                computerPlay= false;
            }
        }
        textView.setText(Html.fromHtml("Your Score: "+User_Overall_Score +"  Computer Score: "+ Computer_Overall_Score));
        if (!endGame()){
           enableButtons();
        }
    }

    private int rollDice(){
        return random.nextInt(6)+1;
    }
    private void disableButtons(){
        Button rollButton = findViewById(R.id.buttonRoll);
        Button holdButton = findViewById(R.id.buttonHold);
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);
    }

    private void enableButtons(){
        Button rollButton = findViewById(R.id.buttonRoll);
        Button holdButton = findViewById(R.id.buttonHold);
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
    }
    private Boolean endGame(){
        TextView textView = findViewById(R.id.textView);
        boolean gameEnded = true;
        if (Computer_Overall_Score>=100){
            disableButtons();
            textView.append("  Computer Won. Hit 'RESET' to play again");
        }
        else if (User_Overall_Score>=100){
            disableButtons();
            textView.append("  User Won. Hit 'RESET' to play again");
        }
        else{
            gameEnded = false;
        }
        return gameEnded;
    }
}
