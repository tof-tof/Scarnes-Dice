package com.example.scarnesdice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
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
    }




    private int rollDice(){
        return random.nextInt(6)+1;
    }
}