package ru.darin.testList.lambdaExample;

interface Executable{
    int execute(int x, int y);
}

class Runner{
    public void run(Executable e){
        int a = e.execute(10, 11);
        System.out.println(a);
    }
}

public class LambdaExpression {
    public static void main(String[] args) {
        Runner runner = new Runner();
        // 2 способ - анонимный класс
        runner.run(new Executable() {
            @Override
            public int execute(int x, int y) {
                System.out.println("Hello from second variant");
                return x + y;
            }
        });

        int a = 3;
        runner.run((x,y)->  x + y + a);
    }
}
