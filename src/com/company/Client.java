package com.company;

import java.util.Random;

public class Client extends Thread {
    String macAddress,type;
    Connection connection;
    int connectionNumber ;

    Client(Connection connection){
        this.macAddress = randomMACAddress();
        this.type = randomType();
        this.connection = connection;
    }
    private String randomMACAddress(){
        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);
        macAddr[0] = (byte)(macAddr[0] & (byte)254);  //zeroing last 2 bytes to make it unicast and locally adminstrated
        StringBuilder sb = new StringBuilder(18);
        for(byte b : macAddr){ if(sb.length() > 0)
                sb.append(":");
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String randomType(){
        String[] typeChoices = {"mobile","tablet","PC"};
        Random rand = new Random();
        int n = rand.nextInt(typeChoices.length);
        return typeChoices[n];
    }

    public void arriveAtRandomTime(){
        Random rand = new Random();

        try {
            sleep(rand.nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(connection.avaliable()){
            System.out.println(toString() + " Arrived ");
        }else {
            System.out.println(toString() + " Arrived and waiting");
        }
        connectionNumber = connection.connect();
        occupy();
    }

    private void occupy(){
        System.out.println("connection "+ connectionNumber + " : " + toString() +  " Occupied");
        served();
    }

    private void served(){
        Random rand = new Random();
        System.out.println("connection "+ connectionNumber + " : " + toString() + " Being Served");
        try {
            sleep(rand.nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loggout();
    }

    private void loggout(){
        Random rand = new Random();
        try {
            sleep(rand.nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connection.disconnect();
        System.out.println("connection "+ connectionNumber + " : " + toString() +  " Logged out");
    }

    public String toString(){
        return  "("+macAddress+")"+"("+type+")";
    }

    public void run() {
       arriveAtRandomTime();
    }
}