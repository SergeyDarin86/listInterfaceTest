package ru.darin.testList.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Vector2DTest {

    Vector2D vector2D;

    @BeforeEach
    public void setUp(){
        vector2D = new Vector2D();  // action
    }

    @Test
    public void newVectorShouldHaveZeroLength(){
        Assertions.assertEquals(0,vector2D.length(),1e-9);
    }

    @Test
    public void newVectorShouldHaveZeroX() {
        Assertions.assertEquals(0, vector2D.getX(), 1e-9);
    }

    @Test
    public void newVectorShouldHaveZeroY() {
        Assertions.assertEquals(0, vector2D.getY(), 1e-9);
    }

}
















