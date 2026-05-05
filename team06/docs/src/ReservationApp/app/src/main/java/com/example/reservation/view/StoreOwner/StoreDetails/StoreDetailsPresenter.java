package com.example.reservation.view.StoreOwner.StoreDetails;

import com.example.reservation.dao.StoreDAO;
import com.example.reservation.domain.Store;

/**
 * Presenter for StoreDetailsActivity.
 */
public class StoreDetailsPresenter {
    private final StoreDetailsView view;
    private final StoreDAO storeDAO;

    /**
     * Constructs a StoreDetailsPresenter.
     *
     * @param view     The view interface for displaying store details and handling UI interactions.
     * @param storeDAO The data access object for retrieving store information.
     */
    public StoreDetailsPresenter(StoreDetailsView view, StoreDAO storeDAO) {
        this.view = view;
        this.storeDAO = storeDAO;
    }

    /**
     * Loads the details of a store based on the provided store ID.
     * Fetches the store from the data source and instructs the view to display the details
     * if the store is found. If the store is not found, it displays an error message.
     *
     * @param storeId The unique identifier of the store to be loaded.
     */
    public void loadStoreDetails(int storeId) {
        Store store = storeDAO.find(storeId);

        if (store != null) {
            view.displayStoreDetails(store);
        } else {
            view.showErrorMessage("Store not found");
        }
    }
}
