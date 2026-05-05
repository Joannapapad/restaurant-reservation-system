package com.example.reservation.test.domain;

import static org.junit.Assert.*;
import com.example.reservation.domain.*;
import com.example.reservation.util.SimpleCalendar;
import com.example.reservation.util.SystemDate;
import com.example.reservation.contact.*;

import org.junit.*;

import java.util.List;
/**
 * Unit tests for the Reservation class.
 */
public class ReservationTest {
    SimpleCalendar simpleCalendar = new SimpleCalendar(2025, 1, 20);
    private Request testRequest;
    private Reservation testReservation;

    @Before
    public void setUp() {
        // Mock current system date for consistent testing
        SystemDate.setStub(simpleCalendar);

        // Create a test Request
        testRequest = new Request(
                101,              // reservationID
                1,                // storeID
                202,              // customerID
                4,                // numofpeople
                ReservationStatus.APPROVED, // status
                "Test comment"    // comment
        );

        // Set additional fields for the request
        testRequest.setScheduledDateTime("2025-01-20", "18:00");

        // Create a Reservation based on the Request
        testReservation = new Reservation(testRequest);
    }

    @Test
    public void testConstructor() {
        assertEquals(testRequest.getReservationID(), testReservation.getReservationId());
        assertEquals(testRequest.getStoreID(), testReservation.getStoreId());
        assertEquals(testRequest.getCustomerId(), testReservation.getCustomerId());
        assertEquals(testRequest.getScheduledDate(), testReservation.getReservationDate());
        assertEquals(testRequest.getScheduledTime(), testReservation.getReservationTime());
        assertEquals(testRequest.getNumofpeople(), testReservation.getNumOfPeople());
        assertEquals(testRequest.getComment(), testReservation.getComment());
    }

    @Test
    public void testSetReservationDate() {
        SimpleCalendar newDate = new SimpleCalendar(2025, 1, 25);
        testReservation.setReservationDate(newDate);
        assertEquals(newDate, testReservation.getReservationDate());
    }

    @Test
    public void testSetReservationTime() {
        SimpleCalendar newTime = new SimpleCalendar(2025, 1, 20, 19, 0);
        testReservation.setReservationTime(newTime);
        assertEquals(newTime, testReservation.getReservationTime());
    }

    @Test
    public void testSetNumOfPeople() {
        testReservation.setNumOfPeople(6);
        assertEquals(6, testReservation.getNumOfPeople());
    }

    @Test
    public void testSetComment() {
        String newComment = "Updated comment";
        testReservation.setComment(newComment);
        assertEquals(newComment, testReservation.getComment());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create another reservation with the same data
        Reservation anotherReservation = new Reservation(testRequest);

        assertEquals(testReservation, anotherReservation);
        assertEquals(testReservation.hashCode(), anotherReservation.hashCode());

        // Modify the second reservation
        anotherReservation.setNumOfPeople(5);
        assertNotEquals(testReservation, anotherReservation);
    }

    @Test
    public void testToString() {
        String expectedString = "Reservation{" +
                "reservationId=" + testReservation.getReservationId() +
                ", storeId=" + testReservation.getStoreId() +
                ", customerId=" + testReservation.getCustomerId() +
                ", reservationDate=" + testReservation.getReservationDate() +
                ", reservationTime=" + testReservation.getReservationTime() +
                ", numOfPeople=" + testReservation.getNumOfPeople() +
                ", comment='" + testReservation.getComment() + '\'' +
                '}';
        assertEquals(expectedString, testReservation.toString());
    }
}
