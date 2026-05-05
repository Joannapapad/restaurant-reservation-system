package com.example.reservation.test.domain;

import com.example.reservation.contact.StoreAddress;
import com.example.reservation.contact.ZipCode;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.contact.TelephoneNumber;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationStatus;
import com.example.reservation.domain.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StoreTest {

    private Store store;
    private ZipCode zip;
    private StoreAddress address;
    private Customer customer;

    @Before
    public void setUp() {
        zip = new ZipCode("15342");
        address = new StoreAddress("Patision", "78", zip, "Athens", "Greece");
        store = new Store(1, 101, "DrinkJo", "Bar", address, 100, 25);

        EmailAddress email = new EmailAddress("zarkaos@gmail.com");
        TelephoneNumber tel = new TelephoneNumber("6984849483");
        customer = new Customer(5, "zarkos", "zarkk", email, "zarkkosks", tel);
    }

    @Test
    public void testGetters() {
        assertEquals(1, store.getStoreId());
        assertEquals(101, store.getOwnerId());
        assertEquals("DrinkJo", store.getName());
        assertEquals(address, store.getAddress());
        assertEquals("Bar", store.getCategory());
        assertEquals(100, store.getCapacity());
        assertEquals(25, store.getTableNumber());
        assertEquals(0, store.getUsedCapacity());
    }

    @Test
    public void testSetters() {
        StoreAddress newAddress = new StoreAddress("Kolonaki", "12", zip, "Athens", "Greece");

        store.setStoreId(2);
        store.setName("HappyBar");
        store.setOwnerId(102);
        store.setAddress(newAddress);
        store.setCategory("Cafe");
        store.setCapacity(120);
        store.setTableNumber(30);

        assertEquals(2, store.getStoreId());
        assertEquals("HappyBar", store.getName());
        assertEquals(102, store.getOwnerId());
        assertEquals(newAddress, store.getAddress());
        assertEquals("Cafe", store.getCategory());
        assertEquals(120, store.getCapacity());
        assertEquals(30, store.getTableNumber());
    }

    @Test
    public void testHasCapacity() {
        assertTrue(store.hasCapacity(5));
        assertFalse(store.hasCapacity(150)); // Exceeds capacity
    }

    @Test
    public void testManageRequestAccept() {
        int numOfPeople = 4;
        int capacityBefore = store.getUsedCapacity();

        // Create a new request
        Request request = new Request(34, store.getStoreId(), customer.getCustomerId(), numOfPeople, ReservationStatus.PENDING, "Near the window");

        // Add the request to the store's requests list before calling manageRequest
        store.addRequest(request);

        System.out.println("Before manageRequest: usedCapacity=" + capacityBefore + ", requests=" + store.getRequests());

        // Call manageRequest to accept the request
        boolean accepted = store.manageRequest(customer, request, true);

        // Get the updated capacity
        int capacityAfter = store.getUsedCapacity();

        System.out.println("After manageRequest: usedCapacity=" + capacityAfter + ", requests=" + store.getRequests() + ", reservations=" + store.getReservation());

        // Assert the results
        assertTrue(accepted);
        assertEquals(capacityBefore + numOfPeople, capacityAfter); // Check if the used capacity was updated correctly
        assertTrue(store.getRequests().isEmpty()); // Ensure the request is removed from the requests list
        assertEquals(1, store.getReservation().size()); // Ensure one reservation was added
    }


    @Test
    public void testManageRequestReject() {
        int numOfPeople = 4;
        int capacityBefore = store.getUsedCapacity();

        Request request = new Request(34, store.getStoreId(), customer.getCustomerId(), numOfPeople, ReservationStatus.PENDING, "Near the window");
        boolean accepted = store.manageRequest(customer, request, false);
        int capacityAfter = store.getUsedCapacity();

        assertFalse(accepted);
        assertEquals(capacityBefore, capacityAfter);
        assertTrue(store.getRequests().isEmpty()); // Request should be removed after rejection
    }

    @Test
    public void testManageRequestFullCapacity() {
        int numOfPeople = 150; // Exceeds capacity
        Request request = new Request(56, store.getStoreId(), customer.getCustomerId(), numOfPeople, ReservationStatus.PENDING, "Middle of the room");

        boolean accepted = store.manageRequest(customer, request, true);

        assertFalse(accepted); // Should not accept request
        assertTrue(store.getRequests().isEmpty()); // Request should be removed
    }

    @Test
    public void testAcceptRequest() {
        Request request = new Request(103, store.getStoreId(), customer.getCustomerId(), 4, ReservationStatus.PENDING, "Window-side table");
        customer.addRequest(request);
        store.addRequest(request);

        store.acceptRequest(customer, store, request);

        assertEquals(ReservationStatus.APPROVED, request.getReservationStatus());
        assertTrue(store.getReservation().contains(new Reservation(request))); // Reservation should be added
        assertFalse(store.getRequests().contains(request)); // Request should be removed
        assertEquals(4, store.getUsedCapacity()); // Used capacity should be updated

        System.out.println("Request Status: " + request.getReservationStatus());
        System.out.println("Store Reservations: " + store.getReservation());
        System.out.println("Store Requests: " + store.getRequests());
        System.out.println("Used Capacity: " + store.getUsedCapacity());
    }

    @Test
    public void testRejectRequest() {
        Request request = new Request(104, store.getStoreId(), customer.getCustomerId(), 4, ReservationStatus.PENDING, "Window-side table");
        customer.addRequest(request);
        store.addRequest(request);

        store.rejectRequest(request, store, customer);

        assertEquals(ReservationStatus.REJECTED, request.getReservationStatus());
        assertFalse(store.getRequests().contains(request)); // Request should be removed
    }

    @Test
    public void testCancelConflictingRequests() {
        // Approved request
        Request approvedRequest = new Request(105, store.getStoreId(), customer.getCustomerId(), 4, ReservationStatus.APPROVED, "Window-side table");
        approvedRequest.setRequestDate("2023-12-01");
        approvedRequest.setRequestTime("12:00");
        store.addRequest(approvedRequest);
        store.addReservation(new Reservation(approvedRequest));

        // Conflicting request
        Request conflictingRequest = new Request(106, store.getStoreId(), customer.getCustomerId(), 4, ReservationStatus.PENDING, "Another table request");
        conflictingRequest.setRequestDate("2023-12-01");
        conflictingRequest.setRequestTime("12:00");
        customer.addRequest(conflictingRequest);

        store.cancelConflictingRequests(store, customer, approvedRequest);

        assertEquals(ReservationStatus.REJECTED, conflictingRequest.getReservationStatus());
    }

    @Test
    public void testStoreAndCustomerReservationsUpdated() {
        Request request = new Request(107, store.getStoreId(), customer.getCustomerId(), 4, ReservationStatus.PENDING, "Window-side table");
        store.manageRequest(customer, request, true); // Accept the request

        List<Reservation> customerReservations = customer.getReservations();
        assertFalse(customer.getRequests().contains(request)); // Request should be removed
        assertEquals(1, customerReservations.size());
        assertTrue(store.getReservation().containsAll(customerReservations));
    }
}
