package com.elham.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username_E_txt , password_E_txt;
    Button signIn , createAccount;
    String username , password;
    String U_serverResponse ;
    String P_serverResponse ;

    public static String current_user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        username_E_txt = findViewById(R.id.username);
        password_E_txt = findViewById(R.id.password);
        signIn = findViewById(R.id.signin);
        createAccount = findViewById(R.id.createAccount);
        //saving username and password in two variables


        //sending data to server


        //navigate user to homepage
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username_E_txt.getText().toString();
                password = password_E_txt.getText().toString();
                System.out.println("im in activity and username and pass is "+username+" and "+password+" ..........");
                U_serverResponse = Response.getServerResponse(username);
                P_serverResponse = Response.getServerResponse(password);
                if(U_serverResponse.equals("ok") && P_serverResponse.equals("ok")){
                    current_user = username;
                    Intent intent=new Intent(SignInActivity.this, HomeActivity.class);
                    current_user=username;
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SignInActivity.this,P_serverResponse,Toast.LENGTH_LONG).show();
                }
            }
        });



        //navigate user to sign up page
        createAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
