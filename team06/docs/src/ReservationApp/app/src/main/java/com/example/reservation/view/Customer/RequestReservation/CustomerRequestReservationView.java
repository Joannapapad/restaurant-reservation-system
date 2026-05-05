package com.example.reservation.view.Customer.RequestReservation;

import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;

import java.util.List;

/**
 * The CustomerRequestReservationView interface defines the methods for displaying customer
 * requests and reservations in the view. It provides methods for showing the requests list,
 * reservations list, and handling error messages.
 * <p>
 * Implementations of this interface are typically responsible for updating the UI based on the
 * data provided by the associated presenter or view model. It allows the presenter to communicate
 * with the view and provide updates as needed.
 * </p>
 */
public interface CustomerRequestReservationView {


    /**
     * Displays the list of customer requests in the view.
     * <p>
     * This method should update the UI to present the provided list of requests, usually in a
     * RecyclerView, ListView, or other UI components suitable for displaying a list.
     * </p>
     *
     * @param requests A list of customer requests to display.
     */
    void displayRequestList(List<Request> requests);

    /**
     * Displays the list of customer reservations in the view.
     * <p>
     * This method should update the UI to present the provided list of reservations, typically
     * in a UI element such as a RecyclerView or ListView.
     * </p>
     *
     * @param reservations A list of customer reservations to display.
     */
    void displayReservationList(List<Reservation> reservations);

    /**
     * Shows an error message to the user.
     * <p>
     * This method can be used to inform the user about an error that occurred, such as when
     * loading data or processing a request. The error message will be displayed on the screen.
     * </p>
     *
     * @param message The error message to be displayed.
     */
    void showErrorMessage(String message);
}

