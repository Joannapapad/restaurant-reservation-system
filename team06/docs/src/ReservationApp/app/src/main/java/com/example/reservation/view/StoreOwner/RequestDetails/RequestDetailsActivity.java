package com.example.reservation.view.StoreOwner.RequestDetails;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CustomerDAOMemory;
import com.example.reservation.MemoryDao.RequestDAOMemory;
import com.example.reservation.MemoryDao.StoreDAOMemory;
import com.example.reservation.R;
import com.example.reservation.domain.Request;


public class RequestDetailsActivity extends AppCompatActivity implements RequestDetailsView {

    private RequestDetailsPresenter presenter;
    private TextView tvRequestDetails;

    private Button btnAccept, btnDeny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);

        // Initialize UI components
        tvRequestDetails = findViewById(R.id.tv_request_details);
        btnAccept = findViewById(R.id.btn_accept);
        btnDeny = findViewById(R.id.btn_deny);

        // Get the request ID from the intent
        int requestId = getIntent().getIntExtra("REQUEST_ID", -1);

        if (requestId == -1) {
            Toast.makeText(this, "Error: No request ID provided", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize presenter
        presenter = new RequestDetailsPresenter(RequestDetailsActivity.this, new RequestDAOMemory(), new StoreDAOMemory(),new CustomerDAOMemory());

        // Load the request details
        presenter.loadRequestDetails(requestId);

        // Set button listeners
        btnAccept.setOnClickListener(v -> presenter.acceptRequest());
        btnDeny.setOnClickListener(v -> presenter.denyRequest());
    }

    @Override
    public void displayRequestDetails(Request request) {
        // Display the request details
        tvRequestDetails.setText(
                "Request ID: " + request.getReservationID() + "\n" +
                        "Store ID: " + request.getStoreID() + "\n" +
                        "Customer ID: " + request.getCustomerId() + "\n" +
                        "Number of People: " + request.getNumofpeople() + "\n" +
                        "Comment: " + request.getComment()
        );
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
