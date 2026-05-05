package com.example.reservation.view.StoreOwner.StoreDetails;

import com.example.reservation.domain.Store;

/**
 * Interface for the Store Details View.
 * Provides methods for displaying store details and showing error messages in the UI.
 */
public interface StoreDetailsView {

    /**
     * Displays the details of a store.
     * The store information is passed as a parameter to be shown in the user interface.
     *
     * @param store The {@link Store} object containing details such as name, address, category, and capacity.
     */
    void displayStoreDetails(Store store);

    /**
     * Shows an error message in the user interface.
     * This method is used to display any errors encountered during the operation.
     *
     * @param message A {@link String} containing the error message to be displayed.
     */
    void showErrorMessage(String message);
}
