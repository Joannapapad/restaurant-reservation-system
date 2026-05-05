package com.example.reservation.test.contact;
import static org.junit.Assert.*;

import com.example.reservation.contact.StoreAddress;

import org.junit.Test;
public class StoreAddressTest {

    @Test
    public void testEquals(){
        StoreAddress address1 = new StoreAddress();
        StoreAddress address2 = new StoreAddress();
        StoreAddress address3 = new StoreAddress();
        address3.setCountry(null);

        assertFalse(address1.equals(null));
        assertEquals(address1,address2);

        address1.setCity("Crete");
        assertFalse(address1.equals(address2));
        assertFalse(address1.equals(address3));


        address2.setCity("Crete");
        assertEquals(address1, address2);
        assertFalse(address2.equals(address3));

    }

    @Test
    public void testHashCode(){
        StoreAddress address1 = new StoreAddress();
        StoreAddress address2 = new StoreAddress();
        StoreAddress address3 = new StoreAddress();
        address3.setCountry(null);
        assertEquals(0,address3.hashCode());

        assertEquals(address1.hashCode(),address2.hashCode());

        address1.setStreet("Marathonos");
        assertFalse(address1.hashCode() == address2.hashCode());

        address2.setStreet("Marathonos");
        assertEquals(address1.hashCode(),address2.hashCode());


    }
}
