package ru.darin.testList;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // это потокобезопасный класс, поэтому нам не нужно беспокоиться о синхронизации потоков
        // должны указать в конструкторе количество итераций назад, прежде чем "защелка" отопрется
        // в нашем примере мы должны отсчитать 3 раза. До этих пор "защелка" будет закрыта
        CountDownLatch countDownLatch = new CountDownLatch(3);

        //три потока будут отсчитывать защелку, а метод main ждет
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++){
            executorService.submit(new Processor(countDownLatch));
        }

        executorService.shutdown();
        // пока в countDownLatch есть какое-либо число, await() будет ждать
        countDownLatch.await(); // открываем защелку
        System.out.println("Latch has been opened, main thread is proceeding");
    }
}

class Processor implements Runnable{
    private CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown(); // декремент на 1
        System.out.println(this.countDownLatch.getCount());
    }
}
