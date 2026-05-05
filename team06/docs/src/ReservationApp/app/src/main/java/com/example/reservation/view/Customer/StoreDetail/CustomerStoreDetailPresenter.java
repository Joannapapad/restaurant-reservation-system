package com.example.reservation.view.Customer.StoreDetail;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.dao.StoreDAO;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.ReservationStatus;
import com.example.reservation.domain.Store;
import com.example.reservation.util.SimpleCalendar;

/**
 * Presenter class responsible for handling store details and managing reservation requests
 * in the Customer Store Detail view.
 */
public class CustomerStoreDetailPresenter {
    private final CustomerStoreDetailView view;
    private final StoreDAO storeDAO;
    private final Customer currentcustomer;  // Assuming you have a CustomerDAO

    /**
     * Constructor to initialize the presenter with the view, StoreDAO, and the current customer.
     *
     * @param view The view that will display the store details and handle interactions.
     * @param storeDAO The DAO responsible for fetching store data.
     * @param currentUserDAO The DAO responsible for fetching the current logged-in user (customer).
     */
    public CustomerStoreDetailPresenter(CustomerStoreDetailView view, StoreDAO storeDAO, CurrentUserDAOMemory currentUserDAO) {
        this.view = view;
        this.storeDAO = storeDAO;
        this.currentcustomer = (Customer) currentUserDAO.retrieve(); // For example, assuming you're using an in-memory DAO
    }

    /**
     * Loads the details of a store by its ID and displays them in the view.
     * If the store is not found, an error message is shown.
     *
     * @param id The ID of the store to retrieve and display details for.
     */
    public void loadDetails(int id) {
        Store store = storeDAO.find(id);
        if (store != null) {
            view.displayStore(store);
        } else {
            view.showErrorMessage("Store not found");
        }
    }

    /**
     * Creates a reservation request for the customer at the specified store.
     * If the store exists, a new request is created and added to the customer's list of requests.
     * The request is scheduled with the specified date and time.
     *
     * @param storeId The ID of the store where the reservation request is being made.
     * @param numOfPeople The number of people for the reservation.
     * @param comment Additional comments for the reservation.
     * @param year The year for the reservation date.
     * @param month The month for the reservation date.
     * @param day The day for the reservation date.
     * @param hour The hour for the reservation time.
     * @param minute The minute for the reservation time.
     */
    public void createRequest(int storeId, int numOfPeople, String comment, int year, int month, int day, int hour, int minute) {
        // Find the store
        Store store = storeDAO.find(storeId);

        if (store != null) {
            // Get the current customer from your session or user context

            // Create the request
            Request newRequest = new Request(
                    generateRequestId(store), // Generate request ID
                    store.getStoreId(),
                    currentcustomer.getCustomerId(),
                    numOfPeople,
                    ReservationStatus.PENDING,
                    comment
            );

            // Create the scheduled date and time for the request
            newRequest.setScheduledDate(new SimpleCalendar(year, month, day));
            newRequest.setScheduledTime(new SimpleCalendar(year, month, day, hour, minute));

            // Add the request to the customer's list
            currentcustomer.addRequest(newRequest);

            view.showErrorMessage("Request successfully created!");
        } else {
            view.showErrorMessage("Store not found");
        }
    }

    /**
     * Generates a unique ID for a reservation request based on the store ID and a random number.
     *
     * @param store The store for which the request is being generated.
     * @return A unique request ID.
     */
    private int generateRequestId(Store store) {
        // Logic to generate a unique request ID
        return store.getStoreId() * 1000 + (int)(Math.random() * 1000);
    }
}
