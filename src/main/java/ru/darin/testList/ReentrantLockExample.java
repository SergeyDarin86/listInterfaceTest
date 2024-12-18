package ru.darin.testList;

// Класс ReentrantLock нужен для синхронизации потоков, но у него есть свои плюсы
// его иногда удобнее использовать, чем ключевое слово "synchronized"
// он позволяет использовать блокировку в обратном порядке и при этом избегать DEADLOCK
// см. пример DeadlockExample

// с использованием synchronized мы должны в разных потоках синхронизироваться на объектах в одном порядке

// иногда можно столкнуться с deadlock, используя synchronized - это взаимная блокировка потоками друг друга
// Он реализует интерфейс Lock

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        Thread thread1 = new Thread(task::firstThread);

        Thread thread2 = new Thread(task::secondThread);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        task.showCounter();
    }
}

class Task {
    private int counter;

    private Lock lock = new ReentrantLock();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
    }

    public void firstThread() {
        lock.lock();
        increment();
        lock.unlock();
    }
    // unlock() - должен всегда вызываться в final-блоке
    public void secondThread() {
        lock.lock();
        increment();
        lock.unlock();
    }

    public void showCounter(){
        System.out.println(counter);
    }
}
