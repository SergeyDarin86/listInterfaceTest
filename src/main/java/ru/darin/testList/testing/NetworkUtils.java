package ru.darin.testList.testing;

public class NetworkUtils {
    public static void getConnection(){
        // получаем соединение с сервером
        // хотим, чтобы он выполнялся не более, чем 1 сек
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
