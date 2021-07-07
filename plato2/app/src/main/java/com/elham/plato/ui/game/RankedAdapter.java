package com.elham.plato.ui.game;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.R;
import com.elham.plato.Response;

import java.util.ArrayList;

public class RankedAdapter extends RecyclerView.Adapter<RankedAdapter.RankedViewHolder> {
    Context context;
    ArrayList<Room> rooms;

    public RankedAdapter(Context ct, ArrayList<Room> rooms){
        context=ct;
        this.rooms = rooms;

    }
    @NonNull
    @Override
    public RankedAdapter.RankedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.tab_ranked_row,parent,false);
        return new RankedViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull RankedAdapter.RankedViewHolder holder, final int position) {


        holder.room_name.setText(rooms.get(position).getRoomId());
        holder.room_players.setText(rooms.get(position).getRoomNumber().toString());



        holder.room_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.giveServerResponse("join room");
                Response.giveServerResponse(rooms.get(position).getRoomId());
                Intent intent=new Intent(context, LoadingActivity.class);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return rooms.size();

    }
    class RankedViewHolder extends RecyclerView.ViewHolder {
        TextView room_name , room_join , room_players;
        ImageView room_image;

        ConstraintLayout main_layout;
        ConstraintLayout row_layout;

        public RankedViewHolder(@NonNull View itemView) {
            super(itemView);
            room_name = itemView.findViewById(R.id.ranked_room_name);
            room_join = itemView.findViewById(R.id.ranked_room_join);
            room_players = itemView.findViewById(R.id.ranked_room_players);
            room_image = itemView.findViewById(R.id.ranked_room_image);
            main_layout = itemView.findViewById(R.id.ranked_mainConstraintlayout);
            row_layout=itemView.findViewById(R.id.ranked_row_layout);

        }
    }


}





