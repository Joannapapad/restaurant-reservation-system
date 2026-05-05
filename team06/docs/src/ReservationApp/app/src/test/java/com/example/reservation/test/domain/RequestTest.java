package com.example.reservation.test.domain;

import static org.junit.Assert.*;
import com.example.reservation.domain.*;
import com.example.reservation.util.SimpleCalendar;
import com.example.reservation.util.SystemDate;
import com.example.reservation.contact.*;

import org.junit.*;

import java.util.List;

public class RequestTest {

    private Request request;
    private Store store;
    private Customer customer;

    @Before
    public void setUp() {
        request = new Request(
                101,
                1,
                102,
                4,
                ReservationStatus.PENDING,
                "A table near the window"
        );

        ZipCode zip = new ZipCode("15342");
        StoreAddress address = new StoreAddress("Patision", "78", zip, "Athens", "Greece");

        store = new Store(
                1,2001,
                "Test Store",

                "Burger",address,
                10,
                50
        );
        EmailAddress email = new EmailAddress("ioannapapad@gmail.com");
        TelephoneNumber tel = new TelephoneNumber("2106004455");

        customer = new Customer(
                102,
                "Ioanna Tsiokani",
                "ioanna.tsiok",
                email,
                "password1123",
                tel
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(101, request.getReservationID());
        assertEquals(1, request.getStoreID());
        assertEquals(102, request.getCustomerId());
        assertEquals(4, request.getNumofpeople());
        assertEquals(ReservationStatus.PENDING, request.getReservationStatus());
        assertEquals("A table near the window", request.getComment());

        SimpleCalendar currentDate = SystemDate.now();
        assertEquals(currentDate.getYear(), request.getRequestDate().getYear());
        assertEquals(currentDate.getMonth(), request.getRequestDate().getMonth());
        assertEquals(currentDate.getDayOfMonth(), request.getRequestDate().getDayOfMonth());
    }

    @Test
    public void testSettersAndGetters() {
        request.setReservationID(102);
        assertEquals(102, request.getReservationID());

        request.setStoreID(2);
        assertEquals(2, request.getStoreID());

        request.setCustomerId(103);
        assertEquals(103, request.getCustomerId());

        request.setNumofpeople(6);
        assertEquals(6, request.getNumofpeople());

        request.setReservationStatus(ReservationStatus.APPROVED);
        assertEquals(ReservationStatus.APPROVED, request.getReservationStatus());

        request.setComment("Birthday dinner");
        assertEquals("Birthday dinner", request.getComment());
    }

    @Test
    public void testStatusChange() {
        request.setReservationStatus(ReservationStatus.APPROVED);
        assertEquals(ReservationStatus.APPROVED, request.getReservationStatus());

        request.setReservationStatus(ReservationStatus.REJECTED);
        assertEquals(ReservationStatus.REJECTED, request.getReservationStatus());
    }

    @Test
    public void testEmptyComment() {
        request.setComment("");
        assertEquals("", request.getComment());
    }

    @Test
    public void testEdgeCaseNumOfPeople() {
        request.setNumofpeople(1);
        assertEquals(1, request.getNumofpeople());

        request.setNumofpeople(100);
        assertEquals(100, request.getNumofpeople());
    }

    @Test
    public void testDisplayRequestDetails() {
        SimpleCalendar currentDate = SystemDate.now();
        request.displayRequestDetails();
    }
}
