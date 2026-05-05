package com.example.reservation.test.contact;
import com.example.reservation.contact.TelephoneNumber;
import static org.junit.Assert.*;
import org.junit.Test;

public class TelephoneNumberTest {

    private TelephoneNumber tel1 = new TelephoneNumber();
    private TelephoneNumber tel2 = new TelephoneNumber();

    @Test
    public void equalsTrueTest() {

        assertEquals(tel1,tel2);

        tel1 = new TelephoneNumber("6554433221");
        tel2 = new TelephoneNumber("6554433221");

        assertEquals(tel1,tel2);
        assertTrue(tel1.getTelephoneNumber().equals(tel2.getTelephoneNumber()));
    }
    @Test
    public void equalsFalseTest() {

        tel1 = new TelephoneNumber("6554433221");
        assertNotEquals(tel1,tel2);

        tel2 = new TelephoneNumber("54466788764");
        assertNotEquals(tel1,tel2);
        assertFalse(tel1.getTelephoneNumber().equals(tel2.getTelephoneNumber()));
    }

    @Test
    public void hasCodeTrueTest() {
        assertTrue(tel1.hashCode() == tel2.hashCode());

        tel1 = new TelephoneNumber("6554433221");
        tel2 = new TelephoneNumber("6554433221");

        assertTrue(tel1.hashCode() == tel2.hashCode());
    }
    @Test
    public void hasCodeFalseTest() {
        tel1 = new TelephoneNumber("6554433221");
        assertFalse(tel1.hashCode() == tel2.hashCode());

        tel2 = new TelephoneNumber("54466788764");
        assertFalse(tel1.hashCode() == tel2.hashCode());

    }


}
