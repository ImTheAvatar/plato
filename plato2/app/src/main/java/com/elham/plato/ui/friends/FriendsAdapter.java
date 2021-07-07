package com.elham.plato.ui.friends;

import android.content.Context;
import android.content.Intent;
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
import com.elham.plato.ui.chat.ChatActivity;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {
    Context context;
    ArrayList<String> friends;

    public FriendsAdapter(Context ct , ArrayList<String> friends){
        context=ct;
        this.friends = friends;

    }
    @NonNull
    @Override
    public FriendsAdapter.FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.friend_row,parent,false);
        return new FriendsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.FriendsViewHolder holder, final int position) {

        //holder.friendName.setText(User.getFriends().get(position).getUserName());
        holder.friendName.setText(friends.get(position));
        //holder.imageView.setImageResource(user.getFriends().get(position).getPicture());


        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("friend",position);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        //return user.getFriends().size()
        return friends.size();
    }
    class FriendsViewHolder extends RecyclerView.ViewHolder {
        TextView friendName;
        ImageView imageView;
        ConstraintLayout mainlayout;
        ConstraintLayout rowlayout;

        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            friendName=itemView.findViewById(R.id.friendName);
            mainlayout = itemView.findViewById(R.id.mainConstraintlayout);
            rowlayout=itemView.findViewById(R.id.row_layout);

        }
    }


}

