import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileImporting {
    public static String findOldLineFriend(User a)
    {
        String ans=a.getUserName()+"->";
        for(int i=0;i<a.getFriends().size();i++)
        {
            ans=ans.concat(a.getFriends().get(i).getUserName()+"->");
        }
        return ans;
    }
    public static String findNewLineFriend(User a,User b)
    {
        String ans=findOldLineFriend(a);
        ans=ans.concat(b.getUserName()+"->");
        return ans;
    }
    public static void addFriend(User a,User b) throws IOException {
        String filePath = "C:\\backups\\usersFriends.txt";
        Scanner sc = new Scanner(new File(filePath));
        StringBuilder buffer = new StringBuilder();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()).append(System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = findOldLineFriend(a);
        String newLine = findNewLineFriend(a,b);
        System.out.println("old line and new line : "+oldLine+" and "+newLine);
        fileContents = fileContents.replaceAll(oldLine, newLine);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
        writer.close();
        a.getFriends().add(b);
    }
    public static void addChat(String a,String b,String chat,String writer)
    {
            try{
                String content = writer+" : "+chat;
                String filePath=a+"--"+b+".txt";
                File file =new File("C:\\backups\\"+filePath);
                if(!file.exists())
                {
                    filePath=b+"--"+a+".txt";
                    file=new File("C:\\backups\\"+filePath);
                }
                if(!file.exists()){
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.newLine();
                bw.close();
            }catch(IOException ioe){
                System.out.println("Exception occurred:");
                ioe.printStackTrace();
            }
    }
    public static String CreateLine(User a){
        String ans=a.getUserName()+" "+a.getPassword()+" ";
        String gameTypeScore=a.getScores().get(gameType.XO).toString()+" ";
        ans=ans.concat(gameTypeScore);
        gameTypeScore=a.getScores().get(gameType.GuessTheWord).toString()+" ";
        ans=ans.concat(gameTypeScore);
        gameTypeScore=a.getScores().get(gameType.DotGame).toString()+" ";
        ans=ans.concat(gameTypeScore);
        gameTypeScore=a.getScores().get(gameType.WarShips).toString()+" ";
        ans=ans.concat(gameTypeScore);
        gameTypeScore=a.getScores().get(gameType.FourInARow).toString()+" ";
        ans=ans.concat(gameTypeScore);
        gameTypeScore=a.getScores().get(gameType.Othello).toString()+" ";
        ans=ans.concat(gameTypeScore);
        ans=ans.concat(a.getBio());
        return ans;
    }
    public static void editProf(User a,String changedThing,String changeTo) throws IOException {
        String filePath = "C:\\backups\\usersInfo.txt";
        Scanner sc = new Scanner(new File(filePath));
        StringBuilder buffer = new StringBuilder();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()).append(System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        String oldLine = CreateLine(a);
        if(changedThing.equals("username"))
        {
            a.setUserName(changeTo);
        }
        if(changedThing.equals("password"))
        {
            a.setPassword(changeTo);
        }
        if(changedThing.equals("bio"))
        {
            a.setBio(changeTo);
        }
        if(changedThing.equals("XO-Score"))
        {
            int changeToInt=Integer.parseInt(changeTo);
            a.scores.remove(gameType.XO);
            a.scores.put(gameType.XO,changeToInt);
        }
        if(changedThing.equals("FourInARow-Score"))
        {
            int changeToInt=Integer.parseInt(changeTo);
            a.scores.remove(gameType.FourInARow);
            a.scores.put(gameType.FourInARow,changeToInt);
        }
        if(changedThing.equals("Warships-Score"))
        {
            int changeToInt=Integer.parseInt(changeTo);
            a.scores.remove(gameType.WarShips);
            a.scores.put(gameType.WarShips,changeToInt);
        }
        if(changedThing.equals("DotGame-Score"))
        {
            int changeToInt=Integer.parseInt(changeTo);
            a.scores.remove(gameType.DotGame);
            a.scores.put(gameType.DotGame,changeToInt);
        }
        if(changedThing.equals("GuessTheWord-Score"))
        {
            int changeToInt=Integer.parseInt(changeTo);
            a.scores.remove(gameType.GuessTheWord);
            a.scores.put(gameType.GuessTheWord,changeToInt);
        }
        if(changedThing.equals("Othello-Score"))
        {
            int changeToInt=Integer.parseInt(changeTo);
            a.scores.remove(gameType.Othello);
            a.scores.put(gameType.Othello,changeToInt);
        }
        String newLine = CreateLine(a);
        fileContents = fileContents.replaceAll(oldLine, newLine);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
        writer.close();
    }
    public static void addUser(User a)
    {
        try {
            File fileName=new File("C:\\backups\\usersInfo.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            String str=a.getUserName()+" "
                    +a.getPassword()+" "
                    +String.valueOf(a.getScores().get(gameType.XO))+" "
                    +String.valueOf(a.getScores().get(gameType.DotGame))+" "
                    +String.valueOf(a.getScores().get(gameType.GuessTheWord))+" "
                    +String.valueOf(a.getScores().get(gameType.WarShips))+" "
                    +String.valueOf(a.getScores().get(gameType.FourInARow))+" "
                    +String.valueOf(a.getScores().get(gameType.Othello))+" "
                    +a.getBio();
            out.write(str);
            out.newLine();
            out.flush();
            fileName=new File("C:\\backups\\usersFriends.txt");
            out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(a.getUserName()+"->");
            out.newLine();
            MainServer.users.add(a);
            out.flush();
            out.close();
            MainServer.users.add(a);
        }
        catch (IOException e) {
            System.out.println("exception " + e);
        }
    }
}
