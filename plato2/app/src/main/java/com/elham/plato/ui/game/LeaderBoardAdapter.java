package com.elham.plato.ui.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//this
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.R;

import java.util.ArrayList;
import java.util.Map;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderViewHolder> {
    Context context;
    ArrayList<Player> top_players_list;

    public LeaderBoardAdapter(Context ct,ArrayList<Player> top_players_list){
        context=ct;
        this.top_players_list = top_players_list;

    }
    @NonNull
    @Override
    public LeaderBoardAdapter.LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.tab_leader_row,parent,false);
        return new LeaderViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull LeaderBoardAdapter.LeaderViewHolder holder, final int position) {



            holder.top_name.setText(top_players_list.get(position).name);
            holder.top_score.setText(top_players_list.get(position).score.toString());





//
//        holder.main_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, ChatActivity.class);
//                intent.putExtra("friend",position);
//                context.startActivity(intent);
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return top_players_list.size();

    }
    class LeaderViewHolder extends RecyclerView.ViewHolder {
        TextView top_name , top_score;


        ConstraintLayout main_layout;
        ConstraintLayout row_layout;

        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            top_name = itemView.findViewById(R.id.top_name);
            top_score = itemView.findViewById(R.id.top_score);
            main_layout = itemView.findViewById(R.id.mainConstraintlayout);
            row_layout=itemView.findViewById(R.id.row_layout);

        }
    }


}


