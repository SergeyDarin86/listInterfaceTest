package ru.darin.testList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// Semaphore используется тогда, когда у нас есть какой-то ресурс
// и несколько потребителей хотят им воспользоваться, но нам нужно ограничить доступ к нему
// например, есть сервер и десять потоков отправляют данные на него

// Semaphore semaphore = new Semaphore(4);
// в качестве аргумента мы передаем количество разрешений в единицу времени
// в данном случае только 4 потока могут обращаться одновременно к этому ресурсу

// semaphore.release(); - отдаем одно разрешение (вызываем тогда, когда в потоке заканчиваем использовать ресурс)

// acquire() - вызываем, когда начинаем взаимодействовать с ресурсом, тем самым забираем один ресурс
// availablePermits() - возвращает количество разрешений, которое у нас свободно

public class SemaphoreExample {

    // в нашей задаче 200 потоков будут обращаться к нашему ресурсу connection,
    // но мы хотим ограничить доступ к нему - чтобы одновременно могли использовать только 10 потоков
    private static final int THREADS_COUNT = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

        // получаем объект Connection
        Connection connection = Connection.getConnection();

        for (int i = 0; i < THREADS_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    connection.workWithSemaphore();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

// ---------------------- паттерн Singleton -------------------------------
// таким способом мы гарантируем, что объект класса Connection будет создан только один раз
// для этого мы используем приватный конструктор
class Connection {
    private static Connection connection = new Connection();
    private int connectionsCount;

    private Semaphore semaphore = new Semaphore(10);

    private Connection() {
    }

    public static Connection getConnection() {
        return connection;
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionsCount++;
            System.out.println(connectionsCount);
        }

        Thread.sleep(5000);

        synchronized (this) {
            connectionsCount--;
        }

    }

    // semaphore.release() - всегда вызывается в final-блоке, т.к. у нас может выброситься исключение
    // и это будет означать, что мы освободили ресурс - он свободен, мы прервем выполнение программы,
    // иначе мы никогда не попадем в следующую инструкцию
    // теперь в любом случае у нас будет вызван данный метод

    public void workWithSemaphore() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        }finally {
            semaphore.release();
        }
    }
}