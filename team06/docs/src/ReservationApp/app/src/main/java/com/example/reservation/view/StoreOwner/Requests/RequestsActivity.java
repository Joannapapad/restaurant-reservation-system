package com.example.reservation.view.StoreOwner.Requests;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;
import com.example.reservation.view.StoreOwner.MainMenu.MainMenuActivity;
import com.example.reservation.view.StoreOwner.MainMenu.StoreAdapter;
import com.example.reservation.view.StoreOwner.RequestReservationList.RequestReservationActivity;
import com.example.reservation.view.StoreOwner.Profile.StoreOwnerProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestsActivity extends AppCompatActivity implements RequestsView, StoreAdapter.StoreSelectionListener {

    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;
    private RequestsPresenter presenter;
    private StoreOwner storeOwner; // StoreOwner will be used directly for fetching the current owner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeowner_requests);
        // Initialize presenter
        presenter = new RequestsPresenter(this);

        // Use the singleton to get the current User (StoreOwner)
        CurrentUserDAOMemory currentUserDAO = CurrentUserDAOMemory.getInstance();
        storeOwner = (StoreOwner) currentUserDAO.retrieve();

        // Handle case where storeOwner might not be set properly
        if (storeOwner == null) {
            Toast.makeText(this, "Error: No logged-in user found", Toast.LENGTH_SHORT).show();
            return;
        }
        // Set up RecyclerView
        recyclerView = findViewById(R.id.recycler_view_stores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load and display stores based on the current StoreOwner's ID
        presenter.loadStores(storeOwner.getOwnerId());

        // Find the buttons in the bottom menu
        ImageButton bottomProfile = findViewById(R.id.bottom_profile);
        ImageButton bottomHome = findViewById(R.id.bottom_home);
        ImageButton bottomRequests = findViewById(R.id.bottom_requests);

        // Set click listeners for each button
        bottomProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Profile action
                handleProfileClick();
            }
        });

        bottomHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Home action
                handleHomeClick();
            }
        });

        bottomRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Requests action
                handleRequestsClick();
            }
        });
    }

    private void handleProfileClick() {
        Intent intent = new Intent(this, StoreOwnerProfileActivity.class);
        startActivity(intent);    }

    private void handleHomeClick() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);    }

    private void handleRequestsClick() {
        Intent intent = new Intent(this, RequestsActivity.class);
        startActivity(intent);    }

    @Override
    public void displayStores(List<Store> stores) {
        // Update RecyclerView with the fetched stores and pass the listener (this activity)
        storeAdapter = new StoreAdapter(stores, this);
        recyclerView.setAdapter(storeAdapter);
    }

    @Override
    public void showNoStoresMessage() {
        // Show a message when no stores are available
        Toast.makeText(this, "No stores available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStoreSelected(Store store) {
        // Handle the store selection and navigate to StoreDetailsActivity
        Intent intent = new Intent(RequestsActivity.this, RequestReservationActivity.class);
        intent.putExtra("STORE_ID", store.getStoreId());
        startActivity(intent);
    }
}
