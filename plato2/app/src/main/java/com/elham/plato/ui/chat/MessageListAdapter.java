package com.elham.plato.ui.chat;

import android.icu.text.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elham.plato.Message;
import com.elham.plato.R;
import com.elham.plato.SignInActivity;

import java.util.ArrayList;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder>{
    private ArrayList<Message> list;
    final int MSG_RIGHT = 0;
    final int MSG_LEFT = 1;


    public MessageListAdapter(ArrayList<Message> list){
        this.list = list;
    }
    @NonNull
    @Override




//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left,parent,false);
//        return new MessageViewHolder(view);
//    }

    public MessageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_RIGHT){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right,parent,false);
            return new MessageListAdapter.ViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left,parent,false);
            return new MessageListAdapter.ViewHolder(view);
        }
    }

    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,  int position) {
//      MessageViewHolder mvh =  (MessageViewHolder) holder;
//      mvh.bind(list.get(position));
//    }

    public void onBindViewHolder(@NonNull MessageListAdapter.ViewHolder holder,  int position) {
        Message message = list.get(position);
        holder.body.setText(message.getBody());
        DateFormat formatter = DateFormat.getDateInstance();
        String time = formatter.format(message.getSentDate());
        holder.date.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



//    private class MessageViewHolder extends RecyclerView.ViewHolder{
//        TextView sender , body , date;
//
//        public MessageViewHolder(@NonNull View itemView) {
//            super(itemView);
//            sender = itemView.findViewById(R.id.message_sender);
//            body = itemView.findViewById(R.id.message_body);
//            date = itemView.findViewById(R.id.message_time);
//        }
//        void bind(Message message){
//            sender.setText(message.getSender());
//            body.setText(message.getBody());
//            DateFormat formatter = DateFormat.getDateInstance();
//            String time = formatter.format(message.getSentDate());
//            date.setText(time);
//        }
//
//    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView  body , date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            body = itemView.findViewById(R.id.show_message);
            date = itemView.findViewById(R.id.message_time);
        }
        void bind(Message message){
            body.setText(message.getBody());
            DateFormat formatter = DateFormat.getDateInstance();
            String time = formatter.format(message.getSentDate());
            date.setText(time);
        }

    }
    @Override
    public int getItemViewType(int position){
        if(list.get(position).getSender().equals(SignInActivity.current_user)){
            return MSG_RIGHT;
        }else {
            return MSG_LEFT;
        }
    }

}





