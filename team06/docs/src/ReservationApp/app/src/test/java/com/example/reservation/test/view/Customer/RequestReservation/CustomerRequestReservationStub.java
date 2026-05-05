package com.example.reservation.test.view.Customer.RequestReservation;

import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;
import com.example.reservation.view.Customer.RequestReservation.CustomerRequestReservationView;

import java.util.List;

public class CustomerRequestReservationStub implements CustomerRequestReservationView {

    private List<Request> displayedRequests;
    private List<Reservation> displayedReservations;
    private String errorMessage;

    @Override
    public void displayRequestList(List<Request> requests) {
        this.displayedRequests = requests;
    }

    @Override
    public void displayReservationList(List<Reservation> reservations) {
        this.displayedReservations = reservations;
    }

    @Override
    public void showErrorMessage(String message) {
        this.errorMessage = message;
    }

    // Getters for testing
    public List<Request> getDisplayedRequests() {
        return displayedRequests;
    }

    public List<Reservation> getDisplayedReservations() {
        return displayedReservations;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
