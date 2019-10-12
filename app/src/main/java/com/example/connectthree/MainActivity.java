package com.example.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import androidx.widget.GridLayout;

import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = yellow and 1 = red
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 means unplayed


    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void center(View view) {


        ImageView imageView11 = (ImageView) view;

        int tappedCounter = Integer.parseInt(imageView11.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;


            if (activePlayer == 0) {
                imageView11.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {
                imageView11.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            imageView11.animate().alpha(1).rotation(3600).setDuration(1000);

            for (int[] winningPosition : winningPositions) {


                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }


                    Button button = findViewById(R.id.button);

                    TextView textView = findViewById(R.id.textView);

                    textView.setText(winner + "Has Won!");

                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

                }

            }

        }


    }

    public void playAgain(View view) {
       Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        GridLayout mGridView =  findViewById(R.id.gridLayout);

        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        for (int i = 0; i < mGridView.getChildCount(); i++) {
            ImageView imageView = (ImageView) mGridView.getChildAt(i);
            imageView.setImageDrawable(null);
        }

        activePlayer = 0; // 0 = yellow and 1 = red
        for (int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }

        gameActive = true;
    }
}
