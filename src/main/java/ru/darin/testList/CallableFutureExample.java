package ru.darin.testList;

import java.util.Random;
import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            Random random = new Random();
            System.out.println("Thread started ...");
            Thread.sleep(200);
            System.out.println("Thread finished");

            int result = random.nextInt(10);

            // если результат <4, то выбрасывается исключение (мы его можем поймать в главном потоке)
            // иначе выводим результат
            if (result < 4) {
                throw new Exception("The value of result is less than 4");
            }

            return result;
        });

        executorService.shutdown();

        try {
            int result = future.get();  // get() - дожидается окончания выполнения потока и далее в потоке main выводим результат
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (
                ExecutionException e) {    // здесь мы поймаем наше исключение выполнения, после того как программа дойдет до метода get()
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}

























