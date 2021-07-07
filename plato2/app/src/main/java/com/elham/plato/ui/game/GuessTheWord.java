package com.elham.plato.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elham.plato.HomeActivity;
import com.elham.plato.R;
import com.elham.plato.Response;

public class GuessTheWord extends AppCompatActivity {
    EditText field;
    String theWord="";
    Button applyBut;
    boolean yourScore=false,OpponentScore=false,yourTurn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_word);
        field=findViewById(R.id.guess_txt_view);
        applyBut=findViewById(R.id.applyBut);
        System.out.println("im in guesstheword");
        if(CreateRoomActivity.isHost)
        {
            yourTurn=true;
            field.setHint("Write What You Want Him To Guess");
        }
        else{
            field.setHint("Write Your Guess");
            Toast.makeText(getApplicationContext(),"wait for the host to determine a word",Toast.LENGTH_LONG).show();
            theWord=Response.getServerResponse();
        }
        applyBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yourTurn) {
                    System.out.println("my turn");
                    Response.giveServerResponse(field.getText().toString());
                    String response=Response.getServerResponse();
                    field.setText("");
                    field.setHint("Write your guess now");
                    System.out.println(response+" this is what server said");
                    if(response.equals("he won"))
                    {
                        Toast.makeText(getApplicationContext(),"your opponent guessed right",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"your opponent lost",Toast.LENGTH_SHORT).show();
                    }
                    if(OpponentScore||yourScore){
                        System.out.println("match ended");
                        if(yourScore&&response.equals("he won"))
                        {
                            System.out.println("draw");
                            Response.giveServerResponse("draw");
                            Toast.makeText(getApplicationContext(),"draw",Toast.LENGTH_LONG).show();
                        }
                        if(OpponentScore&&response.equals("he won"))
                        {
                            System.out.println("lost");
                            Response.giveServerResponse("lost");
                            Toast.makeText(getApplicationContext(),"lost and damned",Toast.LENGTH_LONG).show();
                        }
                        if(yourScore&&!response.equals("he won"))
                        {
                            System.out.println("won");
                            Response.giveServerResponse("won");
                            Toast.makeText(getApplicationContext(),"you won",Toast.LENGTH_LONG).show();
                        }
                        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                    if(response.equals("he won"))
                    {
                        OpponentScore=true;
                    }
                    else{
                        yourScore=true;
                    }
                    System.out.println("changing now");
                    theWord=Response.getServerResponse();
                    yourTurn=!yourTurn;
                }
                else{
                    String guessedWord = checkWord(field.getText().toString());
                    if (guessedWord.equals(theWord)) {
                        field.setText("");
                        field.setHint("write your word now");
                        yourTurn = !yourTurn;
                        Response.giveServerResponse("won");
                        if(OpponentScore||yourScore){
                            if(yourScore)
                            {
                                Response.giveServerResponse("won");
                                Toast.makeText(getApplicationContext(),"you won",Toast.LENGTH_LONG).show();
                            }
                            if(OpponentScore)
                            {
                                Response.giveServerResponse("draw");
                                Toast.makeText(getApplicationContext(),"draw",Toast.LENGTH_LONG).show();
                            }
                            Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        yourScore=true;
                    } else {
                        Toast.makeText(getApplicationContext(), guessedWord, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    String checkWord(String guess)
    {
        String tmp="";
        for(int i=0;i<theWord.length();i++)
        {
            if(i<guess.length())
            {
                if(guess.charAt(i)==theWord.charAt(i))
                {
                    tmp=tmp.concat(String.valueOf(guess.charAt(i)));
                }
                else{
                    tmp=tmp.concat("_");
                }
            }
            else{
                tmp=tmp.concat("_");
            }
        }
        return tmp;
    }
}
