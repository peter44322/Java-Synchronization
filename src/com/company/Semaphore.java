package com.company;

public class Semaphore {
    public int value = 0;

    public Semaphore() {
        value = 0;
    }

    public Semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait() {
        value--;
        if (value < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void Signal() {
        value++;
        if (value <= 0) notify();
    }

    public Boolean avaliable(){
        return value >= 0;
    }
}
