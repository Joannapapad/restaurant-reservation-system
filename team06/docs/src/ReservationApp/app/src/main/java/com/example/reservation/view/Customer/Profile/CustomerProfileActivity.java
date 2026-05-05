package com.example.reservation.view.Customer.Profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;
import com.example.reservation.view.MainLogin.MainLoginActivity;
import com.example.reservation.view.Customer.Edit.CustomerEditActivity;
import com.example.reservation.view.Customer.MainMenu.MainMenuActivity;
import com.example.reservation.view.Customer.RequestReservation.CustomerRequestsActivity;

/**
 * This activity represents the customer's profile page.
 * It displays the customer's information and provides options to edit or delete the profile.
 * Implements the {@link CustomerProfileView} interface for communication with the presenter.
 */
public class CustomerProfileActivity extends AppCompatActivity implements CustomerProfileView {

    private CustomerProfilePresenter presenter;

    /**
     * Called when the activity is created.
     * Initializes the layout and sets up UI components and event listeners.
     *
     * @param savedInstanceState The saved state of the activity (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        presenter = new CustomerProfilePresenter(this, CurrentUserDAOMemory.getInstance());

        // Find the buttons in the bottom menu
        ImageButton bottomProfile = findViewById(R.id.bottom_profile);
        ImageButton bottomHome = findViewById(R.id.bottom_home);
        ImageButton bottomRequests = findViewById(R.id.bottom_requests);

        // Set click listeners for each button
        bottomProfile.setOnClickListener(v -> handleProfileClick());
        bottomHome.setOnClickListener(v -> handleHomeClick());
        bottomRequests.setOnClickListener(v -> handleRequestsClick());

        findViewById(R.id.btnEditC1).setOnClickListener(v -> presenter.onStartEditButtonClick());
        findViewById(R.id.btnDeleteC).setOnClickListener(v -> presenter.onStartDeleteButtonClick());
    }

    /**
     * Retrieves the customer's name passed in the intent.
     *
     * @return The customer's name or null if not provided.
     */
    @Override
    public String getName() {
        return this.getIntent().hasExtra("customer_name")
                ? this.getIntent().getStringExtra("customer_name")
                : null;
    }

    /**
     * Sets the customer's name in the corresponding TextView.
     *
     * @param value The name to set.
     */
    @Override
    public void setName(String value) {
        ((TextView) findViewById(R.id.txtCustomerProfileNameBlank)).setText(value);
    }

    /**
     * Sets the customer's email in the corresponding TextView.
     *
     * @param value The email to set.
     */
    @Override
    public void setEmail(String value) {
        ((TextView) findViewById(R.id.txtCustomerProfileEmailBlank)).setText(value);
    }

    /**
     * Sets the customer's phone number in the corresponding TextView.
     *
     * @param value The phone number to set.
     */
    @Override
    public void setPhone(String value) {
        ((TextView) findViewById(R.id.txtCustomerProfileNumberBlank)).setText(value);
    }

    /**
     * Sets the customer's password in the corresponding TextView.
     *
     * @param value The password to set.
     */
    @Override
    public void setPassword(String value) {
        ((TextView) findViewById(R.id.txtCustomerProfilePasswordBlank)).setText(value);
    }

    /**
     * Sets the customer's ID in the corresponding TextView.
     *
     * @param value The ID to set.
     */
    @Override
    public void setId(String value) {
        ((TextView) findViewById(R.id.txtCustomerIdBlank)).setText(value);
    }

    /**
     * Starts the CustomerEditActivity with the provided customer details for editing.
     *
     * @param customerId The ID of the customer to edit.
     * @param name       The name of the customer.
     * @param email      The email of the customer.
     * @param phone      The phone number of the customer.
     * @param password   The password of the customer.
     */
    @Override
    public void startEditActivity(int customerId, String name, String email, String phone, String password) {
        Intent intent = new Intent(this, CustomerEditActivity.class);
        intent.putExtra("customer_id", customerId);
        intent.putExtra("customer_name", name);
        intent.putExtra("customer_email", email);
        intent.putExtra("customer_phone", phone);
        intent.putExtra("customer_password", password);
        startActivityForResult(intent, 2);
    }

    /**
     * Handles the profile navigation button click.
     * Navigates to the CustomerProfileActivity.
     */
    private void handleProfileClick() {
        startActivity(new Intent(CustomerProfileActivity.this, CustomerProfileActivity.class));
    }

    /**
     * Handles the home navigation button click.
     * Navigates to the MainMenuActivity.
     */
    private void handleHomeClick() {
        startActivity(new Intent(CustomerProfileActivity.this, MainMenuActivity.class));
    }

    /**
     * Handles the requests navigation button click.
     * Navigates to the CustomerRequestsActivity.
     */
    private void handleRequestsClick() {
        startActivity(new Intent(CustomerProfileActivity.this, CustomerRequestsActivity.class));
    }

    /**
     * Shows a confirmation dialog for deleting the customer's profile.
     *
     * @param title   The title of the dialog.
     * @param message The message to display in the dialog.
     */
    public void startDelete(String title, String message) {
        new AlertDialog.Builder(CustomerProfileActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes_delete, (dialog, which) -> presenter.onDoDeleteAndFinish())
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show();
    }

    /**
     * Performs the delete operation and navigates back to the main login screen.
     *
     * @param message The message to display in a Toast upon successful deletion.
     */
    public void doDeleteAndFinish(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CustomerProfileActivity.this, MainLoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Handles the result from the edit activity or other activities.
     *
     * @param requestCode The request code of the activity result.
     * @param resultCode  The result code of the activity.
     * @param data        The intent data returned from the activity.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            recreate();
            presenter.onShowToast(data.getStringExtra("message_to_toast"));
        } else if (requestCode == 100) {
            recreate();
        }
    }

    /**
     * Displays a Toast message to the user.
     *
     * @param value The message to display.
     */
    @Override
    public void showToast(String value) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
