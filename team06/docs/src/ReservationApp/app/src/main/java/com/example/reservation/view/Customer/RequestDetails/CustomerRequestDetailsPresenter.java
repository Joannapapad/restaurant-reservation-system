package com.example.reservation.view.Customer.RequestDetails;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.RequestDAOMemory;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.ReservationStatus;

/**
 * Presenter class for handling the logic of the Customer Request Details screen.
 * Interacts with the {@link CustomerRequestDetailsView} and the data layer (DAOs).
 */
public class CustomerRequestDetailsPresenter {

    private final CustomerRequestDetailsView view;
    private final RequestDAOMemory requestDAO;

    private Customer currentCustomer;
    private Request currentRequest;
    private CurrentUserDAOMemory user;

    /**
     * Constructor for initializing the presenter with necessary dependencies.
     *
     * @param view       The view interface for updating the UI.
     * @param user       The DAO for managing the currently logged-in user.
     * @param requestDAO The DAO for managing requests.
     */
    public CustomerRequestDetailsPresenter(CustomerRequestDetailsView view, CurrentUserDAOMemory user, RequestDAOMemory requestDAO) {
        this.view = view;
        this.requestDAO = requestDAO;
        this.user = user;
        currentCustomer = (Customer) user.retrieve();
    }

    /**
     * Loads the details of a specific request and updates the view.
     *
     * @param requestId The ID of the request to be loaded.
     */
    public void loadRequestDetails(int requestId) {
        // Retrieve the request from the DAO
        currentRequest = requestDAO.find(requestId);

        if (currentRequest == null) {
            view.showErrorMessage("Error: Request not found.");
        } else {
            view.displayRequestDetails(currentRequest);
        }
    }

    /**
     * Cancels the currently loaded request.
     * Marks the request as canceled and removes it from the DAO.
     */
    public void cancelRequest() {
        if (currentRequest != null) {
            // Cancel the request by updating its status (assuming cancel means to mark it as canceled)
            currentCustomer.cancelRequest(currentRequest); // Assuming the status has a CANCELLED value
            requestDAO.delete(currentRequest);
            view.showSuccessMessage("Request canceled successfully.");
        } else {
            view.showErrorMessage("Error: Unable to cancel request.");
        }
    }
}
