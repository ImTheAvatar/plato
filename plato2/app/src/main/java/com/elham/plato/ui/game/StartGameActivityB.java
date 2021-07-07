package com.elham.plato.ui.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.elham.plato.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartGameActivityB extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_b);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_casual:
                        CasualFragment fragment = new CasualFragment();
                        openFragment(fragment);

                        break;
                    case R.id.nav_ranked:
                         RankedFragment fragment1 =new RankedFragment();
                         openFragment(fragment1);
                        break;
                    case R.id.nav_leaderboard:
                        LeaderBoardFragment fragment2 = new LeaderBoardFragment();
                        openFragment(fragment2);
                        break;
                }
                return true;
            }
        });
    }

    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
