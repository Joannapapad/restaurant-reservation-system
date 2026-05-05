package com.example.reservation.view.StoreOwner.SignUp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.reservation.view.StoreOwner.MainMenu.MainMenuActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.ViewModelProvider;
import com.example.reservation.R;
import com.example.reservation.view.StoreOwner.LogIn.StoreOwnerLogInActivity;

/**
 * This activity handles the Store Owner sign-up process.
 * It interacts with the StoreOwnerSignUpPresenter and provides user input forms for creating a new store owner account.
 */
public class StoreOwnerSignUpActivity extends AppCompatActivity implements StoreOwnerSignUpView {

    private StoreOwnerSignUpViewModel viewModel;
    private Button btnFinish;
    private Button btnLogIn;
    private AppCompatImageButton btnBack;

    //private CheckBox checkBox;
    private EditText EdtName;
    private EditText EdtEmail;
    private EditText EdtAfm;
    private EditText EdtPassword;
    private EditText EdtTel;

    private TextView txtSignUp;


    /**
     * onCreate method initializes the activity and its UI components.
     * It sets up the ViewModel, Presenter, and connects UI components to the appropriate actions.
     */
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_sign_up);

        // Initialize ViewModel and Presenter
        viewModel = new ViewModelProvider(this).get(StoreOwnerSignUpViewModel.class);
        StoreOwnerSignUpPresenter presenter = viewModel.getPresenter();

        // Make sure the View is set
        presenter.setView(this);

        // Ensure the View is properly assigned
        if (presenter.getView() == null) {
            throw new IllegalStateException("Presenter view is not set.");
        }

        // Initialize UI components
        EdtName = findViewById(R.id.EdtNameC);
        EdtEmail = findViewById(R.id.EdtEmailC);
        EdtPassword = findViewById(R.id.EdtPassword);
        EdtTel = findViewById(R.id.EdtTel);
        EdtAfm = findViewById(R.id.EdtAfm);
        btnFinish = findViewById(R.id.btnFinish);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnBack = findViewById(R.id.btnBack);

        // Set up button listeners
        btnFinish.setOnClickListener(v -> {
            String username = EdtName.getText().toString();
            String email = EdtEmail.getText().toString();
            String password = EdtPassword.getText().toString();
            String tel = EdtTel.getText().toString();
            String afm = EdtAfm.getText().toString();

            presenter.SignUp(username, email, password, tel, afm);
        });

        btnBack.setOnClickListener(v -> finish());

        btnLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(StoreOwnerSignUpActivity.this, StoreOwnerLogInActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Sets the username in the corresponding EditText field.
     *
     * @param name The username to be set.
     */
    @Override
    public void setUserName(String name) {
        ((EditText)findViewById(R.id.EdtNameC)).setText(name);
    }

    /**
     * Sets the email in the corresponding EditText field.
     *
     * @param email The email to be set.
     */
    @Override
    public void setEmail(String email) {
        ((EditText)findViewById(R.id.EdtEmailC)).setText(email);
    }

    /**
     * Sets the password in the corresponding EditText field.
     *
     * @param password The password to be set.
     */
    @Override
    public void setPassword(String password) {
        ((EditText)findViewById(R.id.EdtPassword)).setText(password);
    }

    /**
     * Sets the telephone number in the corresponding EditText field.
     *
     * @param tel The telephone number to be set.
     */
    @Override
    public void setTel(String tel) {
        ((EditText)findViewById(R.id.EdtTel)).setText(tel);
    }

    /**
     * Sets the AFM in the corresponding EditText field.
     *
     * @param afm The AFM to be set.
     */
    @Override
    public void setAFM(String afm) {
        ((EditText)findViewById(R.id.EdtAfm)).setText(afm);
    }

    /**
     * Displays a success message when the sign-up process completes successfully.
     *
     * @param message A message indicating the success of the sign-up process.
     */
    @Override
    public void successfullySignUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(StoreOwnerSignUpActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Displays an error message when an error occurs during sign-up.
     *
     * @param message An error message to be displayed.
     */
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}

