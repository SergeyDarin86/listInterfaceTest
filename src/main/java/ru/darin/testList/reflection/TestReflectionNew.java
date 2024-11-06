package ru.darin.testList.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

// в данном примере мы будем ДИНАМИЧЕСКИ создавать два объекта
// и вызывать метод одного объекта на втором объекте
// без рефлексии этого невозможно сделать
// этот подход очень гибкий, потому что мы можем передавать в качестве параметров совершенно разные классы

public class TestReflectionNew {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Scanner scanner = new Scanner(System.in);
        //название_класса1 название_класса2 название_метода
        //scanner будет делить нашу строку по пробелам

        Class classObject1 = Class.forName(scanner.next());
        Class classObject2 = Class.forName(scanner.next());
        String methodName = scanner.next();

        // в качестве второго аргумента метода для первого класса будет указываться имя второго класса
        Method method = classObject1.getMethod(methodName, classObject2);

        //будет создан объект с пустым конструктором
        Object o1 = classObject1.getDeclaredConstructor().newInstance();

        //хотим принимать в конструкторе строковый аргумент
        Object o2 = classObject2.getConstructor(String.class).newInstance("Name from Reflection");

        //вызываем метод на объекте о1 с аргументом о2
        method.invoke(o1,o2);

        System.out.println(o1);
    }
}
