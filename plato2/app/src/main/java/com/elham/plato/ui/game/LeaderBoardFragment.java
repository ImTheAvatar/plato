package com.elham.plato.ui.game;

import android.icu.text.Replaceable;
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
import java.util.HashMap;
import java.util.Map;

class Player{
    String name;
    Integer score;

    public Player(String name, Integer score) {
        this.name = name;
        this.score = score;
    }
}
public class LeaderBoardFragment extends Fragment {
    RecyclerView recyclerView;
    TextView your_name , your_score;
    ArrayList<Player>players=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_leaderboard,container,false);

        recyclerView = root.findViewById(R.id.top_recyclerview);
        your_name = root.findViewById(R.id.your_name);
        your_score = root.findViewById(R.id.your_score);
        Response.giveServerResponse("leaderboard");
        String name=Response.getServerResponse();
        Integer score=Integer.parseInt(Response.getServerResponse());
        while(!name.equals("finished"))
        {
            Player ourGuy=new Player(name,score);
            boolean placed=false;
            for(int i=0;i<players.size();i++)
            {
                if(players.get(i).score<score)
                {
                    placed=true;
                    players.add(i,ourGuy);
                    break;
                }
            }
            if(!placed)
            {
                players.add(ourGuy);
            }
            name=Response.getServerResponse();
            score=Integer.parseInt(Response.getServerResponse());
        }

        LeaderBoardAdapter adapter = new LeaderBoardAdapter(getContext(),players);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);







        return root;
    }
}
