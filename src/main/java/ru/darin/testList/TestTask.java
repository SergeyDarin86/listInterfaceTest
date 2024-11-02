package ru.darin.testList;

import java.util.*;

public class TestTask {

    //TODO: ограничения
    // 1) Строка не должна быть пустой
    // 2) Должны быть только буквы или цифры
    private static String str = "";

    private static final String PATTERN = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
    private static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        inputString();

    }

    public static void inputString() {
        while (!str.equalsIgnoreCase("exit")) {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите строку: ");
            try {
                str = in.next(PATTERN);
            }catch (InputMismatchException e){
                System.out.println("Неверный формат ввода данных: Вы должны ввести набор из цифр, букв (латиница, кириллица)");
                str = "";
            }

            if (!str.equalsIgnoreCase("exit")) {
                char[] chars = str.toCharArray();
                fillMap(chars);
                showMap();
//                showMapNew();
            }
        }

    }

    public static void fillMap(char[] chars) {
        map.clear();
        for (int i = 0; i < chars.length; i++) {
            int count = 1;
            for (int j = (i + 1); j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    count++;
                }
            }

            if (!map.containsKey(chars[i])) {
                map.put(chars[i], count);
            }
        }
    }

    public static void showMap() {
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println("\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\""));
    }

    public static void showMapNew() {
        List<Map.Entry<Character, Integer>> entryList= map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).toList();
    }


}
