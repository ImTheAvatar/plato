package com.elham.plato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    Button logOut , aboutUs_btn;

    String aboutStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        logOut = findViewById(R.id.log_out_btn);
        aboutUs_btn = findViewById(R.id.about_us_btn);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Response.giveServerResponse("log out");
            }
        });

        aboutUs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Response.giveServerResponse("about us");
                Intent intent = new Intent(SettingActivity.this,AboutUsActivity.class);
                startActivity(intent);

            }
        });
    }
}
