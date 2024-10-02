package ru.darin.testList;

import java.util.Scanner;

public class WaitNotify {

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotifyExample wne = new WaitAndNotifyExample();

        Thread threadProduce = new Thread(() -> {
            try {
                wne.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadConsume = new Thread(() -> {
            try {
                wne.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadProduce.start();
        threadConsume.start();

        threadProduce.join();
        threadConsume.join();
    }
}

class WaitAndNotifyExample {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started");
            wait(); // 1. отдаем intrinsic lock (монитор) 2. ждем, пока будет вызван notify()
            System.out.println("Producer thread resumed");  // продолжил работу
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000); // ждем, чтобы producer точно запустился первым
        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Waiting for return key pressed");
            scanner.nextLine(); // программа дойдет до этого места и будет ждать, пока мы не нажмем "Enter"
            notify();    // теперь поток, который ожидает после вызова методе wait, проснется
            // когда мы отдадим монитор, поток продолжит свою работу
        }
    }

}
