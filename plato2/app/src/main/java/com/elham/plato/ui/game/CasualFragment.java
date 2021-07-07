package com.elham.plato.ui.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.R;
import com.elham.plato.Response;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CasualFragment extends Fragment {
    RecyclerView recyclerView;
    TextView create_room , join_room;
     CasualAdapter adapter;
     ArrayList<Room> rooms;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tab_casual,container,false);
        create_room = root.findViewById(R.id.room_create);
        join_room = root.findViewById(R.id.room_join);

        rooms =new ArrayList<>();

        System.out.println("i'm in casual");
        Response.giveServerResponse("casual");
        String roomName=Response.getServerResponse();
        Integer roomNum = Integer.parseInt(Response.getServerResponse());
//        System.out.println("im here");
        while(!roomName.equals("finished"))
        {
            rooms.add(new Room(GameFragment.gameType,roomName,roomNum));
            roomName=Response.getServerResponse();
            roomNum = Integer.parseInt(Response.getServerResponse());
        }
        recyclerView = root.findViewById(R.id.casual_recyclerview);
        adapter = new CasualAdapter(getContext(),rooms);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.giveServerResponse("create room");
                Intent intent = new Intent(getContext(),CreateRoomActivity.class);
                startActivity(intent);
            }
        });




        return root;
    }
}
