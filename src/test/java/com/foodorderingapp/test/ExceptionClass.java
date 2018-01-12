package com.foodorderingapp.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;



public class ExceptionClass {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwNullPointerException(){

        thrown.expect(NullPointerException.class);


    }

}

