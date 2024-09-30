package ru.darin.testList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultithreadingLockMonitor {
    public static void main(String[] args) {
        MultithreadingLockMonitor monitorLock = new MultithreadingLockMonitor();
        monitorLock.startThreads();
    }

    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    Random random = new Random();
    Object lock1 = new Object();
    Object lock2 = new Object();

    public void addToList1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }
    }

    public void addToList2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100));
        }
    }

    public void doWork() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public void startThreads() {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(this::doWork);
        Thread thread2 = new Thread(this::doWork);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
        System.out.println("Size of list1 - " + list1.size());
        System.out.println("Size of list2 - " + list2.size());
    }

}
