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
    private Integer joinedNumber;
    private ArrayList<User>joinedUsers=new ArrayList<>();
    private ArrayList<User>spectators=new ArrayList<>(); // for later
    private String roomId;
    private boolean type;
    private gameType game;
    String host;

    public Room(ArrayList<User> joinedUsers, String roomId, boolean type, gameType game,String host) {
        this.joinedUsers = joinedUsers;
        this.host=host;
        this.roomId = roomId;
        this.type = type;
        this.game = game;
        joinedNumber=joinedUsers.size();
    }

    public Integer getJoinedNumber() {
        return joinedNumber;
    }

    public void setJoinedNumber(int joinedNumber) {
        this.joinedNumber = joinedNumber;
    }

    public ArrayList<User> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(ArrayList<User> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    public ArrayList<User> getSpectators() {
        return spectators;
    }

    public void setSpectators(ArrayList<User> spectators) {
        this.spectators = spectators;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public gameType getGame() {
        return game;
    }

    public void setGame(gameType game) {
        this.game = game;
    }
    public Integer getRequiredPlayers(){
        if(game==gameType.XO)
            return 2;
        if(game==gameType.GuessTheWord)
            return 2;
        if(game==gameType.DotGame)
            return 4;
        if(game==gameType.Othello)
            return 2;
        return 2;
    }
}
