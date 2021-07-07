package com.elham.plato.ui.game.xo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elham.plato.HomeActivity;
import com.elham.plato.MainActivity;
import com.elham.plato.R;
import com.elham.plato.Response;
import com.elham.plato.SignInActivity;
import com.elham.plato.ui.game.CreateRoomActivity;

import java.io.IOException;

public class XOActivity  extends AppCompatActivity {
    private Button[][] buttons;
    private int roundCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_o);

        buttons = new Button[3][3];
        buttons[0][0]=findViewById(R.id.btn_00);
        buttons[1][0]=findViewById(R.id.btn_10);
        buttons[2][0]=findViewById(R.id.btn_20);
        buttons[0][1]=findViewById(R.id.btn_01);
        buttons[0][2]=findViewById(R.id.btn_02);
        buttons[1][1]=findViewById(R.id.btn_11);
        buttons[1][2]=findViewById(R.id.btn_12);
        buttons[2][1]=findViewById(R.id.btn_21);
        buttons[2][2]=findViewById(R.id.btn_22);
        if (!CreateRoomActivity.isHost) {
            getOpponentMove();
        }
        buttons[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[0][0].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer integer = 0;
                Response.giveServerResponse(integer.toString());
                integer = 0;
                Response.giveServerResponse(integer.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[0][0].setText("X");
                } else {
                    str = "O";
                    buttons[0][0].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[1][0].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 1;
                Response.giveServerResponse(r.toString());
                Integer c = 0;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[1][1].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 1;
                Response.giveServerResponse(r.toString());
                Integer c = 1;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                getOpponentMove();
            }
        });
        buttons[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[1][2].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 1;
                Response.giveServerResponse(r.toString());
                Integer c = 2;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[2][0].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 2;
                Response.giveServerResponse(r.toString());
                Integer c = 0;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[2][1].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 2;
                Response.giveServerResponse(r.toString());
                Integer c = 1;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[2][2].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 2;
                Response.giveServerResponse(r.toString());
                Integer c = 2;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[0][1].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 0;
                Response.giveServerResponse(r.toString());
                Integer c = 1;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                getOpponentMove();
            }
        });
        buttons[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!buttons[0][2].getText().toString().equals(""))
                {
                    return;
                }
                if (CreateRoomActivity.isHost && roundCount % 2 == 1) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!CreateRoomActivity.isHost && roundCount % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "W8 For Your Opponent", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer r = 0;
                Response.giveServerResponse(r.toString());
                Integer c = 2;
                Response.giveServerResponse(c.toString());
                String str = "X";
                if (CreateRoomActivity.isHost) {
                    buttons[r][c].setText("X");
                } else {
                    str = "O";
                    buttons[r][c].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("won");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im done");
                    Toast.makeText(getApplicationContext(), "DRAW !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                getOpponentMove();
            }
        });
    }

    void getOpponentMove(){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer row = 0;
                Integer column = 0;
                try {
                    row = Integer.parseInt(MainActivity.dis.readUTF());
                    column = Integer.parseInt(MainActivity.dis.readUTF());
                }catch (NumberFormatException e){
                    System.out.println("yes it happened");
                    return;
                }catch (IOException e) {
                    e.printStackTrace();
                }
                String str = "X";
                if (!CreateRoomActivity.isHost) {
                    buttons[row][column].setText("X");
                } else {
                    str = "O";
                    buttons[row][column].setText("O");
                }
                roundCount++;
                if (checkForWin(str)) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("lost and damned");
                    Response.giveServerResponse("im in thread");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    return;
                }
                if (roundCount >= 9) {
                    Response.giveServerResponse("-1");
                    Response.giveServerResponse("draw");
                    Response.giveServerResponse("im in thread");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);

                }
            }
        });
        thread.start();
    }
    private boolean checkForWin(String str) {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("") && field[i][0].equals(str)) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("") && field[0][i].equals(str)) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("") && field[0][0].equals(str)) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("") && field[0][2].equals(str)) {
            return true;
        }
        return false;

    }
}
