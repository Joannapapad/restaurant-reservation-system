package com.example.reservation.view.StoreOwner.AddStore;

import com.example.reservation.contact.StoreAddress;
import com.example.reservation.contact.ZipCode;
import com.example.reservation.dao.CurrentUserDAO;
import com.example.reservation.dao.StoreDAO;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;

import java.util.List;

public class AddStorePresenter {
    private AddStoreView view;
    private CurrentUserDAO currentUserDAO;
    private StoreDAO storeDAO;
    private List<String> cities;
    private List<String> categories;

    public AddStorePresenter(AddStoreView view, CurrentUserDAO currentUserDAO, StoreDAO storeDAO, List<String> cities, List<String> categories) {
        this.view = view;
        this.currentUserDAO = currentUserDAO;
        this.storeDAO = storeDAO;
        this.cities = cities;
        this.categories = categories;

        loadInitialData();
    }

    private void loadInitialData() {
        view.setCityOptions(cities);
        view.setCategoryOptions(categories);
    }

    public void saveStore() {
        String storeName = view.getStoreName();
        String street = view.getStreet();
        String number = view.getStreetNumber();
        String city = view.getCity();
        String country = view.getCountry();
        String postalCode = view.getPostalCode();
        String storeType = view.getStoreType();
        int capacity = view.getCapacity();
        int tableNumber = view.getTableNumber();
        String category = view.getStoreType();

        // Validate input fields
        if (storeName.isEmpty() || street.isEmpty() || number.isEmpty() || city.isEmpty() || postalCode.isEmpty() || country.isEmpty()) {
            view.showErrorMessage("Error", "All fields must be filled");
            return;
        }

        // Retrieve the current user (StoreOwner) from the CurrentUserDAO
        StoreOwner storeOwner = (StoreOwner) currentUserDAO.retrieve();
        if (storeOwner == null) {
            view.showErrorMessage("Error", "No logged-in user found");
            return;
        }

        // Create the ZipCode and StoreAddress
        ZipCode zipCode = new ZipCode(postalCode);
        StoreAddress address = new StoreAddress(street, number, zipCode, city, country);

        // Create and save the Store
        Store store = new Store(0, storeOwner.getOwnerId(), category,storeName, address, capacity, tableNumber);
        storeDAO.addStore(store);

        // Notify the view of the successful save
        view.showSuccessMessage("Store saved successfully for StoreOwner"  +  storeOwner.getOwnerId());
    }
}
