package com.example.reservation.view.Customer.SignUp;

import static androidx.compose.ui.semantics.SemanticsPropertiesKt.setText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import com.example.reservation.R;
import com.example.reservation.view.Customer.MainMenu.MainMenuActivity;
import com.example.reservation.view.Customer.LogIn.CustomerLogInActivity;

/**
 * CustomerSignUpActivity handles the sign-up process for new customers.
 * It provides the UI for users to input their details (name, username, email, password, phone number)
 * and performs the sign-up operation via a Presenter (using MVP pattern).
 */

public class CustomerSignUpActivity extends AppCompatActivity implements CustomerSignUpView {


    private CustomerSignUpViewModel viewModel;
    private Button btnFinishC;
    private Button btnLogInC;
    private ImageButton btnBackCS;
  //  private CheckBox checkBox2;
    private EditText EdtName;
    private EditText edtUsername;
    private EditText EdtEmail;
    private EditText edtPassword;
    private EditText edtTel;

    private TextView txtSignUp;

    /**
     * onCreate initializes the activity and sets up the ViewModel, presenter, and UI elements.
     * It binds UI components, handles button clicks, and ensures interaction with the ViewModel.
     */
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

        // Initialize ViewModel and Presenter
        viewModel = new ViewModelProvider(this).get(CustomerSignUpViewModel.class);
        CustomerSignUpPresenter presenter = viewModel.getPresenter();


        presenter.setView(this);

        // Ensure the View is properly assigned
        if (presenter.getView() == null) {
            throw new IllegalStateException("Presenter view is not set.");
        }

        EdtName = findViewById(R.id.EdtNameC);
        edtUsername = findViewById(R.id.edtUsernameC);
        EdtEmail = findViewById(R.id.EdtEmailC);
        edtPassword = findViewById(R.id.edtPasswordC);
        edtTel  = findViewById(R.id.edtTelC);
        btnFinishC = findViewById(R.id.btnFinishC);
        btnLogInC = findViewById(R.id.btnLogInC);
        btnBackCS = findViewById(R.id.btnBackCS);
//
// Set up button listeners
        btnFinishC.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String name = EdtName.getText().toString();
            String email = EdtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String number = edtTel.getText().toString();


            presenter.SignUp(name, username, email, password, number);
        });

        btnBackCS.setOnClickListener(v -> finish());

        btnLogInC.setOnClickListener(v -> {
            Intent intent = new Intent(CustomerSignUpActivity.this, CustomerLogInActivity.class);
            startActivity(intent);
        });


    }

    /**
     * Method to set the customer's name in the corresponding EditText.
     *
     * @param name Customer's name to set
     */
    @Override
    public void setName(String name) {
        ((EditText)findViewById(R.id.EdtNameC)).setText(name);
    }

    /**
     * Method to set the customer's username in the corresponding EditText.
     *
     * @param userName Customer's username to set
     */
    @Override
    public void setUserName(String userName) {
        ((EditText)findViewById(R.id.edtUsernameC)).setText(userName);
    }

    /**
     * Method to set the customer's email in the corresponding EditText.
     *
     * @param email Customer's email to set
     */
    @Override
    public void setEmail(String email) {
        ((EditText)findViewById(R.id.EdtEmailC)).setText(email);
    }

    /**
     * Method to set the customer's password in the corresponding EditText.
     *
     * @param password Customer's password to set
     */
    @Override
    public void setPassword(String password) {
        ((EditText)findViewById(R.id.edtPasswordC)).setText(password);
    }

    /**
     * Method to set the customer's phone number in the corresponding EditText.
     *
     * @param tel Customer's phone number to set
     */
    @Override
    public void setTel(String tel) {
        ((EditText)findViewById(R.id.edtTelC)).setText(tel);
    }

    /**
     * Show an error message as a Toast when there is an issue with the sign-up process.
     *
     * @param message Error message to display
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is invoked after a successful sign-up. It shows a success message
     * and navigates the user to the main menu of the application.
     *
     * @param message Success message to display
     */
    @Override
    public void successfullySignUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CustomerSignUpActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

}
