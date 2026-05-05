package com.example.reservation.test.domain;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.domain.StoreOwner;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreOwnerTest {

    private StoreOwner owner1;
    private StoreOwner owner2;
    private EmailAddress email1;
    private TelephoneNumber phone1;
    private EmailAddress email2;
    private TelephoneNumber phone2;

    @Before
    public void setUp() {
        email1 = new EmailAddress("owner1@example.com");
        phone1 = new TelephoneNumber("6980978678");

        email2 = new EmailAddress("owner2@example.com");
        phone2 = new TelephoneNumber("6978564332");

        owner1 = new StoreOwner(1, "123456789", "owner1", email1, "password1", phone1);
        owner2 = new StoreOwner(2, "987654321", "owner2", email2, "password2", phone2);
    }

    @Test
    public void testGetters() {
        assertEquals(1, owner1.getOwnerId());
        assertEquals("123456789", owner1.getAFM());
        assertEquals("owner1", owner1.getUserName());
        assertEquals("owner1@example.com", owner1.getEmail().getAddress());
        assertEquals("password1", owner1.getPassword());
        assertEquals("6980978678", owner1.getNumber().getTelephoneNumber());

        assertEquals(2, owner2.getOwnerId());
        assertEquals("987654321", owner2.getAFM());
        assertEquals("owner2", owner2.getUserName());
        assertEquals("owner2@example.com", owner2.getEmail().getAddress());
        assertEquals("password2", owner2.getPassword());
        assertEquals("6978564332", owner2.getNumber().getTelephoneNumber());
    }

    @Test
    public void testSetters() {
        owner1.setOwnerId(42);
        owner1.setAFM("999999999");
        owner1.setUserName("updatedOwner1");
        owner1.setPassword("newPassword1");

        EmailAddress newEmail = new EmailAddress("updatedOwner1@example.com");
        TelephoneNumber newPhone = new TelephoneNumber("6999999999");

        owner1.setEmail(newEmail);
        owner1.setNumber(newPhone);

        assertEquals(42, owner1.getOwnerId());
        assertEquals("999999999", owner1.getAFM());
        assertEquals("updatedOwner1", owner1.getUserName());
        assertEquals("newPassword1", owner1.getPassword());
        assertEquals("updatedOwner1@example.com", owner1.getEmail().getAddress());
        assertEquals("6999999999", owner1.getNumber().getTelephoneNumber());
    }

    @Test
    public void testAFMValidation() {
        assertEquals("123456789", owner1.getAFM());
        owner1.setAFM("987654321");
        assertEquals("987654321", owner1.getAFM());
    }

    @Test
    public void testOwnerIdValidation() {
        assertEquals(1, owner1.getOwnerId());
        owner1.setOwnerId(99);
        assertEquals(99, owner1.getOwnerId());
    }

    @Test
    public void testEmailValidation() {
        assertEquals("owner2@example.com", owner2.getEmail().getAddress());
        EmailAddress newEmail = new EmailAddress("newowner2@example.com");
        owner2.setEmail(newEmail);
        assertEquals("newowner2@example.com", owner2.getEmail().getAddress());
    }

    @Test
    public void testPhoneNumberValidation() {
        assertEquals("6978564332", owner2.getNumber().getTelephoneNumber());
        TelephoneNumber newPhone = new TelephoneNumber("6988888888");
        owner2.setNumber(newPhone);
        assertEquals("6988888888", owner2.getNumber().getTelephoneNumber());
    }

    @Test
    public void testPasswordManagement() {
        assertEquals("password1", owner1.getPassword());
        owner1.setPassword("securePassword123");
        assertEquals("securePassword123", owner1.getPassword());
    }
}
