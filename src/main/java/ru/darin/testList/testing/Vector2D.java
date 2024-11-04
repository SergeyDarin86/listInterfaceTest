package ru.darin.testList.testing;

public class Vector2D {

    // по умолчанию значение координат равно 0
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // вычисляем длину вектора по теореме Пифагора
    // корень квадратный из суммы квадратов катетов
    public double length(){
        return Math.sqrt(x * x + y * y);
    }

}
