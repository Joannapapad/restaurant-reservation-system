package com.example.reservation.view.StoreOwner.MainMenu;

import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreDAO;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;

import java.util.List;

public class MainMenuPresenter {
    private MainMenuView view;
    private StoreDAO storeDAO;
    private CurrentUserDAO currentUserDAO;

    public MainMenuPresenter(MainMenuView view) {
        this.view = view;
        this.storeDAO = new StoreDAOMemory(); // Assuming StoreDAO has a method to find stores by owner
    }

    /**
     * Loads stores based on the owner's ID.
     * @param ownerId The ID of the store owner whose stores are being fetched.
     */
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
