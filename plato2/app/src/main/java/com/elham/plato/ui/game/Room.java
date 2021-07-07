package com.elham.plato.ui.game;

import java.util.ArrayList;

enum gameType{
    XO,
    GuessTheWord,
    DotGame,
    WarShips,
    Othello,
    FourInARow
}
public class Room {
    private gameType game;
    private String roomId;
    private Integer roomNumber;

    public Room(gameType game, String roomId, Integer roomNumber) {
        this.game = game;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
    }

    public gameType getGame() {
        return game;
    }

    public void setGame(gameType game) {
        this.game = game;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRequiredPlayers(){
        if(game==gameType.XO)
            return 2;
        if(game==gameType.GuessTheWord)
            return 1;
        if(game==gameType.DotGame)
            return 2;
        return 1;
    }
}
