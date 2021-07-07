import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private ArrayList<User>friends=new ArrayList<>();
    private String userName;
    private String password;
    private int picture;// for later
    private ArrayList<User>pendingFriends=new ArrayList<>();// for later
    private String bio;
    Map<gameType ,Integer>scores= new HashMap<gameType,Integer>();// first one is the game, second one is the score in the game
    //   emtiazi :  need to create a invite class which has the room id and the user inviting
    private Room currentRoom; // null for no room
    private int currentRoomId; // -1 for no room ..... this one is for "mohkam kari" (maybe in the invite_to_room part i used it) , may be deleted later cuz we already have the currentRoom

    public User(String userName, String password, String bio) {
        this.userName = userName;
        this.password = password;
        this.bio = bio;
        scores.put(gameType.FourInARow,0);
        scores.put(gameType.XO,0);
        scores.put(gameType.Othello,0);
        scores.put(gameType.WarShips,0);
        scores.put(gameType.DotGame,0);
        scores.put(gameType.GuessTheWord,0);
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public ArrayList<User> getPendingFriends() {
        return pendingFriends;
    }

    public void setPendingFriends(ArrayList<User> pendingFriends) {
        this.pendingFriends = pendingFriends;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Map<gameType, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<gameType, Integer> scores) {
        this.scores = scores;
    }
    public void displayUser()
    {
        System.out.println(userName+" "+password+" "
                +scores.get(gameType.XO)+" "
                +scores.get(gameType.DotGame)+" "
                +scores.get(gameType.GuessTheWord)+" "
                +scores.get(gameType.WarShips)+" "
                +scores.get(gameType.FourInARow)+" "
                +scores.get(gameType.Othello)+" "
        );
        for (User friend : friends) {
            System.out.println(friend.getUserName() + " ");
        }
    }
}