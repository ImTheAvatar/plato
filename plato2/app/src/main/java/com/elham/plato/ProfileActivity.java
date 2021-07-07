package com.elham.plato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    TextView username_txt_view;
    EditText bio_editor_field;
    Button backBtn ;
    ImageView settingBtn;
    ArrayList<Integer> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String[] names = {"xoScore","guessthewordScore","warshipsScore","dotgameScore","othelloScore","fourinarowScore"};

        recyclerView = findViewById(R.id.recyclerViewforgames);
        bio_editor_field = findViewById(R.id.editText);
        backBtn = findViewById(R.id.back_button);
        settingBtn = findViewById(R.id.setting_btn);


        scores = new ArrayList<>();
        for(int i=0;i<6;i++){
            scores.add((Integer)(getIntent().getExtras().get(names[i])));
        }





        PlayingStatusListAdapter adapter = new PlayingStatusListAdapter(scores);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //    getIntent().getIntExtra(getString(R.string.usernamefromhome),0);
        String username = (String)getIntent().getExtras().get("USERNAME");
        String bio = (String)getIntent().getExtras().get("BIO");
        username_txt_view = findViewById(R.id.textView3);
        System.out.println("im in profile activity and user name and bio is "+username+" and "+bio);
        username_txt_view.setText(username);
        System.out.println("im passed username");
        bio_editor_field.setText(bio);





        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newbio = bio_editor_field.getText().toString();
                Response.getServerResponse("edit bio");
                Response.getServerResponse(newbio);
                Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });




    }


}
