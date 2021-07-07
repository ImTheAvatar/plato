import com.sun.tools.javac.Main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

class ClientHandler implements Runnable{
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    public  String username;
    public  String pass;
    public User user;
    boolean isInChat=false;
    static boolean newMessage=false;
    ClientHandler(Socket socket) throws Exception{
        this.s=socket;
        this.dis=new DataInputStream(socket.getInputStream());
        this.dos=new DataOutputStream(socket.getOutputStream());
    }
    void signIn() throws IOException {
        username=dis.readUTF();
        dos.writeUTF("ok");
        pass=dis.readUTF();
        boolean foundUser=false;
        int cnt=-1;
        for(int i=0;i<MainServer.users.size();i++)
        {
            if(MainServer.users.get(i).getUserName().equals(username)&&MainServer.users.get(i).getPassword().equals(pass))
            {
                foundUser=true;
                cnt=i;
                break;
            }
        }
        if(foundUser)
        {
            dos.writeUTF("ok");
            user=MainServer.users.get(cnt);
            MainServer.map.put(username,this);
        }
        else{
            dos.writeUTF("Username/Password is incorrect");
            signIn();
        }
    }
    void signUp() throws IOException {
        username=dis.readUTF();
        boolean found=false;
        for(int i=0;i<MainServer.users.size();i++)
        {
            if(MainServer.users.get(i).getUserName().equals(username))
            {
                found=true;
                dos.writeUTF("Username Exists");
                break;
            }
        }
        if(!found)
        {
            dos.writeUTF("ok it's saved");
            pass = dis.readUTF();
            dos.writeUTF("ok it's saved");
            User newUser = new User(username, pass, "no bio yet");
            user=newUser;
            FileImporting.addUser(newUser);
            MainServer.map.put(username,this);
        }
        else {
            pass=dis.readUTF();
            dos.writeUTF("ok it's saved");
            signUp();
        }
    }
    void profilePressed()
    {
        try {
            String whatToDo = dis.readUTF();
            dos.writeUTF(username);
            whatToDo = dis.readUTF();
            dos.writeUTF(user.getBio());
            dis.readUTF();
            dos.writeUTF(user.getScores().get(gameType.XO).toString());
            dis.readUTF();
            dos.writeUTF(user.getScores().get(gameType.GuessTheWord).toString());
            dis.readUTF();
            dos.writeUTF(user.getScores().get(gameType.WarShips).toString());
            dis.readUTF();
            dos.writeUTF(user.getScores().get(gameType.DotGame).toString());
            dis.readUTF();
            dos.writeUTF(user.getScores().get(gameType.Othello).toString());
            dis.readUTF();
            dos.writeUTF(user.getScores().get(gameType.FourInARow).toString());
            whatToDo = dis.readUTF();
            dos.writeUTF("ok");
            whatToDo = dis.readUTF();
            dos.writeUTF("ok");
            FileImporting.editProf(user, "bio", whatToDo);
            homePage();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    void chatList() throws IOException, InterruptedException {
        isInChat=true;
        Integer theGuy=Integer.parseInt(dis.readUTF());
        String chattingTo= user.getFriends().get(theGuy).getUserName();
        System.out.println("the guy we wanna chat with is "+chattingTo);
        dos.writeUTF("ok");
        File chatFile=new File("C:\\backups\\"+chattingTo+"--"+user.getUserName()+".txt");
        if(!chatFile.exists())
        {
            chatFile=new File("C:\\backups\\"+user.getUserName()+"--"+chattingTo+".txt");
        }
        if(!chatFile.exists())
        {
            chatFile.createNewFile();
        }
        Scanner input=new Scanner(chatFile);
        while(input.hasNext())
        {
            String from=input.next();
            input.next();
            String chat=input.nextLine();
            dis.readUTF();
            dos.writeUTF(from);
            dis.readUTF();
            dos.writeUTF(chat);
        }
        dis.readUTF();
        dos.writeUTF("cache done");
        dis.readUTF();
        dos.writeUTF("cache done");
        String whatToDo=dis.readUTF();
        while(!whatToDo.equals("back"))
        {
            System.out.println("message was "+whatToDo+" for "+chattingTo);
            if(MainServer.map.containsKey(chattingTo))
            {
                if(MainServer.map.get(chattingTo).isInChat) {
                    MainServer.map.get(chattingTo).dos.writeUTF(username);
                    MainServer.map.get(chattingTo).dos.writeUTF(whatToDo);
                }
            }
            FileImporting.addChat(username,chattingTo,whatToDo,username);
            whatToDo=dis.readUTF();
        }
        dos.writeUTF("no longer in chat");
        isInChat=false;
        homePage();
    }
    void addFriendButton() throws IOException, InterruptedException {
        String userToBeFriend=dis.readUTF();
        boolean foundOurGuy=false;
        boolean theGuyIsAlreadyFriend=false;
        for(int i=0;i<user.getFriends().size();i++) {
            if(user.getFriends().get(i).getUserName().equals(userToBeFriend))
            {
                theGuyIsAlreadyFriend=true;
                break;
            }
        }
        for(int i=0;i<MainServer.users.size();i++)
        {
            if(MainServer.users.get(i).getUserName().equals(userToBeFriend))
            {
                foundOurGuy=true;
                FileImporting.addFriend(user,MainServer.users.get(i));
                FileImporting.addFriend(MainServer.users.get(i),user);
                System.out.println("added "+MainServer.users.get(i).getUserName()+" to "+user.getUserName()+ " friends");
                break;
            }
        }
        if(foundOurGuy&&!theGuyIsAlreadyFriend)
        {
            dos.writeUTF("ok");
            homePage();
        }
        else{
            dos.writeUTF("nope");
            addFriendButton();
        }
    }
    void friendList() throws IOException, InterruptedException {
        for(int i=0;i<user.getFriends().size();i++)
        {
            dis.readUTF();
            dos.writeUTF(user.getFriends().get(i).getUserName());
        }
        dis.readUTF();
        dos.writeUTF("finished");
        System.out.println("finished friendlist");
        String whatToDo=dis.readUTF();
        dos.writeUTF("ok");
        if(whatToDo.equals("chat"))
        {
            chatList();
        }
        if(whatToDo.equals("give me a friend"))
        {
            friendList();
        }
        if(whatToDo.equals("profile pressed"))
        {
            profilePressed();
        }
        if(whatToDo.equals("add friend"))
        {
            addFriendButton();
        }
    }
    void showLeaderBoard(gameType GType) throws IOException, InterruptedException {
        for(int i=0;i<MainServer.users.size();i++)
        {
            dos.writeUTF(MainServer.users.get(i).getUserName());
            dos.writeUTF(MainServer.users.get(i).getScores().get(GType).toString());
        }
        dos.writeUTF("finished");
        System.out.println("finished leaderboard");
        dos.writeUTF("0");
    }
    void PlusScore(gameType Gtype,Room room) throws IOException {
        Integer score=user.getScores().get(Gtype)+1;
        System.out.println("plusing the score of "+username+" to "+score );
        if(Gtype==gameType.DotGame)
        {
            FileImporting.editProf(user,"DotGame-Score",score.toString());
        }
        if(Gtype==gameType.XO)
        {
            FileImporting.editProf(user,"XO-Score",score.toString());
        }
        if(Gtype==gameType.GuessTheWord)
        {
            FileImporting.editProf(user,"GuessTheWord-Score",score.toString());
        }
        String otherGuy=room.getJoinedUsers().get(1).getUserName();
        if(!room.host.equals(username))
        {
            otherGuy=room.getJoinedUsers().get(0).getUserName();
        }
        FileImporting.addChat(username,otherGuy,"SYSTEM : HAHA NOOB I WON HAHA",username);
    }
    void XOHandle(Room room) throws IOException, InterruptedException {
        String row=dis.readUTF();
        String column=dis.readUTF();
        do {
            System.out.println("username "+user.getUserName()+" told me "+row+" "+column);
            if (room.host.equals(username)) {
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(row);
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(column);
            } else {
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(row);
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(column);
            }
            row=dis.readUTF();
            column=dis.readUTF();
        }while (!row.equals("-1"));
        String whereAreYou=dis.readUTF();
        if(column.equals("won")&&room.getType())
        {
            PlusScore(gameType.XO,room);
        }
        if(whereAreYou.equals("im done"))
        {
            dos.writeUTF("finish");
        }
        MainServer.rooms.remove(room);
        homePage();
    }
    void GuessTheWordHandler(Room room) throws IOException, InterruptedException {
        String firstRound=dis.readUTF();
        if(room.host.equals(username))
        {
            MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(firstRound);
        }
        else{
            if(firstRound.equals("won"))
            {
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF("he won");
            }
            else{
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF("he lost");
            }
        }
        String secondRound=dis.readUTF();
        if(!room.host.equals(username))
        {
            MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(secondRound);
        }
        else {
            if(secondRound.equals("won"))
            {
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF("he won");
            }
            else{
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF("he lost");
            }
        }
        String  won=dis.readUTF();
        if(won.equals("won")&&room.getType())
        {
            PlusScore(gameType.GuessTheWord,room);
        }
        MainServer.rooms.remove(room);
        homePage();
    }
    void OthelloHandler(Room room) throws IOException, InterruptedException {
        String row=dis.readUTF();
        String column=dis.readUTF();
        do {
            System.out.println("username "+user.getUserName()+" told me "+row+" "+column);
            if (room.host.equals(username)) {
                System.out.println("told that to the other guy");
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(row);
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(column);
                System.out.println("told it");
            } else {
                System.out.println("told that to the host");
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(row);
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(column);
                System.out.println("told it");
            }
            row=dis.readUTF();
            column=dis.readUTF();
        }while (!row.equals("-1"));
        String whereAreYou=dis.readUTF();
        if(column.equals("won")&&room.getType())
        {
            PlusScore(gameType.Othello,room);
        }
        if(whereAreYou.equals("im done"))
        {
            dos.writeUTF("finish");
        }
        MainServer.rooms.remove(room);
        homePage();
    }
    void FourInARowHandler(Room room) throws IOException, InterruptedException {
        String row=dis.readUTF();
        String column=dis.readUTF();
        do {
            System.out.println("username "+user.getUserName()+" told me "+row+" "+column);
            if (room.host.equals(username)) {
                System.out.println("told that to the other guy");
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(row);
                MainServer.map.get(room.getJoinedUsers().get(1).getUserName()).dos.writeUTF(column);
                System.out.println("told it");
            } else {
                System.out.println("told that to the host");
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(row);
                MainServer.map.get(room.getJoinedUsers().get(0).getUserName()).dos.writeUTF(column);
                System.out.println("told it");
            }
            row=dis.readUTF();
            column=dis.readUTF();
        }while (!row.equals("-1"));
        String whereAreYou=dis.readUTF();
        if(column.equals("won")&&room.getType())
        {
            PlusScore(gameType.FourInARow,room);
        }
        if(whereAreYou.equals("im done"))
        {
            dos.writeUTF("finish");
        }
        MainServer.rooms.remove(room);
        homePage();
    }
    void loadingScreen(Room room) throws IOException, InterruptedException {
        while(true)
        {
            System.out.println("im in the room of "+room.getJoinedUsers().size()+" out of "+room.getRequiredPlayers());
            if(room.getJoinedUsers().size()>=room.getRequiredPlayers())
            {
                break;
            }
        }
        dos.writeUTF("game starts");
        System.out.println(room.getJoinedUsers().size()+" "+user.getUserName());
        if(room.getGame()==gameType.XO)
        {
            XOHandle(room);
        }
        if(room.getGame()==gameType.GuessTheWord)
        {
            GuessTheWordHandler(room);
        }
        if(room.getGame()==gameType.Othello)
        {
            OthelloHandler(room);
        }
        if(room.getGame()==gameType.FourInARow)
        {
            FourInARowHandler(room);
        }
    }
    void createRoom(gameType roomType,boolean rank) throws IOException, InterruptedException {
        String roomId=dis.readUTF();
        for(int i=0;i<MainServer.rooms.size();i++)
        {
            if(MainServer.rooms.get(i).getRoomId().equals(roomId))
            {
               dos.writeUTF("already exists");
               createRoom(roomType,rank);
               return;
            }
        }
        dos.writeUTF("ok");
        ArrayList<User>joined=new ArrayList<>();
        joined.add(user);
        MainServer.rooms.add(new Room(joined,roomId,rank,roomType,username));
        loadingScreen(MainServer.rooms.get(MainServer.rooms.size()-1));
    }
    void RespondRooms(String roomType,boolean rank) throws IOException, InterruptedException {
        System.out.println("im in respondRooms");
        gameType Gtype = gameType.XO;
        if(roomType.equals("DotGame"))
        {
            Gtype=gameType.DotGame;
        }
        if(roomType.equals("GuessTheWord"))
        {
            Gtype=gameType.GuessTheWord;
        }
        if(roomType.equals("Othello"))
        {
            Gtype=gameType.Othello;
        }
        if(roomType.equals("Four In A Row"))
        {
            Gtype=gameType.FourInARow;
        }
        boolean dont=false;
        System.out.println("the size of the rooms are "+MainServer.rooms.size()+" rank is "+rank);
        for(int i=0;i<MainServer.rooms.size();i++)
        {
            System.out.println("slm");
            if(MainServer.rooms.get(i).getGame()==Gtype&&MainServer.rooms.get(i).getType()== rank)
            {
                if(rank)
                {
                    for(int j=0;j<MainServer.rooms.get(i).getJoinedUsers().size();j++)
                    {
                        if(MainServer.rooms.get(i).getJoinedUsers().get(j).getScores().get(Gtype)>user.getScores().get(Gtype)+2
                                ||MainServer.rooms.get(i).getJoinedUsers().get(j).getScores().get(Gtype)<user.getScores().get(Gtype)-2)
                        {
                            dont=true;
                        }
                    }
                }
                if (!dont) {
                    dos.writeUTF(MainServer.rooms.get(i).getRoomId());
                    dos.writeUTF(MainServer.rooms.get(i).getJoinedNumber().toString());
                }
            }
        }
        dos.writeUTF("finished");
        System.out.println("finished room setup");
        dos.writeUTF("0");
        String whatToDo=dis.readUTF();
        if(whatToDo.equals("ranked"))
        {
            RespondRooms(roomType,true);
            return;
        }
        if(whatToDo.equals("casual"))
        {
            RespondRooms(roomType,false);
            return;
        }
        if(whatToDo.equals("create room"))
        {
            createRoom(Gtype,rank);
            return;
        }
        if(whatToDo.equals("join room"))
        {
            String roomName=dis.readUTF();
            for(int i=0;i<MainServer.rooms.size();i++)
            {
                if(MainServer.rooms.get(i).getRoomId().equals(roomName))
                {
                    MainServer.rooms.get(i).getJoinedUsers().add(user);
                    loadingScreen(MainServer.rooms.get(i));
                    break;
                }
            }
            return;
        }
        if(whatToDo.equals("leaderboard"))
        {
            showLeaderBoard(Gtype);
            whatToDo=dis.readUTF();
            if(whatToDo.equals("ranked"))
            {
                RespondRooms(roomType,true);
            }
            if(whatToDo.equals("casual"))
            {
                RespondRooms(roomType,false);
            }
        }
    }
    void RoomSetup() throws IOException, InterruptedException {
           String whatToDo=dis.readUTF();
           dos.writeUTF("ok");
           if (whatToDo.equals("profile pressed"))
           {
              profilePressed();
           }
           if(whatToDo.equals("give me a friend"))
           {
             friendList();
           }
           if(whatToDo.equals("room setup"))
           {
              RoomSetup();
           }
           String page=dis.readUTF();
           if(page.equals("ranked"))
           {
               RespondRooms(whatToDo,true);
           }
           RespondRooms(whatToDo,false);
    }
    void homePage() throws IOException, InterruptedException {
        String whatToDo=dis.readUTF();
        dos.writeUTF("ok");
        if (whatToDo.equals("profile pressed"))
        {
            profilePressed();
        }
        if(whatToDo.equals("give me a friend"))
        {
            friendList();
        }
        if(whatToDo.equals("room setup"))
        {
            RoomSetup();
        }
        homePage();
    }
    @Override
    public void run() {
        try {
            String whatToDo=dis.readUTF();
            dos.writeUTF("ok");
            if(whatToDo.equals("sign in"))
            {
                signIn();
            }
            else{
                signUp();
            }
            MainServer.map.put(username,this);
            user.displayUser();
            homePage();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Socket getS() { return s; }
    public void setS(Socket s) {
        this.s = s;
    }
    public DataInputStream getDis() {
        return dis;
    }
    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }
    public DataOutputStream getDos() { return dos; }
    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }
}
public class MainServer {
    public static ArrayList<User>users=new ArrayList<>();
    public static ArrayList<Room>rooms=new ArrayList<>();
    public static Map<String ,ClientHandler>map=new ConcurrentHashMap<>();
    public static void init() throws FileNotFoundException {
        File usersFile=new File("C:\\backups\\usersInfo.txt");
        File usersFriends=new File("C:\\backups\\usersFriends.txt");
        Scanner infoInput=new Scanner(usersFile);
        Scanner friendsInput=new Scanner(usersFriends);
        while(infoInput.hasNext())
        {
            String username=infoInput.next();
            String password=infoInput.next();
            int xoScore=Integer.parseInt(infoInput.next());
            int guessthewordScore=Integer.parseInt(infoInput.next());
            int dotgameScore=Integer.parseInt(infoInput.next());
            int warshipScore=Integer.parseInt(infoInput.next());
            int othelloScore=Integer.parseInt(infoInput.next());
            int fourintherowScore=Integer.parseInt(infoInput.next());
            Map<gameType,Integer> scores=new HashMap<gameType,Integer>();
            scores.put(gameType.XO,xoScore);
            scores.put(gameType.DotGame,dotgameScore);
            scores.put(gameType.GuessTheWord,guessthewordScore);
            scores.put(gameType.WarShips,warshipScore);
            scores.put(gameType.FourInARow,fourintherowScore);
            scores.put(gameType.Othello,othelloScore);
            String bio=infoInput.nextLine();
            users.add(new User(username,password,bio));
            users.get(users.size()-1).displayUser();
        }
        while (friendsInput.hasNext())
        {
            String []friendList=friendsInput.nextLine().split("->");
            User current=null;
            for (User user : users) {
                if (user.getUserName().equals(friendList[0])) {
                    current = user;
                    break;
                }
            }
            for(int i=1;i<friendList.length;i++)
            {
                for (User user : users) {
                    if (user.getUserName().equals(friendList[i])) {
                        current.getFriends().add(user);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ServerSocket ss=new ServerSocket(5903);
        System.out.println(ss.getInetAddress().getHostAddress());
        System.out.println("salam");
        System.out.println(MainServer.rooms.size());
        init();
        while (true) {
            Socket socket = ss.accept();
            System.out.println("someone connected");
            ClientHandler ch = new ClientHandler(socket);
            (new Thread(ch)).start();
        }
    }
}