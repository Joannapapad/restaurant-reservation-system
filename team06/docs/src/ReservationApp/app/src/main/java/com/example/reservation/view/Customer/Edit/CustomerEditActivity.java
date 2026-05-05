package com.example.reservation.view.Customer.Edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reservation.MemoryDao.CurrentUserDAOMemory;
import com.example.reservation.R;

/**
 * CustomerEditActivity is responsible for editing customer information.
 * It displays the customer's details and allows for modifications.
 * Implements CustomerEditView to interact with the presenter.
 */
public class CustomerEditActivity extends AppCompatActivity implements CustomerEditView{

    /**
     * Initializes the activity, sets the layout, and prepares the customer data for editing.
     *
     * @param savedInstanceState The state of the activity if it was previously saved.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
        final  CustomerEditPresenter presenter = new CustomerEditPresenter(this, CurrentUserDAOMemory.getInstance());

        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("customer_name");
            String username = intent.getStringExtra("customer_username");
            String email = intent.getStringExtra("customer_email");
            String tel = intent.getStringExtra("customer_tel");
            String password = intent.getStringExtra("customer_password");

            // Set the retrieved values to the UI
            ((EditText) findViewById(R.id.edtEditNameC)).setText(name);
            ((EditText) findViewById(R.id.edtEditUsernameC)).setText(username);
            ((EditText) findViewById(R.id.edtEditEmailC)).setText(email);
            ((EditText) findViewById(R.id.edtEditTelC)).setText(tel);
            ((EditText) findViewById(R.id.edtEditPasswordC)).setText(password);
        }

        findViewById(R.id.btnDoneC).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.onSaveBorrower();
            }
        });
    }

    /**
     * Returns the name entered by the user in the name field.
     *
     * @return The name of the customer.
     */
    @Override
    public String getName() {
        return ((EditText)findViewById(R.id.edtEditNameC)).getText().toString().trim();
    }

    /**
     * Returns the username entered by the user in the username field.
     *
     * @return The username of the customer.
     */
    @Override
    public String getUsername() {
        return ((EditText)findViewById(R.id.edtEditUsernameC)).getText().toString().trim();
    }

    /**
     * Returns the telephone number entered by the user in the phone number field.
     *
     * @return The phone number of the customer.
     */
    @Override
    public String getTel() {
        return ((EditText)findViewById(R.id.edtEditTelC)).getText().toString().trim();
    }

    /**
     * Returns the email entered by the user in the email field.
     *
     * @return The email of the customer.
     */
    @Override
    public String getEmail() {
        return ((EditText)findViewById(R.id.edtEditEmailC)).getText().toString().trim();
    }

    /**
     * Returns the password entered by the user in the password field.
     *
     * @return The password of the customer.
     */
    @Override
    public String getPassword() {
        return ((EditText)findViewById(R.id.edtEditPasswordC)).getText().toString().trim();
    }

    /**
     * Sets the username value to the corresponding EditText field in the UI.
     *
     * @param value The username to be set.
     */
    @Override
    public void setUsername(String value) {
        ((EditText)findViewById(R.id.edtEditUsernameC)).setText(value);

    }

    /**
     * Sets the name value to the corresponding EditText field in the UI.
     *
     * @param value The name to be set.
     */
    @Override
    public void setName(String value) {
        ((EditText)findViewById(R.id.edtEditNameC)).setText(value);

    }

    /**
     * Sets the phone number value to the corresponding EditText field in the UI.
     *
     * @param value The phone number to be set.
     */
    @Override
    public void setTel(String value) {
        ((EditText)findViewById(R.id.edtEditTelC)).setText(value);

    }

    /**
     * Sets the email value to the corresponding EditText field in the UI.
     *
     * @param value The email to be set.
     */
    @Override
    public void setEmail(String value) {
        ((EditText)findViewById(R.id.edtEditEmailC)).setText(value);

    }

    /**
     * Sets the password value to the corresponding EditText field in the UI.
     *
     * @param value The password to be set.
     */
    @Override
    public void setPassword(String value) {
        ((EditText)findViewById(R.id.edtEditPasswordC)).setText(value);

    }

    /**
     * Finishes the activity successfully and returns a message to the calling activity.
     *
     * @param message The success message to be passed to the calling activity.
     */
    @Override
    public void successfullyFinishActivity(String message) {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();
    }

    /**
     * Displays an error message in a toast notification.
     *
     * @param message The error message to be displayed.
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}