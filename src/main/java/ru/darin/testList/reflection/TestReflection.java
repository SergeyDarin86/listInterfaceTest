package ru.darin.testList.reflection;

import ru.darin.testList.annotations.Author;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        Class personClass1 = person.getClass();

        Class personClass2 = Person.class;
        Method[] methods = personClass2.getMethods();
        for (Method method: methods){
//            System.out.println(method.getName() + " --- " + method.getReturnType() + " --- " + Arrays.toString(method.getParameterTypes()));
        }

        Field []fields = personClass1.getDeclaredFields();
        for (Field field : fields){
//            System.out.println(field.getName() + " --- " + field.getType());
        }

        Annotation[]annotations = personClass1.getAnnotations();
        for (Annotation annotation : annotations){
           if (annotation instanceof Author){
               //               System.out.println("Yes");
           }
        }

        Method method = personClass1.getMethod("sayHello");
        method.invoke(new Person());
    }
}
