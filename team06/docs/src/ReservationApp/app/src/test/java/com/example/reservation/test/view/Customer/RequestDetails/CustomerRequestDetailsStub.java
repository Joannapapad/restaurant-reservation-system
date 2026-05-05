package com.example.reservation.test.view.Customer.RequestDetails;

import com.example.reservation.domain.Request;
import com.example.reservation.view.Customer.RequestDetails.CustomerRequestDetailsView;

public class CustomerRequestDetailsStub implements CustomerRequestDetailsView {

    private String errorMessage;
    private String successMessage;
    private Request displayedRequest;

    @Override
    public void showErrorMessage(String message) {
        this.errorMessage = message;
    }

    @Override
    public void showSuccessMessage(String message) {
        this.successMessage = message;
    }

    @Override
    public void displayRequestDetails(Request request) {
        this.displayedRequest = request;
    }

    // Getters for testing
    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public Request getDisplayedRequest() {
        return displayedRequest;
    }
}
