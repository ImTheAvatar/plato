package com.elham.plato;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username_E_txt , password_E_txt;
    private Button signUp;
    private TextView invalidPass_view , invalidUsername_view;
    private boolean validUsername , validPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username_E_txt = findViewById(R.id.newUsername);
        password_E_txt = findViewById(R.id.newPassword);
        signUp = findViewById(R.id.signUp);
        invalidPass_view = findViewById(R.id.invalidPass);
        invalidUsername_view = findViewById(R.id.invalidUsername);

        //set invalid messages invisible at first
        invalidPass_view.setVisibility(View.INVISIBLE);
        invalidUsername_view.setVisibility(View.INVISIBLE);

        //validate username
        password_E_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useName = username_E_txt.getText().toString();
                if( TextUtils.isEmpty(useName)){
                    invalidUsername_view.setVisibility(View.VISIBLE);
                }else{
                    invalidUsername_view.setVisibility(View.INVISIBLE);
                    validUsername=true;
                }
            }
        });
        //validate password
        signUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String pass = password_E_txt.getText().toString();
        if(pass.length()<5){
            invalidPass_view.setVisibility(View.VISIBLE);
        }else{

            invalidPass_view.setVisibility(View.INVISIBLE);
            String serverResponseU = Response.getServerResponse(username_E_txt.getText().toString());
            String serverResponseP = Response.getServerResponse(pass);
            validPass=true;
            if(serverResponseP.equals("ok it's saved") && serverResponseU.equals("ok it's saved")){
                SignInActivity.current_user = username_E_txt.getText().toString();
                Intent intent=new Intent(this, HomeActivity.class);
                SignInActivity.current_user = username_E_txt.getText().toString();
                startActivity(intent);

            }
            else{
                Toast.makeText(this,serverResponseP,Toast.LENGTH_LONG).show();
            }
        }
    }
}
