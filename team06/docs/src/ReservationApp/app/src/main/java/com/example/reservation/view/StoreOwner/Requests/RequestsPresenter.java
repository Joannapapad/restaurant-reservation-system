package com.example.reservation.view.StoreOwner.Requests;

import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreDAO;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;

import java.util.List;

public class RequestsPresenter {
    private RequestsView view;
    private StoreDAO storeDAO;
    private CurrentUserDAO currentUserDAO;

    public RequestsPresenter(RequestsView view) {
        this.view = view;
        this.storeDAO = new StoreDAOMemory(); // Assuming StoreDAO has a method to find stores by owner
    }

    public void loadStores(int ownerId) {
        // Fetch stores linked to the current StoreOwner using the ownerId
        List<Store> stores = storeDAO.findAllByOwnerId(ownerId);

        // Pass the stores back to the view
        if (stores.isEmpty()) {
            view.showNoStoresMessage();
        } else {
            view.displayStores(stores);
        }
    }
}
