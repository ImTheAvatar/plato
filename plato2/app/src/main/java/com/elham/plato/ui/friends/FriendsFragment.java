////package com.elham.plato.ui.friends;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.annotation.Nullable;
////import androidx.fragment.app.Fragment;
////import androidx.lifecycle.Observer;
////import androidx.lifecycle.ViewModelProviders;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import com.elham.plato.R;
////import com.elham.plato.Response;
////import com.google.android.material.floatingactionbutton.FloatingActionButton;
////
////import java.util.ArrayList;
////
////public class FriendsFragment extends Fragment {
////    RecyclerView recyclerView;
////    private FriendsViewModel friendsViewModel;
////    ArrayList<String>friends=new ArrayList<>();
////    FloatingActionButton floatingActionButton;
////    @Override
////    public void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////
////    }
////    public View onCreateView(@NonNull LayoutInflater inflater,
////                             ViewGroup container, Bundle savedInstanceState) {
////        friendsViewModel =
////                ViewModelProviders.of(this).get(FriendsViewModel.class);
////        View root = inflater.inflate(R.layout.fragment_friends, container, false);
////
//////        final TextView textView = root.findViewById(R.id.text_friends);
////
////        String tmp=Response.getServerResponse("give me a friend");
////        while(!tmp.equals("finished"))
////        {
////            friends.add(tmp);
////            tmp=Response.getServerResponse("give me a friend");
////        }
////        recyclerView = root.findViewById(R.id.message_list);
////        FriendsAdapter adapter = new FriendsAdapter(getActivity(),friends);
////        recyclerView.setAdapter(adapter);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
////        recyclerView.setLayoutManager(layoutManager);
////
////        floatingActionButton = root.findViewById(R.id.floatingActionButton);
////        floatingActionButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Response.getServerResponse("add friend");
////                Intent intent = new Intent(getContext(),AddFriendActivity.class);
////                startActivity(intent);
////            }
////        });
////
//////        friendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//////            @Override
//////            public void onChanged(@Nullable String s) {
//////                textView.setText(s);
//////            }
//////        });
////        return root;
////    }
////}
//


package com.elham.plato.ui.friends;

import android.content.Intent;
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

import com.elham.plato.R;
import com.elham.plato.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {
    RecyclerView recyclerView;
    private FriendsViewModel friendsViewModel;
    ArrayList<String>friends=new ArrayList<>();
    FloatingActionButton floatingActionButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendsViewModel =
                ViewModelProviders.of(this).get(FriendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friends, container, false);

//        final TextView textView = root.findViewById(R.id.text_friends);


        Response.getServerResponse("give me a friend");
        String tmp=Response.getServerResponse("give me a friend");

        while(!tmp.equals("finished"))
        {
            friends.add(tmp);
            tmp=Response.getServerResponse("give me a friend");
        }
        recyclerView = root.findViewById(R.id.message_list);
        FriendsAdapter adapter = new FriendsAdapter(getActivity(),friends);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.getServerResponse("add friend");
                Intent intent = new Intent(getContext(),AddFriendActivity.class);
                startActivity(intent);
            }
        });


//        friendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Stinrg>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}