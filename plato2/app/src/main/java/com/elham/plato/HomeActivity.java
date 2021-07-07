package com.elham.plato;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.elham.plato.ui.friends.FriendsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        aboutUs=findViewById(R.id.about_us_btn);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,R.id.navigation_game ,R.id.navigation_shop,R.id.navigation_chat,R.id.navigation_friends)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ImageView profileImg = findViewById(R.id.imageView2);
        profileImg.setOnClickListener(this);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
            }
        });

//        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if(item.getItemId() == R.id.navigation_friends){
//                    Response.getServerResponse("friendList pressed");
//                }
//                return true;
//            }
//        });








    }

    @Override
    public void onClick(View view) {
        Response.getServerResponse("profile pressed");
        String username = Response.getServerResponse("username");
        String bio = Response.getServerResponse("bio");

        int xoScore= Integer.parseInt(Response.getServerResponse("xoScore"));
        int guesstheword= Integer.parseInt(Response.getServerResponse("guesstheword"));
        int dotgame= Integer.parseInt(Response.getServerResponse("dotgame"));
        int warships= Integer.parseInt(Response.getServerResponse("xoScore"));
        int othello= Integer.parseInt(Response.getServerResponse("othello"));
        int fourinarow= Integer.parseInt(Response.getServerResponse("fourinarow"));
        Intent intent=new Intent(this, ProfileActivity.class);
        intent.putExtra("USERNAME",username);
        intent.putExtra("BIO",bio);
        intent.putExtra("xoScore",xoScore);
        intent.putExtra("guessthewordScore",guesstheword);
        intent.putExtra("warshipsScore",warships);
        intent.putExtra("dotgameScore",dotgame);
        intent.putExtra("othelloScore",othello);
        intent.putExtra("fourinarowScore",fourinarow);

        startActivity(intent);
    }
}
