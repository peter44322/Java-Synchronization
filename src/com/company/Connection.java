package com.company;
public class Connection {
    int limit ;
    private int clients = 0;
    private Semaphore connections;

    Connection(int limit){
        this.limit = limit;
        this.connections = new Semaphore(limit);
    }

    public int  connect(){
        connections.Wait();
        clients++;
        return clients;
    }

    public synchronized void disconnect( ){
        connections.Signal();
        clients--;
    }

    public synchronized boolean avaliable(){
        return  clients < limit;
    }
}
