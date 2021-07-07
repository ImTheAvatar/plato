package com.elham.plato.ui.game;

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

import com.elham.plato.R;
import com.elham.plato.Response;
import com.elham.plato.ui.game.fourInRow.FourInRow;

public class GameFragment extends Fragment {
    private GameViewModel gameViewModel;
    TextView xo , FourInRow , guessTheWord,Othello;
    public static gameType gameType;
    public static Room currentRoom;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gameViewModel =
                ViewModelProviders.of(this).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_game, container, false);
//        final TextView textView = root.findViewById(R.id.text_game);
//        gameViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
////                textView.setText(s);
//            }
//        });
        Othello=root.findViewById(R.id.othelloBut);
        xo = root.findViewById(R.id.xo_txt_view);
        FourInRow = root.findViewById(R.id.fourInRow_txt_view);
        guessTheWord = root.findViewById(R.id.guess_txt_view);
        Response.getServerResponse("room setup");
        xo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.getServerResponse("XO");
                gameType= com.elham.plato.ui.game.gameType.XO;
                Intent intent = new Intent(getContext(),StartGameActivityB.class);
                startActivity(intent);
            }
        });

        FourInRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.getServerResponse("Four In A Row");
                gameType= com.elham.plato.ui.game.gameType.FourInARow;
                Intent intent = new Intent(getContext(),StartGameActivityB.class);
                startActivity(intent);
            }
        });
        guessTheWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.getServerResponse("GuessTheWord");
                gameType= com.elham.plato.ui.game.gameType.GuessTheWord;
                Intent intent = new Intent(getContext(),StartGameActivityB.class);
                startActivity(intent);
            }
        });
        Othello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.getServerResponse("Othello");
                gameType= com.elham.plato.ui.game.gameType.Othello;
                Intent intent = new Intent(getContext(),StartGameActivityB.class);
                startActivity(intent);
            }
        });




        return root;
    }
}
