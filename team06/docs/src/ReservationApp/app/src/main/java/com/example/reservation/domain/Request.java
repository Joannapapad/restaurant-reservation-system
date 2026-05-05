package com.example.reservation.domain;

import com.example.reservation.util.*;

/**
 * Represents a request for a reservation in the system.
 * Includes details such as the reservation ID, customer, store,
 * request and scheduled dates/times, and other relevant information.
 */
public class Request {
    private int reservationID;
    private int storeID;
    private int customerId;
    private SimpleCalendar requestDate;     // The date the request was made
    private SimpleCalendar requestTime;     // The time the request was made
    private SimpleCalendar scheduledDate;   // The scheduled date for the reservation
    private SimpleCalendar scheduledTime;   // The scheduled time for the reservation
    private int numofpeople;
    private ReservationStatus reservationStatus;
    private String comment;

    /**
     * Constructs a new Request with the specified details.
     *
     * @param reservationID   The unique ID of the reservation.
     * @param storeID         The ID of the store where the reservation is requested.
     * @param customerId      The ID of the customer making the request.
     * @param numofpeople     The number of people for the reservation.
     * @param status          The initial status of the reservation.
     * @param comment         An optional comment for the reservation.
     */
    public Request(int reservationID, int storeID, int customerId, int numofpeople, ReservationStatus status, String comment) {
        this.reservationID = reservationID;
        this.storeID = storeID;
        this.customerId = customerId;
        this.numofpeople = numofpeople;
        this.reservationStatus = status;
        this.comment = comment;

        // Automatically set the current date and time for the request
        this.requestDate = new SimpleCalendar(SystemDate.now().getYear(), SystemDate.now().getMonth(), SystemDate.now().getDayOfMonth());
        this.requestTime = new SimpleCalendar(SystemDate.now().getYear(), SystemDate.now().getMonth(), SystemDate.now().getDayOfMonth(), SystemDate.now().getHour(), SystemDate.now().getMinute());

        // Initialize the scheduled date and time to be null initially (can be set later)
        this.scheduledDate = null;
        this.scheduledTime = null;
    }

    // Getters and setters for all fields

    /**
     * Gets the unique reservation ID.
     *
     * @return The reservation ID.
     */
    public int getReservationID() {
        return reservationID;
    }

    /**
     * Sets the unique reservation ID.
     *
     * @param reservationID The reservation ID to set.
     */
    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    /**
     * Gets the ID of the store.
     *
     * @return The store ID.
     */
    public int getStoreID() {
        return storeID;
    }

    /**
     * Sets the ID of the store.
     *
     * @param storeID The store ID to set.
     */
    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    /**
     * Gets the ID of the customer.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param customerId The store ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public SimpleCalendar getRequestDate() {
        return requestDate;
    }

    public SimpleCalendar getRequestTime() {
        return requestTime;
    }

    public SimpleCalendar getScheduledDate() {
        return scheduledDate;
    }

    /**
     * Sets the scheduled date of the reservation.
     *
     * @param scheduledDate The scheduled date to set.
     */
    public void setScheduledDate(SimpleCalendar scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    /**
     * gets the scheduled time of the reservation.
     *
     * @return  The scheduled time.
     */
    public SimpleCalendar getScheduledTime() {
        return scheduledTime;
    }

    /**
     * Sets the scheduled time of the reservation.
     *
     * @param scheduledTime The scheduled time to set.
     */
    public void setScheduledTime(SimpleCalendar scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    // Setting the request date and time based on string input
    public void setRequestDate(String date) {
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        this.requestDate = new SimpleCalendar(year, month, day);
    }

    public void setRequestTime(String time) {
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        this.requestTime = new SimpleCalendar(this.requestDate.getYear(), this.requestDate.getMonth(), this.requestDate.getDayOfMonth(), hour, minute);
    }

    /**
     * Sets the scheduled date and time based on the provided strings.
     *
     * @param date The scheduled date in the format "YYYY-MM-DD".
     * @param time The scheduled time in the format "HH:mm".
     */
    public void setScheduledDateTime(String date, String time) {
        String[] dateParts = date.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        this.scheduledDate = new SimpleCalendar(year, month, day);
        this.scheduledTime = new SimpleCalendar(year, month, day, hour, minute);
    }

    /**
     * Displays the details of the request.
     */
    public void displayRequestDetails() {
        System.out.println("Request Date: " + requestDate.getYear() + "-" + requestDate.getMonth() + "-" + requestDate.getDayOfMonth());
        System.out.println("Request Time: " + requestTime.getHour() + ":" + requestTime.getMinute());
        if (scheduledDate != null && scheduledTime != null) {
            System.out.println("Scheduled Date: " + scheduledDate.getYear() + "-" + scheduledDate.getMonth() + "-" + scheduledDate.getDayOfMonth());
            System.out.println("Scheduled Time: " + scheduledTime.getHour() + ":" + scheduledTime.getMinute());
        }
    }

    public int getNumofpeople() {
        return numofpeople;
    }

    public void setNumofpeople(int numofpeople) {
        this.numofpeople = numofpeople;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
