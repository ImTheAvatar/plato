package com.elham.plato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.R;

import java.util.ArrayList;


public class PlayingStatusListAdapter extends RecyclerView.Adapter {
    ArrayList<Integer> scores;
    String[] names = {"xoScore","guessthewordScore","warshipsScore","dotgameScore","othelloScore","fourinarowScore"};
    public PlayingStatusListAdapter(ArrayList<Integer> scores){
        this.scores = scores;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_row,parent,false);
        return new PlayingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlayingViewHolder mvh =  (PlayingViewHolder) holder;
        mvh.bind(position);


    }

    @Override
    public int getItemCount() {
        return 6;
    }
    private class PlayingViewHolder extends RecyclerView.ViewHolder{
        TextView score , numWons , gameName ;
        ProgressBar progressBar;
        ImageView gameImg;

        public PlayingViewHolder(@NonNull View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.score);
//            numWons = itemView.findViewById(R.id.numWons);
            gameName = itemView.findViewById(R.id.gameName);
            gameImg = itemView.findViewById(R.id.gameImg);
        }
        void bind(int i){
            score.setText(scores.get(i).toString());
//            numWons.setText(R.string.numWonstxt);
            gameName.setText(names[i]);
        }
    }


}