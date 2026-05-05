package com.example.reservation.view.StoreOwner.RequestReservationList;

import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;

import java.util.List;

public interface RequestReservationView {
    void displayReservationList(List<Reservation> reservations);

    void showErrorMessage(String message);

    void displayRequestList(List<Request> requests);
}
