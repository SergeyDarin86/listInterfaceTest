package ru.darin.testList.deadLockFromSkillBox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Оригинал находится в папке работ со SkillBox
// проект Deadlock

// здесь мы применяем ReentrantLock для синхронизации
// также мы останавливаем бесконечную работу потоков в цикле while

public class Loader {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Game game = new Game();

        for (int i = 0; i < 2; i++) {
            executorService.submit(game::vasyaThrowsBall);
            executorService.submit(game::vityaThrowsBall);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(1);
        Friend.stop();

        System.out.println("Ребята устали ...");
    }

}

class Game {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    final Friend vasya = new Friend("Вася");
    final Friend vitya = new Friend("Витя");

    public void vasyaThrowsBall() {
        takeLocks(lock1, lock2);
        try {
            vasya.throwBallTo(vitya);
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void vityaThrowsBall() {
        takeLocks(lock2, lock1);
        try {
            vasya.throwBallTo(vitya);
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    private static void takeLocks(Lock lock1, Lock lock2) {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        while (true) {
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken) {
                    return;
                }

                if (firstLockTaken) {
                    lock1.unlock();
                }

                if (secondLockTaken) {
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
