package com.example.reservation.MemoryDao;

import com.example.reservation.dao.*;
import com.example.reservation.domain.*;

import java.util.List;

public class MemoryInitializer extends Initializer {


    /**
     * Clears all data from the in-memory DAOs.
     */
    @Override
    protected void eraseData() {
        // Clear all data from DAOs
        List<Customer> allCustomers = getCustomerDAO().findAll();
        for (Customer customer : allCustomers) {
            getCustomerDAO().delete(customer);
        }

        List<Request> allRequests = getRequestDAO().findAll();
        for (Request request : allRequests) {
            getRequestDAO().delete(request);
        }

        List<Store> allStores = getStoreDAO().findAll();
        for (Store store : allStores) {
            getStoreDAO().delete(store);
        }

        List<Reservation> allReservations = getReservationDAO().findAll();
        for (Reservation reservation : allReservations) {
            getReservationDAO().delete(reservation);
        }

        List<String> allCities = getCityDAO().findAll();
        for (String city : allCities) {
            getCityDAO().delete(city);
        }

        List<String> allCategories = getCategoryDAO().findAll();
        for (String category : allCategories) {
            getCategoryDAO().delete(category);
        }

        List<StoreOwner> allStoreOwners = getStoreOwnerDAO().findAll();
        for (StoreOwner category : allStoreOwners) {
            getStoreOwnerDAO().delete(category);
        }


        User currentUser = getCurrentUserDAO().retrieve();
        if (currentUser != null) {
            getCurrentUserDAO().delete(currentUser);
        }
    }

    /**
     * Provides the in-memory implementation of the CustomerDAO.
     *
     * @return A new instance of CustomerDAOMemory.
     */

    @Override
    public CustomerDAO getCustomerDAO() {
        return new CustomerDAOMemory();
    }

    /**
     * Provides the in-memory implementation of the StoreDAO.
     *
     * @return A new instance of StoreDAOMemory.
     */
    @Override
    public StoreDAO getStoreDAO() {
        return new StoreDAOMemory();
    }

    /**
     * Provides the in-memory implementation of the RequestDAO.
     *
     * @return A new instance of RequestDAOMemory.
     */

    @Override
    public RequestDAO getRequestDAO() {
        return new RequestDAOMemory();
    }

    /**
     * Provides the in-memory implementation of the CityDAO.
     *
     * @return A new instance of CityDAOMemory.
     */

    protected CityDAO getCityDAO() {
        return new CityDAOMemory();
    }

    /**
     * Provides the in-memory implementation of the CategoryDAO.
     *
     * @return A new instance of CategoryDAOMemory.
     */

    protected CategoryDAO getCategoryDAO() {
        return new CategoryDAOMemory();
    }

    /**
     * Provides the in-memory implementation of the ReservationDAO.
     *
     * @return A new instance of ReservationDAOMemory.
     */

    @Override
    public ReservationDAO getReservationDAO() {
        return new ReservationDAOMemory();
    }

    /**
     * Provides the in-memory implementation of the StoreOwnerDAO.
     *
     * @return A new instance of StoreOwnerDAOMemory.
     */

    @Override
    public StoreOwnerDAO getStoreOwnerDAO() {
        return new StoreOwnerDAOMemory();
    }

    /**
     * Provides the singleton instance of the CurrentUserDAO.
     *
     * @return The singleton instance of CurrentUserDAOMemory.
     */
    public CurrentUserDAO getCurrentUserDAO() {
        // Use the singleton instance of CurrentUserDAOMemory
        return (CurrentUserDAO) CurrentUserDAOMemory.getInstance();
    }
}
