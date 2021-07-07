package com.elham.plato.ui.friends;

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

public class AddFriendActivity extends AppCompatActivity {
    EditText friend_username;
    Button add_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        friend_username = findViewById(R.id.friend_username);
        add_friend = findViewById(R.id.add_friend);

        add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String friendUserName = friend_username.getText().toString();
                String serverRes = Response.getServerResponse(friendUserName);
                if(serverRes.equals("ok")){
                    Intent intent = new Intent(AddFriendActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddFriendActivity.this,"username not found",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

}

