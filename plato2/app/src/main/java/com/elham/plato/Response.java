package com.elham.plato;

import java.io.IOException;

public class Response {
    public static String ServerResponse1="-1";
    public static String ServerResponse2="-2";
    public static String response="-3";
    public static String getServerResponse(final String message){
        System.out.println("message is "+message);
        ServerResponse1="-1";
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        MainActivity.dos.writeUTF(message);
                        System.out.println("i send him the message "+message);
                        Response.ServerResponse1= MainActivity.dis.readUTF();
                        System.out.println("send him the message : "+message+"  with the responde of "+Response.ServerResponse1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        });
        thread.start();
        while(ServerResponse1.equals("-1")) { System.out.println("in the loop1");
        }
        return ServerResponse1;
    }
    public static String getServerResponse(){
        ServerResponse2="-2";
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response.ServerResponse2= MainActivity.dis.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        while(ServerResponse2.equals("-2")) { System.out.println("in the loop2");
        }
        return ServerResponse2;
    }
    public static void giveServerResponse(final String massage){
        System.out.println("sending him the message "+massage);
        response="-3";
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MainActivity.dos.writeUTF(massage);
                    Response.response="something";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        while(response.equals("-3")) { System.out.println("in the loop3");
        }
    }
}
