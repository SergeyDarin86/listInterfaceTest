package ru.darin.testList;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MainQueue {
    public static void main(String[] args) {
        Queue<String>queue = new ArrayBlockingQueue<>(3);

        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        System.out.println(queue.offer("four"));

        queue.forEach(System.out::println);

        System.out.println("++++++++++++++++++++");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.peek());

        Stack<Integer>stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());

        List<Integer>integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        Iterator<Integer>iterator = integers.iterator();
        while (iterator.hasNext()){
            iterator.remove();
            System.out.println(iterator.next());
        }
    }
}
