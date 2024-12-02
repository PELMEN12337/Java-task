package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testAddition() {
        int a = 5;
        int b = 10;
        int result = a + b;
        assertEquals(15, result);
    }

}
