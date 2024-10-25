package ru.darin.testList.deadLockFromSkillBox;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Friend implements Comparable<Friend> {

    private final String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private static volatile boolean running = true;

    public static void stop() {
        running = false;
    }

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void throwBallTo(Friend catcher) {
        while (running) {
//            synchronized (this){
//                synchronized (catcher){
            System.out.format("%s: %s кинул мне мяч!%n", catcher.getName(), this.name);
            catcher.throwBallTo(this);
//                }
//            }
        }

    }

    @Override
    public int compareTo(Friend o) {
        return this.getName().compareTo(o.getName());
    }
}

