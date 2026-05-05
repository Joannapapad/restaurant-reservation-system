package com.example.reservation.view.StoreOwner.RequestDetails;


import com.example.reservation.domain.Request;

public interface RequestDetailsView {
    void displayRequestDetails(Request request);
    void showSuccessMessage(String message);
    void showErrorMessage(String message);
}
