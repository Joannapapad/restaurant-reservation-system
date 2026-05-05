package com.example.reservation.view.StoreOwner.RequestReservationList;

import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Store;

import java.util.ArrayList;
import java.util.List;

public class RequestReservationPresenter {

    private final RequestReservationView view;
    private final int storeId;

    private final StoreDAOMemory storedao;
    private final Store store;

    public RequestReservationPresenter(RequestReservationView view, StoreDAOMemory storedao, int storeId) {
        this.view = view;
        this.storeId = storeId;
        this.storedao = storedao;
        this.store = storedao.find(storeId);
    }

    public void loadRequests() {
        System.out.println("Debug: loadRequests called");

        // Fetch requests
        List<Request> requests = store.getRequests();
        System.out.println("Debug: Total requests fetched = " + (requests != null ? requests.size() : "null"));

        if (requests == null || requests.isEmpty()) {
            System.out.println("Debug: No requests available, clearing the adapter");
            view.displayRequestList(new ArrayList<>()); // Clear the RecyclerView
            view.showErrorMessage("No requests available for this store.");
            return;
        }

        // Optional filtering logic
        List<Request> filteredRequests = new ArrayList<>(requests);

        System.out.println("Debug: Filtered requests size = " + filteredRequests.size());
        view.displayRequestList(filteredRequests); // Send filtered data to the view
    }

    public void loadReservations() {
        System.out.println("Debug: loadReservations called");

        List<Reservation> reservations = store.getReservation();
        System.out.println("Debug: Total reservations fetched = " + (reservations != null ? reservations.size() : "null"));

        if (reservations == null || reservations.isEmpty()) {
            System.out.println("Debug: No reservations available, clearing the adapter");
            view.displayReservationList(new ArrayList<>()); // Clear the RecyclerView
            view.showErrorMessage("No reservations available for this store.");
            return;
        }

        // Optional filtering logic
        List<Reservation> filteredReservations = new ArrayList<>(reservations);

        System.out.println("Debug: Filtered reservations size = " + filteredReservations.size());
        view.displayReservationList(filteredReservations); // Send filtered data to the view
    }
}
