package com.elham.plato.ui.game.fourInRow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elham.plato.HomeActivity;
import com.elham.plato.MainActivity;
import com.elham.plato.R;
import com.elham.plato.Response;
import com.elham.plato.ui.game.CreateRoomActivity;

public class FourInRow extends AppCompatActivity {
    Button[][] buttons;
    private boolean player1turn = true;
    private int roundCount=0;
    private int yellow , red;
    static boolean MyTurn=false;
    private int player1points , player2points;
    int[][]colors=new int[7][6];
    Drawable circle0 ;
    int nothing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_in_row);
        buttons = new Button[7][6];
        Drawable circle1 = this.getResources().getDrawable(R.drawable.circle1);
        Drawable circle2 = this.getResources().getDrawable(R.drawable.circle2);
        circle0 = this.getResources().getDrawable(R.drawable.circle0);
//        if(circle0 instanceof ColorDrawable)
//        {
//            nothing=((ColorDrawable) circle0).getColor();
//        }
//        if(circle1 instanceof ColorDrawable)
//        {
//            yellow=((ColorDrawable) circle1).getColor();
//        }
//        if(circle2 instanceof ColorDrawable)
//        {
//            red=((ColorDrawable) circle2).getColor();
//
//        }
        for(int i=0;i<7;i++)
        {
            for(int j=0;j<6;j++)
            {
                colors[i][j]=0 ;
            }
        }
        nothing=0;
        red=1;
        yellow=-1;
//        nothing = ((ColorDrawable)circle0).getColor();
//        yellow = ((ColorDrawable)circle1).getColor();
//        red = ((ColorDrawable)circle2).getColor();
        if(CreateRoomActivity.isHost)
        {
            MyTurn=true;
        }
        else{
            getOpponentMove();
        }

        for(Integer i=0;i<7;i++){
            for(Integer j=0;j<6;j++){
                String buttonID = "btn_"+i+j;
                final int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                final Integer finalI = i;
                final Integer finalJ = j;

                buttons[finalI][finalJ].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(MyTurn)
                        {
                            if(canClick(finalI,finalJ))
                            {
                                Response.giveServerResponse(finalI.toString());
                                Response.giveServerResponse(finalJ.toString());
                                int result=0;
                                if(CreateRoomActivity.isHost)
                                {
                                    System.out.println("result is "+result+" yellow is "+yellow+" red is  "+red);
                                    result=checkForWin(yellow,finalI,finalJ);
                                    if(result==yellow)
                                    {
                                        Response.giveServerResponse("-1");
                                        Response.giveServerResponse("won");
                                        Response.giveServerResponse("im done");
                                        Toast.makeText(getApplicationContext(),"WON",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    }
                                    if(roundCount==42)
                                    {
                                        Response.giveServerResponse("-1");
                                        Response.giveServerResponse("draw");
                                        Response.giveServerResponse("im done");
                                        Toast.makeText(getApplicationContext(),"DRAW",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    }
                                }
                                else {
                                    result=checkForWin(red,finalI,finalJ);
                                    if(result==red)
                                    {
                                        Response.giveServerResponse("-1");
                                        Response.giveServerResponse("won");
                                        Response.giveServerResponse("im done");
                                        Toast.makeText(getApplicationContext(),"WON",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    }
                                    if(roundCount==42)
                                    {
                                        Response.giveServerResponse("-1");
                                        Response.giveServerResponse("draw");
                                        Response.giveServerResponse("im done");
                                        Toast.makeText(getApplicationContext(),"DRAW",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    }
                                }
                                getOpponentMove();
                                MyTurn=false;
                            }else{
                                Toast.makeText(getApplicationContext(),"you cant click here",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"not your turn",Toast.LENGTH_LONG).show();
                        }
                    }
                });



            }
        }}

    void getOpponentMove(){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer row = 0;
                Integer column = 0;
                try {
                    row = Integer.parseInt(MainActivity.dis.readUTF());
                    column = Integer.parseInt(MainActivity.dis.readUTF());
                }catch (Exception e){
                    System.out.println("yes it happened");
                    return;
                }
                int result=0;
                System.out.println("i go the row and column of "+row+" "+column);
                if(!CreateRoomActivity.isHost)
                {
                    result=checkForWin(yellow,row,column);
                    if(result==yellow)
                    {
                        Response.giveServerResponse("-1");
                        Response.giveServerResponse("lost");
                        Response.giveServerResponse("im in thread");
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                    if(roundCount==42)
                    {
                        Response.giveServerResponse("-1");
                        Response.giveServerResponse("draw");
                        Response.giveServerResponse("im in thread");
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                }
                else{
                    result=checkForWin(red,row,column);
                    if(result==red)
                    {
                        Response.giveServerResponse("-1");
                        Response.giveServerResponse("lost");
                        Response.giveServerResponse("im in thread");
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                    if(roundCount==42)
                    {
                        Response.giveServerResponse("-1");
                        Response.giveServerResponse("draw");
                        Response.giveServerResponse("im in thread");
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                }
                MyTurn=true;
            }
        });
        thread.start();
    }
    private int checkForWin(int color,int row,int column){//if it returns 0 means no one
        int count=1;
        int i = row;
        int j = column;
        if(color==yellow){
            colors[i][j]=yellow;
            buttons[row][column].setBackgroundResource(R.drawable.circle1);
        }else{
            colors[i][j]=red;
            buttons[row][column].setBackgroundResource(R.drawable.circle2);
        }

        roundCount++;
        try{
            while(true){
                i++;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("wooooooooooooooooooooooooooooon1");
                    return color;}
            }
        }catch(Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;
        try{
            while(true){
                j++;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("woooooooooooooooooooooooooooon2");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;
        try{
            while(true){
                i--;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }
                if(count==4){
                    System.out.println("woooooooooooooooooooooooooooooooon3");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;
        try{
            while(true){
                j--;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("wooooooooooooooooooooooooooooon4");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;
        try{
            while(true){
                j--;
                i--;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("woooooooooooooooooooooooooon6");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;

        try{
            while(true){
                j++;
                i--;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("woooooooooooooooooooooooon7");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;

        try{
            while(true){
                j++;
                i++;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("wooooooooooooooooooooooooooon8");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }
        i=row;
        j=column;
        count=1;

        try{
            while(true){
                j--;
                i++;
                if(colors[i][j]!=color){
                    break;
                }
                else {
                    count++;
                }

                if(count==4){
                    System.out.println("woooooooooooooooooooooooooon9");
                    return color;}
            }
        }catch (Exception e){
            System.out.println("nvm");
        }

        return 0;

    }
    private void player1wins(){
        player1points++;
        Toast.makeText(this,"player1 wins",Toast.LENGTH_SHORT).show();

    }
    private void player2wins(){
        player2points++;
        Toast.makeText(this,"player2 wins",Toast.LENGTH_SHORT).show();

    }
    private void draw(){
        Toast.makeText(this,"draw!", Toast.LENGTH_SHORT).show();

    }

    boolean canClick(int i,int j){
        if (colors[i][j] != nothing)
            return false;/////////ino man gozashtam
        try {
            return colors[i + 1][j] != nothing;
        } catch (Exception e) {
            return true;
        }
        //}/////inja ro bebinid/////
    }
}


