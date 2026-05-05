package com.example.reservation.view.Customer.StoreDetail;

import com.example.reservation.domain.Store;

/**
 * View interface for displaying store details and handling interactions with the customer.
 */
public interface CustomerStoreDetailView {

    /**
     * Displays the details of a store in the UI.
     *
     * @param store The store object containing the details to be displayed.
     */
    void displayStore(Store store);

    /**
     * Displays an error message to the customer.
     *
     * @param msg The error message to be shown.
     */
    void showErrorMessage(String msg);

}

