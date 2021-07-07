package com.elham.plato.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Replaceable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elham.plato.R;
import com.elham.plato.Response;

public class CreateRoomActivity extends AppCompatActivity {
    EditText new_room_name;
    Button create_new_room;
    public static boolean isHost=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        new_room_name = findViewById(R.id.new_room_name);
        create_new_room = findViewById(R.id.create_room_btn);

        create_new_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = new_room_name.getText().toString();
                if(Response.getServerResponse(newName).equals("ok")){
                    isHost=true;
                    Intent intent = new Intent(CreateRoomActivity.this,LoadingActivity.class);
//                CasualFragment.adapter.notifyDataSetChanged();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"already exists",Toast.LENGTH_SHORT);
                }

            }
        });
    }
}
