package ru.darin.testList.annotations;

public class MyClass {
   @Author(name = "me",dateOfCreation = 2020)
    public void printHelloWorld(){
       System.out.println("Hello world from me");
   }
}
