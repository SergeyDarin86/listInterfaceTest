package ru.darin.testList.lambdaExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorByLambda {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Adelle");
        list.add("Sergio-123");
        list.add("BradleyT");

        // создание своего компаратора
        Comparator<String> comparator = (o1, o2) -> {
            return Integer.compare(o1.length(), o2.length());
        };

        list.sort(comparator);

        System.out.println(list);

        int[]ints = new int[3];
        ints[0] = 2;
        ints[1] = 3;
        ints[2] = 4;
        System.out.println(Arrays.toString(ints));

        //map
        ints = Arrays.stream(ints).map(i-> 2).toArray();
        System.out.println(Arrays.toString(ints));

        System.out.println("==========================");

        List<Integer>integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(10);
        integerList.add(8);
        System.out.println(integerList);

        //filter & map
        integerList = integerList.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .collect(Collectors.toList());
        System.out.println(integerList);

        //forEach
        integerList.forEach(System.out::println);

        //reduce
        int i = integerList.stream().reduce(4,(acc,b) -> acc + b);
        System.out.println(i);
    }
}
