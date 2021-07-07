package com.elham.plato.ui.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.R;
import com.elham.plato.Response;

import java.util.ArrayList;

public class RankedFragment extends Fragment {
    RecyclerView recyclerView;
    TextView room_create_ranked;
    ArrayList<Room> rooms;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_ranked,container,false);
        room_create_ranked=root.findViewById(R.id.room_create_ranked);

        rooms = new ArrayList<>();

        System.out.println("im in ranked");
//        System.out.println("im not supposed to be here");
        Response.giveServerResponse("ranked");
        String roomName=Response.getServerResponse();
        Integer roomNum = Integer.parseInt(Response.getServerResponse());
        while(!roomName.equals("finished"))
        {
            rooms.add(new Room(GameFragment.gameType,roomName,roomNum));
            roomName=Response.getServerResponse();
            roomNum = Integer.parseInt(Response.getServerResponse());
        }


        recyclerView = root.findViewById(R.id.ranked_recyclerview);
        RankedAdapter adapter = new RankedAdapter(getContext(),rooms);
        recyclerView.setAdapter(adapter);

//        recyclerView.setAdapter(CasualFragment.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        room_create_ranked.setOnClickListener(new View.OnClickListener() {
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
