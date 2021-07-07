package com.elham.plato.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.elham.plato.HomeActivity;

import com.elham.plato.MainActivity;

import com.elham.plato.Message;
import com.elham.plato.R;
import com.elham.plato.Response;
import com.elham.plato.SignInActivity;
import com.elham.plato.ui.friends.FriendsFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {
    static ArrayList<Message> messageList=new ArrayList<>();
    static Integer theGuy;
    static String from;
    static String theChat;
    Button confirm , backBtn;
    EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        final RecyclerView recyclerView = findViewById(R.id.chat_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MessageListAdapter adapter = new MessageListAdapter(messageList);
        recyclerView.setAdapter(adapter);
//        Message[] items = {
//                new Message("elham" , "hi what's up?"),
//                new Message("zahra","no idea")
//        };
        confirm=findViewById(R.id.chat_submit);
        messageText=findViewById(R.id.chat_edit_text);
        backBtn = findViewById(R.id.chat_back_button);
//        messageList.addAll(Arrays.asList(items));
        Response.getServerResponse("chat");
        theGuy= (Integer) getIntent().getExtras().get("friend");
        Response.getServerResponse(theGuy.toString());
        from=Response.getServerResponse("give me the sender");
        theChat=Response.getServerResponse("the chat he is sending");
        while(!from.equals("cache done")||!theChat.equals("cache done"))
        {
            messageList.add(new Message(from,theChat));
            System.out.println(from+" "+theChat);
            from=Response.getServerResponse("give me the sender");
            theChat=Response.getServerResponse("the chat he is sending");
        }
        adapter.notifyDataSetChanged();
        final Thread receiverThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {

                    try {
                        ChatActivity.from= MainActivity.dis.readUTF();
                        System.out.println("im still in this thread1");
                        if(from.equals("no longer in chat"))
                        {
                            messageList.clear();
                            break;
                        }
                        ChatActivity.theChat=MainActivity.dis.readUTF();
                        if(theChat.equals("no longer in chat")){
                            messageList.clear();
                            break;
                        }
                        System.out.println("im still in this thread2");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ChatActivity.messageList.add(new Message(from,theChat));
                }
            }
        });
        receiverThread.start();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.giveServerResponse(messageText.getText().toString());
                ChatActivity.messageList.add(new Message(SignInActivity.current_user,messageText.getText().toString()));
                messageText.setText("");

//                adapter.notifyDataSetChanged();

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.giveServerResponse("back");
                Intent intent = new Intent(ChatActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

                //if this doesn't work uncomment 91 and 92
//                Intent intent = new Intent(ChatActivity.this, FriendsFragment.class);
//                startActivity(intent);

            }
        });


    }
}