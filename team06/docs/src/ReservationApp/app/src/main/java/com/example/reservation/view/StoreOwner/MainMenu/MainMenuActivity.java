package com.example.reservation.view.StoreOwner.MainMenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Store;
import com.example.reservation.domain.StoreOwner;
import com.example.reservation.view.StoreOwner.AddStore.AddStoreActivity;
import com.example.reservation.view.StoreOwner.Requests.RequestsActivity;
import com.example.reservation.view.StoreOwner.StoreDetails.StoreDetailsActivity;
import com.example.reservation.view.StoreOwner.Profile.StoreOwnerProfileActivity;

import java.util.List;

/**
 * Main menu activity for the store owner, where they can view and manage their stores.
 * This activity implements the MainMenuView and StoreSelectionListener interfaces.
 */
public class MainMenuActivity extends AppCompatActivity implements MainMenuView, StoreAdapter.StoreSelectionListener {

    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;
    private MainMenuPresenter presenter;
    private StoreOwner storeOwner; // StoreOwner will be used directly for fetching the current owner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_storeowner);

        // Initialize presenter
        presenter = new MainMenuPresenter(this);

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
        ImageButton plusButton = findViewById(R.id.plusButton);

        // Set click listeners for each button
        bottomProfile.setOnClickListener(v -> handleProfileClick());
        bottomHome.setOnClickListener(v -> handleHomeClick());
        bottomRequests.setOnClickListener(v -> handleRequestsClick());
        plusButton.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, AddStoreActivity.class)));
    }

    /**
     * Handles the click event for the profile button.
     * Navigates to the StoreOwnerProfileActivity.
     */
    private void handleProfileClick() {
        startActivity(new Intent(MainMenuActivity.this, StoreOwnerProfileActivity.class));
    }

    /**
     * Handles the click event for the home button.
     * Reloads the MainMenuActivity.
     */
    private void handleHomeClick() {
        startActivity(new Intent(MainMenuActivity.this, MainMenuActivity.class));
    }

    /**
     * Handles the click event for the requests button.
     * Navigates to the RequestsActivity.
     */
    private void handleRequestsClick() {
        startActivity(new Intent(MainMenuActivity.this, RequestsActivity.class));
    }

    /**
     * Displays the list of stores in the RecyclerView.
     * Called by the presenter after stores have been fetched.
     *
     * @param stores A list of stores to display.
     */
    @Override
    public void displayStores(List<Store> stores) {
        // Update RecyclerView with the fetched stores and pass the listener (this activity)
        storeAdapter = new StoreAdapter(stores, this);
        recyclerView.setAdapter(storeAdapter);
    }

    /**
     * Displays a message indicating that no stores are available.
     */
    @Override
    public void showNoStoresMessage() {
        // Show a message when no stores are available
        Toast.makeText(this, "No stores available", Toast.LENGTH_SHORT).show();
    }

    /**
     * Handles store selection from the RecyclerView.
     * Navigates to the StoreDetailsActivity to view details of the selected store.
     *
     * @param store The store that was selected.
     */
    @Override
    public void onStoreSelected(Store store) {
        // Handle the store selection and navigate to StoreDetailsActivity
        Intent intent = new Intent(MainMenuActivity.this, StoreDetailsActivity.class);
        intent.putExtra("STORE_ID", store.getStoreId());
        startActivity(intent);
    }
}
