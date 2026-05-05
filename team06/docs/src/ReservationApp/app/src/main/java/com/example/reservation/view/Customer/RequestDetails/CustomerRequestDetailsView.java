package com.example.reservation.view.Customer.RequestDetails;

import com.example.reservation.domain.Request;

/**
 * Interface for the Customer Request Details view.
 * Defines the methods that the presenter can call to interact with the UI.
 */
public interface CustomerRequestDetailsView {
    /**
     * Displays the details of a specific request on the UI.
     *
     * @param request The request object containing the details to be displayed.
     */
    void displayRequestDetails(Request request);

    /**
     * Displays a success message to the user, typically after an operation completes successfully.
     *
     * @param message The success message to be shown.
     */
    void showSuccessMessage(String message);

    /**
     * Displays a success message to the user, typically after an operation completes successfully.
     *
     * @param message The success message to be shown.
     */
    void showErrorMessage(String message);
}

