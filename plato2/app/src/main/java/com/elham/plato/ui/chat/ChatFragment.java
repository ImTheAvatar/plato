package com.elham.plato.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.Message;
import com.elham.plato.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatFragment extends Fragment {
    private ChatViewModel chatViewModel;
    ArrayList<Message> messageList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatViewModel =
                ViewModelProviders.of(this).get(ChatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
//        final TextView textView = root.findViewById(R.id.text_chat);
//        chatViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        Message[] items = {
                new Message("elham" , "hi what's up?"),
                new Message("zahra","no idea")
        };

        messageList = new ArrayList<>();
        messageList.addAll(Arrays.asList(items));



        RecyclerView recyclerView = root.findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MessageListAdapter adapter = new MessageListAdapter(messageList);
        recyclerView.setAdapter(adapter);




        return root;
    }
}
