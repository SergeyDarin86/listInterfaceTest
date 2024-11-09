package ru.darin.testList;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        List<Integer> list1 = new LinkedList<>();
        list1.add(1);

        Set<Integer> map = new HashSet<>();

        Collections.sort(list);

        List<String> stringList = new ArrayList<>();
        stringList.add("hello");
        stringList.add("my");
        stringList.add("job");
        stringList.add("occurrences");

        stringList.sort((o2, o1) -> Integer.compare(o1.length(), o2.length()));
        System.out.println(stringList);

        stringList.sort(Comparator.reverseOrder());

        Collections.sort(stringList, Comparator.reverseOrder());

//        System.out.println(stringList);

        List<Person> people = new ArrayList<>();
        people.add(new Person(3, "Sat"));
        people.add(new Person(1, "Sct"));
        people.add(new Person(2, "Sbt"));

        Collections.sort(people);

//        System.out.println(people);
//
//        Set<Person>personSet = new TreeSet<>();
//        personSet.add(new Person(1,"erew"));
//        personSet.add(new Person(1,"erew"));
//        personSet.add(new Person(1,"erew"));

        List<Animal>animals = new ArrayList<>();
        animals.add(new Animal(1,"hedgehog"));
        animals.add(new Animal(2,"elephant"));
        animals.add(new Animal(3,"aist"));

        animals.sort(Comparator.comparing(Animal::getName));

//        System.out.println(animals);

        Queue<String>queue = new LinkedList<>();
        queue.add("one");
        queue.add("two");
        queue.add("three");

        queue.remove();

        queue.offer("one");
        queue.poll();

        Queue<String>stringQueue = new ArrayBlockingQueue<>(10);

    }

}

class Animal{
    int id;
    String name;

    public Animal(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class Person implements Comparable<Person>{
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }

}