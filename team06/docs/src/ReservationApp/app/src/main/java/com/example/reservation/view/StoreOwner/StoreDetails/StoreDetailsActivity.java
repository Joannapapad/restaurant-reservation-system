package com.example.reservation.view.StoreOwner.StoreDetails;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.R;
import com.example.reservation.domain.Store;
import com.example.reservation.MemoryDao.StoreDAOMemory;

/**
 * Displays details of the selected store.
 */
public class StoreDetailsActivity extends AppCompatActivity implements StoreDetailsView {
    private StoreDetailsPresenter presenter;

    private TextView txtStoreNameValue;
    private TextView txtStoreAddressValue;
    private TextView txtStoreCategoryValue;
    private TextView txtStoreCapacityValue;
    private ImageButton btnBack;

    /**
     * Called when the activity is first created.
     * Initializes the views and loads the store details based on the store ID passed via Intent.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        // Initialize views
        txtStoreNameValue = findViewById(R.id.txtStoreNameValue);
        txtStoreAddressValue = findViewById(R.id.txtStoreAddressValue);
        txtStoreCategoryValue = findViewById(R.id.txtStoreCategoryValue);
        txtStoreCapacityValue = findViewById(R.id.txtStoreCapacityValue);
        btnBack = (android.widget.ImageButton)findViewById(R.id.btnBack);

        // Get the store ID from the Intent
        int storeId = getIntent().getIntExtra("STORE_ID", -1);

        if (storeId == -1) {
            Toast.makeText(this, "Error: Store ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        btnBack.setOnClickListener(v -> finish());

        // Initialize the presenter
        presenter = new StoreDetailsPresenter(this, new StoreDAOMemory());
        presenter.loadStoreDetails(storeId);
    }

    /**
     * Displays the store details in the corresponding views.
     *
     * @param store The Store object containing the details to be displayed.
     */
    @Override
    public void displayStoreDetails(Store store) {
        txtStoreNameValue.setText(store.getName());
        txtStoreAddressValue.setText(store.getAddress().toString());
        txtStoreCategoryValue.setText(store.getCategory());
        txtStoreCapacityValue.setText(String.valueOf(store.getCapacity()));
    }

    /**
     * Displays an error message as a Toast.
     *
     * @param message The error message to display.
     */
    @Override
    public void showErrorMessage(String message) {
        // Show error message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
