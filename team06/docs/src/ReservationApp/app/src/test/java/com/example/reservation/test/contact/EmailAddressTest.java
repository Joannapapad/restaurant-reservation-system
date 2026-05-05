package com.example.reservation.test.contact;
import static org.junit.Assert.assertEquals;

import com.example.reservation.contact.EmailAddress;
import static org.junit.Assert.*;
import org.junit.Test;
public class EmailAddressTest {

   private EmailAddress email1 = new EmailAddress();
    private EmailAddress email2 = new EmailAddress();

    @Test
    public void equalsTrueTest() {

        assertEquals(email1,email2);
        email1 = new EmailAddress("Fani12@gmail.com");
        email2 = new EmailAddress("Fani12@gmail.com");

        assertEquals(email1,email2);
        assertTrue(email1.getAddress().equals(email2.getAddress()));
    }

    @Test
    public void equalsFalseTest() {

        email1 = new EmailAddress("Fani12@gmail.com");
        assertNotEquals(email1,email2);

        email2 = new EmailAddress("zark@gmail.com");
        assertNotEquals(email1,email2);
        assertFalse(email1.getAddress().equals(email2.getAddress()));

    }

    @Test
    public void hasCodeTrueTest() {

        assertTrue(email1.hashCode() == email2.hashCode());

        email1 = new EmailAddress("Fani12@gmail.com");
        email2 = new EmailAddress("Fani12@gmail.com");

        assertTrue(email1.hashCode() == email2.hashCode());
    }

    @Test
    public void hasCodeFalseTest() {

        email1 = new EmailAddress("Fani12@gmail.com");
        assertFalse(email1.hashCode() == email2.hashCode());

        email2 = new EmailAddress("zark@gmail.com");
        assertFalse(email1.hashCode() == email2.hashCode());
    }




}

