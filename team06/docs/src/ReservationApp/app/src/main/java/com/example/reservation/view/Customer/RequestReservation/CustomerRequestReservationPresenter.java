package com.example.reservation.view.Customer.RequestReservation;

import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;

import java.util.List;

/**
 * Presenter class for managing customer requests and reservations.
 * Responsible for interacting with the {@link CustomerRequestReservationView} and the {@link CustomerDAOMemory}.
 */
public class CustomerRequestReservationPresenter {

    private final CustomerRequestReservationView view;
    private final CustomerDAOMemory customerDAO;
    private final Customer customer;

    private final int customerId;

    /**
     * Constructor for initializing the presenter with the necessary dependencies.
     *
     * @param view        The view interface for displaying requests and reservations.
     * @param customerDAO The DAO object for accessing customer data.
     * @param customerId  The ID of the customer for whom data will be loaded.
     */
    public CustomerRequestReservationPresenter(CustomerRequestReservationView view, CustomerDAOMemory customerDAO, int customerId) {
        this.view = view;
        this.customerDAO = customerDAO;
        this.customerId = customerId;
        this.customer = customerDAO.findbyid(customerId);

    }

    /**
     * Loads the list of requests for the current customer.
     * If the requests are successfully retrieved, they are displayed in the view.
     * Otherwise, an error message is shown.
     */
    public void loadRequests() {
        List<Request> requests = customer.getRequests();
        if (requests != null) {
            view.displayRequestList(requests);  // Update the view with the list of requests
        } else {
            view.showErrorMessage("Failed to load requests");
        }
    }

    /**
     * Loads the list of reservations for the current customer.
     * If the reservations are successfully retrieved, they are displayed in the view.
     * Otherwise, an error message is shown.
     */
    public void loadReservations() {
        List<Reservation> reservations = customer.getReservations();
        if (reservations != null) {
            view.displayReservationList(reservations);  // Update the view with the list of reservations
        } else {
            view.showErrorMessage("Failed to load reservations");
        }
    }
}
