package com.example.reservation.view.Customer.RequestDetails;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Request;
import com.example.reservation.MemoryDao.RequestDAOMemory;
import com.example.reservation.view.Customer.RequestDetails.CustomerRequestDetailsPresenter;
import com.example.reservation.view.Customer.RequestDetails.CustomerRequestDetailsView;

/**
 * Activity class for displaying details of a specific customer request.
 * Implements {@link CustomerRequestDetailsView} to interact with the presenter and update the UI.
 */
public class CustomerRequestDetailsActivity extends AppCompatActivity implements CustomerRequestDetailsView {

    private CustomerRequestDetailsPresenter presenter;
    private TextView tvRequestDetails;
    private Button btnCancel;

    /**
     * Called when the activity is first created.
     * Sets up the UI components and initializes the presenter.
     *
     * @param savedInstanceState The saved instance state, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details_customer);

        // Initialize UI components
        btnCancel = findViewById(R.id.btn_cancel_request);

        // Get the request ID from the intent
        int requestId = getIntent().getIntExtra("REQUEST_ID", -1);

        if (requestId == -1) {
            Toast.makeText(this, "Error: No request ID provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize presenter
        presenter = new CustomerRequestDetailsPresenter(CustomerRequestDetailsActivity.this, CurrentUserDAOMemory.getInstance(),new RequestDAOMemory());

        // Load the request details
        presenter.loadRequestDetails(requestId);

        // Set the cancel button listener
        btnCancel.setOnClickListener(v -> presenter.cancelRequest());
    }

    /**
     * Displays the details of a specific request in the UI.
     *
     * @param request The {@link Request} object containing the request details.
     */
    @Override
    public void displayRequestDetails(Request request) {
        // Βεβαιωθείτε ότι όλα τα TextView έχουν αρχικοποιηθεί
        TextView tvRequestDate = findViewById(R.id.tv_request_date);
        TextView tvRequestTime = findViewById(R.id.tv_request_time);
        TextView tvScheduledDate = findViewById(R.id.tv_scheduled_date);
        TextView tvScheduledTime = findViewById(R.id.tv_scheduled_time);
        TextView tvStoreID = findViewById(R.id.tv_store_id);
        TextView tvNumOfPeople = findViewById(R.id.tv_num_of_people);
        TextView tvReservationStatus = findViewById(R.id.tv_reservation_status);
        TextView tvComment = findViewById(R.id.tv_comment);

        // Ενημερώστε τα στοιχεία UI με τα δεδομένα
        tvRequestDate.setText("Request Date: " + request.getRequestDate());
        tvRequestTime.setText("Request Time: " + request.getRequestTime());
        tvScheduledDate.setText("Scheduled Date: " + request.getScheduledDate());
        tvScheduledTime.setText("Scheduled Time: " + request.getScheduledTime());
        tvStoreID.setText("Store ID: " + request.getStoreID());
        tvNumOfPeople.setText("Number of People: " + request.getNumofpeople());
        tvComment.setText("Comment: " + request.getComment());
    }

    /**
     * Displays a success message and finishes the activity.
     *
     * @param message The message to be displayed.
     */
    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Displays an error message in a toast.
     *
     * @param message The error message to be displayed.
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
