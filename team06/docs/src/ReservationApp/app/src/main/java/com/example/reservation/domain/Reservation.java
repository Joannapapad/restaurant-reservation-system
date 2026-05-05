package com.example.reservation.domain;

import com.example.reservation.util.SimpleCalendar;

import java.util.Objects;

/**
 * Reservation class to represent confirmed reservations for a store.
 */
public class Reservation {
    private final int reservationId; // Unique ID for the reservation
    private final int storeId; // ID of the store where the reservation is made
    private final int customerId; // ID of the customer making the reservation
    private SimpleCalendar reservationDate; // Date of the reservation
    private SimpleCalendar reservationTime; // Time of the reservation
    private int numOfPeople; // Number of people for the reservation
    private String comment; // Optional comment or note about the reservation

    /**
     * Constructor to create a Reservation from an approved Request.
     *
     * @param request The approved Request object.
     */
    public Reservation(Request request) {
        this.reservationId = request.getReservationID();
        this.storeId = request.getStoreID();
        this.customerId = request.getCustomerId();
        this.reservationDate = request.getRequestDate();
        this.reservationTime = request.getRequestTime();
        this.numOfPeople = request.getNumofpeople();
        this.comment = request.getComment();
    }

    /**
     * Gets the unique reservation ID.
     *
     * @return The reservation ID.
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Gets the store ID where the reservation was made.
     *
     * @return The store ID.
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Gets the customer ID who made the reservation.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the date of the reservation.
     *
     * @return The reservation date.
     */
    public SimpleCalendar getReservationDate() {
        return reservationDate;
    }
    /**
     * Gets the time of the reservation.
     *
     * @return The reservation time.
     */
    public SimpleCalendar getReservationTime() {
        return reservationTime;
    }

    /**
     * Gets the number of people for the reservation.
     *
     * @return The number of people.
     */
    public int getNumOfPeople() {
        return numOfPeople;
    }

    /**
     * Gets the comment or note about the reservation.
     *
     * @return The comment or note.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the date of the reservation.
     *
     * @param reservationDate The new reservation date.
     */
    public void setReservationDate(SimpleCalendar reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * Sets the time of the reservation.
     *
     * @param reservationTime The new reservation time.
     */
    public void setReservationTime(SimpleCalendar reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * Sets the number of people for the reservation.
     *
     * @param numOfPeople The new number of people.
     */
    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }
    /**
     * Sets a new comment or note for the reservation.
     *
     * @param comment The new comment or note.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Checks equality between this Reservation and another object.
     *
     * @param o The object to compare.
     * @return True if the two objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId &&
                storeId == that.storeId &&
                customerId == that.customerId &&
                numOfPeople == that.numOfPeople &&
                Objects.equals(reservationDate, that.reservationDate) &&
                Objects.equals(reservationTime, that.reservationTime) &&
                Objects.equals(comment, that.comment);
    }

    /**
     * Generates a hash code for the Reservation object.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(reservationId, storeId, customerId, reservationDate, reservationTime, numOfPeople, comment);
    }

    /**
     * Returns a string representation of the Reservation object.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", storeId=" + storeId +
                ", customerId=" + customerId +
                ", reservationDate=" + reservationDate +
                ", reservationTime=" + reservationTime +
                ", numOfPeople=" + numOfPeople +
                ", comment='" + comment + '\'' +
                '}';
    }
}
