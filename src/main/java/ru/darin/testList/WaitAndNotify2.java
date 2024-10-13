package ru.darin.testList;

import java.util.LinkedList;
import java.util.Queue;

public class WaitAndNotify2 {

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread threadProduce = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadConsume = new Thread(() -> {
            try {
                pc.consume();
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

/**
 * Используем низкоуровневые инструменты (wait()/notify()) для взаимодействия между потоками
 * без использования дополнительных классов
 */

class ProducerConsumer {
    // wait и notify - это очень низкоуровневые инструменты для взаимодействия между потоками
    // используем обычную очередь - она не потокобезопасна
    private Queue<Integer> queue = new LinkedList();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true){
            synchronized (lock){
                while (queue.size()==LIMIT){
                    lock.wait();
                }
                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true){
            synchronized (lock){
                while (queue.size() == 0){
                    lock.wait();
                }
                int value = queue.poll();
                System.out.println(value);
                System.out.println("Queue size is -- " + queue.size());
                lock.notify();
            }
            Thread.sleep(1000);
        }
    }
}
