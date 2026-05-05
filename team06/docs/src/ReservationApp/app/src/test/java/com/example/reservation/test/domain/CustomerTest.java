package com.example.reservation.test.domain;

import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationStatus;
import com.example.reservation.domain.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;
    private Customer existingCustomer;
    private Store store;

    @Before
    public void setUp() {
        EmailAddress email = new EmailAddress("john.doe@gmail.com");
        TelephoneNumber tel = new TelephoneNumber("1234567890");

        customer = new Customer(1, "John Doe", "johndoe", email, "password123", tel);
        existingCustomer = new Customer(2, "Jane Smith", "janesmith", email, "password456", tel);

        store = new Store(10, 100, "CoffeeShop", "Cafe", null, 50, 10);

        HashMap<Integer, Customer> customerData = new HashMap<>();
        customerData.put(existingCustomer.getCustomerId(), existingCustomer);
        customer.setCustomerData(customerData);
    }

    @Test
    public void testSignUpNewCustomer() {
        assertTrue(customer.signUp(customer));
        assertTrue(customer.checksExistenceOfId(customer));
    }

    @Test
    public void testSignUpExistingCustomer() {
        assertFalse(customer.signUp(existingCustomer)); // Customer already exists
    }

    @Test
    public void testSignInValidCustomer() {
        Customer existingCustomer = new Customer(1, "Jane Smith", "janesmith",
                new EmailAddress("jane.smith@example.com"), "password456",
                new TelephoneNumber("1234567890"));

        HashMap<Integer, Customer> customerData = new HashMap<>();
        customerData.put(existingCustomer.getCustomerId(), existingCustomer);
        existingCustomer.setCustomerData(customerData);

        assertTrue(existingCustomer.signIn("janesmith", "password456"));
    }


    @Test
    public void testSignInInvalidCredentials() {
        assertFalse(existingCustomer.signIn("janesmith", "wrongpassword"));
    }

    @Test
    public void testSignInNonExistentCustomer() {
        assertFalse(customer.signIn("nonexistent", "password123"));
    }

    @Test
    public void testCheckExistenceOfId() {
        assertTrue(customer.checksExistenceOfId(existingCustomer));
        assertFalse(customer.checksExistenceOfId(customer)); // Customer not yet signed up
    }

    @Test
    public void testCheckExistenceOfUserName() {
        assertTrue(customer.checkExistenceOfUserName("janesmith"));
        assertFalse(customer.checkExistenceOfUserName("johndoe"));
    }

    @Test
    public void testCreateRequest() {
        Request request = customer.createRequest(store, customer, 4, "Table by the window");
        assertNotNull(request);
        assertEquals(4, request.getNumofpeople());
        assertEquals(ReservationStatus.PENDING, request.getReservationStatus());
        assertEquals(store.getStoreId(), request.getStoreID());
        assertTrue(customer.getRequests().contains(request));
    }

    @Test
    public void testCancelRequest() {
        Request request = customer.createRequest(store, customer, 4, "Cancel this request");
        assertTrue(customer.getRequests().contains(request));

        customer.cancelRequest(request);
        assertFalse(customer.getRequests().contains(request));
    }

    @Test
    public void testRateStore() {
        customer.rateStore(10, 5, "Excellent service!");
        // Additional validation for ratings can be added once `ratings` list is accessible.
    }

    @Test
    public void testAddReservation() {
        Reservation reservation = new Reservation(
                new Request(101, store.getStoreId(), customer.getCustomerId(), 4, ReservationStatus.APPROVED, "Reservation test")
        );
        customer.addReservation(reservation);

        List<Reservation> reservations = customer.getReservations();
        assertEquals(1, reservations.size());
        assertTrue(reservations.contains(reservation));
    }
}
