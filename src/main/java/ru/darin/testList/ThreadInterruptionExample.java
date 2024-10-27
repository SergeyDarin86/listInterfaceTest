package ru.darin.testList;

import java.util.Random;

// InterruptedException - возникает тогда, когда выполняемый поток прерывает другой поток
// когда у нас какие-то методы выполняются в потоке, который был прерван

public class ThreadInterruptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            Random random = new Random();
            for (int i = 0; i < 1_000_000_000; i++){
                // в текущем потоке мы можем посмотреть: прерывали нас или нет
                // если вернет "true", то мы можем как-то грамотно завершить текущий поток
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Thread was interrupted");
                    break;
                }
                Math.sin(random.nextDouble());
            }
        });

        System.out.println("Thread started its work ...");
        thread.start();

        // прекращает работу (мы как-бы сообщаем потоку, что хотим завершить его выполнение), но при этом не убивает поток
        // stop() - deprecated, убивает поток, но все, что внутри может находиться в неопределенном состоянии
        // и мы просто потеряем данные
        // ДОЛЖЕН ВЫЗЫВАТЬСЯ ДО МЕТОДА JOIN() - ИНАЧЕ ИНСТРУКЦИИ ДО НАС НЕ ДОЙДУТ
        Thread.sleep(2000);
        thread.interrupt();
        thread.join();

        System.out.println("Thread finished its work");
    }
}
