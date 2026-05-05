package com.example.reservation.test.contact;
import static org.junit.Assert.*;

import com.example.reservation.contact.ZipCode;

import org.junit.Test;


public class ZipCodeTest {

    private ZipCode zipCode1 = new ZipCode();
    private ZipCode zipCode2 = new ZipCode();


    @Test
    public void testEqualsTrue(){

        assertEquals(zipCode1,zipCode2);

        zipCode1 = new ZipCode("12345");
        zipCode2 = new ZipCode("12345");

        assertEquals(zipCode1,zipCode2);

        assertTrue(zipCode1.getCode().equals(zipCode2.getCode()));

    }
    @Test
    public void testEqualsFalse(){
        zipCode1 = new ZipCode("12345");
        zipCode2 = new ZipCode("1234");

        assertNotEquals(zipCode1,zipCode2);

        assertFalse(zipCode1.getCode().equals(zipCode2.getCode()));
    }

    @Test
    public void testHashCodeTrue(){

        assertTrue(zipCode1.hashCode() == zipCode2.hashCode());

        zipCode1 = new ZipCode("12345");
        zipCode2 = new ZipCode("12345");

        assertTrue(zipCode1.hashCode() == zipCode2.hashCode());

    }

    @Test
    public void testHashCodeFalse(){

        zipCode1 = new ZipCode("12345");
        assertFalse(zipCode1.hashCode() == zipCode2.hashCode());

        zipCode2 = new ZipCode("1234");
        assertFalse(zipCode1.hashCode() == zipCode2.hashCode());
    }
}