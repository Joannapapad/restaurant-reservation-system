package com.example.reservation.view.StoreOwner.RequestReservationList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Request;
import com.example.reservation.domain.Reservation;
import com.example.reservation.view.StoreOwner.RequestDetails.RequestDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class RequestReservationActivity extends AppCompatActivity implements RequestReservationView {

    private RequestReservationPresenter presenter;
    private Button btnRequests, btnReservations;
    private int storeId; // Store ID for the current store
    private RequestAdapter requestAdapter;
    private ReservationAdapter reservationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_reservation_list);

        // Initialize buttons
        btnRequests = findViewById(R.id.btn_requests);
        btnReservations = findViewById(R.id.btn_reservations);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_requests_reservations);

        // Set a LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Adapters
        requestAdapter = new RequestAdapter();
        reservationAdapter = new ReservationAdapter();

        // Set the default adapter to Requests initially
        recyclerView.setAdapter(requestAdapter);
        requestAdapter.setOnRequestClickListener(request -> {
            if (request != null) {
                goToRequestDetailsActivity(request.getReservationID());
            } else {
                Toast.makeText(this, "Error: Unable to open request details.", Toast.LENGTH_SHORT).show();
            }
        });

        // Get the store ID from intent
        storeId = getIntent().getIntExtra("STORE_ID", -1);

        System.out.println("Debug: Received storeId = " + storeId);

        if (storeId == -1) {
            Toast.makeText(this, "Error: Store ID is missing.", Toast.LENGTH_SHORT).show();
            System.out.println("Debug: Store ID is missing, finishing activity.");
            finish();
            return;
        }

        // Initialize presenter
        presenter = new RequestReservationPresenter(this, new StoreDAOMemory(), storeId);
        System.out.println("Debug: Presenter initialized with storeId = " + storeId);

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

        // Initially load requests
        System.out.println("Debug: Loading initial requests");
        presenter.loadRequests();
    }

    @Override
    public void displayRequestList(List<Request> requests) {
        if (requests != null && !requests.isEmpty()) {
            requestAdapter.updateData(requests);
        } else {
            System.out.println("Debug: Request list is empty or null");
            requestAdapter.updateData(new ArrayList<>()); // Pass an empty list
        }
    }

    @Override
    public void displayReservationList(List<Reservation> reservations) {
        if (reservations != null && !reservations.isEmpty()) {
            reservationAdapter.updateData(reservations);
        } else {
            System.out.println("Debug: Reservation list is empty or null");
            reservationAdapter.updateData(new ArrayList<>()); // Pass an empty list
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        System.out.println("Debug: Error message displayed - " + message);
    }

    private void goToRequestDetailsActivity(int requestId) {
        Intent intent = new Intent(this, RequestDetailsActivity.class);
        intent.putExtra("REQUEST_ID", requestId); // Pass the request ID to the details activity
        startActivity(intent);
    }
}
