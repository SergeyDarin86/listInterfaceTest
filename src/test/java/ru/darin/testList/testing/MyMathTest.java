package ru.darin.testList.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyMathTest {

    @Test
    void divideShouldThrowException() {
        Assertions.assertThrows(ArithmeticException.class, ()->MyMath.divide(1,0));
    }
}