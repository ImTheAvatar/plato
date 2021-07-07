package com.elham.plato.ui.game.othello;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Replaceable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elham.plato.HomeActivity;
import com.elham.plato.R;
import com.elham.plato.Response;
import com.elham.plato.SignInActivity;
import com.elham.plato.ui.game.CreateRoomActivity;

public class OthelloActivity extends AppCompatActivity {

    static Button[][] buttons = new Button[6][6];
    static int[][] numbers=new int[6][6];
    static boolean YourTurn = false;

    static Integer yourPoints=0 , opponentPoints=0;

    static TextView player1_txt_view , player2_txt_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othello);

        System.out.println("im in othello now");
        player1_txt_view=findViewById(R.id.player1_txt_view);
        player2_txt_view=findViewById(R.id.player2_txt_view);
        player2_txt_view.setText("2");
        player1_txt_view.setText("2");
        if(CreateRoomActivity.isHost)
            YourTurn=true;
        else
            getOpponentsMove();
        for(int i=0;i<6;i++)
            {
            for(int j=0;j<6;j++)
            {
                numbers[i][j]=0;
            }
        }
        for(Integer i = 0; i<6; i++){
            for(Integer j=0;j<6;j++){
                String buttonID = "btn_"+i.toString()+j.toString();

                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                final Integer finalI = i;
                final Integer finalJ = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(canClick(finalI,finalJ))
                        {
                            if (YourTurn){
                                Response.giveServerResponse(finalI.toString());
                                Response.giveServerResponse(finalJ.toString());
                                int color=Color.BLACK;
                                if(CreateRoomActivity.isHost)
                                {
                                    color=Color.WHITE;
                                }
                                clicked(color,finalI,finalJ);
                                if(yourPoints+opponentPoints>=36)
                                {
                                    Response.giveServerResponse("-1");
                                    if(yourPoints>opponentPoints)
                                    {
                                        Toast.makeText(getApplicationContext(),"won",Toast.LENGTH_LONG).show();
                                        Response.giveServerResponse("won");
                                    }else {
                                        if (yourPoints < opponentPoints) {
                                            Toast.makeText(getApplicationContext(),"lost",Toast.LENGTH_LONG).show();
                                            Response.giveServerResponse("lost");
                                        }else{
                                            Toast.makeText(getApplicationContext(),"draw",Toast.LENGTH_LONG).show();
                                            Response.giveServerResponse("draw");
                                        }
                                    }
                                    Response.giveServerResponse("im done");
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                }
                                YourTurn=false;
                                System.out.println("i told the server");
                                updateTexts();
                                getOpponentsMove();
                            }
                            else
                                Toast.makeText(getApplicationContext(),"not your turn",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"cant click here",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
        buttons[2][2].setBackgroundColor(Color.BLACK);
        buttons[2][3].setBackgroundColor(Color.WHITE);
        buttons[3][2].setBackgroundColor(Color.WHITE);
        buttons[3][3].setBackgroundColor(Color.BLACK);
        numbers[2][2]=-1;
        numbers[2][3]=1;
        numbers[3][2]=1;
        numbers[3][3]=-1;
    }
    void getOpponentsMove(){
        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Integer j=null;
                Integer i= null;
                try {
                    i = Integer.parseInt(Response.getServerResponse());
                    j =Integer.parseInt(Response.getServerResponse());
                } catch (NumberFormatException e) {
                    System.out.println("yes it happened");;
                    return;
                }
                Integer color=Color.WHITE;
                if(CreateRoomActivity.isHost)
                    color=Color.BLACK;
                clicked(color,i,j);
                if(yourPoints+opponentPoints>=36)
                {
                    Response.giveServerResponse("-1");
                    if(yourPoints>opponentPoints)
                    {
                        Response.giveServerResponse("won");
                    }else {
                        if (yourPoints < opponentPoints) {
                            Response.giveServerResponse("lost");
                        }else{
                            Response.giveServerResponse("draw");
                        }
                    }
                    Response.giveServerResponse("im in thread");
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                YourTurn=true;
            }
        });
        thread.start();
    }
    boolean canClick(int i,int j){
        if(numbers[i][j]!=0)
            return false;
        try {
            if (numbers[i + 1][j + 1] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i + 1][j] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i + 1][j -1] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i ][j + 1] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i - 1][j + 1] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i - 1][j - 1] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i - 1][j] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        try {
            if (numbers[i][j-1] != 0)
                return true;
        }catch (Exception e){
            System.out.println("nvm");
        }
        return false;
    }
    @SuppressLint("SetTextI18n")
    void clicked(Integer t, Integer x, Integer y) {
        if(t==Color.BLACK)
        {
            t=-1;
        }else t=1;
        numbers[x][y]=t;
        int cnt=t;
        int j=y;
        int i=x;
        try {
            while (true) {
                j++;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (j > y) {
                        j--;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                i++;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (i > x) {
                        i--;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                i--;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (i < x) {
                        i++;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                j--;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (j < y) {
                        j++;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                j++;
                i++;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (j > y && i > x) {
                        i--;
                        j--;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                j--;
                i++;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (j < y && i > x) {
                        i--;
                        j++;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                j++;
                i--;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (j > y && i < x) {
                        i++;
                        j--;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        j=y;
        i=x;
        try {
            while (true) {
                j--;
                i--;
                if(numbers[i][j]==0)
                    break;
                if (numbers[i][j] == t) {
                    while (j < y && i < x) {
                        i++;
                        j++;
                        numbers[i][j] = t;
                    }
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException ex){}
        yourPoints=0;
        opponentPoints=0;
        for(int n=0;n<6;n++)
        {
            for(int m=0;m<6;m++)
            {
                if(numbers[n][m]==1)
                {
                    if(CreateRoomActivity.isHost)
                    {
                        yourPoints++;
                    }
                    else{
                        opponentPoints++;
                    }
                    buttons[n][m].setBackgroundColor(Color.WHITE);
                }
                if(numbers[n][m]==-1)
                {
                    if(CreateRoomActivity.isHost)
                    {
                        opponentPoints++;
                    }
                    else{
                        yourPoints++;
                    }
                    buttons[n][m].setBackgroundColor(Color.BLACK);
                }
                if(numbers[n][m]==0)
                {
                    buttons[n][m].setBackgroundColor(Color.GRAY);
                }
            }
        }
       // player1_txt_view.setText(yourPoints.toString());
       // player2_txt_view.setText(opponentPoints.toString());

    }
    @SuppressLint("SetTextI18n")
    void updateTexts(){
        player1_txt_view.setText(yourPoints.toString());
        player2_txt_view.setText(opponentPoints.toString());
    }
}

