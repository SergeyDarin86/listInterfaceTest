package ru.darin.testList;

public class MultithreadingSynchronized {

    private int count;

    public static void main(String[] args) throws InterruptedException {
        MultithreadingSynchronized multithreading = new MultithreadingSynchronized();
        multithreading.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                increment();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++){
                    increment();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(count);
    }

    public void increment(){
        synchronized (this){
            count++;
        }
    }

}