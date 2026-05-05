package com.example.reservation.view.Customer.RequestReservation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;
import com.example.reservation.view.Customer.RequestDetails.CustomerRequestDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomerRequestsActivity is responsible for displaying and managing the list of requests and reservations
 * for a customer. It interacts with the CustomerRequestReservationPresenter to load data (requests or reservations),
 * and it switches between two views (requests and reservations) based on user interaction.
 * <p>
 * The activity shows either a list of customer requests or reservations using a RecyclerView, and allows the user
 * to view details for specific requests.
 * </p>
 */
public class CustomerRequestsActivity extends AppCompatActivity implements CustomerRequestReservationView {

    private CustomerRequestReservationPresenter presenter;
    private Button btnRequests, btnReservations;
    private int customerId; // Store ID for the current store
    private CustomerRequestAdapter requestAdapter;
    private CustomerReservationAdapter reservationAdapter;

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_request_reservation_list);

        // Initialize buttons
        btnRequests = findViewById(R.id.btn_requests);
        btnReservations = findViewById(R.id.btn_reservations);
        btnBack = findViewById(R.id.btnBack);
        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_requests_reservations);


        // Set a LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Adapters
        requestAdapter = new CustomerRequestAdapter();
        reservationAdapter = new CustomerReservationAdapter();
        CurrentUserDAOMemory currentUserDAO = CurrentUserDAOMemory.getInstance();
        customerId = ((Customer) currentUserDAO.retrieve()).getCustomerId();

        // Set the default adapter to Requests initially
        recyclerView.setAdapter(requestAdapter);
        requestAdapter.setOnRequestClickListener(request -> {
            if (request != null) {
                goToRequestDetailsActivity(request.getReservationID());
            } else {
                Toast.makeText(this, "Error: Unable to open request details.", Toast.LENGTH_SHORT).show();
            }
        });


        System.out.println("Debug: Received storeId = " + customerId);

        if (customerId == -1) {
            Toast.makeText(this, "Error: Store ID is missing.", Toast.LENGTH_SHORT).show();
            System.out.println("Debug: Store ID is missing, finishing activity.");
            finish();
            return;
        }

        // Initialize presenter
        presenter = new CustomerRequestReservationPresenter(this, new CustomerDAOMemory(), customerId);
        System.out.println("Debug: Presenter initialized with storeId = " + customerId);

        // Set button click listeners
        btnRequests.setOnClickListener(view -> {
            System.out.println("Debug: Requests button clicked");
            presenter.loadRequests();

            recyclerView.setAdapter(requestAdapter); // Switch to the requests adapter
            System.out.println("Debug: Adapter switched to RequestAdapter, size = " + requestAdapter.getItemCount());

            btnRequests.setEnabled(false);
            btnReservations.setEnabled(true);
        });

        btnReservations.setOnClickListener(view -> {
            System.out.println("Debug: Reservations button clicked");
            presenter.loadReservations();

            recyclerView.setAdapter(reservationAdapter); // Switch to the reservations adapter
            System.out.println("Debug: Adapter switched to ReservationAdapter, size = " + reservationAdapter.getItemCount());

            btnRequests.setEnabled(true);
            btnReservations.setEnabled(false);
        });

        btnBack.setOnClickListener(view -> {
            finish();
        });
        // Initially load requests
        System.out.println("Debug: Loading initial requests");
        presenter.loadRequests();
    }

    /**
     * Displays the list of customer requests in the view.
     * This method is called by the presenter to update the UI when requests data is retrieved.
     *
     * @param requests The list of customer requests to display.
     */
    @Override
    public void displayRequestList(List<Request> requests) {
        if (requests != null && !requests.isEmpty()) {
            requestAdapter.updateData(requests);  // Update the adapter with new data
        } else {
            requestAdapter.updateData(new ArrayList<>()); // Pass an empty list if no requests
        }
    }

    /**
     * Displays the list of customer reservations in the view.
     * This method is called by the presenter to update the UI when reservation data is retrieved.
     *
     * @param reservations The list of customer reservations to display.
     */
    @Override
    public void displayReservationList(List<Reservation> reservations) {
        if (reservations != null && !reservations.isEmpty()) {
            reservationAdapter.updateData(reservations);
        } else {
            System.out.println("Debug: Reservation list is empty or null");
            reservationAdapter.updateData(new ArrayList<>()); // Pass an empty list
        }
    }

    /**
     * Shows an error message as a Toast to the user.
     * This method is used to inform the user of any issues while loading data.
     *
     * @param message The error message to display.
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        System.out.println("Debug: Error message displayed - " + message);
    }

    /**
     * Navigates to the request details activity when a request is clicked.
     *
     * @param requestId The ID of the selected request.
     */
    private void goToRequestDetailsActivity(int requestId) {
        Intent intent = new Intent(this, CustomerRequestDetailsActivity.class);
        intent.putExtra("REQUEST_ID", requestId); // Pass the request ID to the details activity
        startActivity(intent);
    }
}
