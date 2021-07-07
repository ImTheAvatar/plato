package com.elham.plato.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.elham.plato.MainActivity;
import com.elham.plato.R;
import com.elham.plato.ui.game.fourInRow.FourInRow;
import com.elham.plato.ui.game.othello.OthelloActivity;
import com.elham.plato.ui.game.xo.XOActivity;

import java.io.IOException;

public class LoadingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MainActivity.dis.readUTF();
                    Intent intent = null;
                    if(GameFragment.gameType==gameType.XO)
                    {
                        intent=new Intent(getApplicationContext(), XOActivity.class);
                    }
                    if(GameFragment.gameType==gameType.GuessTheWord)
                    {
                        intent=new Intent(getApplicationContext(),GuessTheWord.class);
                    }
                    if(GameFragment.gameType==gameType.Othello)
                    {
                        intent=new Intent(getApplicationContext(), OthelloActivity.class);
                    }
                    if(GameFragment.gameType==gameType.FourInARow)
                    {
                        intent=new Intent(getApplicationContext(), FourInRow.class);
                    }
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}