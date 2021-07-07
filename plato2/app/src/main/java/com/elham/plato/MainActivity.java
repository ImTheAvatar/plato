package com.elham.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn , signUp;
    public static DataOutputStream dos;
    public static DataInputStream dis;
    public static Socket socket = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn = findViewById(R.id.sign_in_btn);
        signUp = findViewById(R.id.sign_up_btn);

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.getServerResponse("sign up");

                Intent intent=new Intent(MainActivity.this , SignUpActivity.class);
                startActivity(intent);

            }
        });
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.137.1",5903);
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View view) {
        Response.getServerResponse("sign in");
        Intent intent=new Intent(this, SignInActivity.class);
        startActivity(intent);

    }
}
