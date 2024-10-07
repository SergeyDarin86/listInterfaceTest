package ru.darin.testList;

import java.util.Scanner;

public class MultiThreading {

    public static void main(String[] args) {
        ThreadClass threadClass = new ThreadClass();
        threadClass.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        threadClass.shutDown();
    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from Runner");
    }
}

class ThreadClass extends Thread {

    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello from my Thread");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutDown() {
        this.running = false;
    }

}
