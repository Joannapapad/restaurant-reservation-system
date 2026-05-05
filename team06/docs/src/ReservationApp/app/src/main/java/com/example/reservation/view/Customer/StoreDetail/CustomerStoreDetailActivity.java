package com.example.reservation.view.Customer.StoreDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Store;

/**
 * Activity to display store details and allow the customer to request a reservation at the store.
 * Implements the CustomerStoreDetailView interface to handle displaying data and errors.
 */
public class CustomerStoreDetailActivity extends AppCompatActivity implements CustomerStoreDetailView {

    private CustomerStoreDetailPresenter presenter;
    private TextView txtStoreNameValuec;
    private TextView txtStoreAddressValuec;
    private TextView txtStoreCategoryValuec;
    private TextView txtStoreCapacityValuec;
    private TextView txtStoreTypeValuec;
    private ImageButton btnBackc;
    private Button btnRequest;  // Button to trigger the request popup

    /**
     * Initializes the activity, sets up views, and binds the necessary presenter and listeners.
     *
     * @param savedInstanceState The saved state of the activity, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_store_detail);

        // Initialize views
        txtStoreNameValuec = findViewById(R.id.txtStoreNameValuec);
        txtStoreAddressValuec = findViewById(R.id.txtStoreAddressValuec);
        txtStoreCategoryValuec = findViewById(R.id.txtStoreCategoryValuec);
        txtStoreCapacityValuec = findViewById(R.id.txtStoreCapacityValuec);
        txtStoreTypeValuec = findViewById(R.id.txtStoreTypeValuec);
        btnBackc = findViewById(R.id.btnBackc);
        btnRequest = findViewById(R.id.btnRequest);  // Initialize the request button

        int storeId = getIntent().getIntExtra("STORE_ID", -1);
        if (storeId == -1) {
            Toast.makeText(this, "Error: Store ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnBackc.setOnClickListener(v -> finish());

        presenter = new CustomerStoreDetailPresenter(this, new StoreDAOMemory(), CurrentUserDAOMemory.getInstance());
        presenter.loadDetails(storeId);

        // Setup request button click listener to show the popup
        btnRequest.setOnClickListener(v -> showRequestPopup(storeId));
    }

    /**
     * Displays a popup for the customer to submit a reservation request for the selected store.
     *
     * @param storeId The ID of the store for which the reservation request is being made.
     */
    private void showRequestPopup(int storeId) {
        // Create the popup view
        View popupView = getLayoutInflater().inflate(R.layout.popup_request, null);
        final EditText edtNumOfPeople = popupView.findViewById(R.id.edtNumOfPeople);
        final EditText edtComment = popupView.findViewById(R.id.edtComment);
        final DatePicker datePicker = popupView.findViewById(R.id.datePicker);
        final TimePicker timePicker = popupView.findViewById(R.id.timePicker);
        Button btnSubmitRequest = popupView.findViewById(R.id.btnSubmitRequest);

        // Create and show the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);
        AlertDialog dialog = builder.create();
        dialog.show();

        btnSubmitRequest.setOnClickListener(v -> {
            // Get data from the popup fields
            int numOfPeople = Integer.parseInt(edtNumOfPeople.getText().toString().trim());
            String comment = edtComment.getText().toString().trim();
            int scheduledDay = datePicker.getDayOfMonth();
            int scheduledMonth = datePicker.getMonth();
            int scheduledYear = datePicker.getYear();
            int scheduledHour = timePicker.getCurrentHour();
            int scheduledMinute = timePicker.getCurrentMinute();

            // Create and send the request via presenter
            presenter.createRequest(storeId, numOfPeople, comment, scheduledYear, scheduledMonth, scheduledDay, scheduledHour, scheduledMinute);

            // Dismiss the dialog
            dialog.dismiss();
        });
    }

    /**
     * Displays the store details in the activity views.
     *
     * @param store The store data to display.
     */
    @Override
    public void displayStore(Store store) {
        txtStoreNameValuec.setText(store.getName());
        txtStoreAddressValuec.setText(store.getAddress().toString());
        txtStoreCategoryValuec.setText(store.getCategory());
        txtStoreCapacityValuec.setText(String.valueOf(store.getCapacity()));
    }

    /**
     * Displays an error message as a toast.
     *
     * @param msg The error message to display.
     */
    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
