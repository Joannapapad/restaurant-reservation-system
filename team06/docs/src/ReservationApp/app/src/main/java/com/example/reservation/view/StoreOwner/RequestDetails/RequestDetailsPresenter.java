package com.example.reservation.view.StoreOwner.RequestDetails;

import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.MemoryDao.RequestDAOMemory;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Store;

public class RequestDetailsPresenter {

    private final RequestDetailsView view;
    private final RequestDAOMemory requestDAO;
    private final StoreDAOMemory storeDAO; // Add DAO to fetch Store
    private final CustomerDAOMemory customerDAO; // Add DAO to fetch Store

    private Request currentRequest;
    private Store currentStore;
    private Customer currentCustomer;


    public RequestDetailsPresenter(RequestDetailsView view, RequestDAOMemory requestDAO, StoreDAOMemory storeDAO,CustomerDAOMemory customerDAO) {
        this.view = view;
        this.requestDAO = requestDAO;
        this.storeDAO = storeDAO;
        this.customerDAO = customerDAO;
    }

    public void loadRequestDetails(int requestId) {
        currentRequest = requestDAO.find(requestId);

        if (currentRequest == null) {
            view.showErrorMessage("Error: Request not found.");
        } else {
            // Load the store associated with the request
            currentStore = storeDAO.find(currentRequest.getStoreID());

            if (currentStore == null) {
                view.showErrorMessage("Error: Store associated with the request not found.");
            } else {
                view.displayRequestDetails(currentRequest);
            }
        }
    }

    public void acceptRequest() {
        if (currentRequest != null && currentStore != null) {
            // Create a Customer object (assuming it can be retrieved from the current request)
            Customer customer = customerDAO.findbyid(currentRequest.getCustomerId());

            if (currentStore.manageRequest(customer, currentRequest, true)) {
                view.showSuccessMessage("Request accepted successfully.");
            } else {
                view.showErrorMessage("Error: Store does not have enough capacity to accept the request.");
            }
        } else {
            view.showErrorMessage("Error: Unable to accept request.");
        }
    }

    public void denyRequest() {
        if (currentRequest != null && currentStore != null) {
            // Reject the request using manageRequest
            currentStore.manageRequest(null, currentRequest, false);
            view.showSuccessMessage("Request denied successfully.");
        } else {
            view.showErrorMessage("Error: Unable to deny request.");
        }
    }
}
